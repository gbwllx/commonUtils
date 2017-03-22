package com.taobao.muming.engineering.idempotent;

/**
 * description:
 * author: gubing.gb
 * date: 2017/3/7.
 */
public enum IdempotentBizType {
    //创建订单
    CREATE_PUR_ORDER(2);

    private int type;

    private IdempotentBizType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
