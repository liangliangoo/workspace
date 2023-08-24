package com.xiaoxiong.flink.constant;

/**
 * @Author 六月
 * @Date 2023/8/23 10:38
 * @Version 1.0
 */
public interface GoogleAdsDeliveryConstant {

    String accessible_customers_addr = "https://googleads.googleapis.com/v14/customers:listAccessibleCustomers";
    String ads_delivery_data_addr = "https://googleads.googleapis.com/v14/customers/%s/googleAds:searchStream";
    String refresh_token_addr = "https://www.googleapis.com/oauth2/v3/token";
    String developer_token = "WcnFLsqitzwP2WqKIACtcw";
    String client_id = "176739580177-36p5n21mfduclh2hausmadagf550t7qt.apps.googleusercontent.com";
    String client_secret = "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";
    String grant_type = "refresh_token";
    String refresh_token = "1//06D3kl2c1r4LjCgYIARAAGAYSNwF-L9IrYS8Z2rWdm-ZkUC_KygUIdz-pTnEh4nXAIs0gc2U7NAwcB9_CbSwiUQSmQJkNDvELnOM";
    String refresh_token_cache = "attr:googleAds:refreshToken";
    String sql = "SELECT campaign.name, campaign.id, ad_group.name, ad_group.id, ad_group_ad.ad.id, ad_group_ad.ad.name, metrics.clicks, metrics.conversions_value, metrics.cost_micros, metrics.impressions, customer.id, customer.descriptive_name, campaign.app_campaign_setting.app_id, campaign.app_campaign_setting.app_store FROM ad_group_ad WHERE segments.date = '%s'";

}
