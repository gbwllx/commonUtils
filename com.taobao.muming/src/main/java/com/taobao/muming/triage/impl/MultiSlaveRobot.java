package com.taobao.muming.triage.impl;

import java.util.List;

/**
 * description:
 * author: gubing.gb
 * date: 2017/3/9.
 */
public class MultiSlaveRobot {
    private String MEDICAL_DICT_FILE;   //医疗分词字典文件位置
    private String MAXENT_MODEL_FILE;   //分类模型位置

    public MultiSlaveRobot(String triageModelFile, String medicalDictFile, String white_list_file) {
        this.MAXENT_MODEL_FILE = triageModelFile;
        this.MEDICAL_DICT_FILE = medicalDictFile;
    }

    public List judge(List params) {
        return null;
    }

    public double[] runQuery(List params) {
        return null;
    }
}
