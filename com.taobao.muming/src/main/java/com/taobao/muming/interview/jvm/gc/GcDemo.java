package com.taobao.muming.interview.jvm.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2017/1/14.
 */
public class GcDemo {
    /**
     * 4M
     */
    private final static int BYTE_SIZE = 4 * 1024 * 1024;

    public static void main(String[] args) {
        overflowdemo();
    }

    /**
     * 简单溢出
     * java -Xmn4m -Xms20m -Xmx20m -XX:+PrintGCDetails GcDemo
     */
    private static void overflowdemo() {
        List<Object> list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(new byte[BYTE_SIZE]);
            System.out.println(i);
        }
    }

    /**
     1 ygc--->1 fgc--->2 ygc--->1 fgc--->3 ygc--->1 fgc
     */


}
