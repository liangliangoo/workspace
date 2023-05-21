package com.xiaoxiong.opposdk;

import com.omni.ads.baseconfig.OmniAds;
import com.omni.ads.baseconfig.ResultDto;
import com.omni.ads.exception.OmniAdsResponseException;
import com.omni.ads.model.adscommunal.AdsConfigReq;
import com.omni.ads.model.adscommunal.AdsConfigResp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 六月
 * @Date 2023/5/17 12:22
 * @Version 1.0
 */
public class V3CommunalConfigGetList {

    //定义omniAds
    public OmniAds omniAds;
    //注意：owner_id是Long类型，传入的参数结尾要加“L”
    private final static Long OWNER_ID = 1000185183L;
    //API授权接入方唯一身份标识；
    private final static String API_ID = "0b5192625c794244b06e5f65263b81a1";
    //开通API授权后获得的私钥，在开通授权邮件中会与app_id一块提供；
    private final static String API_KEY = "00d2e9450ffa4518961b4bd28e115065";
    //重要提示：返回类型暂定ResultDto<Object>，该返回类型由omniAds.adsCommunal().v3CommunalConfigGetList(data)决定，调用该方法后获取返回类型再进行修改
    public ResultDto<List<AdsConfigResp>> getConfigList() throws Exception {
        OmniAds omniAds = new OmniAds(OWNER_ID, API_ID, API_KEY);
        // 默认使用沙箱环境(测试环境)
        omniAds.useSandbox();
        //线上环境
        //omniAds.useProduction();
        // debug==true 会打印请求详细信息
        omniAds.setDebug(true);
        //传递参数分为4种情况：
        //1.传递对象参数---接口备注详情里标注了需要实例化的传参对象，通过data.setxxx 设置必要参数, 详情见文档
         AdsConfigReq data = new AdsConfigReq();
        data.setType("EXTENSION_TYPE");
        //2.map动态传递参数---文档里未标注实例化的传参对象则用map传递，通过map.put()设置必要参数，详情见文档
         Map<String,Object> map = new HashMap();
        map.put("type","EXTENSION_TYPE");
        //3.字段传参，详情见文档
        String type = "EXTENSION_TYPE";
        //4.不传参
        //访问接口，每个功能点对应不同的集群，接口备注详情里都有标注
        ResultDto<List<AdsConfigResp>> listResultDto = omniAds.adsCommunal().v3CommunalConfigGetList(data);
        return listResultDto;
    }
    public static void main(String[] args) {
        try {
            //实例化调用类
            V3CommunalConfigGetList entity = new V3CommunalConfigGetList();
            //重要提示：类型暂定ResultDto<Object>，该返回类型由omniAds.adsCommunal().v3CommunalConfigGetList(data)决定，调用该方法后获取返回类型再进行修改
            System.out.println(entity.getConfigList());
            //response.getData();获取响应data数据
        } catch (OmniAdsResponseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
