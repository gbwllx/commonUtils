package com.taobao.muming.interview.jvm.error;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 *          一般不会出现
 *
 *          MaxTenuringThreshold这个参数用于控制对象能经历多少次Minor GC才晋升到旧生代，
 *          默认值是15，那是不是意味着对象要经历15次minor gc才晋升到旧生代呢（不是）
 *
 *          其中desired_survivor_size是指survivor space/2，从上面的代码可看出，
 *          在计算存活周期这个阈值时，hotspot会遍历所有age的table，并对其所占用的大小进行累积，
 *          当累积的大小超过了survivor space的一半时，则以这个age作为新的存活周期阈值，
 *          最后取age和MaxTenuringThreshold中更小的一个值。
 *          按照这样的规则，上面的运行效果就可验证了，第一次minor gc的时候存活周期的阈值为MaxTenuringThreshold，
 *          minor gc结束后计算出新的阈值为1，在第二次minor gc时object 1的age已经是1了，因此object1被晋升到了旧生代。
 *          这个规则对于Serial GC以及ParNew GC（但对于开启了UseAdaptiveSizePolicy的ParNew GC而言也无效，默认是不开启的）均有效，
 *          对于PS（Parallel Scavenge） GC而言，在默认的情况下第一次以InitialTenuringThreshold（默认为7）为准，之后在每次minor GC后均会动态计
 *
 *          方法：
 *          设置XX：MaxTenuringThreshold为一个很大的值，使对象无法及时的移动到年老代中，导致年轻代内存溢出
 *          java8已经不能设置为很大的值
 *
 *
 *
 * @author: gubing.gb
 * @date: 2016/12/29.
 */
public class YoungOutOfMemoryDemo {
    public static void main(String[] args) throws Exception {
        List<Object> list = new ArrayList<Object>();

        while (true) {
            list.add(123);
        }
    }
}
