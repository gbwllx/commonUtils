package com.taobao.muming.engineering.designpattern.structurepattern.adapterpattern;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
public interface Target {
    /**
     * 这是源类Adaptee也有的方法
     */
    public void sampleOperation1();
    /**
     * 这是源类Adapteee没有的方法
     */
    public void sampleOperation2();
}
