package com.taobao.muming.dailytest.proxy.dynamicpjdk;

public class TestProxy {
	public static void main(String[] args){
		BookFacadeProxy proxy = new BookFacadeProxy();
		BookFacade bookProxy = (BookFacade) proxy.bind(new BookFacadeImpl());
		bookProxy.addBook();
	}
}
