package com.taobao.muming.engineering.designpattern.behaviorpattern.chainpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: gubing.gb
 * @date: 2016/12/26.
 * 简单的责任链模式没有Context，但是感觉耦合太厉害，把上下文关系抽象出，放到Context中维护
 */
public class Context {
    //private Request request;
    //private Response response;
    private static List<AbstractHandler> chain = new ArrayList<AbstractHandler>();

    static {
        chain.add(new AaXxxHandler());
        chain.add(new BbXxxhandler());
    }

    public Response execute(Request request) {
        Response response = null;
        for (AbstractHandler handler : chain) {
            try {
                response = handler.execute(request);
            } catch (Exception e) {
                //response.setErrCode();
                response.setErrMsg(e.toString());
                break;
            }
        }
        return response;
    }
}
