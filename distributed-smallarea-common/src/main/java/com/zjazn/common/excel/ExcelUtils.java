package com.zjazn.common.excel;

import com.alibaba.excel.EasyExcel;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ExcelUtils<T> {

    /*
    * 使用示例：
    *
    *  List<Student> students= new ExcelUtils<Student>().readExcel(file, Student.class);
    * */
    public List<T> readExcel(MultipartFile file,Class t) throws IOException {
        ExcelListener<T> targetBean = new ExcelListener<T>();
        //工作薄对象
        EasyExcel.read(file.getInputStream(), t,new ExcelListener<T>()).sheet().doRead();
        return (List<T>) targetBean.list;
    }
    public List<T> readExcel(File file,Class t) throws IOException {
        ExcelListener<T> targetBean = new ExcelListener<T>();
        //工作薄对象
        EasyExcel.read(file, t,new ExcelListener<T>()).sheet().doRead();
        return (List<T>) targetBean.list;
    }
    public List<T> readExcel(String filePath,Class t) throws IOException {
        ExcelListener<T> targetBean = new ExcelListener<T>();
        //工作薄对象
        EasyExcel.read(filePath, t,new ExcelListener<T>()).sheet().doRead();
        return (List<T>) targetBean.list;
    }

    /*
    * 写操作
    * 使用示例：
    * new ExcelUtils<Student>().writerExcel("C:\\学生表.xlsx",Student.class,students);
     * */
    public void writerExcel(String createPath,Class t,List<T> list) {
        EasyExcel.write(createPath, t).sheet().doWrite(list);
    }
    public void writerExcel(File file,Class t,List<T> list) {
        EasyExcel.write(file, t).sheet().doWrite(list);
    }
}
