package com.kam.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import sun.misc.BASE64Encoder;

public class CommonUtils {

	public static String generateShortUuid() {
		String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
				"q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
				"U", "V", "W", "X", "Y", "Z" };

		StringBuffer shortBuffer = new StringBuffer();

		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}

		return shortBuffer.toString();
	}
	
	public static String encoderByMd5(String str) {
		String encodeStr = null;
		
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			encodeStr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return encodeStr;
	}
}
