package com.taobao.muming.engineering.designpattern.creatorpattern.factorypattern.abstractfactorypattern;

/**
 * Created by zhangzhiqi on 16/12/25.
 */
public class XxxFactoryImpl implements XxxFactory {
    public AaXxxProductService createAaXxxProduct() {
        return new AaXxxProductServiceImpl();
    }

    public BbXxxProductService createBbXxxProduct() {
        return new BbXxxProductServiceImpl();
    }
}
