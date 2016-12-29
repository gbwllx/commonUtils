package com.taobao.muming.interview.jvm;

/**
 * @description:
 * 在经常动态生成大量Class的应用中，需要特别注意类的回收状况。
 * 这类场景除了上面提到的程序使用了CGLib字节码增强和动态语言之外，
 * 常见的还有：大量JSP或动态产生JSP文件的应用（JSP第一次运行时需要编译为Java类）、
 * 基于OSGi的应用（即使是同一个类文件，被不同的加载器加载也会视为不同的类）等。
 *
 * 静态的报PermGenSpace，动态加载的报MethodAreaOutOfMemory Error? 好像是这样的
 * 方法区和持久代的关系：
 * 方法区物理上存在于堆里，而且是在堆的持久代里面；但在逻辑上，方法区和堆是独立的。
 * 一般说堆的持久代就是说方法区，因为一旦JVM把方法区（类信息，常量池，静态字段，方法）
 * 加载进内存以后，这些内存一般是不会被回收的了。
 * @author: gubing.gb
 * @date: 2016/12/29.
 */
public class MethodAreaOutOfMemoryDemo {
}
