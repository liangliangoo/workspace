package com.xiaoxiong.feishu;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author 六月
 * @Date 2023/7/17 14:22
 * @Version 1.0
 */
public class FeiShuTests {

    private ExecutorService executorService = new ThreadPoolExecutor(10, 200, 1000L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

    @Test
    public void getToken() {
        System.out.println(FeiShuUtil.getDataMiddleAccessToken("cli_a3c8756f05f8d013", "VnHF27D55ilxKJK4srC8pcY1q3Rl2FV3"));
    }

    @Test
    public void syncData2BitableTest() {
        ArrayList<String> list = Lists.newArrayList("coudui-services", "im-services","family-services", "mmc-services", "mission-services", "polaris-services", "room-services", "octopus-services");
        list.forEach(str -> syncErrorCode2Bitable("IS3vbvROeafk5XsxEi8cjKAQnOg", "tblx5HrDudrCe3Yw", str));

    }


    private void syncErrorCode2Bitable(String tableToken, String tableId, String services) {
        String token = FeiShuUtil.getDataMiddleAccessToken("cli_a3c8756f05f8d013", "VnHF27D55ilxKJK4srC8pcY1q3Rl2FV3");
        BitableRequestDTO recordRequest = BitableRequestDTO.builder().
                tenantAccessToken(token).appToken(tableToken).tableId(tableId)
                .build();
        for (ErrorCode value : ErrorCode.values()) {
            recordRequest.setParam(buildParam(value.name(), services));
            String result = FeiShuUtil.addBitableRecordOne(recordRequest);
            System.out.println(result);
        }

    }

    private JSONObject buildParam(String column1,String column2) {
        JSONObject fields = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("业务异常", column1);//
        data.put("服务", column2);
        fields.put("fields", data);
        return fields;
    }


}

