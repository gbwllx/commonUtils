package com.taobao.muming.engineering.designpattern.structurepattern.proxypattern;

/**
 * @description: 装饰器模式关注于在一个对象上动态的添加方法，然而代理模式关注于控制对对象的访问
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
public class ProxyObject extends AbstractObject{
    RealObject realObject = new RealObject();
    @Override
    public void operation() {
        //调用目标对象之前可以做相关操作
        System.out.println("before");
        realObject.operation();
        //调用目标对象之后可以做相关操作
        System.out.println("after");
    }
}
