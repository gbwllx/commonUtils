package com.taobao.muming.engineering.idempotent;

/**
 * description: 幂等result
 * author: gubing.gb
 * date: 2017/2/14.
 */
public class IdempotentResult<T> {

    private T data;

    private boolean exist;

    private Throwable throwable;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
