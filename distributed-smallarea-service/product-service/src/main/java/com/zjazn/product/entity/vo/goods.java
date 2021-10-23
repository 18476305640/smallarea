package com.zjazn.product.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class goods implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String store_id;
    private String name;
    private String describe;
    private BigDecimal price;
    private String cover;
    private Float star;



}
