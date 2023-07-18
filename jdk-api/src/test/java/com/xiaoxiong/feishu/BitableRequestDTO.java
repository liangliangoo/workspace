package com.xiaoxiong.feishu;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 多维表格请求
 *
 * @author dongming
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BitableRequestDTO {

    /** 权限 token */
    private String tenantAccessToken;

    /** 多维表格 token */
    private String appToken;

    /** 多维表格 tableId */
    private String tableId;

    /** 字段id */
    private String fieldId;

    /** 视图id */
    private String viewId;

    /** 记录id */
    private String recordId;

    /** api 中使用的用户id类型 user_id/open_id/union_id 默认 open_id */
    private String userIdType;

    /** 参数 */
    private JSONObject param;

}
