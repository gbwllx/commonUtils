package com.taobao.muming.engineering.designpattern.structurepattern.proxypattern;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
public class RealObject extends AbstractObject {
    @Override
    public void operation() {
        //一些操作
        System.out.println("一些操作");
    }
}
