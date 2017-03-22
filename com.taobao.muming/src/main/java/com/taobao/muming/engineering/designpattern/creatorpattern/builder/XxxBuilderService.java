package com.taobao.muming.engineering.designpattern.creatorpattern.builder;

/**
 * Created by zhangzhiqi on 16/12/25.
 */
public interface XxxBuilderService {
    void setPart(String name, String type, String color);
    XxxProductServiceImpl getProduct();
}
