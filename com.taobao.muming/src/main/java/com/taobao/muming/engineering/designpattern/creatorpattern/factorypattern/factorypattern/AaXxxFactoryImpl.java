package com.taobao.muming.engineering.designpattern.creatorpattern.factorypattern.factorypattern;

/**
 * Created by zhangzhiqi on 16/12/25.
 */
public class AaXxxFactoryImpl implements XxxFactory {
    public XxxProductService createProduct() {
        return new AaXxxProductServiceImpl();
    }
}
