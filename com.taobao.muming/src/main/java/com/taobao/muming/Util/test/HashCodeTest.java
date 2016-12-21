package com.taobao.muming.Util.test;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class HashCodeTest {
    public int hashCode(String str) {
    	char[] value = str.toCharArray();
    	int hash = 1708205068;
        int h = hash;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
    }
    
    public void setLong() {
    	Set<Long> s = new HashSet<Long>();
    	s.add(1l);
    	s.add(2l);
    	Set<Long> c = new TreeSet<Long>();
    	c.add(1l);
    	c.add(2l);
		System.out.println(s.containsAll(c));
		

    }
    
    public static void main(String[] args){
    	HashCodeTest hc = new HashCodeTest();
    	System.out.println(hc.hashCode("fsfds143321313432fafsafdsfdsfa"));
    	hc.setLong();
    }
}
