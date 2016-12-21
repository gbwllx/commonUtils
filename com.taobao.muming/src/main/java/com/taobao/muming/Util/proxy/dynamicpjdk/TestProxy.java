package com.taobao.muming.Util.proxy.dynamicpjdk;

public class TestProxy {
	public static void main(String[] args){
		BookFacadeProxy proxy = new BookFacadeProxy();
		BookFacade bookProxy = (BookFacade) proxy.bind(new BookFacadeImpl());
		bookProxy.addBook();
	}
}
