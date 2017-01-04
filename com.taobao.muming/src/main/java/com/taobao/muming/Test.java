package com.taobao.muming;

import org.apache.commons.lang3.ClassUtils;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2017/1/4.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(ClassUtils.getAbbreviatedName(Test.class, 4));
        System.out.println(ClassUtils.getSimpleName(Test.class, "haha"));
        //System.out.print(ClassUtils.getShortCanonicalName(Test.class, ""));
    }
}
