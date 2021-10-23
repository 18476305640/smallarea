package com.zjazn.common.baseUtils.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonOption {
    private String questionId;
    private String optionId;
    private String value;
}
