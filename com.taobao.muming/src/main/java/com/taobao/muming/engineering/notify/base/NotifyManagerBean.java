package com.taobao.muming.engineering.notify.base;

import com.taobao.muming.engineering.notify.receive.processor.NotifyMessageListener;
import com.taobao.muming.engineering.notify.send.SendResult;

import java.util.List;

/**
 * description:
 * author: gubing.gb
 * date: 2017/3/7.
 */
public class NotifyManagerBean {
    private CharSequence groupId;
    private NotifyMessageListener messageListener;
    private int messageTPCorePoolSize;
    private int messageTPMaxPoolSize;
    private boolean debug;
    private int debugLocalPort;
    private int waitForConnTime;


    public CharSequence getGroupId() {
        return groupId;
    }

    public SendResult sendMessage(ObjectMessage message) {
        return null;
    }

    public SendResult sendMessage(BytesMessage message) {
        return null;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setMessageListener(NotifyMessageListener messageListener) {
        this.messageListener = messageListener;
    }

    public void subscriberAfterInited(List<Binding> bindings) {
    }

    public void subscribe(List<Binding> bindings) {
    }

    public void setWaitForConnTime(int waitForConnTime) {
        this.waitForConnTime = waitForConnTime;
    }

    public void setMessageTPCorePoolSize(int messageTPCorePoolSize) {
        this.messageTPCorePoolSize = messageTPCorePoolSize;
    }

    public void setMessageTPMaxPoolSize(int messageTPMaxPoolSize) {
        this.messageTPMaxPoolSize = messageTPMaxPoolSize;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void setDebugLocalPort(int debugLocalPort) {
        this.debugLocalPort = debugLocalPort;
    }

    public void init() {
    }
}
