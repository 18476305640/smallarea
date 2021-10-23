package com.zjazn.uaa.common;

import java.util.Date;

public class IdGen {
    public static String get(Date date) {
        int min = 100;
        int max = 999;
        String number = (int)(Math.floor(Math.random() * (max - min + 1)) + min)+"";
        String idString = date.getTime()+"";
        return idString;
    }
    public static String get() {
        return get(new Date());

    }
    public static void main(String[] args) {
        System.out.println(IdGen.get());

    }
}
