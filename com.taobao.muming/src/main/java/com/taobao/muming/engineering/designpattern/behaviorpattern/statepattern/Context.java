package com.taobao.muming.engineering.designpattern.behaviorpattern.statepattern;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
public class Context {
    private State state;

    public Context(State state){
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    /// 对请求做处理，并设置下一个状态
    public void Request()
    {
        state.execute(this);
    }
}
