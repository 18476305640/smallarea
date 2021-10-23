package com.zjazn.common.baseUtils.vo;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;


public class JsonTemplate {
    private String userId;
    private String value;

    public JsonTemplate(String userId, String value) {
        this.userId = userId;
        this.value = value;
    }

    public  String gen() {
        JsonQ jsonQ = new JsonQ();

        ArrayList<JsonOption> jsonOptions = new ArrayList<>();
        JsonOption jsonOption0 = new JsonOption();
        jsonOption0.setQuestionId("stu1_q1");
        jsonOption0.setOptionId("");
        jsonOption0.setValue(this.value);
        jsonOptions.add(jsonOption0);

        for(int i=2; i<=9; i++) {
            JsonOption jsonOption1 = new JsonOption();
            jsonOption1.setQuestionId("stu1_q"+i);
            jsonOption1.setOptionId("");
            jsonOptions.add(jsonOption1);
        }
        for(int i=10; i<=12; i++) {
            JsonOption jsonOption2 = new JsonOption();
            jsonOption2.setQuestionId("stu1_q"+i);
            jsonOption2.setOptionId("stu1q"+i+"_o1");
            jsonOption2.setValue("无");
            jsonOptions.add(jsonOption2);
        }
            JsonOption jsonOption3 = new JsonOption();
            jsonOption3.setQuestionId("stu1_q20");
            jsonOption3.setOptionId("stu1q20_o1");
            jsonOption3.setValue("否");
            jsonOptions.add(jsonOption3);
        for(int i=13; i<=19; i++) {
            JsonOption jsonOption4 = new JsonOption();
            jsonOption4.setQuestionId("stu1_q"+i);
            jsonOption4.setOptionId("");

            jsonOptions.add(jsonOption4);
        }

        jsonQ.setType("0");
        jsonQ.setUserId(this.userId);
        jsonQ.setResultOptionCreateRequests(jsonOptions);
        return   JSON.toJSONString(jsonQ);



    }

}
