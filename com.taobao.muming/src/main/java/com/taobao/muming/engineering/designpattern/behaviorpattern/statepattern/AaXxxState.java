package com.taobao.muming.engineering.designpattern.behaviorpattern.statepattern;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 * 这个是状态自己保存的下一个状态，这个上下关系维护可以提取出去，形成状态机
 */
public class AaXxxState implements State {
    public void execute(Context context) {
        System.out.println("当前状态是Aa");
        context.setState(new BbXxxState());
    }
}
