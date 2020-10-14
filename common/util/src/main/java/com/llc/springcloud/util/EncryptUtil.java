package com.llc.springcloud.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {

	public static String sha(String str) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
		byte[] bt = str.getBytes(StandardCharsets.UTF_8);
		byte[] out = md.digest(bt);
		StringBuilder hexValue = new StringBuilder();
		for (byte b : out) {
			int val = ((int) b) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
}
