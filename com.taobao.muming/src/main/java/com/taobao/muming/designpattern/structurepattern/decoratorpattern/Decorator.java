package com.taobao.muming.designpattern.structurepattern.decoratorpattern;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
public class Decorator implements Component{
    private Component component;

    public Decorator(Component component){
        this.component = component;
    }

    public void sampleOperation() {
        // 委派给构件
        component.sampleOperation();
    }

}
