package com.taobao.muming.triage;

import com.taobao.muming.triage.common.ResultObj;

/**
 * description:
 * author: gubing.gb
 * date: 2017/3/9.
 */
public interface TriageService {
    public ResultObj<Integer> assortIll2Dep(String quertStr);
}
