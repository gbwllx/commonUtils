package com.taobao.muming.Util.test;

public class Utf8Test {
	
	/*
	 * \xf0\x9f\x98\x8a 
	 * -16 -97 -104 -127 -8 
	 * 16 00010000 -16 11110000(0xF0) 
	 * 97 01100001 -97 10011111(0x9F) 
	 * 104 01101000 -104 10011000(0x98) 
	 * 127 01111111 -127 10000010(0x82) 
	 * 74 01110100 -74 10001100(0x8A) 
	 * 8 00001000 -8 11111000(0xF8)
	 */
	public static String removeFourChar(String content) {
		byte[] conbyte = content.getBytes();
		for (int i = 0; i < conbyte.length; i++) {
			if ((conbyte[i] & 0xF8) == 0xF0) {
				for (int j = 0; j < 4; j++) {
					conbyte[i + j] = 0x30; // ascii 16进制表示的字符0
				}
				i += 3;
			}
		}
		content = new String(conbyte);
		return content.replaceAll("0000", "");
	}
	
	/*
	 * &十六进制：C0 B1 二进制：11000000 10110001 对比两个字节编码的表示方式： 110xxxxx 10xxxxxx
	 * 提取出对应的UNICODE编码： 00000 110001
	 * 可以看出此编码并非“标准”的UTF-8编码，因为其第一个字节的“有效编码”全为0，去除高位0后的编码仅有6位
	 * 。由前面所述，此字符仅用一个字节的UTF-8编码表示就够了。
	 * JAVA在把字符还原为UTF-8编码时，是按照“标准”的方式处理的，因此我们得到的是仅有1个字节的编码。
	 * 不好使了，哈哈
	 * 大家可以试试运行这段代码：
	 */

	public static void main(String[] args) throws Exception {
		byte[][] bytes = {
				// 00110001
				{ (byte) 0x31 },
				// 11000000 10110001
				{ (byte) 0xC0, (byte) 0xB1 },
				// 11100000 10000000 10110001
				{ (byte) 0xE0, (byte) 0x80, (byte) 0xB1 },
				// 11110000 10000000 10000000 10110001
				{ (byte) 0xF0, (byte) 0x80, (byte) 0x80, (byte) 0xB1 },
				// 11111000 10000000 10000000 10000000 10110001
				{ (byte) 0xF8, (byte) 0x80, (byte) 0x80, (byte) 0x80,
						(byte) 0xB1 },
				// 11111100 10000000 10000000 10000000 10000000 10110001
				{ (byte) 0xFC, (byte) 0x80, (byte) 0x80, (byte) 0x80,
						(byte) 0x80, (byte) 0xB1 }, };
		for (int i = 0; i < 6; i++) {
			String str = new String(bytes[i], "UTF-8");
			System.out.println("原数组长度：" + bytes[i].length + "/t转换为字符串：" + str
					+ "/t转回后数组长度：" + str.getBytes("UTF-8").length);
		}
		
		String text = "123😁😁😁😁12312";
		removeFourChar(text);
	}
	/*
	 * 　　运行结果为：
	 * 
	 * 原数组长度：1 转换为字符串：1 转回后数组长度：1 原数组长度：2 转换为字符串：1 转回后数组长度：1 原数组长度：3 转换为字符串：1
	 * 转回后数组长度：1 原数组长度：4 转换为字符串：1 转回后数组长度：1 原数组长度：5 转换为字符串：1 转回后数组长度：1 原数组长度：6
	 * 转换为字符串：1 转回后数组长度：1
	 */
}
