package com.taobao.muming.engineering.idempotent;

/**
 * description:
 * author: gubing.gb
 * date: 2017/3/7.
 */
public interface IdempotentService {

    public <T> IdempotentResult<T> execute(IdempotentKey key, IdempotentCallback<T> callback);

}
