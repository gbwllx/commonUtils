package com.taobao.muming.Util.proxy.dynamicpcglib;

public class BookTest {
	public static void main(String[] args){
		BookFacadeProxy cglib = new BookFacadeProxy();
		BookFacadeImpl1 bookCglib = (BookFacadeImpl1)cglib.getInstance(new BookFacadeImpl1());
		bookCglib.addBook();
	}
}
