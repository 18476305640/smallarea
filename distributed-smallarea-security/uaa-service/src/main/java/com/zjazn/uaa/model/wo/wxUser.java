package com.zjazn.uaa.model.wo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class wxUser {
    private String session_key;
    private String openid;
}
