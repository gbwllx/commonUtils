package com.taobao.muming.Util.proxy.staticp;

public class CountProxy implements Count{
	private CountImpl countImpl;

	public CountProxy(CountImpl countImpl){
		this.countImpl = countImpl;
	}
	
	public void queryCount() {
		System.out.println("事务处理之前");  
		countImpl.queryCount();
		System.out.println("事务处理之后");  
	}

	public void updateCount() {
		System.out.println("事务处理之前");  
		countImpl.updateCount();
		System.out.println("事务处理之后");  
	}
	
}
