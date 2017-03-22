package com.taobao.muming.engineering.designpattern.creatorpattern.factorypattern.simplefactory;

/**
 * Created by zhangzhiqi on 16/12/25.
 * 优点：简单工厂模式能够根据外界给定的信息，决定究竟应该创建哪个具体类的对象。明确区分了各自的职责和权力，有利于整个软件体系结构的优化。
 * 缺点：很明显工厂类集中了所有实例的创建逻辑，容易违反GRASPR的高内聚的责任分配原则
 */
public class XxxFactory {
    private XxxService s;

    public XxxService getInstance(int type) {
        switch (type) {
            case 1:
                s = new BbXxxServiceImpl();
            default:
                s = new AaXxxServiceImpl();
        }
        return s;
    }
}
