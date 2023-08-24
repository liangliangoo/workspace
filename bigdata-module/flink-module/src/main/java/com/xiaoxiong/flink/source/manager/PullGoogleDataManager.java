package com.xiaoxiong.flink.source.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xiaoxiong.flink.component.OkHttpClientUtil;
import com.xiaoxiong.flink.dto.GoogleAdsDeliveryDataDTO;
import com.xiaoxiong.flink.utils.OkHttpUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.collections.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.xiaoxiong.flink.constant.GoogleAdsDeliveryConstant.*;

/**
 * @Author 六月
 * @Date 2023/8/24 18:09
 * @Version 1.0
 */
@Slf4j
public class PullGoogleDataManager {

    private static final List<String> customers = Lists.newArrayList("7528323849", "7355973676");
    private OkHttpClient okHttpClient = OkHttpClientUtil.okHttpClient();

    public static void main(String[] args) {
        PullGoogleDataManager pullGoogleDataManager = new PullGoogleDataManager();
        System.out.println(pullGoogleDataManager.pullGoogleDeliveryData("2023-08-19"));
    }

    public List<GoogleAdsDeliveryDataDTO> pullGoogleDeliveryData(String dateStr) {
        String token = pullGoogleAdsToken();
        List<GoogleAdsDeliveryDataDTO> result = Lists.newArrayList();
        customers.forEach(item -> {
            String ret = pullAdsDeliveryData(String.format(sql, dateStr), token, item);
            result.addAll(parseJson(ret));
        });
        return result;
    }

    private String pullAdsDeliveryData(String sql, String token, String customerId) {
        String[] header = {"developer-token", developer_token, "Authorization", "Bearer " + token};
        JSONObject body = new JSONObject();
        body.put("query", sql);
        Request request = OkHttpUtil.buildPostRequest(String.format(ads_delivery_data_addr, customerId), body.toJSONString(), header);
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String result = response.body().string();
            System.out.println(result);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String pullGoogleAdsToken() {
        String refreshToken = refresh_token;
        Map<String, String> body = Maps.newHashMap();
        body.put("refresh_token", refreshToken);
        body.put("grant_type", grant_type);
        body.put("client_id", client_id);
        body.put("client_secret", client_secret);
        String ret = doPostJson(refresh_token_addr, JSON.toJSONString(body));
        log.info("pullGoogleAdsToken url {} body {} ret {}", refresh_token_addr, JSON.toJSONString(body), ret);
        JSONObject response = JSON.parseObject(ret);
        if (response.isEmpty()) {
            throw new RuntimeException("resonpse is null");
        }
        return response.getString("access_token");
    }

    private List<GoogleAdsDeliveryDataDTO> parseJson(String ret) {
        JSONArray jsonObject = JSON.parseArray(ret);
        if (Objects.isNull(jsonObject) || jsonObject.isEmpty()) {
            log.info("pullAdsDeliveryData fail");
            return Lists.newArrayList();
        }
        JSONObject json = jsonObject.toJavaList(JSONObject.class).get(0);
        JSONArray results = json.getJSONArray("results");
        if (CollectionUtils.isEmpty(results)) {
            return Lists.newArrayList();
        }
        return results.toJavaList(JSONObject.class).parallelStream().map(this::convertByJson).collect(Collectors.toList());
    }

    private GoogleAdsDeliveryDataDTO convertByJson(JSONObject json) {
        log.debug("convertByJson {}", json.toJSONString());
        GoogleAdsDeliveryDataDTO ret = GoogleAdsDeliveryDataDTO.builder().build();
        JSONObject campaign = json.getJSONObject("campaign");
        if (Objects.nonNull(campaign) && Objects.nonNull(campaign.getJSONObject("appCampaignSetting"))) {
            JSONObject appCampaignSetting = campaign.getJSONObject("appCampaignSetting");
            ret.setApp(appCampaignSetting.getString("appId"));
            ret.setPlatform(appCampaignSetting.getString("appStore"));
        }
        ret.setPartnerName("Google Ads");
        ret.setChannel("Google Ads");

        if (Objects.nonNull(campaign)) {
            ret.setCampaignName(campaign.getString("name"));
            ret.setCampaignId(campaign.getString("id"));
        }
        JSONObject adGroup = json.getJSONObject("adGroup");
        if (Objects.nonNull(adGroup)) {
            ret.setAdgroupName(adGroup.getString("name"));
            ret.setAdgroupId(adGroup.getString("id"));
        }
        JSONObject adGroupAd = json.getJSONObject("adGroupAd");
        if (Objects.nonNull(adGroupAd)) {
            JSONObject ad = adGroupAd.getJSONObject("ad");
            if (Objects.nonNull(ad)) {
                ret.setAdId(ad.getString("id"));
                ret.setAdName(ad.getString("name"));
            }
        }

        // 获取不到
        ret.setInstalls(0L);
        ret.setSkadInstalls(0L);

        JSONObject customer = json.getJSONObject("customer");
        if (Objects.nonNull(customer)) {
            ret.setAccountName(customer.getString("descriptiveName"));
            ret.setAccountId(customer.getString("id"));
        }

        JSONObject metrics = json.getJSONObject("metrics");
        if (Objects.nonNull(metrics)) {
            ret.setClicks(Optional.ofNullable(metrics.getLong("clicks")).orElse(0L));
            ret.setNetworkCost(Optional.ofNullable(metrics.getLong("costMicros")).orElse(0L));
            ret.setImpressions(Optional.ofNullable(metrics.getLong("impressions")).orElse(0L));
        }

        return ret;
    }

    public String doPostJson(String url, String json) {
        log.info("do post request and url[{}]", url);
        return exectePost(url, json, MediaType.parse("application/json; charset=utf-8"));
    }

    private String exectePost(String url, String data, MediaType contentType) {
        RequestBody requestBody = RequestBody.create(contentType, data);
        Request request = new Request.Builder().url(url).post(requestBody).build();

        return execute(request);
    }

    public String execute(Request request) {
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            log.error("error");
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return "";
    }
}
