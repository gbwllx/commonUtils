package com.taobao.muming.interview.jvm.error;

/**
 * @description: 原因：Stack空间不足以创建额外的线程，要么是创建的线程过多，要么是Stack空间确实小了。
 * 解决：由于JVM没有提供参数设置总的stack空间大小，但可以设置单个线程栈的大小；
 * 而系统的用户空间一共是3G，除了Text/Data/BSS/MemoryMapping几个段之外，
 * Heap和Stack空间的总量有限，是此消彼长的。因此遇到这个错误，可以通过两个途径解决：
 * 1.通过-Xss启动参数减少单个线程栈大小，这样便能开更多线程（当然不能太小，太小会出现StackOverflowError）；
 * 2.通过-Xms -Xmx 两参数减少Heap大小，将内存让给Stack（前提是保证Heap空间够用）。
 * @author: gubing.gb
 * @date: 2016/12/29.
 */
public class UnableToCreateNewNativeThreadOutOfMemoryDemo {
    public static void main(String[] args) {
        /*在JVM中每启动一个线程都会分配一块本地内存，用于存放线程的调用栈，
        该空间仅在线程结束时释放。当没有足够本地内存创建线程时就会出现该错误。
        通过以下代码可以很容易再现该问题
        */
        while (true) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(60 * 60 * 1000);
                    } catch (InterruptedException e) {
                    }
                }
            }).start();
        }
    }
}
