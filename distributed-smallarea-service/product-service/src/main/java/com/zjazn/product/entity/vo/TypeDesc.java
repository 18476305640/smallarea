package com.zjazn.product.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeDesc {
    private String id;
    private String name;

    //一个一级分类有多个二级分类
    private List<SmallTypeDesc> children = new ArrayList<>();

}
