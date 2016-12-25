package com.taobao.muming.designpattern.creatorpattern.builder;

/**
 * Created by zhangzhiqi on 16/12/25.
 */
public class Client {
    public static void main(String[] args) {
        Director director = new Director();

        XxxProductServiceImpl productA = director.getAProduct();
        productA.showProduct();
        XxxProductServiceImpl productB = director.getBProduct();
        productB.showProduct();
    }
}
