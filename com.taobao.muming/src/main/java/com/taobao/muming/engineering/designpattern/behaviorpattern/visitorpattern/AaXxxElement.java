package com.taobao.muming.engineering.designpattern.behaviorpattern.visitorpattern;

/**
 * Created by zhangzhiqi on 16/12/25.
 */
public class AaXxxElement extends AbstractXxxElement {
    public void accept(XxxVisitorService visitor) {
        visitor.visit(this);
    }

    public void doSomething() {
        System.out.println("这是元素Aa");
    }
}
