package com.taobao.muming.engineering.designpattern.behaviorpattern.iteratorpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
public class Container implements Containable {
    private List list = new ArrayList();
    public void add(Object obj) {
        list.add(obj);
    }

    public Iterator iterator() {
        return new Iterator(list);
    }

    public void remove(Object obj) {
        list.remove(obj);
    }
}
