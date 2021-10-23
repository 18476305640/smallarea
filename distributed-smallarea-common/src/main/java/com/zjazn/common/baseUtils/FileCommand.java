package com.zjazn.common.baseUtils;
import java.io.File;

public class FileCommand {
    //根据文件路径删除文件
    public static Boolean delete(String file_path) {
        File file = new File(file_path);
        boolean delete = file.delete();
        return delete;
    }

}
