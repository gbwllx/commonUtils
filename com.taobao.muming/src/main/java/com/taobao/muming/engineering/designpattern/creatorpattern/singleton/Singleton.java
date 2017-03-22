package com.taobao.muming.engineering.designpattern.creatorpattern.singleton;

/**
 * Created by zhangzhiqi on 16/12/25.
 */


//4. 静态内部类，利用类加载特性，延迟加载，线程安全。【推荐使用】
public class Singleton {
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
    private Singleton (){}
    public static final Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static void main(String[] args) {
        System.out.println(getInstance());
    }
}

//3. 饿汉式，提前加载，不是按需加载，【可用】
class Singleton4 {
    private static Singleton4 instance = new Singleton4();

    private Singleton4() {
    }

    public static Singleton4 getInstance() {
        return instance;
    }
}

//3. 双重校验，按需加载，线程安全【可用】 JSR133
class Singleton3 {
    private volatile static Singleton3 singleton;
    private Singleton3 (){}
    public static Singleton3 getSingleton() {
        if (singleton == null) {
            synchronized (Singleton3.class) {
                if (singleton == null) {
                    singleton = new Singleton3();
                }
            }
        }
        return singleton;
    }
}


//2. 懒汉式，线程安全，效率低，【不推荐用】
class Singleton2 {
    private static Singleton2 instance;

    private Singleton2() {
    }

    public static synchronized Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}

//1. 懒汉式单例类.在第一次调用的时候实例化自己，线程不安全，【不可用】
class Singleton1 {
    private Singleton1() {
    }

    private static Singleton1 single = null;

    //静态工厂方法
    public static Singleton1 getInstance() {
        if (single == null) {
            single = new Singleton1();
        }
        return single;
    }

}

