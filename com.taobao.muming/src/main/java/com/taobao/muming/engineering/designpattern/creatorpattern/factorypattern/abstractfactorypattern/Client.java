package com.taobao.muming.engineering.designpattern.creatorpattern.factorypattern.abstractfactorypattern;


/**
 * Created by zhangzhiqi on 16/12/25.
 */
public class Client {
    public static void main(String[] args) {
        XxxFactory factory = new XxxFactoryImpl();
        AaXxxProductService xxxProductService = (AaXxxProductService) factory.createAaXxxProduct();
        xxxProductService.getAaXxxProduct();

        BbXxxProductService yyyProductService = (BbXxxProductService) factory.createBbXxxProduct();
        yyyProductService.getBbXxxProduct();
    }
}
