package com.taobao.muming.designpattern.behaviorpattern.observerpattern;

import java.util.Vector;

/**
 * Created by zhangzhiqi on 16/12/25.
 */
public abstract class AbstractXxxSubject {

    private Vector<XxxObserverService> obs = new Vector<XxxObserverService>();

    public void addObserver(XxxObserverService obs) {
        this.obs.add(obs);
    }

    public void delObserver(XxxObserverService obs) {
        this.obs.remove(obs);
    }

    protected void notifyObserver() {
        for (XxxObserverService o : obs) {
            o.update();
        }
    }

    public abstract void doSomething();

}
