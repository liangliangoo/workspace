package com.xiaoxiong.oppo;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Objects;

import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Base64.encodeBase64String;

/**
 * @Author 六月
 * @Date 2023/1/30 14:11
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Builder
@Data
public class OppoUserDataVO {

    /**
     * imei
     */
    private String imei;
    /**
     * 即 oaId
     */
    private String ouId;
    /**
     * 客户端mac地址 可不填
     */
    private String mac;
    /**
     * 客户端IP地址 可不填
     */
    private String clientIp;
    /**
     * 时间发生时间戳 ms
     */
    private Long timestamp;
    /**
     * 投放应用的报名 or 快应用id
     */
    private String pkg;
    /**
     * 转化数据类型：
     * 1、激活，2、注册，3、游戏付费，
     * 4、次留，5、应用内授信，6、应用
     * 内下单（电商），7、应用付费 8、
     * 自定义目标， 9、第 3 日留存 10、 第 4 日留存， 11、第 5 日留存 12、 第 6 日留存， 13、第 7 日留存，
     * 14、第 8 日留存，15、拉活，16、
     * 快应用付费，17、次留（DP）18、
     * 卸载激活 19、快应用关键行为
     * 20、游戏关键行为
     */
    private Integer dataType;
    /**
     * 自定义目标类型：dataType 填了 8
     * 之后补充，枚举值与客户沟通后补充  可不填
     */
    private Integer customType;
    /**
     * 渠道：1、OPPO，2、一加，0、其他
     */
    private Integer channel;
    /**
     * 0：无加密（默认为 0） 1：imei md5 加密 2：oaid md5 加密
     */
    private Integer type;
    /**
     * 应用类别：1 应用（默认值） 2 游戏 3 快应用 0 其他  可不填
     */
    private Integer appType;
    /**
     * 付费金额 单位：分  可不填
     */
    private Long payAmount;
    /**
     * 归因类型：1：广告主归因，0：OPPO 归因（默认或者不填即为 0），2：助攻归因
     */
    private Integer ascribeType;
    /**
     * 广告主回传转化数据时，附带已经归因好的广告 id
     */
    private Long adId;
    /**
     * 请求ID  可不填
     */
    private String requestId;

    /**
     * json转 OppoUserDataVo 并对相关数据加密
     *
     * @param jsonStr json
     * @param key     base64加密的key
     * @return
     */
    public static OppoUserDataVO convertToVo(String jsonStr, String key, Boolean isAdvertisers) {
        OppoUserDataVO userDataVO = new OppoUserDataVO();
        JSONObject json = JSONObject.parseObject(jsonStr);
        userDataVO.setChannel(1);

        if (StringUtils.isBlank(json.getString("oaId")) &&
            StringUtils.isBlank(json.getString("imei"))) {
            return null;
        }

        //加密
        userDataVO.setImei(json.getString("imei"));
        if (StringUtils.isBlank(userDataVO.getImei())) {
            String ouid = encodeByBase64(json.getString("oaId"), key);
            if (StringUtils.isNotBlank(ouid)) {
                userDataVO.setType(2);
                userDataVO.setOuId(ouid);
            } else {
                return null;
            }
        } else {
            // imei = 0 特殊处理
            if (userDataVO.getImei().equalsIgnoreCase("0")) {
                return null;
            }
            // 加密 imei
            String imei = encodeByBase64(userDataVO.getImei(), key);
            //String imei = userDataVO.getImei();
            if (StringUtils.isNotBlank(imei)) {
                userDataVO.setType(0);
                userDataVO.setImei(imei);
            } else {
                return null;
            }
        }

        userDataVO.setTimestamp(System.currentTimeMillis());
        userDataVO.setPkg(json.getString("packageName"));
        userDataVO.setDataType(json.getInteger("dataType"));
        if (Boolean.TRUE.equals(isAdvertisers)) {
            userDataVO.setAscribeType(1);
            tryFilledAdid(userDataVO, json);
            //log.info("广告 归因 userDataVo {}", JSON.toJSONString(userDataVO));
        } else {
            userDataVO.setAscribeType(0);
        }
        if (StringUtils.isNotBlank(json.getString("payAmount"))) {
            userDataVO.setPayAmount(Long.parseLong(json.getString("payAmount")));
        }
        return userDataVO;
    }

    /**
     * base64加密 imei or ouid
     *
     * @param data
     * @param key  base64 加密的key
     * @return
     */
    public static String encodeByBase64(String data, String key) {
        try {
            final Key dataKey = new SecretKeySpec(decodeBase64(key),
                "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, dataKey);
            byte[] encryptData = cipher.doFinal(data.getBytes());
            return encodeBase64String(encryptData).replaceAll("\r",
                "").replaceAll("\n", "");
        } catch (Exception e) {
            log.error("encodeByBase64 异常:{}", e);
        }
        return null;
    }

    private static void tryFilledAdid(OppoUserDataVO userDataVO, JSONObject params) {
        if (Objects.isNull(userDataVO) || Objects.isNull(params)) {
            return;
        }
        if (StringUtils.isNotBlank(params.getString("adverID")) &&
                StringUtils.isNumeric(params.getString("adverID"))) {
            userDataVO.setAdId(Long.parseLong(params.getString("adverID")));
        }
    }

}
