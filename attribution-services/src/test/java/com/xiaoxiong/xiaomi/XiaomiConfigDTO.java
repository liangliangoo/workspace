package com.xiaoxiong.xiaomi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 *  * 【自定义激活】
 *  * 查看转化跟踪
 *  * 应用:ID: 1217072名称: 暖聊-同城单身男女交友
 *  * 转化方案:API上报方案
 *  * 秘钥A(encrypt_key):LiFCamIWrVlPflix
 *  * 秘钥B(sign_key):rrjomIplxfZkTbhG
 *  *
 *  * 【自定义注册】
 *  * 查看转化跟踪
 *  * 应用:ID: 1217072名称: 暖聊-同城单身男女交友
 *  * 转化方案:API上报方案
 *  * 秘钥A(encrypt_key):vxktbjhhUvFEJQea
 *  * 秘钥B(sign_key):tHAdevcKaIBrBxaZ
 *  *
 *  * 【自定义留存】
 *  * 查看转化跟踪
 *  * 应用:ID: 1217072名称: 暖聊-同城单身男女交友
 *  * 转化方案:API上报方案
 *  * 秘钥A(encrypt_key):YScuOgBWlNwEeAzJ
 *  * 秘钥B(sign_key):MurlaQEgahHarAQC
 *  *
 *  * 【付费】--付费次数接口
 *  * 查看转化跟踪
 *  * 应用:ID: 1217072名称: 暖聊-同城单身男女交友
 *  * 转化方案:API上报方案
 *  * 秘钥A(encrypt_key):FQqMxDDOqbXpXjir
 *  * 秘钥B(sign_key):zfUHsuBCrrOesSrx
 *  *
 *  * 【首次付费】--付费人数接口
 *  * 查看转化跟踪
 *  * 应用:ID: 1217072名称: 暖聊-同城单身男女交友
 *  * 转化方案:API上报方案
 *  * 秘钥A(encrypt_key):WNVeJSUCZCBmQOGi
 *  * 秘钥B(sign_key):FJdtBglnaMRdfAAF
 * @Author 六月
 * @Date 2023/6/14 14:32
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class XiaomiConfigDTO {

    /** 归因事件 类型 1：激活；2注册；7、付费 */
    private Integer dataType;
    private Integer applicationId;
    private Integer customerId;
    private String encryptKey;
    private String signKey;

    public static void main(String[] args) {
        XiaomiConfigDTO build1 = XiaomiConfigDTO.builder().dataType(1).applicationId(1217072).customerId(433472).encryptKey("LiFCamIWrVlPflix").signKey("rrjomIplxfZkTbhG").build();
        XiaomiConfigDTO build2 = XiaomiConfigDTO.builder().dataType(2).applicationId(1217072).customerId(433472).encryptKey("vxktbjhhUvFEJQea").signKey("tHAdevcKaIBrBxaZ").build();
        XiaomiConfigDTO build3 = XiaomiConfigDTO.builder().dataType(7).applicationId(1217072).customerId(433472).encryptKey("FQqMxDDOqbXpXjir").signKey("zfUHsuBCrrOesSrx").build();
        System.out.println(JSON.toJSONString(Lists.newArrayList(build1, build2, build3)));

        String extData = "{\"uploadConfig\":[{\"applicationId\":1217072,\"customerId\":433472,\"dataType\":1,\"encryptKey\":\"LiFCamIWrVlPflix\",\"signKey\":\"rrjomIplxfZkTbhG\"},{\"applicationId\":1217072,\"customerId\":433472,\"dataType\":2,\"encryptKey\":\"vxktbjhhUvFEJQea\",\"signKey\":\"tHAdevcKaIBrBxaZ\"},{\"applicationId\":1217072,\"customerId\":433472,\"dataType\":7,\"encryptKey\":\"FQqMxDDOqbXpXjir\",\"signKey\":\"zfUHsuBCrrOesSrx\"}]}";

        JSONObject jsonObject = JSON.parseObject(extData);
        JSONArray uploadConfig = jsonObject.getJSONArray("uploadConfig");
        List<XiaomiConfigDTO> xiaomiConfigDTOS = uploadConfig.toJavaList(XiaomiConfigDTO.class);
        System.out.println(JSON.toJSONString(xiaomiConfigDTOS));
    }

}
