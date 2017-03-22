package com.taobao.muming.engineering.notify.base;

/**
 * description:
 * author: gubing.gb
 * date: 2017/3/7.
 */
public class Binding {
    public static Binding direct(String topic, String messageType, String groupId, int i, boolean b) {
        return null;
    }

    public static Binding pattern(String topic, String subscribeExp, String groupId, int i, boolean b) {
        return null;
    }

    public static Binding header(String topic, String subscribeExp, String groupId, int i, boolean b) {
        return null;
    }

    public static Binding fanout(String topic, String groupId, int i, boolean b) {
        return null;
    }
}
