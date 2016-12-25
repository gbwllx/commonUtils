package com.taobao.muming.designpattern.behaviorpattern.mediatorpattern;

/**
 * Created by zhangzhiqi on 16/12/25.
 * 抽象同事类
 */
public abstract class AbstractColleague {
    protected int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number){
        this.number = number;
    }
    //抽象方法，修改数字时同时修改关联对象
    //public abstract void setNumber(int number, AbstractColleague coll);
    //依赖中介者
    public abstract void setNumber(int number, AbstractMediator mediator);
}
