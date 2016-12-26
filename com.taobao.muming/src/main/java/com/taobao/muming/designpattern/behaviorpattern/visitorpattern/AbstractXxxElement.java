package com.taobao.muming.designpattern.behaviorpattern.visitorpattern;

/**
 * Created by zhangzhiqi on 16/12/25.
 */
public abstract class AbstractXxxElement {
    public abstract void accept(XxxVisitorService visitor);
    public abstract void doSomething();
}
