package com.taobao.muming.designpattern.creatorpattern.factorypattern.simplefactory;

import com.taobao.muming.designpattern.creatorpattern.factorypattern.simplefactory.domain.XxxDO;

/**
 * Created by zhangzhiqi on 16/12/25.
 */
public class AaXxxServiceImpl implements XxxService {
    public XxxDO getResult() {
        return new XxxDO();
    }
}
