package com.xiaoxiong.feishu;

import lombok.Data;

@Data
public class FeiShuUrl {

    /** 获取 tenant_access_token（企业自建应用） */
    public static final String tenant_access_token = "https://open.feishu.cn/open-apis/auth/v3/tenant_access_token/internal/";

    /** 获取群列表 */
    public static final String chat_list = "https://open.feishu.cn/open-apis/chat/v4/list";

    /** 发送消息卡片 */
    public static final String message_send = "https://open.feishu.cn/open-apis/message/v4/send/";

    /** 创建飞书云文档 */
    public static final String CREATE_FILE = "https://open.feishu.cn/open-apis/docx/v1/documents";

    /** 创建飞书云文档块 */
    public static final String CREATE_FILE_BLOCK = "https://open.feishu.cn/open-apis/docx/v1/documents/%s/blocks/%s/children";

    /** 多维表格字段 */
    public static final String BITABLE_FIELD = "https://open.feishu.cn/open-apis/bitable/v1/apps/%s/tables/%s/fields";

    /** 更新多维表格字段 */
    public static final String UPDATE_BITABLE_FIELD = "https://open.feishu.cn/open-apis/bitable/v1/apps/%s/tables/%s/fields/%s";

    /** 获取多维表格记录 */
    public static final String SHOW_BITABLE_RECORD = "https://open.feishu.cn/open-apis/bitable/v1/apps/%s/tables/%s/records";

    /** 获取多维表格指定记录 */
    public static final String SEARCH_BITABLE_RECORD = "https://open.feishu.cn/open-apis/bitable/v1/apps/%s/tables/%s/records/%s";

    /** 批量删除多维表格记录 */
    public static final String DELETE_BITABLE_RECORD = "https://open.feishu.cn/open-apis/bitable/v1/apps/%s/tables/%s/records/batch_delete";

    /** 批量新增多维表格记录 */
    public static final String ADD_BITABLE_RECORD = "https://open.feishu.cn/open-apis/bitable/v1/apps/%s/tables/%s/records/batch_create?user_id_type=%s";

    public static final String ADD_BITABLE_RECORD_ONE = "https://open.feishu.cn/open-apis/bitable/v1/apps/%s/tables/%s/records";

    /** 更新多维表格记录 */
    public static final String UPDATE_BITABLE_RECORD = "https://open.feishu.cn/open-apis/bitable/v1/apps/%s/tables/%s/records/%s";

    /** 获取视图信息 */
    public static final String SHOW_BITABLE_VIEW = "https://open.feishu.cn/open-apis/bitable/v1/apps/%s/tables/%s/views";

    /** 更新视图 */
    public static final String UPDATE_BITABLE_VIEW = "https://open.feishu.cn/open-apis/bitable/v1/apps/%s/tables/%s/views/%s";

    /** 发送消息（接收者支持类型 open_id/user_id/union_id/email/chat_id） */
    public static final String MESSAGE = "https://open.feishu.cn/open-apis/im/v1/messages?receive_id_type=%s";

    /** 回复消息 */
    public static final String REPLY_MESSAGE = "https://open.feishu.cn/open-apis/im/v1/messages/%s/reply";

    /** 完成任务 */
    public static final String COMPLETE_TASK = "https://open.feishu.cn/open-apis/task/v1/tasks/%s/complete";

    /** 加入 hook url */
    public static final String HOOK_URL = "https://open.feishu.cn/open-apis/bot/v2/hook/%s";

}
