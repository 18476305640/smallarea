package com.zjazn.product.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmallTypeDesc {
    private String id;
    private String cover;
    private String name;
    private String parentId;
}
