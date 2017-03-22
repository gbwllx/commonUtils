package com.taobao.muming.engineering.designpattern.behaviorpattern.templatepattern;

/**
 * Created by zhangzhiqi on 16/12/25.
 */
public class Client {
    public static void main(String[] args) {
          AbstractXxxService xxxService = new AaXxxServiceImpl();
          xxxService.execute();
    }
}
