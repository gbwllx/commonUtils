package com.taobao.muming.designpattern.behaviorpattern.statepattern;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
public class Client {
    public static void main(String[] args) {
        // 设置Context的初始状态为AaState
        Context context = new Context(new AaXxxState());

        // 不断地进行请求，同时更改状态
        context.Request();
        context.Request();
        context.Request();
        context.Request();
    }
}
