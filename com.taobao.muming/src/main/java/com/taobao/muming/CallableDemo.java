package com.taobao.muming;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallableDemo implements Callable<String> {
	@Override
	public String call() throws Exception {
		final String orgName = Thread.currentThread().getName();
		try {
			Thread.currentThread().setName("huhu");
			for (int i = 0; i < 100; i++) {
				System.out.println(Thread.currentThread().getName() + "----"
						+ i);
			}
		} finally {
			Thread.currentThread().setName(orgName);
		}
		return null;
	}

	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(2);
		CallableDemo demo = new CallableDemo();

		pool.submit(new CallableDemo());
		pool.submit(new CallableDemo());
	}
}