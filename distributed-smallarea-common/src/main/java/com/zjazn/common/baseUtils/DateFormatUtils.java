package com.zjazn.common.baseUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {
    public static String DataToString(Date date,String format) {
        SimpleDateFormat sdf=new SimpleDateFormat(format);  //"yyyy-MM-dd HH:mm:ss"
        String DateformatString = sdf.format(date);
        return DateformatString;
    }
    public static void main(String[] args) {
        String s = DateFormatUtils.DataToString(new Date(), "yyyy-MM-dd");
        System.out.println(s);
    }
}
