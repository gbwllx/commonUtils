package com.taobao.muming.engineering.designpattern.behaviorpattern.strategypattern;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
public class Context {
    private StrategyService strategyService;

    public Context(StrategyService service){
        this.strategyService = service;
    }

    public void execute(){
        strategyService.execute();
    }
}
