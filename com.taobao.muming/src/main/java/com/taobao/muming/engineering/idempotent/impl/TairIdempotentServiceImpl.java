package com.taobao.muming.engineering.idempotent.impl;

import com.taobao.muming.engineering.idempotent.IdempotentCallback;
import com.taobao.muming.engineering.idempotent.IdempotentKey;
import com.taobao.muming.engineering.idempotent.IdempotentResult;
import com.taobao.muming.engineering.idempotent.IdempotentService;
import com.taobao.muming.engineering.lock.TairMutexLocker;
import com.taobao.muming.engineering.lock.tair.TairException;

import javax.annotation.Resource;

/**
 * description: 基于tair幂等实现类
 * author: gubing.gb
 * date: 2017/3/7.
 */
public class TairIdempotentServiceImpl implements IdempotentService {
    @Resource(name = "tairMutexLocker")
    TairMutexLocker tairMutexLocker;

    @Override
    public <T> IdempotentResult<T> execute(IdempotentKey key, IdempotentCallback<T> callback) {
        IdempotentResult<T> result = new IdempotentResult<>();

        /**
         * 不需要幂等
         */
        if (!key.isNeedIdempotent()) {
            doBizWithoutIdempotent(callback, result);
            return result;
        }

        TairMutexLocker.Lock lock = TairMutexLocker.Lock.of(key.getCustomerId() + "$" + key.getType().getType() + "$" + key.getKey(), 0L, 20L);

        try {
            if (!tairMutexLocker.tryLock(lock)) {
                result.setExist(true);
                return result;
            }
        } catch (TairException e) {
            // tair异常直接抛出去
            if (!key.isContinueAfterIdempotentExcepiton()) {
                //result.setThrowable(new PredicateException(ErrorDef.GET_MUTEX_LOCKER_ERROR, "tair error", e));
                return result;
            }
            //如果出现tair异常，让业务继续走下去。业务要保证在数据层做幂等
            //logger.error("tair error", e);
        }

        doBizInternal(callback, result, lock);
        return result;
    }

    private <T> void doBizInternal(IdempotentCallback<T> callable, IdempotentResult<T> result, TairMutexLocker.Lock lock) {
        try {
            T data = callable.doBiz();
            result.setData(data);
        } catch (Throwable e) {
            tairMutexLocker.releaseLock(lock);
            result.setThrowable(e);
            return;
        }
        lock.setLockTime((long) TairMutexLocker.NO_CARE_LOCK_TIME);
        tairMutexLocker.extendLockTime(lock);
    }

    private <T> void doBizWithoutIdempotent(IdempotentCallback<T> callable, IdempotentResult<T> result) {
        try {
            T data = callable.doBiz();
            result.setData(data);
        } catch (Throwable e) {
            result.setThrowable(e);
        }
    }
}
