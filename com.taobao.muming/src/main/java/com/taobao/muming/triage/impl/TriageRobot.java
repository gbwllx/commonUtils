package com.taobao.muming.triage.impl;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * description:
 * author: gubing.gb
 * date: 2017/3/9.
 */
public class TriageRobot {
    private String MEDICAL_DICT_FILE = Constant.DICT_NAME;   //医疗分词字典文件
    private String TRIAGE_MODEL_FILE = Constant.MODE_NAME;   //分类模型文件
    private String WHITE_LIST_FILE = Constant.WHITE_LIST_NAME; //白名单文件
    private MultiSlaveRobot slaveRobot;

    public TriageRobot() throws IOException {
        slaveRobot = new MultiSlaveRobot(TRIAGE_MODEL_FILE, MEDICAL_DICT_FILE, WHITE_LIST_FILE);
    }

    public List decodeQuery(String queryStr) {
        List<String> params = new ArrayList<String>(3);
        try {
            JSONObject json = JSONObject.parseObject(queryStr);

            if (json.containsKey("note")) {  //咨询问题
                params.add(json.getString("note"));
            } else {
                params.add("");
            }
            if (json.containsKey("history")) {
                JSONObject history = json.getJSONObject("history");
                if (history.containsKey("gender")) {
                    params.add(history.getString("gender"));
                } else {
                    params.add("unknown");
                }
                if (history.containsKey("age")) {
                    params.add(history.getString("age"));
                } else {
                    params.add("unknown");
                }
            } else {
                params.add("unknown");
                params.add("unknown");
            }
        } catch (Exception e) {
            params.set(0, "");     //咨询问题
            params.set(1, "unknow");   //性别
            params.set(2, "unknow");   //年龄
        }

        return params;
    }

    /**
     * @param queryStr 以json格式传入的查询
     * @return 返回分类结果, 代码如下
     * 0：未识别
     * 1：皮肤病
     * 2: 儿科
     * 3：妇科
     * 4：男科
     * 5: 消化内科
     */
    public List run(String queryStr) {
        List params = decodeQuery(queryStr);
        return slaveRobot.judge(params);
    }

    public double[] runQuery(String query) {
        List params = decodeQuery(query);
        return slaveRobot.runQuery(params);
    }
}
