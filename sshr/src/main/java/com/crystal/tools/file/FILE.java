package com.crystal.tools.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class FILE {

	public static FileType checkFileType(InputStream inputStream)throws Exception {

		byte[] src = new byte[28];
		inputStream.read(src, 0, 28);
		inputStream.close();

		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		String fileHead = stringBuilder.toString();

		if (fileHead == null || fileHead.length() == 0) {
			return FileType.NONE;
		}

		fileHead = fileHead.toUpperCase();
		FileType[] fileTypes = FileType.values();

		for (FileType type : fileTypes) {
			if (fileHead.startsWith(type.getValue())) {
				return type;
			}
		}

		return FileType.NONE;
	}
	
	public static void main(String[] args) throws Exception {
		checkFileType(new FileInputStream(new File("E:\\IMG\\test.HEIC")));
	}
}
