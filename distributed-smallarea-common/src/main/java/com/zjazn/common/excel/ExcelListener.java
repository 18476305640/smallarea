package com.zjazn.common.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

public class ExcelListener<T> extends AnalysisEventListener<T> {



    //容器
    public static List<Object> list=new ArrayList<>();

    //每读取一行，都调用该方法
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        //将行数据加入容器
        list.add(t);
    }

    //读取完成后的回调
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("读取完成！");
    }


}
