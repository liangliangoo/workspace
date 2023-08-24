package com.xiaoxiong.flink.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *
 * @Author 六月
 * @Date 2023/8/23 15:10
 * @Version 1.0
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GoogleAdsDeliveryDataDTO {

    /** 应用名 */
    private String app;
    /** 推广渠道 */
    private String partnerName;
    /** 活动名 */
    private String campaignName;
    /** 活动id */
    private String campaignId;
    /** 广告组名称 */
    private String adgroupName;
    /** 广告组id */
    private String adgroupId;
    /** 广告名 */
    private String adName;
    /** 广告id */
    private String adId;
    /** 推广渠道 */
    private String channel;
    /** 国家代码 */
    private String countryCode;
    /** 广告消耗 */
    private Long networkCost;
    private Long impressions;
    private Long clicks;
    private Long installs;
    private Long skadInstalls;
    private Double revenue;
    private String platform;
    private String trAdmaster;
    private String accountAbbreviations;
    private String accountName;
    private String accountId;
    private Date recordDate;
    private String accountType;
    private String agentName;
    private String accountOwner;

}
