package com.taobao.muming.Util.proxy.staticp;

public class CountTest {
	public static void main(String[] args){
		CountImpl ci = new CountImpl();
		CountProxy cp = new CountProxy(ci);
		
		cp.updateCount();
		cp.queryCount();
	}
}
