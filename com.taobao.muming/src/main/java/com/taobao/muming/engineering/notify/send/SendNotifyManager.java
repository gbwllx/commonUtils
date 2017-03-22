package com.taobao.muming.engineering.notify.send;

import com.taobao.muming.engineering.notify.base.BytesMessage;
import com.taobao.muming.engineering.notify.base.MessageType;
import com.taobao.muming.engineering.notify.base.NotifyManagerBean;
import com.taobao.muming.engineering.notify.base.ObjectMessage;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Map;

/**
 * description:
 * author: gubing.gb
 * date: 2017/3/7.
 */
public class SendNotifyManager {
    /**
     * Notify消息管理器
     */
    private NotifyManagerBean notifyManagerBean;

    /**
     * 消息主题
     */
    private String topic;

    /**
     * 客户端投递消息至Notify服务器超时时间，单位毫秒
     */
    private int clientPostTimeout = 6 * 1000;

    /**
     * Notify服务器投递消息到客户端超时时间，单位毫秒
     */
    private int postTimeout = 10 * 1000;

    /**
     * 消息在Notify存活的时间，单位秒（默认在Server端存活七天）
     */
    private int timeToLive = 7 * 24 * 3600;

    /**
     * 只发送一次消息
     */
    private boolean sendOnceMessage = false;

    /**
     * 异步消息处理器
     */
    private AsynSendResultListener asynSendResultListener;

    //可定制消息头、可回调、可异步发送异步监听结果


    public <T> SendResult sendObjectMessage(MessageType messageType, T messageBody) {
        // 参数检查
        ObjectMessage message = this.createObjectMessage(messageType, messageBody, null);
        return this.notifyManagerBean.sendMessage(message);
    }

    public <T> SendResult sendBytesMessage(MessageType messageType, T messageBody) {
        // 参数检查
        BytesMessage message = this.createByteMessage(messageType, messageBody, null);
        return this.notifyManagerBean.sendMessage(message);
    }

    /**
     * 创建Notify对象类型消息
     *
     * @param messageType
     * @param messageBody
     * @param <T>
     * @return
     */
    private <T> ObjectMessage createObjectMessage(MessageType messageType, T messageBody, Map<String, String> properties) {

        // 参数检查
        if (StringUtils.isBlank(this.topic) || StringUtils.isBlank(this.notifyManagerBean.getGroupId()) || null == messageType || null == messageBody) {
            throw new RuntimeException("invalid paramater , please check input paramter , topic or groupId or messageType or messageBody can't be null!");
        }

        ObjectMessage message = new ObjectMessage();
        message.setObject(messageBody);

        message.setTopic(this.topic);
        message.setGroupId(this.notifyManagerBean.getGroupId());
        message.setMessageType(messageType.getType());

        message.setSendOnceMessage(this.sendOnceMessage);

        if (this.clientPostTimeout > 0) {
            message.setClientPostTimeout(this.clientPostTimeout);
        }

        if (this.postTimeout > 0) {
            message.setPostTimeout(this.postTimeout);
        }

        if (this.timeToLive > 0) {
            message.setTimeToLive(this.timeToLive);
        }

        // 添加消息头
        if (null != properties && !properties.isEmpty()) {
            for (Map.Entry<String, String> entry : properties.entrySet()) {
                message.setStringProperty(entry.getKey(), entry.getValue());
            }
        }
        return message;
    }

    /**
     * 获取Byte消息,使用Hessian序列化方式
     *
     * @param messageType
     * @param messageBody
     * @param properties
     * @param <T>
     * @return
     */
    private <T> BytesMessage createByteMessage(MessageType messageType, T messageBody, Map<String, String> properties) {
        // 参数检查
        if (StringUtils.isBlank(this.topic) || StringUtils.isBlank(this.notifyManagerBean.getGroupId()) || null == messageType || null == messageBody) {
            throw new RuntimeException("invalid paramater , please check input paramter , topic or groupId or messageType or messageBody can't be null!");
        }

        BytesMessage message = new BytesMessage();
        try {
            message.setBody(HessianUtil.serialize(messageBody));
        } catch (IOException e) {
            e.printStackTrace();
        }

        message.setTopic(this.topic);
        message.setGroupId(this.notifyManagerBean.getGroupId());
        message.setMessageType(messageType.getType());

        message.setSendOnceMessage(this.sendOnceMessage);

        if (this.clientPostTimeout > 0) {
            message.setClientPostTimeout(this.clientPostTimeout);
        }

        if (this.postTimeout > 0) {
            message.setPostTimeout(this.postTimeout);
        }

        if (this.timeToLive > 0) {
            message.setTimeToLive(this.timeToLive);
        }

        // 添加消息头
        if (null != properties && !properties.isEmpty()) {
            for (Map.Entry<String, String> entry : properties.entrySet()) {
                message.setStringProperty(entry.getKey(), entry.getValue());
            }
        }
        return message;
    }
}
