package com.taobao.muming.dailytest.thread;

import org.apache.commons.lang3.ClassUtils;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2017/1/4.
 */
public class ThreadLocalDemo {
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
        public Integer initialValue(){
            return 0;
        }
    };

    public int getNextNum(){
        seqNum.set(seqNum.get()+1);
        return seqNum.get();
    }

    public static void main(String[] args) {
        final ThreadLocalDemo sn = new ThreadLocalDemo();

        new Thread(new Runnable(){
            public void run()
            {
                for (int i = 0; i < 3; i++) {
                    System.out.println("thread["+Thread.currentThread().getName()+
                            "] sn["+sn.getNextNum()+"]");
                }
            }
        }).start();

        new Thread(new Runnable(){
            public void run()
            {
                for (int i = 0; i < 3; i++) {
                    System.out.println("thread["+Thread.currentThread().getName()+
                            "] sn["+sn.getNextNum()+"]");
                }
            }
        }).start();

        new Runnable(){
            public void run()
            {
                for (int i = 0; i < 3; i++) {
                    System.out.println("thread["+Thread.currentThread().getName()+
                            "] sn["+sn.getNextNum()+"]");
                }
            }
        }.run();

        System.out.println(ClassUtils.getAbbreviatedName(ThreadLocalDemo.class, 4));
        System.out.println(ClassUtils.getSimpleName(ThreadLocalDemo.class, "haha"));
        //System.out.print(ClassUtils.getShortCanonicalName(ThreadLocalDemo.class, ""));
    }
}

