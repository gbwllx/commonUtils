package com.taobao.muming.engineering.designpattern.creatorpattern.factorypattern.factorypattern;

/**
 * Created by zhangzhiqi on 16/12/25.
 */
public class Client {
    public static void main(String[] args) {
        XxxFactory factory = new AaXxxFactoryImpl();
        XxxProductService productService = (XxxProductService) factory.createProduct();
        productService.getProduct();
    }
}
