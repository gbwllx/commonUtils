package com.taobao.muming.triage.common;

import java.io.Serializable;

/**
 * Created by zzy96232 on 2016/8/24.
 */

public class ResultObj<T> implements Serializable {

  private static final long serialVersionUID = -4872371007434025010L;
  protected boolean success = true;
  protected String retMessage;
  protected T model;

  public ResultObj() {
  }

  public ResultObj(boolean success, String retCode, String retMessage) {
    this.success = success;
    this.retMessage = retMessage;
  }

  public ResultObj(T model) {
    this.model = model;
  }

  public ResultObj(boolean success, String retCode, String retMessage, T model) {
    this.success = success;
    this.retMessage = retMessage;
    this.model = model;
  }


  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getRetMessage() {
    return retMessage;
  }

  public void setRetMessage(String retMessage) {
    this.retMessage = retMessage;
  }

  public T getModel() {
    return model;
  }

  public void setModel(T model) {
    this.model = model;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}