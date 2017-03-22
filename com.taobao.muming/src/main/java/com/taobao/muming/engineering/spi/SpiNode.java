package com.taobao.muming.engineering.spi;

/**
 * description:
 * author: gubing.gb
 * date: 2017/3/7.
 */
public interface SpiNode<T, R> {

    /**
     * 业务识别
     *
     * @param t
     * @return
     */
    boolean recognize(T t);

    /**
     * 业务处理
     *
     * @param t
     * @return
     */
    R apply(T t) throws Exception;
}
