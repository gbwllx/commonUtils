package com.taobao.muming.Util.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


public class CharactorSet {
	//通过转换流处理   InputStreamReader  OutputStreamWriter
	public static void main(String[] args) throws IOException{		
//		String s = "你好";
//		byte[] b1 = s.getBytes();
//		
//		System.out.println(Arrays.toString(b1));
		
//		String s = "你好";
//		byte[] b1 = s.getBytes("GBK");
//		
//		System.out.println(Arrays.toString(b1));
		
//		
//		System.out.println(Arrays.toString(b1));
//		String s1 = new String(b1, "iso8859-1");
//		System.out.println(s1);
		
		//writeText();
//		String s = readText();
		//String xx = "褰撴墍鏈塻trs涓篵lank鏄細鎶涘嚭";
		//System.out.println(new String(xx.getBytes("gbk"), "utf-8"));
		//String s ="32222c226170705f6e616d65223a22c9b3b7a2b9dcbcd2222c2261706b5f6e61";
		String pre = "62757965724e69636b3d5b31cac5cbaec0b6ccec5d2c20706169644665653d5b ";
		System.out.println(toStringHex(pre));
		
//		System.out.println("s"+s);
//		byte[] b1 = s.getBytes("gbk");
//		System.out.println(Arrays.toString(b1));
//		String s2 = new String(b1, "utf-8");
//		System.out.println("s2"+s2);
		//System.out.println(getEncoding(xx));
	}
	
	public static String toStringHex(String s) 
	{ 
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(
						s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			s = new String(baKeyword, "gbk");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}
	
	public static String readText() throws IOException{
		InputStreamReader isr = new InputStreamReader(new FileInputStream("gbk.txt"));
		
		char []buf = new char[10];
		int len = isr.read(buf);
		String str = new String(buf, 0, len);
		System.out.println(str);
		
		isr.close();
		return str;
	}
	
	public static void writeText() throws IOException{
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("gbk.txt"), "utf-8");
		osw.write("你好");
		osw.close();
	}

	public static String getEncoding(String str) {
    	String encode = "";
        encode = "ISO-8859-1";
        Map<String, String> ret = new HashMap<String, String>();
        try {
        	String xx = new String(str.getBytes(encode), encode);
            if (str.equals(xx)) {
                String s1 = encode;
                ret.put(s1, str);
            }
        } catch (Exception exception1) {
        }
        encode = "GBK";
        try {
        	String xx = new String(str.getBytes(encode), encode);
            if (str.equals(xx)) {
                String s3 = encode;
                ret.put(s3, str);
            }
        } catch (Exception exception3) {
        }
        encode = "GB2312";
        try {
        	String xx = new String(str.getBytes(encode), encode);
            if (str.equals(xx)) {
                String s = encode;
                ret.put(s, str);
            }
        } catch (Exception exception) {
        }
        encode = "UTF-8";
        try {
        	String xx = new String(str.getBytes(encode), encode);
            if (str.equals(xx)) {
                String s2 = encode;
                ret.put(s2, str);
            }
        } catch (Exception exception2) {
        }
    	StringBuilder sb = new StringBuilder();
    	for(String s : ret.keySet())
    	{
    		sb.append(s).append(",");
    	}
        return StringUtils.substring(sb.toString(), 0, -1);
    }

	public static String getEncoding(byte[] str) {
		String encode = "";
		encode = "ISO-8859-1";
		System.out.println("str:"+Arrays.toString(str));
		Map<String, String> ret = new HashMap<String, String>();
		try {
			String xx = new String(str, encode);
			if (str.equals(xx)) {
				String s1 = encode;
				ret.put(xx, s1);
			}
		} catch (Exception exception1) {
		}
		encode = "GBK";
		try {
			String xx = new String(str, encode);
			if (str.equals(xx)) {
				String s3 = encode;
				ret.put(xx, s3);
			}
		} catch (Exception exception3) {
		}
		encode = "GB2312";
		try {
			String xx = new String(str, encode);
			if (str.equals(xx)) {
				String s = encode;
				ret.put(xx, s);
			}
		} catch (Exception exception) {
		}
		encode = "UTF-8";
		try {
			String xx = new String(str, encode);
			if (str.equals(xx)) {
				String s2 = encode;
				ret.put(xx, s2);
			}
		} catch (Exception exception2) {
		}
		StringBuilder sb = new StringBuilder();
		for (String s : ret.keySet()) {
			sb.append(s).append(",");
		}
		return StringUtils.substring(sb.toString(), 0, -1);
	}

}
