package com.taobao.muming.engineering.lock;

import com.taobao.muming.engineering.lock.tair.MultiClusterTairManager;
import com.taobao.muming.engineering.lock.tair.ResultCode;
import com.taobao.muming.engineering.lock.tair.TairException;

/**
 * description: 依赖tair的分布式锁实现
 * author: gubing.gb
 * date: 2017/3/7.
 */
public class TairMutexLocker {
    private MultiClusterTairManager tairManager;
    private int tairNamespace;
    public static int LOCK_INIT_VERSION = 10000;
    public static int NO_CARE_LOCK_TIME = 0;

    public void setTairNamespace(int tairNamespace) {
        this.tairNamespace = tairNamespace;
    }

    public void setTairManager(MultiClusterTairManager tairManager) {
        this.tairManager = tairManager;
    }

    /**
     * 分布式锁
     * 使用方式：
     * Lock lock = Lock.of(xx,xx,xx);
     * try{
     * if(tryLock(lock)){
     * do something
     * }
     * }catch(TairException e){
     * handler tairException
     * }
     * finaly{
     * releaseLock(lock);
     * }
     *
     * @param lock 锁
     * @return 加锁成功或失败
     * @throws TairException tair 超时或异常，此处抛出异常，需要调用方根据业务情况相应处理
     */
    public boolean tryLock(Lock lock) throws TairException {
        long waitTime = 0;
        String key = generateLockKey(lock.getLockKey());

        while (true) {
            ResultCode result = tairManager.put(tairNamespace, key, "true", LOCK_INIT_VERSION, lock.getLockTime());

            if (result == null) {
                throw new TairException(null, "tair result is null, key:[" + key + "]");
            }

            if (!result.isSuccess() && result != ResultCode.VERERROR) {
                throw new TairException(null, "tair result is fail, key:[" + key + "]");
            }

            if (result.isSuccess()) {
                lock.isLocked = true;
                lock.setVersion(1);
                return true;
            }

            if (waitTime >= lock.getTimeout()) {
                break;
            }

            try {
                Thread.sleep(100);
                waitTime += 100;
            } catch (Exception e) {
                return false;
            }
        }

        return false;
    }

    /**
     * @param lock
     * @return
     */
    public boolean releaseLock(Lock lock) {
        if (!lock.isLocked()) {
            return true;
        }

        ResultCode resultCode = tairManager.delete(tairNamespace, generateLockKey(lock.getLockKey()));
        return resultCode.isSuccess();
    }

    public boolean extendLockTime(Lock lock) {
        if (!lock.isLocked()) {
            return true;
        }
        String key = generateLockKey(lock.getLockKey());
        ResultCode resultCode = tairManager.put(tairNamespace, key, "true", lock.getVersion(), lock.getLockTime());
        if (!resultCode.isSuccess()) {
            return false;
        }
        lock.setVersion(lock.getVersion() + 1);

        return true;
    }

    private String generateLockKey(String lockKey) {
        return "lock_" + lockKey;
    }

    public static class Lock {
        /**
         * 加锁的业务Key
         */
        private String lockKey;
        /**
         * 等待的超时时间（单位毫秒），如果<=0，则不阻塞，立即返回
         */
        private Long timeout;

        /**
         * 加锁的最大时间（单位秒），一般设置为最大业务执行时间
         */
        private Long lockTime;

        /**
         * 是否锁定
         */
        private Boolean isLocked;

        /**
         * 锁版本
         */
        private Integer version = 0;

        public static Lock of(String lockKey, Long timeout, Long lockTime) {
            Lock lock = new Lock();
            lock.lockKey = lockKey;
            lock.timeout = timeout;
            lock.lockTime = lockTime;
            lock.isLocked = false;
            return lock;
        }

        public String getLockKey() {
            return lockKey;
        }

        public void setLockKey(String lockKey) {
            this.lockKey = lockKey;
        }

        public Long getTimeout() {
            return timeout;
        }

        public void setTimeout(Long timeout) {
            this.timeout = timeout;
        }

        public Long getLockTime() {
            return lockTime;
        }

        public void setLockTime(Long lockTime) {
            this.lockTime = lockTime;
        }

        public Boolean isLocked() {
            return isLocked;
        }


        public Integer getVersion() {
            return version;
        }

        public void setVersion(Integer version) {
            this.version = version;
        }
    }
}
