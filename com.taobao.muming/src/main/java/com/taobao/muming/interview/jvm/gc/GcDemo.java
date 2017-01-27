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
     * 1M
     */
    private final static int BYTE_SIZE_1M = 1 * 1024 * 1024;
    /**
     * 4M
     */
    private final static int BYTE_SIZE_4M = 4 * 1024 * 1024;

    public static void main(String[] args) throws InterruptedException {
        //Thread.sleep(10000L);
        //overFlow4MDemo();
        //overFlow1MDemo();
        ygcFgcDemo();
    }

    /**
     * 简单溢出
     * java -Xmn4m -Xms20m -Xmx20m -XX:+PrintGCDetails GcDemo
     */
    private static void overFlow4MDemo() {
        List<Object> list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(new byte[BYTE_SIZE_4M]);
            System.out.println(i);
        }
    }

    /**
     * 简单溢出
     * java -Xmn4m -Xms20m -Xmx20m -XX:+PrintGCDetails GcDemo
     */
    private static void overFlow1MDemo() {
        List<Object> list = new ArrayList();
        for (int i = 0; i < 20000; i++) {
            System.out.println(i);
            list.add(new byte[BYTE_SIZE_1M]);
        }
    }

    /**
     * 1 ygc--->1 fgc--->2 ygc--->1 fgc--->3 ygc--->1 fgc
     * java -Xmn4m -Xms20m -Xmx20m -XX:+PrintGCDetails GcDemo
     */
    private static void ygcFgcDemo() {
        List<Object> list = new ArrayList();
        for (int i = 0; i < 20000; i++) {
            //ygc
            int j = 0;
            for (; j < i + 1; j++) {
                System.out.println(i);
                list.add(new byte[BYTE_SIZE_1M]);
                list.add(new byte[BYTE_SIZE_1M]);//触发YGC
            }
            //fgc
            System.out.println(i);
            int size = (14 - i * 2);
            list.add(new byte[BYTE_SIZE_1M * size]);
            list.add(new byte[BYTE_SIZE_1M]);
            list.clear();
            list.add(new byte[BYTE_SIZE_1M]);//触发FGC；占位置，有其他初始对象被回收
            list.clear();

        }
    }

}
