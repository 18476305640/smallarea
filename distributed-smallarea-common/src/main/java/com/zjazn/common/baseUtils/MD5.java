package com.zjazn.common.baseUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5 {  

    public static String encrypByMd5(String context) {  
        try {  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            md.update(context.getBytes());//update����  
            byte [] encryContext = md.digest();//���ø÷�����ɼ���  
  
            int i;  
            StringBuffer buf = new StringBuffer("");  
            for (int offset = 0; offset < encryContext.length; offset++) {//����Ӧ��ת����ʮ�����ƣ�  
                i = encryContext[offset];  
                if (i < 0) i += 256;  
                if (i < 16) buf.append("0");  
                buf.append(Integer.toHexString(i));  
           }  
           String code32=buf.toString();// 32λ�ļ���  
           return code32;
        } catch (NoSuchAlgorithmException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }
        return null;
    }  

    public static String getDepthCode(String code) {
    	String eet=encrypByMd5(encrypByMd5(code)).substring(2,12);
    	return  eet;
    
    }

    public static void main(String[] args) {  
        MD5 md5 = new MD5();
        String jcode="zjazn";
        String tt=MD5.encrypByMd5(jcode);


    }  
}  