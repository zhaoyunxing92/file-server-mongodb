package com.sunny.mongodb.file.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 工具类.
 * 
 * @since 1.0.0 2017年3月28日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
public class MD5Util {

	/** 
     * 获取该输入流的MD5值 
     *  
     * @param is 
     * @return 
     * @throws NoSuchAlgorithmException 
     * @throws IOException 
     */  
    public static String getMD5(InputStream is) throws NoSuchAlgorithmException, IOException {  
        StringBuilder md5 = new StringBuilder();
        MessageDigest md = MessageDigest.getInstance("MD5");  
        byte[] dataBytes = new byte[1024];  
          
        int nread = 0;   
        while ((nread = is.read(dataBytes)) != -1) {  
            md.update(dataBytes, 0, nread);  
        };  
        byte[] mdbytes = md.digest();  
          
        // convert the byte to hex format  
        for (byte mdbyte : mdbytes) {
            md5.append(Integer.toString((mdbyte & 0xff) + 0x100, 16).substring(1));
        }  
        return md5.toString();  
    }  

}
