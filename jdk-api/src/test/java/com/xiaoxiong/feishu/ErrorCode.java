package com.xiaoxiong.feishu;

/**
 * @author yanlv
 * @version 0.1 : ErrorCode v0.1 2017/11/24 下午2:26 yanlv Exp $
 */
public enum ErrorCode {

    /**
     * 错误码配置
     */
    ACCESS_TOKEN_EXPIRED("1000", "common.error.code.1000"),
    NEED_LOGIN("1001", "common.error.code.1001"),
    SUCCESS("000000", "success"),
    SYSTEM_EXCEPTION("000001", "common.error.code.000001"),
    FLOW_CONTROL("987654", "common.error.code.987654"),

    /*******************************系统层面异常 100000 保留***********************************/

    //服务器异常
    SERVER_EXCEPTION("100000", "common.error.code.100000"),

    //无效参数
    INVALID_PARAM("100001", "common.error.code.100001"),

    //缺少参数
    MISS_PARAM("100002", "common.error.code.100002"),

    APP_KEY_EXCEPTION("100003", "common.error.code.100003"),

    APP_SECRET_EXCEPTION("100004", "common.error.code.100004"),
    APP_NEED_LOGIN("100005", "common.error.code.100005"),

    SMS_SEND_FAILED("100006", "common.error.code.100006"),

    NO_PERMISSIONS("188888", "common.error.code.188888"),
    MQ_SEND_ERROR("100007", "common.error.code.100007"),
    SYSTEM_BLACKED("100008", "common.error.code.100008"),
    SYSTEM_FUNCTION_MAINTENANCE("100009", "common.error.code.100009"),
    OPEN_FEIGN_REMOTE_REQUEST_FAIL("100010", "common.error.code.100010"),
    MQ_SEND_FAIL("100011", "common.error.code.100011"),
    EXCEPTION_DELIVERY_ERROR("100012", "common.error.code.100012"),
    //ios 弹出弹框提醒
    EXCEPTION_ERROR_ALTER("100013", "common.error.code.100013"),
    // 提现封禁，客户端弹出封禁弹框，服务端自动3s后封禁
    WITHDRAW_NEED_BLOCKED("100014", "common.error.code.100014"),
    // 无接口访问权限
    NO_URL_PERMISSION("100015", "common.error.code.100015"),
    //重复点击提示
    REPEAT_SUBMIT("100016", "common.error.code.100016"),
    //封禁设备失败
    BLOCK_DEVICE_FAILED("100017", "common.error.code.100017"),
    // 直接退出应用
    EXIT_APP("100018", "退出应用"),

    ///22     im

    //im创建失败
    USER_REGISTER_ERROR("200000", "common.error.code.200000"),
    TOKEN_LOGIN_WRONG("200001", "common.error.code.200001"),
    IM_TOKEN_ALREAY_REGISTER("200002", "common.error.code.200002"),
    IM_TOKEN_ACCID_WRONG("200003", "common.error.code.200003"),
    APP_IM_KEY_SECRET_MISSED("200004", "common.error.code.200004"),
    IM_CALLBACK_NOT_EXISTS("200005", "common.error.code.200005"),
    IM_CALLBACK_INVALID("200006", "common.error.code.200006"),
    IM_CHATINFO_CREATE_FAIL("200007", "common.error.code.200007"),
    IM_REQUEST_NIM_ERROR("200008", "common.error.code.200008"),
    IM_CONV_DELETE_PERMISSION("200009", "common.error.code.200009"),
    NIM_ALREAY_REGIST("200010", "common.error.code.200010"),
    IM_CHAT_NOT_EXIST("200011", "common.error.code.200011"),
    ACCOST_FAIL_BLACK_LIST("200012", "common.error.code.200012"),
    RISK_SCAN_REQUEST_IS_NULL("200013", "common.error.code.200013"),
    GET_RISK_SCAN_RESULT_PARAM_IS_NULL("200014", "common.error.code.200014"),
    IM_RISK_CONTROL("200015", "common.error.code.200015"),
    IM_POP_MESSAGE_PARAM_IS_NULL("200016", "common.error.code.200016"),
    IM_UID_EQUALS_TO_UID("200017", "common.error.code.200017"),
    IM_APP_NOT_EXIST("200018", "common.error.code.200018"),
    IM_RISK_DEL_MSG_ONE_WAY("20019", "common.error.code.200015"),

    //user
    USER_NOT_EXIST("300000", "common.error.code.300000"),
    JWT_INVALID("300001", "common.error.code.300001"),
    PHONE_CODE_ERRPR("300002", "common.error.code.300002"),
    JWT_TOKEN_EXPIRE("300004", "common.error.code.300004"),
    REFRESH_TOKEN_INVALID("300005", "common.error.code.300004"),
    TWO_TOKEN_INVALID("300006", "common.error.code.300006"),
    PROPERTY_PRESENTATION_ERROR("300007", "common.error.code.300007"),
    SUBSCRIBE_USER_ERROR("300008", "common.error.code.300008"),
    BLACK_USER_ERROR("300009", "common.error.code.300009"),
    REAL_VERIFY_RESULT_ERROR("300010", "common.error.code.300010"),
    REAL_VERIFY_RESULT_FAIL("300011", "common.error.code.300011"),
    BLACK_USER_ID_IS_NULL("300012", "common.error.code.300012"),
    FAIL_TO_GET_IP("300013", "common.error.code.300013"),
    FAIL_TO_GET_DEVICE_ID("300014", "common.error.code.300014"),
    LOGIN_WECHAT_ERROR("300015", "common.error.code.300015"),
    LOGIN_WECHAT_OPENID_ERROR("300016", "common.error.code.300016"),
    LOGIN_WECHAT_INFO_ERROR("300017", "common.error.code.300017"),
    CALCULATE_DISTANCE_PARAM_ERROR("300018", "common.error.code.300018"),
    LOGIN_TYPE_ERROR("300019", "common.error.code.300019"),
    USER_LOGOUT("300020", "common.error.code.300020"),
    USER_LESS_BASE_INFO("300021", "common.error.code.300021"),
    USER_UPLOAD_VOICE_FAIL("300022", "common.error.code.300022"),
    ONE_CLICK_LOGIN_FAIL("300023", "common.error.code.300023"),
    DEVICE_REGISTER_ERROR("300024", "common.error.code.300024"),
    USER_MISSION_CHECK_ERROR("300025", "common.error.code.300025"),
    USER_CANCELLATION_IN_THREE_MONTH("300026", "common.error.code.300026"),
    VERIFYING_CHANGE_AVATAR_ERROR("300027", "common.error.code.300027"),
    USER_THIRD_PARTY_CHECK_ERROR("300028", "common.error.code.300028"),
    USER_UPLOAD_VIDEO_FAIL("300030", "common.error.code.300030"),
    USER_PERMISSION_SHOW_MESSAGE("300031", "common.error.code.300031"),
    USER_HAD_BAN("300032", "当前用户已被封禁"),

    //4 appservices
    APP_ID_KEY_ERROR("4000001", "common.error.code.4000001"),
    APP_INFO_NOT_EXIST("4000002", "common.error.code.4000002"),
    APP_SEND_SMS_STATUS_WRONG("4000003", "common.error.code.4000003"),
    APP_SEND_SMS_PARAMS_ERROR("4000004", "common.error.code.4000004"),
    APP_SEND_SMS_SEND_ERROR("4000005", "common.error.code.4000005"),
    APP_SEND_CODE_REDIS_FAIL("4000006", "common.error.code.4000006"),
    GET_QINIU_TOKEN_ERROR("4000007", "common.error.code.4000007"),
    UPLOAD_OR_CANCEL_PARAM_IS_NULL("4000008", "common.error.code.4000008"),
    UPLOAD_PUSH_TOKEN_ERROR("4000009", "common.error.code.4000009"),
    CANCEL_PUSH_TOKEN_ERROR("4000010", "common.error.code.4000010"),
    SEND_PUSH_PARAM_IS_NULL("4000011", "common.error.code.4000011"),
    SEND_PUSH_ERROR("4000012", "common.error.code.4000012"),
    SEND_DATA_RANGERS_EVENT_ERROR("4000013", "common.error.code.4000013"),
    FEED_NOT_EXIST("4000014", "common.error.code.4000014"),
    FEED_COMMENT_NOT_EXIST("4000015", "common.error.code.4000015"),
    EXIST_BLACK_RELATION("4000016", "common.error.code.4000016"),
    COMMENT_FAIED("4000017", "common.error.code.4000017"),
    DATARANGERS_GROUP_NAME_ERROR("4000018", "common.error.code.4000018"),
    LIKE_FAIED("4000019", "common.error.code.4000019"),
    APP_FEED_CHECK_RISCK__FAIED("4000020", "common.error.code.4000020"),
    APP_SMS_PHONE_ERROR("4000021", "common.error.code.4000021"),

    //5 payService
    PREPARE_PAY_REQUEST_ERROR("500001", "common.error.code.500001"),
    VERIFY_REQUEST_IS_NULL("500002", "common.error.code.500002"),
    ALIPAY_PREPARE_PAY_ERROR("500003", "common.error.code.500003"),
    ALIPAY_RETURN_ORDER_NO_NOT_EXIST("500004", "common.error.code.500004"),
    ALIPAY_RETURN_UNSING_ERROR("500005", "common.error.code.500005"),
    ALIPAY_RETURN_CONTENT_ERROR("500006", "common.error.code.500006"),
    ALIPAY_QUERY_ORDER_ERROR("500007", "common.error.code.500007"),
    ALIPAY_QUERY_ORDER_RETURN_NULL("500008", "common.error.code.500008"),
    HADNLE_DB_ERROR("500009", "common.error.code.500009"),
    WECHAT_PREPARE_PAY_ERROR("500010", "common.error.code.500010"),
    WECHAT_SIGN_ERROR("500011", "common.error.code.500011"),
    WECHAT_RETURN_IS_NULL("500012", "common.error.code.500012"),
    IAP_VERIFY_IS_NULL("500013", "common.error.code.500013"),
    IAP_VERIFY_UID_IS_NULL("500014", "common.error.code.500014"),
    IAP_VERIFY_PRODUCT_ID_IS_NULL("500015", "common.error.code.500015"),
    IAP_VERIFY_ORDER_NO_IS_NULL("500016", "common.error.code.500016"),
    IAP_VERIFY_RECEIPT_IS_NULL("500017", "common.error.code.500017"),
    PRODUCT_ID_NOT_EXIST("500018", "common.error.code.500018productId"),
    JSAPI_OPENID_IS_NULL("500019", "common.error.code.500019"),
    PAY_WAY_NOT_SUPPOERT("500020", "common.error.code.500020"),
    BALANCE_PAY_COINS_IS_NULL("500021", "common.error.code.500021"),
    CURRENT_USER_HAS_NO_ACCOUNT("500022", "common.error.code.500022"),
    BALANCE_NOT_ENOUGH("500023", "common.error.code.500023"),
    ORDER_QUERY_ID_IS_NULL("500024", "common.error.code.500024"),
    ORDER_QUERY_ID_NOT_EXIST("500025", "common.error.code.500025"),
    DECODE_ALIPAY_CALLBACK_PARAM_ERROR("500027", "common.error.code.500027"),
    INIT_ALIPAY_CLIENT_ERROR("500028", "common.error.code.500028"),
    GOOGLE_ORDER_CHECK_ERROR("500029", "common.error.code.500029"),


    // 6 feedServices
    FEED_ID_IS_NULL("600001", "common.error.code.600001"),
    REMOVE_FEED_ID_NOT_EXIST("600002", "common.error.code.600002"),
    REMOVE_COMMENT_ID_NOT_EXIST("600003", "common.error.code.600003"),
    CANCEL_LIKE_ID_IS_NULL("600004", "common.error.code.600004"),
    CANCEL_LIKE_ID_NOT_EXIST("600005", "common.error.code.600005"),

    //7 coin相关错误码
    COIN_IM_ORDER_SAVE_ERROR("700001", "common.error.code.700001"),
    COIN_BLANACE_REDUCE_ERROR("700002", "common.error.code.700002"),
    COIN_PAY_ORDER_DUMPLICATE("700003", "common.error.code.700003"),
    COIN_ORDER_SAVE_ERROR("700004", "common.error.code.700004"),
    COIN_ORDER_ADD_ERROR("700005", "common.error.code.700005"),
    COIN_ACCOUNT_NOT_EXITS("700006", "common.error.code.700006"),
    COIN_ACCOUNT_BALANCE_LESS("700007", "common.error.code.700007"),
    COIN_APP_ACCOUNT_NOT_EXITS("700008", "common.error.code.700008"),
    COIN_ACCOUNT_BALANC_FEE_NOT_ENOUGH("700009", "common.error.code.700009"),

    /** 金币余额扣除失败(用户未首充) */
    COIN_FIRST_CHARGE_BLANACE_REDUCE_ERROR("700009", ""),
    COIN_GIFT_NOT_EXIST("700010", "common.error.code.700010"),
    COIN_GIFT_GIVE_FAIL("700011", "common.error.code.700011"),

    COIN_ORDER_TO_CHARGE("700012", "common.error.code.700012"),
    COIN_PARAM_ERROR("700013", "common.error.code.700013"),
    COIN_MODIFY_CONFIG_ERROR("700014", "common.error.code.700014"),
    COIN_SEND_GIFT_TO_PACKAGE_GIFT_KEY_IS_NULL("700015", "common.error.code.700015"),
    COIN_AUTUMN_FESTIVAL_GIFT_SEND_TIME_ERROR("700016", "common.error.code.700016"),
    COIN_SILVER_RABBIT_OR_GOLD_RABBIT_NOT_SEND("700017", "common.error.code.700017"),
    COIN_GIFT_CAN_NOT_SEND_TWO_PERSON("700018", "common.error.code.700018"),

    //8 蓝领相关业务异常
    LANLING_CHECK_IN_EXIST("800001", "common.error.code.800001"),
    LANLING_ACCOST_ERROR("800002", "common.error.code.800002"),
    LANLING_SUBSCRIBE_IN_BLACK("800003", "common.error.code.800003"),
    LANLING_SUBSCRIBE_USER_ID_IS_NULL("800004", "common.error.code.800004"),
    LANLING_HOME_PAGE_USER_ID_IS_NULL("800005", "common.error.code.800005"),
    LANLING_REAL_VERIFY_ERROR("800006", "common.error.code.800006"),
    LANLING_VERIFY_RESULT_ERROR("800007", "common.error.code.800007"),
    LANLING_GET_USER_BASE_ERROR("800008", "common.error.code.800008"),
    LANLING_GET_USER_PREFERENCE_ERROR("800009", "common.error.code.800009"),
    LANLING_SUBSCRIBE_IS_BLACKED("800010", "common.error.code.800010"),
    LANLING_GET_COIN_BALANCE_ERROR("800011", "common.error.code.800011"),
    LANLING_GET_RECHARGE_PRODUCT_ERROR("800012", "common.error.code.800012"),
    LANLING_UPLOAD_PARAM_IS_NULL("800013", "common.error.code.800013"),
    LANLING_UPLOAD_PUSH_TOKEN_ERROR("800014", "common.error.code.800014"),
    LANLING_CANCEL_PARAM_IS_NULL("800013", "common.error.code.800013"),
    LANLING_SEND_PUSH_ERROR("800014", "common.error.code.800014"),
    LANLING_LIMIT_REPOER("800015", "common.error.code.800015"),
    LANLING_GET_LOTTERY_COUNT_PARAM_ERROR("800016", "common.error.code.800016"),
    LANLING_LOTTERY_COUNT_ERROR("800017", "common.error.code.800017"),
    LANLING_ACTIVITY_ERROR("800018", "common.error.code.800018"),
    ALIPAY_DISMATCH_ERROR("800019", "common.error.code.800019"),
    TRANSACTION_NOT_EXIST("800020", "common.error.code.800020"),
    LANLING_LOTTERY_CHANCE_ID_ERROR("800021", "common.error.code.800021"),
    VIP_PREMISSION_DENIED("800022", "common.error.code.800022"),
    LANLING_USER_NOT_EXIST_IN_ES("800023", "common.error.code.8000230"),
    LANLING_GET_MY_GUARD_PARAM_ERROR("800024", "common.error.code.8000240"),
    LANLING_GET_GUARD_ME_PARAM_ERROR("800025", "common.error.code.8000250"),
    LANLING_GET_MISSION_LIST_FAIL("8000260", "common.error.code.8000260"),
    LANLING_VOICE_MATCH_NEED_UPGRADE_VERSION("8000270", "common.error.code.8000270"),
    LANLING_VIDEO_MATCH_NEED_UPGRADE_VERSION("800028", "common.error.code.8000280"),
    LANLING_USER_NOT_IN_MATCH_QUEUE("8000290", "common.error.code.8000290"),
    LANLING_CHECK_IN_FAILED("800030", "common.error.code.8000300"),
    LANLING_CHECK_INTIMACY_FAILED("800031", "common.error.code.8000310"),
    LANLING_FAMILY_CHECK_IN("800032", "common.error.code.8000320"),
    LANLING_FAMILY_FREE_USER("800033", "common.error.code.8000330"),
    LANLING_FAMILY_GIFT_XYBX("8000340", "common.error.code.8000340"),
    LANLING_GET_VIDEO_DURATION_FAIL("800035", "common.error.code.8000350"),
    LANLING_GET_VIDEO_POINT_FAIL("800036", "common.error.code.8000360"),
    LANLING_SYS_CONFIG_NOT_EXIST("800037", "common.error.code.8000370"),
    LANLING_COIN_POINT_EDIT_ERROR("800038", "common.error.code.8000380"),
    LANLING_SEND_NAME_ERROR("800039", "common.error.code.8000390"),
    LANLING_MODIFY_GODDESS_ERROR("800040", "common.error.code.8000400"),
    LANLING_USER_NOBLE_VIP("800041", "common.error.code.8000410"),
    LANLING_GODDESS_NOT_EXIST("800042", "common.error.code.8000420"),
    LANLING_FAMILY_SET_MAX_NUM("800043", "common.error.code.8000430"),
    LANLING_REAL_PEOPLE_VERIFY_RESULT_IS_VERIFYING("800044", "common.error.code.8000440"),
    LANLING_ACCOSTED("800045", "common.error.code.8000450"),
    LANLING_AIRDROP_CAN_GRAB_IN_FAMILY("800046", "common.error.code.8000460"),
    LANLING_AIRDROP_COUNTDOWN("800047", "common.error.code.8000470"),
    LANLING_CAN_GIVE_GIFTS_TO_YOURSELF("800048", "common.error.code.8000480"),
    LANLING_VERIFY_RESULT_REPEAT_ERROR("8000071", "common.error.code.8000071"),
    LANLING_NOT_REAL_PERSON_VERIFY("8000072", "common.error.code.8000072"),
    LANLING_NOT_REAL_NAME_VERIFY("8000073", "common.error.code.8000073"),
    LANLING_BLACK_USER_CAN_NOT_SEND_GIFT("8000074", "common.error.code.8000074"),

    //风控相关接口异常
    SCAN_TEXT_ERROR("8000010", "common.error.code.8000010"),
    SCAN_IMAGE_ERROR("8000011", "common.error.code.8000011"),
    FACE_COMPARISON_SIMILARITY_ERROR("8000012", "common.error.code.8000012"),
    NAME_COMPARISON_SIMILARITY_ERROR("8000013", "common.error.code.8000013"),
    FACE_COMPARISON_SIMILARITY_FAILED("8000014", "common.error.code.8000014"),
    REAL_NAME_AUTHENTICATION_ERROR("8000015", "common.error.code.8000015"),
    LAST_SCAN_DOING("8000016", "common.error.code.8000016"),
    SCAN_TEXT_FAILED("8000017", "common.error.code.8000017"),

    LANLING_FULL_VERIFY_ERROR("800018", "common.error.code.800018"),
    LANLING_FULL_VERIFY_RESULT_ERROR("800019", "common.error.code.8000190"),
    INSUFFICIENT_BALANCE_OF_POINT("800020", "common.error.code.8000200"),

    ORDER_NOT_EXIST("800021", "common.error.code.8000210"),
    EXCEL_FORMAT_ERROR("800022", "common.error.code.8000220"),
    ONE_OFF_EXIST("800023", "common.error.code.800023"),
    TEXT_MAX_200("800024", "common.error.code.800024"),
    LANLING_PARAM_ERROR("800025", "common.error.code.800025"),
    LANLING_FAMILY_NAME_EXIST("800026", "common.error.code.800026"),
    LANLING_CREATE_FAMILY_FAIL("800027", "common.error.code.800027"),
    LANLING_CREATE_FAMILY_NO_QUALIFY("800028", "common.error.code.800028"),
    LANLING_CREATE_FAMILY_RISK_FAIL("800029", "common.error.code.800029"),
    LANLING_FAMILY_NOT_EXIST("800030", "common.error.code.800030"),
    LANLING_FAMILY_HAS_DISSOLVE("800031", "common.error.code.800031"),
    LANLING_FAMILY_HAS_APPLIED("800032", "common.error.code.800032"),
    LANLING_FAMILY_KICK_LIMIT("800033", "common.error.code.800033"),
    LANLING_FAMILY_NO_MORE_ONE_CREATE("800034", "common.error.code.800034"),
    LANLING_FAMILY_NO_MORE_ONE_APPLY("800035", "common.error.code.800035"),
    LANLING_FAMILY_APPLY_NOT_EXIST("800036", "common.error.code.800036"),
    LANLING_FAMILY_MEMBER_FULL("800037", "common.error.code.800037"),
    LANLING_FAMILY_AUTH_FAIL("800038", "common.error.code.800038"),
    LANLING_GET_CHAT_LIST_FAIL("800039", "common.error.code.800039"),
    LANLING_FAMILY_APPLY_GUEST_FAIL("800040", "common.error.code.800040"),
    LANLING_FAMILY_HAS_NO_AUTH("800041", "common.error.code.800041"),
    LANLING_FAMILY_HAS_KICKED("800042", "common.error.code.800042"),
    LANLING_FAMILY_KICK_FAIL("800043", "common.error.code.800043"),
    LANLING_FAMILY_EXIT_NOT_IN("800044", "common.error.code.800044"),
    LANLING_FAMILY_EXIT_FAIL("800045", "common.error.code.800045"),
    LANLING_FAMILY_BATCH_GIVE_GIFT_FAIL("800046", "common.error.code.800046"),
    LANLING_FAMILY_CO_OWNER_MAX("800047", "common.error.code.800047"),
    LANLING_FAMILY_HAS_BANED("800048", "common.error.code.800048"),
    LANLING_FAMILY_HAS_ENTER("800049", "common.error.code.800049"),
    LANLING_FAMILY_NOT_OPEN("800050", "common.error.code.800050"),
    LANLING_FAMILY_DISMISS_FAIL("800051", "common.error.code.800051"),
    LANLING_FAMILY_EDIT_NAME_OR_ICON_FAIL("800052", "common.error.code.800052"),
    LANLING_FAMILY_GUEST_CANT_APPOINT("800053", "common.error.code.800053"),
    LANLING_FAMILY_GUEST_FULL("800054", "common.error.code.800054"),
    LANLING_AV_MATCH_NEED_UPGRADE_VERSION("800055", "common.error.code.800055"),
    LANLING_AV_MATCH_FREE_TIME_RULE_NOT_EXITS("800056", "common.error.code.800056"),
    LANLING_RED_PACKET_AMOUNT_NOT_ENOUGH("800057", "common.error.code.800057"),
    LANLING_RED_PACKET_NUM_NOT_ENOUGH("800058", "common.error.code.800058"),
    LANLING_RED_PACKET_NUM_BEYOND("800059", "common.error.code.800059"),
    LANLING_GIVE_NOBLE_USER("800060", "common.error.code.800060"),
    LANLING_GODDESS_GENERAL_USER("800061", "common.error.code.800061"),
    LANLING_FAMILY_SPEAKER("800062", "common.error.code.800062"),
    LANLING_FAMILY_NOT_BELONG_MEMBER("800063", "common.error.code.800063"),
    LANLING_AUDIT_USER_SUBMIT("800064", "common.error.code.800064"),
    LANLING_BACK_USER_PERMISSION("800065", "common.error.code.800065"),
    LANLING_DEVICE_ID_CHECK_ERROR("800066", "common.error.code.800066"),
    LANLING_SEARCH_BY_UID_NEED_REAL_VERIFY("800067", "common.error.code.800067"),
    LANLING_VIP_ANTI_KICK_AND_ANTI_FORBIDDEN("800068", "common.error.code.800068"),
    LANLING_ENTRY_SPECIAL_EFFECT_WEAR("800069", "common.error.code.800069"),
    LANLING_VERSION_TOO_LOW_CAN_NOT_PRODUCT_CONFIRM("800070", "common.error.code.800070"),
    LANLING_ACCOUNT_CONTAIN_CHINESE("800071", "common.error.code.800071"),
    LANLING_FAMILY_NOT_OPEN_TO_YOU("800072", "common.error.code.800072"),

    // 凑对相关,
    COUDUI_EXIT_VIDEO_MATCH("810001", "common.error.code.810001"),
    COUDUI_VIDEO_MATCH_SUCCESS("810002", "common.error.code.810002"),

    //音视频相关
    AV_INCIMACY_LESS_TO_VOICE_CALL("900010", "common.error.code.900010"),
    AV_BLACK_RELEATION_NOT_CALL("900011", "common.error.code.900011"),
    AV_COIN_LESS_TO_CALL("900012", "common.error.code.900012"),
    AV_VOICE_SESSION_FAIL("900013", "common.error.code.900013"),
    AV_COIN_ORDER_FAIL("900014", "common.error.code.900014"),
    AV_MATCH_UN_CALLED("900015", "common.error.code.900015"),
    AV_INCIMACY_LESS_TO_VIDEO_CALL("900016", "common.error.code.900016"),
    AV_VIDEO_SESSION_FAIL("900017", "common.error.code.900017"),
    AV_CALL_SESSION_NEED_UPGRADE_VERSION("900018", "common.error.code.900018"),
    AV_CALL_USER_OFF_VOICE_SWITCH("900019", "common.error.code.900019"),
    AV_CALL_USER_OFF_VIDEO_SWITCH("900020", "common.error.code.900020"),
    AV_VOICE_SESSION_NOT_EXIST("900021", "common.error.code.900021"),
    AV_VIDEO_SESSION_NOT_EXIST("900022", "common.error.code.900022"),
    AV_VIDEO_ACCOST_RUN_OUT("900023", "common.error.code.900023"),
    AV_VIDEO_WITH_LIVE("900024", "common.error.code.900024"),
    AV_VIDEO_NOW("900025", "common.error.code.900025"),
    AV_VIDEO_FAILED_TO_GRAB("900026", "common.error.code.900026"),

    /** 金币不足错误码 COIN_BLANACE_REDUCE_ERROR  COIN_FIRST_CHARGE_BLANACE_REDUCE_ERROR */
    COIN_NOT_ENOUGH_VOICE_MATCH("110001", "common.error.code.110001"),
    COIN_NOT_ENOUGH_VIDEO_MATCH("110002", "common.error.code.110002"),
    COIN_NOT_ENOUGH_VOICE_CALL("110003", "common.error.code.110003"),
    COIN_NOT_ENOUGH_VIDEO_CALL("110004", "common.error.code.110004"),
    COIN_NOT_ENOUGH_GIFT_USER("110005", "common.error.code.110005"),
    COIN_NOT_ENOUGH_GIFT_VIDEO_MATCH("110006", "common.error.code.110006"),
    COIN_NOT_ENOUGH_GIFT_VIDEO_CALL("110007", "common.error.code.110007"),
    COIN_NOT_ENOUGH_GIFT_CHAT("110008", "common.error.code.110008"),
    COIN_NOT_ENOUGH_GIFT_GUARD_LIST("110009", "common.error.code.110009"),
    COIN_NOT_ENOUGH_ACCOST("110010", "common.error.code.110010"),
    COIN_NOT_ENOUGH_CHAT("110011", "common.error.code.110011"),
    COIN_NOT_ENOUGH_FAMILY_GIFT("110012", "common.error.code.110012"),
    COIN_NOT_ENOUGH_FAMILY_CHAT("110013", "common.error.code.110013"),
    COIN_NOT_ENOUGH_VOICE_ROOM_GIFT("1100134", "common.error.code.1100134"),
    COIN_NOT_ENOUGH_LIVE_ROOM_GIFT("110015", "common.error.code.110015"),
    COIN_NOT_ENOUGH_DATING_LIVE_ROOM_GIFT("110016", "common.error.code.110016"),
    COIN_NOT_ENOUGH_ROOM_AUCTION_GIFT("110017", "common.error.code.110017"),
    COIN_NOT_ENOUGH_DEPTH_GIFT("110018", "common.error.code.110018"),
    COIN_NOT_ENOUGH_BUY_CUPID("110019", "common.error.code.110019"),
    COIN_NOT_ENOUGH_USER_GIFT_WALL("110020", "common.error.code.110008"),
    COIN_NOT_ENOUGH_FAMILY_RED_PACKET("110021", "common.error.code.110021"),
    COIN_NOT_ENOUGH_AI_CHAT("110022", "common.error.code.110022"),


    FIRST_CHARGE_COIN_NOT_ENOUGH_VOICE_MATCH("120001", "common.error.code.120001"),
    FIRST_CHARGE_COIN_NOT_ENOUGH_VIDEO_MATCH("120002", "common.error.code.120002"),
    FIRST_CHARGE_COIN_NOT_ENOUGH_VOICE_CALL("120003", "common.error.code.120003"),
    FIRST_CHARGE_COIN_NOT_ENOUGH_VIDEO_CALL("120004", "common.error.code.120004"),
    FIRST_CHARGE_COIN_NOT_ENOUGH_GIFT_USER("120005", "common.error.code.120005"),
    FIRST_CHARGE_COIN_NOT_ENOUGH_GIFT_VIDEO_MATCH("120006", "common.error.code.120006"),
    FIRST_CHARGE_COIN_NOT_ENOUGH_GIFT_VIDEO_CALL("120007", "common.error.code.120007"),
    FIRST_CHARGE_COIN_NOT_ENOUGH_GIFT_CHAT("120008", "common.error.code.120008"),
    FIRST_CHARGE_COIN_NOT_ENOUGH_GIFT_GUARD_LIST("120009", "common.error.code.120009"),
    FIRST_CHARGE_COIN_NOT_ENOUGH_ACCOST("120010", "common.error.code.120010"),
    FIRST_CHARGE_COIN_NOT_ENOUGH_CHAT("120011", "common.error.code.120011"),
    FIRST_CHARGE_COIN_NOT_ENOUGH_FAMILY_GIFT("120012", "common.error.code.120012"),
    FIRST_CHARGE_COIN_NOT_ENOUGH_FAMILY_CHAT("120013", "common.error.code.120013"),
    FIRST_CHARGE_COIN_NOT_ENOUGH_VOICE_ROOM_GIFT("120014", "common.error.code.120014"),
    FIRST_CHARGE_COIN_NOT_ENOUGH_LIVE_ROOM_GIFT("120015", "common.error.code.120015"),
    FIRST_CHARGE_COIN_NOT_ENOUGH_DATING_LIVE_ROOM_GIFT("120016", "common.error.code.120016"),
    FIRST_CHARGE_COIN_NOT_ENOUGH_ROOM_AUCTION_GIFT("120017", "common.error.code.120017"),
    FIRST_CHARGE_COIN_NOT_ENOUGH_BUY_CUPID("120018", "common.error.code.120018"),
    FIRST_CHARGE_COIN_NOT_ENOUGH_USER_GIFT_WALL("120020", "common.error.code.120008"),
    FIRST_CHARGE_COIN_NOT_ENOUGH_FAMILY_RED_PACKET("120021", "common.error.code.120021"),


    // remote config
    REMOTE_CONFIG_KEY_EXIST("130001", "common.error.code.130001"),
    REMOTE_CONFIG_JSON_ERROR("130002", "common.error.code.130002"),

    /**
     * 金币不足错误码
     */
    COIN_NOT_ENOUGH_FAMILY_SPEAKER("140001", "common.error.code.140001"),

    //语音房
    ROOM_NOT_FIND_MESSAGE("150001", "common.error.code.150001"),
    ROOM_SET_NOT_EMPTY("150002", "common.error.code.150002"),
    USER_ALREADY_IN_ROOM_BOOTH("150002", "common.error.code.1500020"),
    USER_NOT_EXIST_IN_ROOM_BOOTH("150003", "common.error.code.150003"),
    ROOM_USER_PERMISSION_NOT_ENOUGH("150005", "common.error.code.150005"),
    SET_SEAT_ERROR("150004", "common.error.code.150004"),
    DOWN_SEAT_ERROR("150005", "common.error.code.1500050"),
    OPERATE_SEAT_ERROR("150006", "common.error.code.150006"),
    MODIFY_ADMINISTRATION_ERROR("150007", "common.error.code.150007"),
    EDIT_ROOM_ERROR("150008", "common.error.code.150008"),
    KICK_OUT_ROOM_USER_ERROR("150009", "common.error.code.150009"),

    NIM_REQUEST_ERROR("150010", "common.error.code.150010"),
    CREATE_LIVE_ROOM_ERROR("150011", "common.error.code.150011"),

    // 任务（成就）
    MISSION_REWARD_HANDOUT_ERROR("160001", "common.error.code.160001"),
    MISSION_RECORD_NOT_FOUND("160002", "common.error.code.160002"),
    MISSION_NOT_FINISH("160003", "common.error.code.160003"),
    MISSION_ALREADY_RECEIVE("160004", "common.error.code.160004"),
    MISSION_ALREADY_FINISH("160005", "common.error.code.160005"),
    MISSION_PROCESS_ERROR("160006", "common.error.code.160006"),
    MISSION_REFRESH_RED_PACKET("160007", "common.error.code.160007"),
    MISSION_GET_USER_INFO_ERROR("160008", "common.error.code.160008"),
    MISSION_CONFIG_NOT_FOUND("160009", "common.error.code.160009"),
    MISSION_STATE_FAILURE("160010", "common.error.code.160010"),
    MISSION_NO_REWARDS_AVAILABLE("160011", "common.error.code.160011"),
    MISSION_IS_AUTO_REWARD("160012", "common.error.code.160012"),
    HIGH_QUALITY_CONDITION_ERROR("162001", "用户未满足进入合格-优质阶段条件"),
    HIGH_QUALITY_TODAY_ALL_COMPLETE("162002", "当日全部任务已完成"),

    // push 推送服务
    PUSH_CONFIG_NOT_FOUND("170001", "common.error.code.170001"),
    PUSH_USER_CLIENT_ID_NOT_FOUND("170002", "common.error.code.170002"),
    PUSH_CONFIG_ERROR("170003", "common.error.code.170003"),
    PUSH_REQUEST_DATA_ERROR("170004", "common.error.code.170004"),
    PUSH_USER_CID_NOT_FOUND("170005", "common.error.code.170005"),
    PUSH_USER_GROUP_NOT_FOUND("170006", "common.error.code.170006"),
    PUSH_SETTING_CONFIG_ERROR("170007", "common.error.code.170007"),
    PUSH_DATA_RANGER_USER_GROUP_NOT_FOUND("170008", "common.error.code.170008"),
    PUSH_TITLE_LENGTH_TOO_LONG("170009", "common.error.code.170009"),
    PUSH_BODY_LENGTH_TOO_LONG("170010", "common.error.code.170010"),
    PUSH_SINGLE_PUSH_LIMIT("170011", "common.error.code.170011"),
    PUSH_SINGLE_PUSH_API_LIMIT("170012", "common.error.code.170012"),

    // 活动
    ACTIVITY_PARAM_ERROR("180000", "参数错误"),
    ACTIVITY_NO_BOOST_COUPON("180001", "助力券数量不足"),
    ACTIVITY_NO_WHITE_SNOW("180002", "白雪花不足哦，快去完成任务获取更多雪花吧~"),
    ACTIVITY_OPEN_BOX_LIMIT("180003", "今日次数达到上限"),
    ACTIVITY_OPEN_BOX_ERROR("180004", "匹配系统忙，请稍后在试"),
    ACTIVITY_PRIZE_NO_RECHARGE("180005", "充值金额尚未达到"),
    ACTIVITY_PRIZE_HAS_TAKE("180006", "充值奖励已领取"),
    ACTIVITY_CHECK_IN_LIMIT("180007", "今日已签到"),
    ACTIVITY_ERROR_180008("180008", "文字涉及敏感词汇，请重新编辑发送哦~"),
    ACTIVITY_ERROR_180009("180009", "需要选择属性并填写用户寄语，才可上架哦~！"),
    ACTIVITY_ERROR_180010("180010", "你已经有cp了哦"),
    ACTIVITY_ERROR_180011("180011", "剩余次数不足"),
    ACTIVITY_ERROR_180012("180012", "愿望太长啦！"),
    ACTIVITY_ERROR_180015("180015", "邀请人已经有cp了哦"),
    ACTIVITY_ERROR_180016("180016", "豪华弹幕今日12点开放，敬请期待~"),
    ACTIVITY_ERROR_180017("180017", "活动已结束"),
    ACTIVITY_ERROR_180018("180018", "当前金钥匙数不足"),
    ACTIVITY_ERROR_180019("180019", "需要换成金钥匙才行哦！"),
    ACTIVITY_ERROR_180020("180020", "抽奖券不足！"),
    ACTIVITY_ERROR_180021("180021", "抽奖失败，请稍后重试！"),
    ACTIVITY_ERROR_180022("180022", "当日点赞次数超过三次"),
    ACTIVITY_ERROR_180023("180023", "今日已领取过哦～"),
    ACTIVITY_ERROR_180024("180024", "奖池非法"),
    ACTIVITY_ERROR_180025("180025", "排行榜奖品下发失败"),
    ACTIVITY_ERROR_180026("180026", "重复答题"),
    ACTIVITY_ERROR_180027("180027", "答题次数超出当天答题限制"),
    ACTIVITY_ERROR_180028("180028", "金币不足"),
    ACTIVITY_ERROR_180029("180029", "领取奖励失败"),
    ACTIVITY_ERROR_180030("180030", "尚未结成心动关系"),
    ACTIVITY_ERROR_180031("180031", "当前动作已经进行过了哦～"),
    ACTIVITY_ERROR_180032("180032", "没有与该用户进行过会话"),
    ACTIVITY_ERROR_180033("180033", "骰子数量不足"),
    ACTIVITY_ERROR_180034("180034", "当前行为未完成"),
    OFFICIAL_BINE_ERROR_181001("181001", "对方暂不接受官宣邀请噢"),
    OFFICIAL_BINE_ERROR_181002("181002", "创建订单失败"),
    OFFICIAL_BINE_ERROR_181003("181003", "页面索引失效"),
    OFFICIAL_BINE_ERROR_181004("181004", "无进行中的官宣流程"),
    OFFICIAL_BINE_ERROR_181005("181005", "开启官宣房失败"),
    OFFICIAL_BINE_ERROR_181006("181006", "无剩余编辑次数"),
    OFFICIAL_BINE_ERROR_181007("181007", "金币不足"),
    OFFICIAL_BINE_ERROR_181008("181008", "尚未支付定金"),
    OFFICIAL_BINE_ERROR_181009("181009", "尚未生成订单"),
    OFFICIAL_BINE_ERROR_181010("181010", "订单支付处理失败"),
    OFFICIAL_BINE_ERROR_181011("181011", "下发优惠券失败"),
    OFFICIAL_BINE_ERROR_181012("181012", "取消官宣失败"),
    OFFICIAL_BINE_ERROR_181013("181013", "操作太快啦～"),
    OFFICIAL_BINE_ERROR_181014("181014", "暂无完成的官宣"),
    FEMALE_SYS_TAKE_REWARD_ERROR("182001", "女生体系领奖"),

    // order
    ORDER_ERROR_NO_PRODUCT("240001", "商品不存在"),

    // 活动通用异常
    ACTIVITY_NONE("190000", "未能查询到活动"),
    ACTIVITY_CONFIG_ERROR("190001", "活动配置异常"),
    ACTIVITY_DRAW_POOL_SWITCH("190002", "奖池已切换，请刷新页面重试"),
    ACTIVITY_DRAW_DEDUCT_ERROR("190003", "抽奖资源扣除异常"),
    ACTIVITY_END_ERROR("190004", "活动已经结束"),

    // UMP 配置异常
    DRAW_CONFIG_ERROR("210000", "抽奖配置错误"),
    LUCKY_BAG_COUPON_ERROR("210001", "福袋抽奖次数错误"),

    // chatie
    CHATIE_COIN_BLANACE_REDUCE_ERROR("210001", "common.error.code.210001"),
    CHATIE_CALL_USER_COIN_ERROR("220001", "common.error.code.220001"),

    // product
    PRODUCT_INFO_NOT_EXIST("250000", "product.error.not.exist"),
    PRODUCT_SEND_TO_PACKAGE_FAIL("250001", "user.package.error.send.fail"),
    PRODUCT_USER_PACKAGE_NOT_ENOUGH("250002", "user.package.error.not.enough"),
    PRODUCT_USE_PACKAGE_FAIL("250003", "user.package.error.deduct.fail"),

    // 内部异常
    SERVER_RETRY_EXCEPTION("230001", "common.error.code.230001"),

    // 通用
    USER_GROUP_ERROR("900001", "用户分组不符"),

    // 拍卖房
    AUCTION_GUEST_ERROR("240000", "用户已在嘉宾位"),

    // QCP 项目
    QCP_ERROR("250001", "操作错误"),
    QCP_NOT_FOUND("250002", "未查询到记录"),
    QCP_NO_PERMISSION("250003", "无权限"),
    QCP_REPEAT_OPERATION("250004", "重复操作"),
    QCP_AVATAR_NOT_PERSON("250005", "头像需要上传本人真实照片哦~"),
    QCP_ID_CARD_REAL_NAME_NOT_EQUAL("240006", "姓名与身份证号不匹配哦~"),
    QCP_USER_UNDER_AGE("250007", "抱歉，未成年用户暂时不可使用此应用~"),
    QCP_SAVE_USER_INFO_FAIL("250008", "保存用户资料失败~"),
    QCP_CP_INVALID("250009", "CP 状态已经失效~"),
    QCP_NEED_LOVE_EXPERIENCE_CARD("250010", "需要开通恋爱体验卡才可以操作哦~"),
    QCP_RENEW_CP_LIMIT("250011", "CP 续期限制"),
    QCP_TASK_LOCKED("250012", "当前任务未解锁不可完成哦"),
    QCP_TEXT_VIOLATION("250013", "输入内容违规，请重新输入"),
    QCP_IMAGE_VIOLATION("250014", "图片违规，请重新输入"),
    QCP_NEED_REAL_PERSON("250015", "需要通过真人认证才可以操作"),
    QCP_REAL_NAME_CARD_ID_NOT_EQUAL("250016", "输入的姓名与身份证号不匹配哦~"),
    QCP_OPERATING_TOO_FAST("250017", "操作过快，稍等下哦~"),

    WITHDRAWAL_ERROR("260000", "该微信已提现过"),

    USER_IS_CANCELLATION("270000", "common.error.code.270000"),

    TIKTOK_TOKEN_FAIL("280000", "获取 tiktok token 失败"),
    ;

    private String code;

    private String description;

    ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ErrorCode findByCode(String code) {

        for (ErrorCode type : ErrorCode.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }

        return null;
    }

    /***
     * 1. gateway错误码   9  开头的六位字符
     *
     * 2. im-services 错误码 2开头的六位字符
     *
     * 3. 系统通用层面的 错误码 1开头的六位字符
     *
     * 4. app-services 错误码 4开头六位字符
     *
     * 5. user-services 3 开头的六位字符
     *
     * 6. pay-services 5 开头的六位字符
     *
     * 7. feed-services 6  开头的六位字符
     *
     * 8. mission-services 7  开头的六位字符
     *
     * 9. 通用业务层面异常 9 开头的六位字符
     *
     * 10. attribution-services 28 开头的六位字符
     */


}
