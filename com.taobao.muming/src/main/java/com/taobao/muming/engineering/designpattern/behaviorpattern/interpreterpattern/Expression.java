package com.taobao.muming.engineering.designpattern.behaviorpattern.interpreterpattern;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
public interface Expression {
    Object interpreter(Context context);
}
