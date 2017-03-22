package com.taobao.muming.engineering.designpattern.behaviorpattern.mediatorpattern;

/**
 * Created by zhangzhiqi on 16/12/25.
 * 中介者实现类
 */
public class Mediator extends AbstractMediator {
    public Mediator(AbstractColleague a, AbstractColleague b){
        super(a, b);
    }

    public void aAffectb() {
        int number = colleaguea.getNumber();
        colleagueb.setNumber(number*100);
    }

    public void bAffecta() {
        int number = colleagueb.getNumber();
        colleaguea.setNumber(number/100);
    }


}
