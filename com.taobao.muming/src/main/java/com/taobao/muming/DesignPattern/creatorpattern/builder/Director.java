package com.taobao.muming.designpattern.creatorpattern.builder;

/**
 * Created by zhangzhiqi on 16/12/25.
 */
public class Director {
    private XxxBuilderService builderService = new XxxBuilderServiceImpl();

    public XxxProductServiceImpl getAProduct(){
        builderService.setPart("AProduct", "circle", "red");
        return builderService.getProduct();
    }
    public XxxProductServiceImpl getBProduct() {
        builderService.setPart("BProduct", "rectangle", "blue");
        return builderService.getProduct();
    }
}
