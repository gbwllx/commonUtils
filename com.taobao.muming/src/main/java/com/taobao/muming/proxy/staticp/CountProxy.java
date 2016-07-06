package com.taobao.muming.proxy.staticp;

public class CountProxy implements Count{
	private CountImpl countImpl;

	public CountProxy(CountImpl countImpl){
		this.countImpl = countImpl;
	}
	
	public void queryCount() {
		System.out.println("������֮ǰ");  
		countImpl.queryCount();
		System.out.println("������֮��");  
	}

	public void updateCount() {
		System.out.println("������֮ǰ");  
		countImpl.updateCount();
		System.out.println("������֮��");  
	}
	
}
