package com.taobao.muming.designpattern.behaviorpattern.mementopattern;

import java.util.Map;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
public class Memento {
    private Map<String, Object> stateMap;

    public Memento(Map<String, Object> map){
        this.stateMap = map;
    }

    public Map<String, Object> getStateMap() {
        return stateMap;
    }

    public void setStateMap(Map<String, Object> stateMap) {
        this.stateMap = stateMap;
    }
}
