package com.taobao.muming.engineering.designpattern.creatorpattern.factorypattern.factorypattern;

/**
 * Created by zhangzhiqi on 16/12/25.
 */
public class BbXxxFactoryImpl implements XxxFactory {
    public XxxProductService createProduct() {
        return new BbXxxProductServiceImpl();
    }
}
