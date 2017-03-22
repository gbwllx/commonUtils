package com.taobao.muming.dailytest.osgi.plugin.impl;


import com.taobao.muming.dailytest.osgi.plugin.api.ICheckService;

/**
 * Created by gubing.gb on 2016/12/9.
 */
public class CheckServiceImpl implements ICheckService {
    public String check(String money) {
        return money;
    }
}
