package com.taobao.muming;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTest {
	public void test(){
		ExecutorService executor = Executors.newSingleThreadExecutor();
		
		FutureTask<String> future = new FutureTask<String>(new Callable<String>(){
			public String call(){return "";}
		});
		executor.execute(future);
		
		try{
			String result = future.get(5000, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			future.cancel(true);
		} finally {
			executor.shutdown();
		}
	}
}
