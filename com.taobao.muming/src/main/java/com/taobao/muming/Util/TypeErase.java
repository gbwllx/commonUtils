package com.taobao.muming.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeErase {
	@SuppressWarnings("rawtypes")
	public static void main(String args[]){
		Class c1 = new ArrayList<Integer>().getClass();
		Class c2 = new ArrayList<String>().getClass(); 
		System.out.println(c1 == c2);
		/* Output
		true
		*/
		
		List<Integer> list = new ArrayList<Integer>();
		Map<Integer, String> map = new HashMap<Integer, String>();
		System.out.println(Arrays.toString(list.getClass().getTypeParameters()));
		System.out.println(Arrays.toString(map.getClass().getTypeParameters()));
		/* Output
		[E]
		[K, V]
		*/
	}
}
