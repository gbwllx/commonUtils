package com.taobao.muming.designpattern.creatorpattern.prototype;

import com.taobao.muming.designpattern.creatorpattern.prototype.domain.Product;

import java.util.ArrayList;

/**
 * Created by zhangzhiqi on 16/12/25.
 */
public abstract class AbstractPrototype implements Cloneable {
    //private List list = new ArrayList<String>();
    //ArrayList实现支持深复制
    private ArrayList<Product> list = new ArrayList<Product>();
    Product product = new Product();

    public AbstractPrototype clone() {
        AbstractPrototype prototype = null;
        try {
            prototype = (AbstractPrototype) super.clone();
            //转成ArrayList之后才支持深复制,测试现在好像直接支持了,之后研究深复制浅复制时再继续测试
            //prototype.list = (ArrayList) ((ArrayList) this.list).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return prototype;
    }

    public void setList(ArrayList<Product> list) {
        this.list = list;
    }

    public ArrayList<Product> getList() {
        return list;
    }
}
