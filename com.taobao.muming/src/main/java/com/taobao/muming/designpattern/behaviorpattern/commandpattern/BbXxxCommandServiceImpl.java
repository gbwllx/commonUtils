package com.taobao.muming.designpattern.behaviorpattern.commandpattern;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
public class BbXxxCommandServiceImpl implements XxxCommandService{
    private Receiver receiver;

    public BbXxxCommandServiceImpl(Receiver receiver){
        this.receiver = receiver;
    }

    public void execute() {
        this.receiver.doBbthing();
    }
}
