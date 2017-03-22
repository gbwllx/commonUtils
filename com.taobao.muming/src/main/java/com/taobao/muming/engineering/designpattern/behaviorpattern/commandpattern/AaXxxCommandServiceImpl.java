package com.taobao.muming.engineering.designpattern.behaviorpattern.commandpattern;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
public class AaXxxCommandServiceImpl implements XxxCommandService{
    private Receiver receiver;

    public AaXxxCommandServiceImpl(Receiver receiver){
        this.receiver = receiver;
    }

    public void execute() {
        this.receiver.doAathing();
    }
}
