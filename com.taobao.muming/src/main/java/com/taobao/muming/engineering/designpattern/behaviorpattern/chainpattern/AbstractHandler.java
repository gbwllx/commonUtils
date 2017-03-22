package com.taobao.muming.engineering.designpattern.behaviorpattern.chainpattern;


import com.taobao.muming.engineering.designpattern.behaviorpattern.chainpattern.exception.BizException;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 */
public abstract class AbstractHandler {
    public final Response execute(Request request) throws BizException{
        Response response = doBiz(request);
        return response;
    }

    public abstract Response doBiz(Request request);
}
