package com.xiongliang.problemInvestigation.serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @Author 六月
 * @Date 2023/7/24 17:40
 * @Version 1.0
 */
public class Main {

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("clazzRoom", "clazz1");
        jsonObject.put("name", "1");
        jsonObject.put("age", "18");
        Student student = JSON.parseObject(jsonObject.toJSONString(), Student.class);
        System.out.println(student);
        System.out.println(com.alibaba.fastjson2.JSON.toJSON(student));
    }

}
