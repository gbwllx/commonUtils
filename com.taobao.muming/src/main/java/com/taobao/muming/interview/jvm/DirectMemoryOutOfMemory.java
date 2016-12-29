package com.taobao.muming.interview.jvm;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 *          堆外内存溢出 一般与nio有关
 * @author: gubing.gb
 * @date: 2016/12/29.
 */
public class DirectMemoryOutOfMemory {
    /*
     *          报的是堆溢出，这块JAVA8也有特别处理吗？
     */
    public static void main(String[] args) {
        List<ByteBuffer> buffers = new ArrayList<ByteBuffer>();
        while(true){
            ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
            buffers.add(buffer);
        }
    }
}
