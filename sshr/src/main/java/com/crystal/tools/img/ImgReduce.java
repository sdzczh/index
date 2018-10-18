package com.crystal.tools.img;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
/**
 * @描述 图片压缩<br>
 * @author 赵赫
 * @版本 v1.0.0
 * @日期 2017-8-11
 */
public class ImgReduce {
	
	private ImgReduce(){}
	
	/** 
     * 按压缩比例 的方式对图片进行压缩 
     * @param imgsrc 源图片地址 
     * @param imgdist 目标图片地址 
     * @param rate 压缩比例  
	 * @throws Exception 
	 * @author 赵赫
	 * @版本 v1.0.0
	 * @日期 2017-8-11
     */  
    public static void reduceImg(String imgsrc, String imgdist, Float rate) throws Exception {  
    	int width = 0;
    	int height = 0;
            File srcfile = new File(imgsrc);  
            if (rate != null && rate > 0) {  
                int[] results = getImgWidth(srcfile);  
            	width = (int) (results[0] * rate);  
            	height = (int) (results[1] * rate);  
            }  
            Image src = javax.imageio.ImageIO.read(srcfile);  
            BufferedImage tag = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);  
            tag.getGraphics().drawImage(src.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);  
            FileOutputStream out = new FileOutputStream(imgdist);  
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
            encoder.encode(tag);  
            out.close();  
    }  

    /**
	 * @描述 获取图片宽高<br>
	 * @param file 文件
	 * @return 宽高数组[0]:宽 [1]:高
	 * @author 赵赫
	 * @版本 v1.0.0
	 * @日期 2017-8-11
	 */
    public static int[] getImgWidth(File file) {  
        InputStream is = null;  
        BufferedImage src = null;  
        int result[] = { 0, 0 };  
        try {  
            is = new FileInputStream(file);  
            src = javax.imageio.ImageIO.read(is);  
            result[0] = src.getWidth(null); // 得到源图宽  
            result[1] = src.getHeight(null); // 得到源图高  
            is.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    } 
    
    /**
     * @描述 方法测试<br>
     * @throws Exception
     * @author 赵赫
     * @版本 v1.0.0
     * @日期 2017-8-11
     */
    public static void test() throws Exception {  
        System.out.println("压缩图片开始...");  
        File srcfile = new File("E:/a.jpg");  
        System.out.println("压缩前srcfile size:" + srcfile.length());  
        reduceImg("E:/a.jpg", "E:/"+System.currentTimeMillis()+".jpg",0.2f);  
        File distfile = new File("E:/a_reduce.jpg");  
        System.out.println("压缩后distfile size:" + distfile.length());  
      
    }  
    
    public static void main(String[] args) throws Exception {
		test();
	}
}
