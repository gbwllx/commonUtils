package com.taobao.muming.interview.jvm.error;

/**
 * @description:
 *  方法：递归调用自己
 *  结果：Exception in thread "main" java.lang.StackOverflowError
 * @author: gubing.gb
 * @date: 2016/12/29.
 */
public class StackOverFlowDemo {
    public static void main(String[] args) {
        new StackOverFlowDemo().f();
    }

    public void f(){
        f();
    }
}
