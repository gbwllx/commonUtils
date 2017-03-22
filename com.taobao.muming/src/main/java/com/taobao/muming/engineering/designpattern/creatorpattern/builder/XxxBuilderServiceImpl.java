package com.taobao.muming.engineering.designpattern.creatorpattern.builder;

/**
 * Created by zhangzhiqi on 16/12/25.
 */
public class XxxBuilderServiceImpl implements XxxBuilderService{
    private XxxProductServiceImpl productService = new XxxProductServiceImpl();

    public XxxProductServiceImpl getProduct(){
        return this.productService;
    }

    public void setPart(String name, String type, String color) {
        productService.setColor(color);
        productService.setName(name);
        productService.setType(type);
    }
}
