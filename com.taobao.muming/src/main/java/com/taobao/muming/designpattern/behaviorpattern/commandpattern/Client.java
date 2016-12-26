package com.taobao.muming.designpattern.behaviorpattern.commandpattern;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
public class Client {
    public static void main(String[] args){
        Receiver receiver = new Receiver();
        XxxCommandService commandb = new BbXxxCommandServiceImpl(receiver);
        XxxCommandService commanda = new AaXxxCommandServiceImpl(receiver);
        //单封装client关于Receiver的逻辑
        //command.execute();

        //继续封装客户端通过调用者来执行命令
        Invoker invoker = new Invoker();
        invoker.setXxxCommandService(commanda);
        invoker.action();
        invoker.setXxxCommandService(commandb);
        invoker.action();
    }
}
