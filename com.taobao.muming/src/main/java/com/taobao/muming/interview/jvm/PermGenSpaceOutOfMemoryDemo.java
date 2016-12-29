package com.taobao.muming.interview.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 原因：Permanent Generation空间不足，不能加载额外的类。
 * 解决：调整-XX:PermSize= -XX:MaxPermSize= 两个参数来增大PermGen内存。
 *
 * java8没有了Perm区，这个异常应该没有了，相应的metaspace异常后续可以观察
 * @author: gubing.gb
 * @date: 2016/12/29.
 */
public class PermGenSpaceOutOfMemoryDemo {
    /*PermGen space即永久代，是非堆内存的一个区域。主要存放的数据是类结构及调用了intern()的字符串。*/
    public static void main(String[] args) {
        //List<Class<?>> classes = new ArrayList<Class<?>>();
        // while (true) {
        //MyClassLoader cl = new MyClassLoader();
        //try {
        //classes.add(cl.loadClass("Dummy"));
        //} catch (ClassNotFoundException e) {
        //   e.printStackTrace();
        //}
        //}

/*类加载的日志可以通过btrace跟踪类的加载情况：*/
//import com.sun.btrace.annotations.*;
//import static com.sun.btrace.BTraceUtils.*;

//@BTrace
//public class ClassLoaderDefine {
//    @SuppressWarnings("rawtypes")
//    @OnMethod(clazz = "+java.lang.ClassLoader", method = "defineClass", location = @Location(Kind.RETURN))
//    public static void onClassLoaderDefine(@Return Class cl) {
//        println("=== java.lang.ClassLoader#defineClass ===");
//        println(Strings.strcat("Loaded class: ", Reflective.name(cl)));
//        jstack(10);
//    }
//}
//        除了btrace也可以打开日志加载的参数来查看加载了哪些类，
//      可以把参数-XX:+TraceClassLoading打开，或使用参数-verbose:class（-XX:+TraceClassLoading, -XX:+TraceClassUnloading），
//      在日志输出中即可看到哪些类被加载到Java虚拟机中。该参数也可以通过jflag的命令java -jar jflagall.jar -flag +ClassVerbose动态打开-verbose:class。


        //下面是一个使用了String.intern()的例子：
        List<String> list = new ArrayList<String>();
        int i = 0;
        while (true) {
            list.add(("Consume more memory!" + (i++)).intern());
            System.out.println(i);
        }
    }

//    你可以通过以下btrace脚本查找该类调用：
//    import com.sun.btrace.annotations.*;
//    import static com.sun.btrace.BTraceUtils.*;
//    @BTrace
//    public class StringInternTrace {
//
//        @OnMethod(clazz = "/.*/", method = "/.*/",
//                location = @Location(value = Kind.CALL, clazz = "java.lang.String", method = "intern"))
//        public static void m(@ProbeClassName String pcm, @ProbeMethodName String probeMethod,
//                             @TargetInstance Object instance) {
//            println(strcat(pcm, strcat("#", probeMethod)));
//            println(strcat(">>>> ", str(instance)));
//        }
//    }
}


