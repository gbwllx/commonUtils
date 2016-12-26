package com.taobao.muming.designpattern.behaviorpattern.chainpattern;


/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
public class Response<T> {
    private Integer errCode;
    private String errMsg;
    private T result;

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
