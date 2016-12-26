package com.taobao.muming.designpattern.behaviorpattern.observerpattern;

/**
 * Created by zhangzhiqi on 16/12/25.
 */
public class XxxSubject extends AbstractXxxSubject {
    public void doSomething() {
        System.out.println("被观察者事件反生");
        this.notifyObserver();
    }
}
