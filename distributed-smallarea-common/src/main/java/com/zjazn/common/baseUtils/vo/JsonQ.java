package com.zjazn.common.baseUtils.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonQ {
    private String type;
    private String userId;
    private List<JsonOption> resultOptionCreateRequests;

}
