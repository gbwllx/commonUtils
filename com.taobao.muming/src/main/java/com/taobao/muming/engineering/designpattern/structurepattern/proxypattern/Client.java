package com.taobao.muming.engineering.designpattern.structurepattern.proxypattern;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
public class Client {

    public static void main(String[] args) {
        AbstractObject obj = new ProxyObject();
        obj.operation();
    }

}
