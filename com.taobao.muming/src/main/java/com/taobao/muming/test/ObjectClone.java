package com.taobao.muming.Util.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

public class ObjectClone {
    public static void test() throws IOException, ClassNotFoundException {
    	Set<Long> s = new HashSet<Long>();
    	s.add(1l);
    	s.add(2l);
    	Set<Long> c = s;
    	System.out.println(c==s);
    	System.out.println(s);
    	ByteArrayOutputStream byteOut = new ByteArrayOutputStream();  
    	ObjectOutputStream out= new ObjectOutputStream(byteOut);  
    	out.writeObject(s);//写对象，序列化  
    	ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());   
    	ObjectInputStream in = new ObjectInputStream(byteIn);
    	@SuppressWarnings("unchecked")
		Set<Long> dv = (Set<Long>)in.readObject(); //读对象，反序列化  
    	System.out.println(dv==c);
    	
    }
    
    public static void main(String[] args) throws ClassNotFoundException, IOException{
    	test();
    }
}
