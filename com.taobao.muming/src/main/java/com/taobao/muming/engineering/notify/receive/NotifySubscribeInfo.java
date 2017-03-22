package com.taobao.muming.engineering.notify.receive;

import com.taobao.muming.engineering.notify.base.MessageType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * description:
 * author: gubing.gb
 * date: 2017/3/7.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotifySubscribeInfo {
    /**
     * 订阅组的groupId
     */
    String subscribeGroupId();

    /**
     * 消息类型
     */
    MessageType[] messageType();

    /**
     * 订阅类型
     */
    NotifySubscribeType subscribeType() default NotifySubscribeType.DIRECT;

    /**
     * 订阅表达式,当订阅方式为header或pattern时生效
     */
    String subscribeExp() default "";

}
