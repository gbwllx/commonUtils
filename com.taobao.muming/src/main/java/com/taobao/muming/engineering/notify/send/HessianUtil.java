package com.taobao.muming.engineering.notify.send;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * description:
 * author: gubing.gb
 * date: 2017/3/7.
 */
public class HessianUtil {

    /**
     * 序列化
     *
     * @param obj
     * @return
     */
    public static byte[] serialize(Object obj) throws IOException {
        if (obj == null) throw new NullPointerException();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        HessianOutput ho = new HessianOutput(os);
//        ho.writeObject(obj);
        return os.toByteArray();
    }

    /**
     * 反序列化
     *
     * @param by
     * @return
     * @throws IOException
     */
    public static Object deserialize(byte[] by) throws IOException {
        if (by == null) throw new NullPointerException();

//        ByteArrayInputStream is = new ByteArrayInputStream(by);
//        HessianInput hi = new HessianInput(is);
//        return hi.readObject();
        return null;
    }

//    public static Object deserialize(byte[] by, ExtSerializerFactory extSerializerFactory) throws IOException {
//        if (by == null) throw new NullPointerException();
//
//        ByteArrayInputStream is = new ByteArrayInputStream(by);
//        HessianInput hi = new HessianInput(is);
//        if (extSerializerFactory != null) {
//            hi.getSerializerFactory().addFactory(extSerializerFactory); //注册ext到序列化工厂
//        }
//        return hi.readObject();
//    }

}
