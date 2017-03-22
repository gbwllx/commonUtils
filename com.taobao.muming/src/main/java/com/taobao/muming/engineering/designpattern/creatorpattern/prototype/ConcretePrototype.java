package com.taobao.muming.engineering.designpattern.creatorpattern.prototype;

import com.taobao.muming.engineering.designpattern.creatorpattern.prototype.domain.Product;

/**
 * Created by zhangzhiqi on 16/12/25.
 */
public class ConcretePrototype extends AbstractPrototype {

    public void show() {
        System.out.println("原型模式实现类");
        for (Product s : this.getList()) {
            System.out.println(s);
        }
        System.out.println(product);
    }
}
