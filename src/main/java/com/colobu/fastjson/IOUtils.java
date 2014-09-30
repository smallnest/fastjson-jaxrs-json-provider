package com.colobu.fastjson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IOUtils {
	private final static int BUFFER_SIZE = 4096;

	/**
	 * read a String from an InputStream object.
	 * @param in InputStream
	 * @return String
	 * @throws Exception
	 */
	public static String inputStreamToString(InputStream in) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = br.readLine()) != null) {
			buffer.append(line);
		}
		return buffer.toString();
	}
}
