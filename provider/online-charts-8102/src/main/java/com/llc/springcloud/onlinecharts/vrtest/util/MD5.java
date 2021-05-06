package com.llc.springcloud.onlinecharts.vrtest.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {


	public static String encode(String s) {
		try {
			byte b[] = s.getBytes("UTF-8");
			return md5(b);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String fix(int i, int digital) {
		String left = "0000000000";
		String s = (new StringBuilder(String.valueOf(left))).append(i).toString();
		return s.substring(s.length() - digital);
	}

	public static byte[] toByte(long i) {
		byte b[] = new byte[8];
		for (int j = 0; j < 8; j++) {
			b[j] = (byte) (int) (i & 255L);
			i >>= 8;
		}
		return b;
	}

	public static byte[] toByte(int i) {
		byte b[] = new byte[4];
		for (int j = 0; j < 4; j++) {
			b[j] = (byte) (i & 0xff);
			i >>= 8;
		}
		return b;
	}

	public static String md5(byte b[]) {
		MessageDigest msgDigest = null;
		try {
			msgDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("System doesn't support MD5 algorithm.");
		}
		msgDigest.update(b);
		byte bytes[] = msgDigest.digest();
		String md5Str = new String(encodeHex(bytes));
		return md5Str;
	}

	public static char[] encodeHex(byte data[]) {
		int l = data.length;
		char out[] = new char[l << 1];
		int i = 0;
		int j = 0;
		for (; i < l; i++) {
			out[j++] = DIGITS[(0xf0 & data[i]) >>> 4];
			out[j++] = DIGITS[0xf & data[i]];
		}
		return out;
	}

	private static final char DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

}
