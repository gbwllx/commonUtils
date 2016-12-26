package com.taobao.muming.designpattern.behaviorpattern.interpreterpattern;

import java.util.Objects;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
public interface Expression {
    Object interpreter(Context context);
}
