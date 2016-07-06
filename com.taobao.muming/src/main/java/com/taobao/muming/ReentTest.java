package com.taobao.muming;

import java.util.concurrent.locks.ReentrantLock;

public class ReentTest {
	static ReentrantLock lock = new ReentrantLock();  
    private static String[] threadArr = {"A","B","C"};  
      
    public static void main(String[] args){  
    	ReentTest pc = new ReentTest();  
        pc.startDemo();  
    }  
      
    void startDemo(){  
        for(int i = 0;i<10;i++){  
            for(String name : threadArr){  
                TestThread t = new TestThread(name);  
                t.start();  
                try {  
                    Thread.sleep(100);  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
      
  
    class TestThread extends Thread{  
        //�Զ����߳�����  
        TestThread(String str){  
            super(str);           
        }  
          
        public void run(){  
            try {  
                lock.lockInterruptibly();  
              
                System.out.println(Thread.currentThread().getName()+Thread.currentThread().getId());  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            } finally{  
                lock.unlock();  
            }     
        }  
    }  
}