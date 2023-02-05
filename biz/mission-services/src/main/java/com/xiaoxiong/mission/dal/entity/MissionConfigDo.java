package com.xiaoxiong.mission.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;

/**
 * 任务配置表
 * @Author 六月
 * @Date 2023/1/29 18:37
 * @Version 1.0
 */
@Builder
@TableName("t_mission_config")
public class MissionConfigDo extends BaseDo{

    /** 任务类型
     * com.xiaoxiong.mission.enums.MissionTypeEnum
     * */
    private String missionType;
    /** 完成阈值 */
    private Long targetValue;
    /** 奖励类型
     * com.xiaoxiong.mission.enums.PrizeTypeEnum
     * */
    private String prizeType;
    /** 奖励具体值 金币、积分、礼物key etc */
    private String prizeValue;
    /** 奖励数量 */
    private Long prizeNums;
    /** 可完成最大次数 */
    private Long limitTimes;
    /** 是否自动下发 */
    private Boolean autoReceive;
    /** 跳转路由 */
    private String router;
    /** 图片标 */
    private String icon;
    /** 所属业务 */
    private String bizKey;
    /** 状态 */
    private String status;
    /** 分组 */
    private String groupId;
    /** 优先级 */
    private Integer priority;
    /** 扩展数据 */
    private String extData;

}
