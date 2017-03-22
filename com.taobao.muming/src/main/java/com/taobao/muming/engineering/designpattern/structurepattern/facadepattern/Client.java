package com.taobao.muming.engineering.designpattern.structurepattern.facadepattern;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
public class Client {

    public static void main(String[] args) {

        Facade facade = new Facade();
        facade.test();
    }

}
