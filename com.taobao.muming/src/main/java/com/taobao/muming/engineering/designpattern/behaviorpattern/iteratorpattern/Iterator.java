package com.taobao.muming.engineering.designpattern.behaviorpattern.iteratorpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 * 只有实现自己的容器时才会用到，但是现在这个需求几乎消失了，所以这个模式只供学习
 */
public class Iterator implements Iteratable{
    private List list = new ArrayList();
    private int cursor =0;
    public Iterator(List list){
        this.list = list;
    }
    public boolean hasNext() {
        if(cursor==list.size()){
            return false;
        }
        return true;
    }
    public Object next() {
        Object obj = null;
        if(this.hasNext()){
            obj = this.list.get(cursor++);
        }
        return obj;
    }
}
