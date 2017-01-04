package com.taobao.muming.annotation;

import java.lang.annotation.*;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2017/1/4.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {
    /**
     * 颜色枚举
     */
    public enum Color {
        BULE, RED, GREEN
    }

    ;

    /**
     * 颜色属性
     *
     * @return
     */
    Color fruitColor() default Color.GREEN;
}
