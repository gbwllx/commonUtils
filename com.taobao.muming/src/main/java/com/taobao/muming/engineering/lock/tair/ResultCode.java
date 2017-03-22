package com.taobao.muming.engineering.lock.tair;

/**
 * description:
 * author: gubing.gb
 * date: 2017/3/7.
 */
public class ResultCode {
    public static final ResultCode VERERROR = createResultCode(-3997, "version error. Always happened in highly concurrent write circumstances, try reduce concurrency");
    private final int code;
    private final String message;

    public ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private static ResultCode createResultCode(int i, String s) {
        return new ResultCode(i, s);
    }

    public boolean isSuccess() {
        return code >= 0;
    }
}
