package com.taobao.muming.engineering.idempotent;

import org.apache.commons.lang.StringUtils;

/**
 * description: 幂等key：包含key(如果是外部调用需传入外部key)，业务类型，客户ID
 * author: gubing.gb
 * date: 2017/3/7.
 */
public class IdempotentKey {

    private IdempotentKey() {
    }

    private String key;

    /**
     * 业务类型
     */
    private IdempotentBizType type;

    /**
     * 幂等校验异常时，是否允许业务继续执行
     */
    private boolean continueAfterIdempotentExcepiton = true;

    /**
     * 客户ID
     */
    private long customerId;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isContinueAfterIdempotentExcepiton() {
        return continueAfterIdempotentExcepiton;
    }

    public void setContinueAfterIdempotentExcepiton(boolean continueAfterIdempotentExcepiton) {
        this.continueAfterIdempotentExcepiton = continueAfterIdempotentExcepiton;
    }

    public static IdempotentKey of(String key) {
        IdempotentKey idempotentKey = new IdempotentKey();
        idempotentKey.setKey(key);
        return idempotentKey;
    }


    public static IdempotentKey of(String key, IdempotentBizType idempotentBizType) {
        IdempotentKey idempotentKey = new IdempotentKey();
        idempotentKey.setKey(key);
        idempotentKey.setType(idempotentBizType);
        return idempotentKey;
    }

    //根据需求类型与客户ID生成幂等key，外部调用需要外部传入key，内部只需要customerId
    public static IdempotentKey of(String key, IdempotentBizType idempotentBizType, long customerId) {
        IdempotentKey idempotentKey = new IdempotentKey();
        idempotentKey.setKey(key);
        idempotentKey.setType(idempotentBizType);
        idempotentKey.setCustomerId(customerId);
        return idempotentKey;
    }

    public IdempotentBizType getType() {
        return type;
    }

    public void setType(IdempotentBizType type) {
        this.type = type;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public boolean isNeedIdempotent() {
        return StringUtils.isNotBlank(key);
    }
}

