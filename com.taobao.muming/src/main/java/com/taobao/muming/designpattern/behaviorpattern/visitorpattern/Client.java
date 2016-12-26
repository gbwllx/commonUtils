package com.taobao.muming.designpattern.behaviorpattern.visitorpattern;

import java.util.List;

/**
 * Created by zhangzhiqi on 16/12/25.
 */
public class Client {
    public static void main(String[] args){
        List<AbstractXxxElement> list = ObjectStructure.getList();
        for(AbstractXxxElement e: list){
            e.accept(new XxxVisitorServiceImpl());
        }
    }
}
