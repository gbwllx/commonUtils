package com.taobao.muming.algorithm.book.silicon.part6;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Sum2 {
	/*
	 * ��ÿ��������һ�Σ�ֱ���ҵ���ʱ�临�Ӷ�O(N2)���ռ临�Ӷ�O(1)
	 */
	public static void sum21(int[] arr, int dest){
		System.out.println("sum21:");
		if(arr != null && arr.length >= 2){
			for(int i = 0; i < arr.length; i++){
				for(int j = i + 1; j < arr.length; j++){
					if(arr[i]+arr[j] == dest){
						print(arr, i, j);
						return;
					}
				}
			}
		}
		System.out.print("none");
	}

	/*
	 * 	ɨ��һ�����hashmap���жϲ�ֵ�Ƿ��ڱ��У�ʱ�临�Ӷ�O(N)���ռ临�Ӷ�O(N)
	 */
	public static void sum22(int[] arr, int dest){
		System.out.println("sum22:");
		if(arr != null && arr.length >= 2){
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			for(int i = 0; i < arr.length; i++){
				map.put(arr[i], i);
			}
			
			for(int i = 0; i < arr.length; i++){
				int key = dest - arr[i];
				if(map.containsKey(key) && key != arr[i]){
					int j = map.get(key);
					print(arr, i, j);
					return;
				}
			}
		}
		System.out.print("none");
	}
	
	/*
	 * 	������O(NlogN)���ٶ��ֲ���O(NlogN),ʱ�临�Ӷ�O(NlogN),�ռ临�Ӷ�O(1),ȱ��ı�ԭ����ṹ
	 */
	public static void sum23(int[] arr, int dest){
		System.out.println("sum23:");
		if(arr != null && arr.length >= 2){
			Arrays.sort(arr);
			for(int i = 0; i < arr.length; i++){
				int key = dest - arr[i];
				int j = Arrays.binarySearch(arr, key);
				if(j >= 0){
					print(arr, i, j);
					return;
				}
			}
		}

		System.out.print("none");
		/*for(int i = 0; i < arr.length; i++)
			System.out.println(arr[i]);
		*/
	}
	
	private static void print(int[] arr, int i, int j) {
		System.out.println(i + ":" + arr[i]);
		System.out.println(j + ":" + arr[j]);
	}
	
	public static void main(String[] args){
		int[] arr = new int[]{2, 3, 1, 4, 6, 9, 8, 10, 22, 33, 44, 23, 34, 25, 11};
		sum21(arr, 7);
		sum22(arr, 21);
		sum23(arr, 20);
	}
}
