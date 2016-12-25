package com.taobao.muming.designpattern.creatorpattern.builder;

/**
 * Created by zhangzhiqi on 16/12/25.
 */
public class XxxProductServiceImpl implements XxxProductService{
    private String name;
    private String type;
    private String color;

    public void showProduct(){
        System.out.print(this.name+" ");
        System.out.print(this.type+" ");
        System.out.println(this.color);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
