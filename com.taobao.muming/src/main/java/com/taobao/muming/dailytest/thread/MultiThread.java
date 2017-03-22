package com.taobao.muming.dailytest.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * description:
 * author: gubing.gb
 * date: 2017/3/15.
 */
public class MultiThread implements Runnable {
    //代码1
//    private volatile static int count = 0;
//
//    public static void increment() {
//        count++;
//    }

    //代码2
//    private static int count = 0;
//
//    synchronized public static void increment() {
//        count++;
//    }

    //代码3
    private static AtomicInteger count = new AtomicInteger(0);

    public static void increment() {
        count.getAndIncrement();
    }

    public void run() {
        int i = 0;
        while (i < 2000) {
            increment();
            System.out.println(Thread.currentThread().getName() + "    " + count);
            i++;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new MultiThread(), "线程1").start();
        new Thread(new MultiThread(), "线程2").start();
    }
}

