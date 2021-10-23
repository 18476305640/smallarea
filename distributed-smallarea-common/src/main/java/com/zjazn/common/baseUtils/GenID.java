package com.zjazn.common.baseUtils;

import java.util.Date;

public class GenID {
    public static void main(String[] args) {
        for (int i =0;i<100;i++) {
            System.out.println(GenID.get());
        }
    }

    private static int number = 1;
    private static String tis = new Date().getTime()+"";
    public static String get( ) {
        String c = new Date().getTime()+"";
        if(c.equals(GenID.tis)) {

            return c+(++GenID.number+"");
        }else {
            GenID.tis=c;
            GenID.number=0;
            return c+"0";
        }

    }
}
