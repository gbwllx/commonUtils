package com.taobao.muming.designpattern.structurepattern.decoratorpattern;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
public class ConcreteDecoratorA extends Decorator {

    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    public void sampleOperation() {
        super.sampleOperation();
        // 写相关的业务代码
    }
}
