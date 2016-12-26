package com.taobao.muming.designpattern.behaviorpattern.iteratorpattern;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
public interface Containable {
    public void add(Object obj);
    public void remove(Object obj);
    public Iterator iterator();
}
