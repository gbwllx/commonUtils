package com.taobao.muming.designpattern.structurepattern.bridgepattern;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
public class RefinedAbstraction extends Abstraction {

    public RefinedAbstraction(Implementor impl) {
        super(impl);
    }
    //其他的操作方法
    public void otherOperation(){

    }
}
