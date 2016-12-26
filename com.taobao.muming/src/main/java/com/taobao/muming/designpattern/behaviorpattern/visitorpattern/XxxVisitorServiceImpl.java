package com.taobao.muming.designpattern.behaviorpattern.visitorpattern;

/**
 * Created by zhangzhiqi on 16/12/25.
 */
public class XxxVisitorServiceImpl implements XxxVisitorService{
    public void visit(AaXxxElement ela) {
        ela.doSomething();
    }

    public void visit(BbXxxElement elb) {
        elb.doSomething();
    }
}
