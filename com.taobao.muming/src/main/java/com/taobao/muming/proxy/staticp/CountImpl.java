package com.taobao.muming.proxy.staticp;

public class CountImpl implements Count{
	public void queryCount() {
		System.out.println("查看账户方法...");
	}

	public void updateCount() {
		System.out.println("修改账户方法...");
	}
}
