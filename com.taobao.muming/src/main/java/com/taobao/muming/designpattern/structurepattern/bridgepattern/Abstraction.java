package com.taobao.muming.designpattern.structurepattern.bridgepattern;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
public abstract class Abstraction {

    protected Implementor impl;

    public Abstraction(Implementor impl){
        this.impl = impl;
    }
    //示例方法
    public void operation(){

        impl.operationImpl();
    }
}
