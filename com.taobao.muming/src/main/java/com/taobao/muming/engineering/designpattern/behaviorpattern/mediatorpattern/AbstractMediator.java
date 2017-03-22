package com.taobao.muming.engineering.designpattern.behaviorpattern.mediatorpattern;

/**
 * Created by zhangzhiqi on 16/12/25.
 * 中介者抽象类
 */
public abstract class AbstractMediator {
    protected AbstractColleague colleaguea;
    protected AbstractColleague colleagueb;

    public AbstractMediator(AbstractColleague colleaguea, AbstractColleague colleagueb){
        this.colleaguea = colleaguea;
        this.colleagueb = colleagueb;
    }

    public abstract void aAffectb();

    public abstract void bAffecta();
}
