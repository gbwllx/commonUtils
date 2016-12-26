package com.taobao.muming.designpattern.structurepattern.flyweightpattern;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
public class Client {
    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight fly = factory.factory(new Character('a'));
        fly.operation("First Call");

        fly = factory.factory(new Character('b'));
        fly.operation("Second Call");

        fly = factory.factory(new Character('a'));
        fly.operation("Third Call");
    }
}
