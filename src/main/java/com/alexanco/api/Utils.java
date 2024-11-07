package com.alexanco.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Utils {

	public static String getStringTimestamp() {
		return String.valueOf(System.currentTimeMillis());
	}

	public static String hexToAscii(String hexStr) {
		StringBuilder output = new StringBuilder("");

		for (int i = 0; i < hexStr.length(); i += 2) {
			String str = hexStr.substring(i, i + 2);
			output.append((char) Integer.parseInt(str, 16));
		}

		return output.toString();
	}

	public static HashMap<String, String> parseString2HashMap(String message) {
		if (message.startsWith(";") || message.endsWith(";")) {
			// throw new AppException("Message malformatted");
			throw new AppException("Unformatted payload");
		}

		List<String> listInfo = Arrays.asList(message.split(";"));

		HashMap<String, String> infoMap = new HashMap<>();

		for (String info : listInfo) {
			String[] infoField = info.split(":");
			if (infoField.length != 2) {
				// throw new AppException("Message malformatted");
				throw new AppException("Unformatted payload");
			}
			infoMap.put(infoField[0], infoField[1]);
		}

		return infoMap;
	}

	public static byte[] hexStringToByteArray(String s) {
		byte[] b = new byte[s.length() / 2];
		for (int i = 0; i < b.length; i++) {
			int index = i * 2;
			int v = Integer.parseInt(s.substring(index, index + 2), 16);
			b[i] = (byte) v;
		}
		return b;
	}

	/**
	 * Append (or fill if empty) the stringbuffer passed in with the
	 * hex equivalent of the long passed in
	 *
	 * @param sb  stringbuffer to convert
	 * @param dec long int to convert
	 * @return StringBuffer of hexed values
	 */
	public static StringBuffer b2x(StringBuffer sb, long dec) {
		int d = (int) (dec);
		return sb.append(n2x(d >> 4)).append(n2x(d));
	}

	/**
	 * Return a String containing the hex equivalent of the integer passed in
	 *
	 * @param dec integer to convert
	 * @return String representation of the string buffer containing the hex
	 */
	public static String b2x(int dec) {
		return b2x(new StringBuffer(), dec).toString();
	}

	public static char n2x(int n) {
		char[] x = {
			'0',
			'1',
			'2',
			'3',
			'4',
			'5',
			'6',
			'7',
			'8',
			'9',
			'a',
			'b',
			'c',
			'd',
			'e',
			'f',
		};

		n &= 0xf;
		return x[n];
	}

	/**
	 * Return a String containing the hex equivalent of the byte array passed in
	 *
	 * @param a byte array of the input data
	 * @param o offset into the byte array to convert
	 * @param l Length in bytes to convert
	 * @return String containing the hex output
	 */
	public static String byteArrayToString(byte[] a, int o, int l) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < l; i++) {
			b2x(sb, a[o + i]);
		}
		return sb.toString();
	}
 
}
