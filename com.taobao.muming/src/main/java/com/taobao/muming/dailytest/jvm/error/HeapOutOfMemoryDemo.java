package com.taobao.muming.dailytest.jvm.error;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 设置vm options
 *              -Xms128M -Xmx512M -XX:PermSize=64M -XX:MaxPermSize=128M
 *
 *              情况多见于对象过多，存在多余引用，使对象未及时释放
 *
 *              结果：运行10s，当Old代已经满，Eden代已经满，这时候如果还有新的对象产生，GC没有回收，会报堆溢出
 *              Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * @author: gubing.gb
 * @date: 2016/12/29.
 */
public class HeapOutOfMemoryDemo {
    public static void main(String[] args) throws Exception {
        List<Object> list = new ArrayList<Object>();

        //Thread.sleep(10000);
        while (true) {
            //不停创建新对象往一个list里添加（强引用），会报堆溢出
            //list.add(new Object());
            //只添加引用也不行
            list.add(123);
        }
    }
}


/**
 * 原因：Heap内存溢出，意味着Young和Old generation的内存不够。
 解决：调整java启动参数 -Xms -Xmx 来增加Heap内存。


 堆内存溢出时，首先判断当前最大内存是多少（参数：-Xmx 或 -XX:MaxHeapSize=），
 可以通过命令 jinfo -flag MaxHeapSize 查看运行中的JVM的配置，
 如果该值已经较大则应通过 mat 之类的工具查找问题，
 或 jmap -histo查找哪个或哪些类占用了比较多的内存。
 参数-verbose:gc(-XX:+PrintGC) -XX:+PrintGCDetails可以打印GC相关的一些数据。
 如果问题比较难排查也可以通过参数-XX:+HeapDumpOnOutOfMemoryError在OOM之前Dump内存数据再进行分析。
 此问题也可以通过histodiff打印多次内存histogram之前的差值，有助于查看哪些类过多被实例化，如果过多被实例化的类被定位到后可以通过btrace再跟踪。
 */
