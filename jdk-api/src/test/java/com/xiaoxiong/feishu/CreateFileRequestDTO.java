package com.xiaoxiong.feishu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建文件请求
 *
 * @author dongming
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateFileRequestDTO {

    /** 权限 token */
    private String tenantAccessToken;

    /** 文件夹 token（打开文件夹最后一串字符） */
    private String folderToken;

    /** 标题 */
    private String title;

    /** 文件夹-folder；文档-docx；电子表格-sheet；多维表格-bitable */
    private String type;

}
