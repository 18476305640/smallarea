package com.zjazn.common.baseUtils;


import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FUU {
    /*
    * 文件上传工具类

    相关application.properties配置 !!!：
    ### 表示所有的访问都经过静态资源路径
    spring.mvc.static-path-pattern=/**
    ### 覆盖默认静态资源路径，最后加上自己的路径，这样上传后，就可以访问到
    spring.web.resources.static-locations=classpath:/META-INF/resources/,classpath:/resources/,\
    classpath:/static/,classpath:/public/,classpath:/image/

    # Single file max size  即单个文件大小
    spring.servlet.multipart.max-file-size=1024MB
    # All files max size      即总上传的数据大小
    spring.servlet.multipart.max-request-size=1024MB

    * */


    public static int ix = 0;
    public static Long dx = new Date().getTime();
    //获取codeResetTime毫秒内的唯一码
    public static String getI(Integer codeResetTime) {
        if ((new Date().getTime()-dx)>codeResetTime) {
            ix=0;
            dx = new Date().getTime();
        }
        return (ix++)+"";
    }
    //获取ClashPath在系统中的绝对路径
    public static String getClassPath() throws FileNotFoundException {
        return  ResourceUtils.getURL("classpath:").getPath();
    }
    //获取ClashPath下的目录在系统中的绝对路径
    public static String getClassPath(String downDir) throws FileNotFoundException {
        return  ResourceUtils.getURL("classpath:").getPath()+downDir+File.separator;
    }
    //根据Data+时间格式（yMd Hms）生成指定格式的时期字符串
    public static String getHasFormatDate(String dateFormat,Date date){
        return new SimpleDateFormat(dateFormat).format(date);

    }
    //检查是否有指定路径，没有则生成（目录）
    public static Boolean checkGenPath(String path) {
        try {
            File file = new File(path);
            if(! file.exists()){
                file.mkdirs();
            }
        }catch (Exception e) {
            return false;
        }
        return true;
    }

    //获取文件后缀名
    public static String getFileSuffixName(String fileAllName) {
        int i = fileAllName.lastIndexOf(".");
        String suffix = fileAllName.substring(i,fileAllName.length());
        return suffix;
    }
    //获取指定范围内的随机整数
    public static String getRangeRandomInt(Integer integer) {
        Random random = new Random();
        return random.nextInt(integer+1)+"";
    }
    //根据MultipartFile文件对象--classPathDownDir要保存在classPath哪个目录下--dateFormat时间格式--codeResetTime（codeResetTime秒重置一下码值，如果<1000，默认就1050，这是因为<1000可能出现重复值 ）
    public static String fuu(MultipartFile file,String classPathDownDir,String dateFormat,Integer codeResetTime) throws FileNotFoundException {

        String fileName = FUU.getHasFormatDate(dateFormat,new Date())
                +FUU.getI(codeResetTime>1000?1050:codeResetTime)
                +FUU.getFileSuffixName(file.getOriginalFilename());
        String path =FUU.getClassPath(classPathDownDir)+fileName;
        if (FUU.checkGenPath(path) && !file.isEmpty()) {
            try {
                // 转存文件
                file.transferTo(new File(path));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }
    //根据MultipartFile文件对象--classPathDownDir要保存在classPath哪个目录下--dataFormat时间格式--codeResetTime（codeResetTime秒重置一下码值，如果<1000，默认就1050，这是因为<1000可能出现重复值 ）
    public static String up(MultipartFile file,String up_path,String dateFormat,Integer codeResetTime) throws FileNotFoundException {

        String fileName = FUU.getHasFormatDate(dateFormat,new Date())
                +FUU.getI(codeResetTime>1000?1050:codeResetTime)
                +FUU.getFileSuffixName(file.getOriginalFilename());

        String path =up_path+File.separator+fileName;
        if (FUU.checkGenPath(path) && !file.isEmpty()) {
            try {
                // 转存文件
                file.transferTo(new File(path));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }
}
