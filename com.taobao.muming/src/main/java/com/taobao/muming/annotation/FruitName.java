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
public @interface FruitName {
    String value() default "";
}
