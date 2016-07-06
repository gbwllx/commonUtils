package com.taobao.muming.utils;

import java.io.ByteArrayOutputStream;

public class CharsetUtils {
	//������ 
	//i - Ҫת�����ַ����������� 
	//���أ� 
	//��ʮ�����ƣ����� 16��������ʾ���޷�������ֵ���ַ�����ʾ��ʽ�� 
	// ת���ַ���Ϊʮ�����Ʊ��� 
	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

	// ת��ʮ�����Ʊ���Ϊ�ַ��� 
	public static String toStringHex(String s) {
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
			s = new String(baKeyword, "utf-8");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}

	/* 
	* 16���������ַ��� 
	*/ 
	private static String hexString="0123456789ABCDEF"; 
	/* 
	* ���ַ��������16��������,�����������ַ����������ģ� 
	*/ 
	public static String encode(String str) {
		// ����Ĭ�ϱ����ȡ�ֽ�����
		byte[] bytes = str.getBytes();
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		// ���ֽ�������ÿ���ֽڲ���2λ16��������
		for (int i = 0; i < bytes.length; i++) {
			sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
			sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
		}
		return sb.toString();
	}

	/* 
	* ��16�������ֽ�����ַ���,�����������ַ����������ģ� 
	*/ 
	public static String decode(String bytes) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(
				bytes.length() / 2);
		// ��ÿ2λ16����������װ��һ���ֽ�
		for (int i = 0; i < bytes.length(); i += 2)
			baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
					.indexOf(bytes.charAt(i + 1))));
		return new String(baos.toByteArray());
	}
	
	/** 
	* ��ָ��byte������16���Ƶ���ʽ��ӡ������̨ 
	* @param hint String 
	* @param b byte[] 
	* @return void 
	*/ 
	public static void printHexString(String hint, byte[] b) {
		System.out.print(hint);
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			System.out.print(hex.toUpperCase() + " ");
		}
		System.out.println("");
	}

	/** 
	* 
	* @param b byte[] 
	* @return String 
	*/ 
	public static String Bytes2HexString(byte[] b) {
		String ret = "";
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += hex.toUpperCase();
		}
		return ret;
	}

	/** 
	* ������ASCII�ַ��ϳ�һ���ֽڣ� 
	* �磺"EF"--> 0xEF 
	* @param src0 byte 
	* @param src1 byte 
	* @return byte 
	*/ 
	public static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 }))
				.byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 }))
				.byteValue();
		byte ret = (byte) (_b0 ^ _b1);
		return ret;
	}

	/** 
	* ��ָ���ַ���src����ÿ�����ַ��ָ�ת��Ϊ16������ʽ 
	* �磺"2B44EFD9" --> byte[]{0x2B, 0x44, 0xEF, 0xD9} 
	* @param src String 
	* @return byte[] 
	*/ 
	public static byte[] HexString2Bytes(String src) {
		byte[] ret = new byte[8];
		byte[] tmp = src.getBytes();
		for (int i = 0; i < 8; i++) {
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		}
		return ret;
	}
}
