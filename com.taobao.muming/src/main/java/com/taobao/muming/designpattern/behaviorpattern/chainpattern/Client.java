package com.taobao.muming.designpattern.behaviorpattern.chainpattern;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
public class Client {
    public static void main(String[] args){
        Context context = new Context();
        Request request = new Request();
        Response response = context.execute(request);
    }
}
