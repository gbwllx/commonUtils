package com.taobao.muming.designpattern.behaviorpattern.mementopattern;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
class Caretaker {
    private Map<String, Memento> memMap = new HashMap<String, Memento>();

    public Memento getMemento(String index) {
        return memMap.get(index);
    }

    public void setMemento(String index, Memento memento) {
        this.memMap.put(index, memento);
    }
}
