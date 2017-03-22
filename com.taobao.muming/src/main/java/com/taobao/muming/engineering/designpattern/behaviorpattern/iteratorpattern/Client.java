package com.taobao.muming.engineering.designpattern.behaviorpattern.iteratorpattern;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
public class Client {
    public static void main(String[] args){
        Container container = new Container();
        container.add("小明");
        container.add("小红");
        container.add("小刚");
        Iterator it = container.iterator();
        while(it.hasNext()){
            String str = (String)it.next();
            System.out.println(str);
        }
    }
}
