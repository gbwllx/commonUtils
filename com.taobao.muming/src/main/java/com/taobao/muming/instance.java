package com.taobao.muming;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * author: gubing.gb
 * date: 2017/2/10.
 */
public class instance {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        List list = new ArrayList();
        list.add("fs");
        list.add(1);
        //list.get(0).;
        for (Object o : list) {
            if (o instanceof String) {
                System.out.println(o);
            } else {
                System.out.println(o.toString());
            }
        }

        List<Integer> listi = new ArrayList<Integer>();
        listi.add(1);
        listi.add(2);
        listi.add(3);
        //通过反射调用list的add方法,跳过编译期检查
        listi.getClass().getMethod("add", Object.class).invoke(listi, "abc");
        for(int i = 0;i<listi.size();i++){
            System.out.println(listi.get(i));
        }
    }
}
