package com.taobao.muming.designpattern.structurepattern.flyweightpattern;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 * 已存在对象就不创建，即为享元模式
 */
public class FlyweightFactory {
    private Map<Character, Flyweight> files = new HashMap<Character, Flyweight>();

    public Flyweight factory(Character state) {
        //先从缓存中查找对象
        Flyweight fly = files.get(state);
        if (fly == null) {
            //如果对象不存在则创建一个新的Flyweight对象
            fly = new AaFlyweight(state);
            //把这个新的Flyweight对象添加到缓存中
            files.put(state, fly);
        }
        return fly;
    }
}
