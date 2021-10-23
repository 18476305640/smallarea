package com.zjazn.common.common;

import sun.misc.BASE64Decoder;

import java.io.UnsupportedEncodingException;

public class Base64Utils {
    /**
     *
     * 将 s 进行 BASE64 编码
     * base64 编码、解码util
     * 解码   编码字符格式必须一致
     * @return String
     */
    public static String encode(String s) {
        if (s == null)
            return null;
        String res = "";
        try {
            res = new sun.misc.BASE64Encoder().encode(s.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 将 BASE64 编码的字符串 s 进行解码
     *
     * @return String
     */
    public static String decode(String s) {
        if (s == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b,"utf-8");
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        String c=Base64Utils.decode("eyJhdWQiOlsicmVzMSJdLCJ1c2VyX25hbWUiOiJ7XCJmdWxsbmFtZVwiOlwi566h55CG5ZGYXCIsXCJpZFwiOjEsXCJwYXNzd29yZFwiOlwiJDJhJDEwJHNaOHdHeDR5L3BtQjNYOEJBRmVsUy5qakdjNkxPMHg5bjFhc0dXL1hFT09lUDBhdXZhZEpxXCIsXCJ1c2VybmFtZVwiOlwicm9vdFwifSIsInNjb3BlIjpbIlJPTEVfQURNSU4iLCJST0xFX1VTRVIiLCJST0xFX0FQSSJdLCJleHAiOjE2MjM1NzU0MjUsImF1dGhvcml0aWVzIjpbInAxIiwicDMiXSwianRpIjoiMjU5MmMxZWItMjE0Zi00NzQxLTk1YjYtNDdmYmMxNTU1MGI3IiwiY2xpZW50X2lkIjoiYzEifQ");
        System.out.println(c);
    }


}