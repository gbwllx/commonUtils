package com.taobao.muming.designpattern.behaviorpattern.mediatorpattern;

/**
 * Created by zhangzhiqi on 16/12/25.
 */
public class ColleagueB extends AbstractColleague{
    public void setNumber(int number, AbstractMediator mediator) {
        this.number = number;
        mediator.bAffecta();
    }
}
