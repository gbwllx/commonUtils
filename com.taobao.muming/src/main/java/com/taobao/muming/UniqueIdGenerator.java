package com.taobao.muming.Util;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

class TraceIdGenerator {

	private static String IP_16 = "ffffffff";
	private static String IP_int = "255255255255";
	private static String PID = "0000";
	private static char PID_FLAG = 'd';

	private static final String regex = "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b";
	private static final Pattern pattern = Pattern.compile(regex);
	private static AtomicInteger count = new AtomicInteger(1000);

	static {
		try {
			String ipAddress = "";//FIXME:getLocalAddress();
			if (ipAddress != null) {
				IP_16 = getIP_16(ipAddress);
				IP_int = getIP_int(ipAddress);
			}

			PID = getHexPid(111);//FIXME:getCurrrentPid());
		} catch (Throwable e) {
		}
	}

	static String getHexPid(int pid) {
		// unsign short 0 to 65535
		if (pid < 0)
			pid = 0;
		if (pid > 65535) {
			String strPid = Integer.toString(pid);
			strPid = strPid.substring(strPid.length() - 4, strPid.length());
			pid = Integer.parseInt(strPid);
		}
		String str = Integer.toHexString(pid);
		while (str.length() < 4)
			str = "0" + str;
		return str;
	}

	private static String getTraceId(String ip, long timestamp, int nextId) {
		StringBuilder appender = new StringBuilder(32);
		appender.append(ip).append(timestamp).append(nextId).append(PID_FLAG)
				.append(PID);
		return appender.toString();
	}

	static String generate() {
		return getTraceId(IP_16, System.currentTimeMillis(), getNextId());
	}

	static String generate(String ip) {
		if (ip != null && !ip.isEmpty() && validate(ip)) {
			return getTraceId(getIP_16(ip), System.currentTimeMillis(),
					getNextId());
		} else {
			return generate();
		}
	}

	static String generateIpv4Id() {
		return IP_int;
	}

	static String generateIpv4Id(String ip) {
		if (ip != null && !ip.isEmpty() && validate(ip)) {
			return getIP_int(ip);
		} else {
			return IP_int;
		}
	}

	private static boolean validate(String ip) {
		try {
			return pattern.matcher(ip).matches();
		} catch (Throwable e) {
			return false;
		}
	}

	private static String getIP_16(String ip) {
		String[] ips = ip.split("\\.");
		StringBuilder sb = new StringBuilder();
		for (String column : ips) {
			String hex = Integer.toHexString(Integer.parseInt(column));
			if (hex.length() == 1) {
				sb.append('0').append(hex);
			} else {
				sb.append(hex);
			}

		}
		return sb.toString();
	}

	private static String getIP_int(String ip) {
		return ip.replace(".", "");
	}

	private static int getNextId() {
		for (;;) {
			int current = count.get();
			int next = (current > 9000) ? 1000 : current + 1;
			if (count.compareAndSet(current, next))
				return next;
		}
	}
}
