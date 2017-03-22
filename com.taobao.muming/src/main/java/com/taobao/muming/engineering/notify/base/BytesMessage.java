package com.taobao.muming.engineering.notify.base;

/**
 * description:
 * author: gubing.gb
 * date: 2017/3/7.
 */
public class BytesMessage {
    private byte[] body;
    private Object object;
    private String topic;
    private CharSequence groupId;
    private String messageType;
    private boolean sendOnceMessage;
    private int clientPostTimeout;
    private int postTimeout;
    private int timeToLive;

    public <T> void setObject(T object) {
        this.object = object;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setGroupId(CharSequence groupId) {
        this.groupId = groupId;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public void setSendOnceMessage(boolean sendOnceMessage) {
        this.sendOnceMessage = sendOnceMessage;
    }

    public void setClientPostTimeout(int clientPostTimeout) {
        this.clientPostTimeout = clientPostTimeout;
    }

    public void setPostTimeout(int postTimeout) {
        this.postTimeout = postTimeout;
    }

    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    public void setStringProperty(String key, String value) {
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
