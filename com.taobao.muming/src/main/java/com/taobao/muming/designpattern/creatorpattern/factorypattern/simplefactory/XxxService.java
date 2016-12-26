package com.taobao.muming.designpattern.creatorpattern.factorypattern.simplefactory;

/**
 * Created by zhangzhiqi on 16/12/25.
 */
//简单工厂模式获得子类对象，调用子类方法。
public interface XxxService<T> {
    T getResult();
}
