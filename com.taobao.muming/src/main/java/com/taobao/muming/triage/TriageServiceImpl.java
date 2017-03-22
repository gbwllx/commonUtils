package com.taobao.muming.triage;

import com.taobao.muming.triage.common.ResultObj;
import com.taobao.muming.triage.impl.TriageRobot;

import javax.annotation.Resource;

/**
 * description:
 * author: gubing.gb
 * date: 2017/3/9.
 */
public class TriageServiceImpl implements TriageService {
    @Resource
    private TriageRobot triageRobot;

    @Override
    public ResultObj<Integer> assortIll2Dep(String quertStr) {
        ResultObj resultObj = new ResultObj();

        triageRobot.run(quertStr);
        return null;
    }
}
