package com.taobao.muming.designpattern.behaviorpattern.strategypattern;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
public class Client {
    public static void main(String[] args) {
        Context context = null;
        System.out.println("-----执行策略Aa-----");
        context = new Context(new AaStrategyServiceImpl());
        context.execute();

        System.out.println("-----执行策略Bb-----");
        context = new Context(new BbStrategyServiceImpl());
        context.execute();
    }
}
