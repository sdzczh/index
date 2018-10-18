package com.crystal.tools.img;

import java.io.File;

/**
 * @描述 图片检测<br>
 * @author 赵赫
 * @版本 v1.0.0
 * @日期 2017-8-11
 */
public class ImgCheck {

	private ImgCheck(){};
	
	/**
	 * @描述 检测文件是否超出指定大小<br>
	 * @param filePath 文件地址
	 * @param accessLen 安全值byte 1m=1024kb 1kb=1024byte
	 * @return true:超出 false:安全
	 * @author 赵赫
	 * @版本 v1.0.0
	 * @日期 2017-8-11
	 */
	public static boolean checkAccessLength(String filePath,long accessLen){
		File file = new File(filePath);
		long length = file.length();
		if(length>accessLen){
			return true;
		}
		return false;
	}
}
