package com.taobao.muming.dailytest.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2017/1/6.
 */
public class Startup {
    public static void main(String[] args) throws ClassNotFoundException {
        int i = 0;

        while (true) {
            ClassLoaderDemo cld = new ClassLoaderDemo();
            System.out.println(cld.getParent());
            try {
                Class<?> personClass = cld.findClass("com.taobao.muming.dailytest.classloader.Person");

                Object person = personClass.newInstance();
                Method sayHelloMethod = personClass.getMethod("sayHello");
                sayHelloMethod.invoke(person);
                System.out.println(++i);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
