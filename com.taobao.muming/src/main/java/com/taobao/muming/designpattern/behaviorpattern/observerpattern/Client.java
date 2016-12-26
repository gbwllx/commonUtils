package com.taobao.muming.designpattern.behaviorpattern.observerpattern;

/**
 * Created by zhangzhiqi on 16/12/25.
 */
public class Client {
    public static void main(String[] args){
        AbstractXxxSubject sub = new XxxSubject();
        sub.addObserver(new AaXxxObserverServiceImpl()); //添加观察者Aa
        sub.addObserver(new BbXxxObserverServiceImpl()); //添加观察者Bb
        sub.doSomething();
    }
}
