package com.taobao.muming.dailytest.proxy.staticp;

public class CountTest {
	public static void main(String[] args){
		CountImpl ci = new CountImpl();
		CountProxy cp = new CountProxy(ci);
		
		cp.updateCount();
		cp.queryCount();
	}
}
