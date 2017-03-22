package com.taobao.muming.engineering.notify.base;

import java.io.Serializable;

/**
 * description:
 * author: gubing.gb
 * date: 2017/3/7.
 */
public enum MessageType implements Serializable {
    /**
     * PO
     */
    PUR_ORDER_CREATED("EPP", "create-pur-order", "创建采购订单单成功");

    MessageType(String topic, String type, String desc) {
        this.topic = topic;
        this.type = type;
        this.desc = desc;
    }

    /**
     * 消息主题
     */
    private String topic;

    /**
     * 消息类型
     */
    private String type;

    /**
     * 消息描述
     */
    private String desc;

    public String getTopic() {
        return topic;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
