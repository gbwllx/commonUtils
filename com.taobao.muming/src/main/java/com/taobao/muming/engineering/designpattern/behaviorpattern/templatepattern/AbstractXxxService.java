package com.taobao.muming.engineering.designpattern.behaviorpattern.templatepattern;

/**
 * Created by zhangzhiqi on 16/12/25.
 */
public abstract class AbstractXxxService {
    protected abstract void method1();

    protected abstract void method2();

    protected void method3(){
        System.out.println("method3");
    }

    public void execute(){
        method1();
        method2();
        method3();
    }
}
