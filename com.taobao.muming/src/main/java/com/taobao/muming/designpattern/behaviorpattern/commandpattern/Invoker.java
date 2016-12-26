package com.taobao.muming.designpattern.behaviorpattern.commandpattern;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
public class Invoker {
    private XxxCommandService xxxCommandService;

    public void setXxxCommandService(XxxCommandService commandService) {
        this.xxxCommandService = commandService;
    }

    public void action() {
        this.xxxCommandService.execute();
    }
}
