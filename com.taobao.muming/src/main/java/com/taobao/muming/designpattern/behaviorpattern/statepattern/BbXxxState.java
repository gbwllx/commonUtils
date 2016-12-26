package com.taobao.muming.designpattern.behaviorpattern.statepattern;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
public class BbXxxState implements State {
    public void execute(Context context) {
        System.out.println("当前状态是Bb");
        context.setState(new AaXxxState());
    }
}
