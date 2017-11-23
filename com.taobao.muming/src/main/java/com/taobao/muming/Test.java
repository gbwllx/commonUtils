package com.taobao.muming;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * description:
 * author: gubing.gb
 * date: 2017/3/31.
 */
public class Test {
    public static void main(String[] args) {
        DecimalFormat format = new DecimalFormat("0.0");
        format.setRoundingMode(RoundingMode.HALF_UP);
        System.out.println(format.format(2432.4500d));
        // jdk8 ===> 2432.4  jdk7 ===> 2432.5
        // 2432.4500d这个D不能保证是.4500000001还是.449999999999
    }
}
