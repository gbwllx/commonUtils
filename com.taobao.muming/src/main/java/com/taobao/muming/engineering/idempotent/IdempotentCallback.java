package com.taobao.muming.engineering.idempotent;

/**
 * description: 幂等回调接口
 * author: gubing.gb
 * date: 2017/3/7.
 */
public interface IdempotentCallback<T> {
    T doBiz() throws Exception;
}

