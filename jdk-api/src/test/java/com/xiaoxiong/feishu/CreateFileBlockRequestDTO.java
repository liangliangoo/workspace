package com.xiaoxiong.feishu;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建文件块请求
 *
 * @author dongming
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateFileBlockRequestDTO {

    /** 权限 token */
    private String tenantAccessToken;

    /** 文档id（打开云文档最后一串字符）*/
    private String documentId;

    /** 块id（初始值为 文档id）*/
    private String blockId;

    /** 请求体（参考: https://open.feishu.cn/document/ukTMukTMukTM/uUDN04SN0QjL1QDN/document-docx/docx-v1/document-block/get）*/
    private JSONObject body;

}
