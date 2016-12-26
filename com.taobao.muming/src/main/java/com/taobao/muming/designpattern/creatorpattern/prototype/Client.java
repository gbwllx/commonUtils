package com.taobao.muming.designpattern.creatorpattern.prototype;

import com.taobao.muming.designpattern.creatorpattern.prototype.domain.Product;

import java.util.ArrayList;

/**
 * Created by zhangzhiqi on 16/12/25.
 */
public class Client {
    public static void main(String[] args) {
        ConcretePrototype concretePrototype = new ConcretePrototype();

        ArrayList<Product> list = new ArrayList<Product>();
        Product product = new Product();
        product.setName("aa");
        product.setSex("xx");
        list.add(product);
        concretePrototype.setList(list);
        for (int i = 0; i < 10; i++) {
            ConcretePrototype clonecp = (ConcretePrototype) concretePrototype.clone();
            clonecp.show();
        }
    }
}
