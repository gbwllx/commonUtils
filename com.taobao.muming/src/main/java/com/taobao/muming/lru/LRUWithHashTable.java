package com.taobao.muming.lru;

import java.util.Hashtable;

public class LRUWithHashTable {
	private int cacheSize;  
	@SuppressWarnings("rawtypes")
	private Hashtable nodes;//��������  
	private int currentSize;  
	private Entry first;//����ͷ  
	private Entry last;//����β  
	      
	@SuppressWarnings("rawtypes")
	public LRUWithHashTable(int i) {  
	    currentSize = 0;  
	    cacheSize = i;  
	    nodes = new Hashtable(i);//��������  
	}  
	      
	/** 
	 * ��ȡ�����ж���,������������ǰ�� 
	 */  
	public Entry get(Object key) {  
	    Entry node = (Entry) nodes.get(key);  
	    if (node != null) {  
	        moveToHead(node);  
	        return node;  
	    } else {  
	        return null;  
	    }  
	}  
	      
	/** 
	 * ���� entry��hashtable, ����entry  
	 */  
	@SuppressWarnings("unchecked")
	public void put(Object key, Object value) {  
	    //�Ȳ鿴hashtable�Ƿ���ڸ�entry, ������ڣ���ֻ������value  
	    Entry node = (Entry) nodes.get(key);  
	          
	    if (node == null) {  
	        //���������Ƿ��Ѿ�������С.  
	        if (currentSize >= cacheSize) {  
	            nodes.remove(last.key);  
	            removeLast();  
	        } else {  
	            currentSize++;  
	        }             
	        node = new Entry();  
	    }  
	    node.value = value;  
	    //������ʹ�õĽڵ�ŵ�����ͷ����ʾ����ʹ�õ�.  
	    moveToHead(node);  
	    nodes.put(key, node);  
	}  
	  
	/** 
	 * ��entryɾ��, ע�⣺ɾ������ֻ����cache���˲Żᱻִ�� 
	 */  
	public void remove(Object key) {  
	    Entry node = (Entry) nodes.get(key);  
	    //��������ɾ��  
	    if (node != null) {  
	        if (node.prev != null) {  
	            node.prev.next = node.next;  
	        }  
	        if (node.next != null) {  
	            node.next.prev = node.prev;  
	        }  
	        if (last == node)  
	            last = node.prev;  
	        if (first == node)  
	            first = node.next;  
	    }  
	    //��hashtable��ɾ��  
	    nodes.remove(key);  
	}  
	  
	/** 
	 * ɾ������β���ڵ㣬��ʹ����� ʹ�õ�entry 
	 */  
	private void removeLast() {  
	    //����β��Ϊ��,������βָ��null. ɾ������β��ɾ������ʹ�õĻ������  
	    if (last != null) {  
	        if (last.prev != null)  
	            last.prev.next = null;  
	        else  
	            first = null;  
	        last = last.prev;  
	    }  
	}  
	      
	/** 
	 * �ƶ�������ͷ����ʾ����ڵ�������ʹ�ù��� 
	 */  
	private void moveToHead(Entry node) {  
	    if (node == first)  
	        return;  
	    if (node.prev != null)  
	        node.prev.next = node.next;  
	    if (node.next != null)  
	        node.next.prev = node.prev;  
	    if (last == node)  
	        last = node.prev;  
	    if (first != null) {  
	        node.next = first;  
	        first.prev = node;  
	    }  
	    first = node;  
	    node.prev = null;  
	    if (last == null)  
	        last = first;  
	}  
	/* 
	 * ��ջ��� 
	 */  
	public void clear() {  
	    first = null;  
	    last = null;  
	    currentSize = 0;  
	}   
}  
	  
class Entry {  
	Entry prev;//ǰһ�ڵ�  
	Entry next;//��һ�ڵ�  
	Object value;//ֵ  
	Object key;//��  
}  
