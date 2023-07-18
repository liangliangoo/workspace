package com.xiaoxiong.feishu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 飞书工具类
 *
 * @author dongming
 */
@Slf4j
public class FeiShuUtil {


    /**
     * 获取 tenant_access_token（企业自建应用）
     * 数据中台【PDCA】监控报警记录使用
     *
     * @return String
     */
    public static String getDataMiddleAccessToken(String appId,String appSecret) {
        String tenantAccessToken;
        Map<String, String> requestBodyMap = new HashMap<>();
        requestBodyMap.put("app_id", appId);
        requestBodyMap.put("app_secret", appSecret);
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        String response = HttpClientUtil.doPostJsonAndSetHeaders(FeiShuUrl.tenant_access_token, JSON.toJSONString(requestBodyMap), headers);
        if (StringUtils.isNotEmpty(response)) {
            JSONObject jsonObject = JSONObject.parseObject(response);
            String msg = jsonObject.getString("msg");
            if (StringUtils.isNotEmpty(msg) && "ok".equals(msg)) {
                tenantAccessToken = "Bearer " + jsonObject.getString("tenant_access_token");
                return tenantAccessToken;
            } else {
                throw new RuntimeException("response error");
            }
        } else {
            throw new RuntimeException("获取飞书tenant_access_token错误");
        }
    }

    public static Map<String, String> getHeader(String accessToken) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        headers.put("Authorization", accessToken);
        return headers;
    }

    /**
     * 创建飞书云文件
     *
     * @param request 请求参数
     * @return String
     */
    public static String createFile(CreateFileRequestDTO request) {
        Map<String, String> headers = getHeader(request.getTenantAccessToken());

        JSONObject param = new JSONObject();
        param.put("folder_token", request.getFolderToken());
        param.put("title", request.getTitle());
        param.put("type", request.getType());
        log.info("param {} header {}", param.toJSONString(), JSON.toJSONString(headers));
        return HttpClientUtil.doPost(FeiShuUrl.CREATE_FILE, param, headers);
    }

    /**
     * 创建文档块
     *
     * @param request 请求参数
     * @return String
     */
    public static String createFileBlock(CreateFileBlockRequestDTO request) {
        Map<String, String> headers = getHeader(request.getTenantAccessToken());
        String requestUrl = String.format(FeiShuUrl.CREATE_FILE_BLOCK, request.getDocumentId(), request.getBlockId());
        log.info("requestUrl {} param {} header {}", requestUrl, JSON.toJSONString(request), JSON.toJSONString(headers));
        return HttpClientUtil.doPost(requestUrl, request.getBody(), headers);
    }

    /**
     * 获取多维表格所有字段
     *
     * @param request 请求参数
     * @return String
     */
    public static String getBitableField(BitableRequestDTO request) {
        Map<String, String> headers = getHeader(request.getTenantAccessToken());
        String requestUrl = String.format(FeiShuUrl.BITABLE_FIELD, request.getAppToken(), request.getTableId());
        return HttpClientUtil.get(requestUrl, null, headers);
    }

    /**
     * 更新多维表格所有字段
     *
     * @param request 请求参数
     * @return String
     */
    public static String updateBitableField(BitableRequestDTO request) {
        Map<String, String> headers = getHeader(request.getTenantAccessToken());
        String requestUrl = String.format(FeiShuUrl.UPDATE_BITABLE_FIELD, request.getAppToken(), request.getTableId(), request.getFieldId());
        return HttpClientUtil.doPut(requestUrl, request.getParam(), headers);
    }

    /**
     * 新增多维表格字段
     *
     * @param request 请求参数
     * @return String
     */
    public static String addBitableField(BitableRequestDTO request) {
        Map<String, String> headers = getHeader(request.getTenantAccessToken());
        String requestUrl = String.format(FeiShuUrl.BITABLE_FIELD, request.getAppToken(), request.getTableId());
        return HttpClientUtil.doPost(requestUrl, request.getParam(), headers);
    }

    /**
     * 获取多维表格所有记录
     *
     * @param request 请求参数
     * @return String
     */
    public static String getBitableRecord(BitableRequestDTO request) {
        Map<String, String> headers = getHeader(request.getTenantAccessToken());
        String requestUrl = String.format(FeiShuUrl.SHOW_BITABLE_RECORD, request.getAppToken(), request.getTableId());
        return HttpClientUtil.get(requestUrl, null, headers);
    }

    /**
     * 获取多维表格所有记录
     *
     * @param request 请求参数
     * @return String
     */
    public static String getBitableRecordByCondition(BitableRequestDTO request, Map<String, Object> filter) {
        Map<String, String> headers = getHeader(request.getTenantAccessToken());
        String requestUrl = String.format(FeiShuUrl.SHOW_BITABLE_RECORD, request.getAppToken(), request.getTableId());
        return HttpClientUtil.get(requestUrl, filter, headers);
    }

    /**
     * 获取多维表格所有记录
     *
     * @param request 请求参数
     * @return String
     */
    public static String findBitableRecord(BitableRequestDTO request) {
        Map<String, String> headers = getHeader(request.getTenantAccessToken());
        String requestUrl = String.format(FeiShuUrl.SEARCH_BITABLE_RECORD, request.getAppToken(), request.getTableId(), request.getRecordId());
        return HttpClientUtil.get(requestUrl, null, headers);
    }

    /**
     * 批量删除多维表格记录
     *
     * @param request 请求参数
     * @return String
     */
    public static String deleteBitableRecord(BitableRequestDTO request) {
        Map<String, String> headers = getHeader(request.getTenantAccessToken());
        String requestUrl = String.format(FeiShuUrl.DELETE_BITABLE_RECORD, request.getAppToken(), request.getTableId());
        return HttpClientUtil.doPost(requestUrl, request.getParam(), headers);
    }

    /**
     * 批量增加多维表格记录
     *
     * @param request 请求参数
     * @return String
     */
    public static String addBitableRecord(BitableRequestDTO request) {
        Map<String, String> headers = getHeader(request.getTenantAccessToken());
        String userIdType = StringUtils.isBlank(request.getUserIdType()) ? "open_id" : request.getUserIdType();
        String requestUrl = String.format(FeiShuUrl.ADD_BITABLE_RECORD, request.getAppToken(), request.getTableId(), userIdType);
        return HttpClientUtil.doPost(requestUrl, request.getParam(), headers);
    }

    /**
     * 单条增加多维表格记录
     *
     * @param request 请求参数
     * @return String
     */
    public static String addBitableRecordOne(BitableRequestDTO request) {
        Map<String, String> headers = getHeader(request.getTenantAccessToken());
        String requestUrl = String.format(FeiShuUrl.ADD_BITABLE_RECORD_ONE, request.getAppToken(), request.getTableId());
        return HttpClientUtil.doPost(requestUrl, request.getParam(), headers);
    }


    /**
     * 批量删除多维表格记录
     *
     * @param request 请求参数
     * @return String
     */
    public static String updateBitableRecord(BitableRequestDTO request) {
        Map<String, String> headers = getHeader(request.getTenantAccessToken());
        String requestUrl = String.format(FeiShuUrl.UPDATE_BITABLE_RECORD, request.getAppToken(), request.getTableId(), request.getRecordId());
        return HttpClientUtil.doPut(requestUrl, request.getParam(), headers);
    }

    /**
     * 获取多维表格视图
     *
     * @param request 请求参数
     * @return String
     */
    public static String getBitableView(BitableRequestDTO request) {
        Map<String, String> headers = getHeader(request.getTenantAccessToken());
        String requestUrl = String.format(FeiShuUrl.SHOW_BITABLE_VIEW, request.getAppToken(), request.getTableId());
        return HttpClientUtil.get(requestUrl, request.getParam(), headers);
    }

    /**
     * 更新多维表格视图
     *
     * @param request 请求参数
     * @return String
     */
    public static String updateBitableView(BitableRequestDTO request) {
        Map<String, String> headers = getHeader(request.getTenantAccessToken());
        String requestUrl = String.format(FeiShuUrl.UPDATE_BITABLE_VIEW, request.getAppToken(), request.getTableId(), request.getViewId());
        return HttpClientUtil.doPatch(requestUrl, request.getParam(), headers);
    }

    /**
     * 构建卡片消息多列布局标题
     *
     * @param content 内容
     * @param color   颜色内容
     * @return JSONObject
     */
    public static JSONObject buildMultiColumnHeader(String content, String color) {
        JSONObject header = new JSONObject();
        JSONObject title = new JSONObject();
        title.put("tag", "plain_text");
        title.put("content", content);
        header.put("title", title);
        header.put("template", color);
        return header;
    }

    /**
     * 构建卡片消息多列布局表头(每列等长)
     *
     * @param tableHeaderList 表头
     * @return JSONObject
     */
    public static JSONObject buildMultiColumnTableHeader(List<String> tableHeaderList) {
        JSONObject tableHeader = new JSONObject();
        tableHeader.put("tag", "column_set");
        tableHeader.put("flex_mode", "none");
        tableHeader.put("background_style", "grey");
        tableHeader.put("horizontal_spacing", "default");
        JSONArray columns = new JSONArray();
        for (String header : tableHeaderList) {
            JSONObject column = new JSONObject();
            column.put("tag", "column");
            column.put("width", "weighted");
            column.put("weight", 1);
            JSONArray elements = new JSONArray();
            JSONObject element = new JSONObject();
            element.put("tag", "markdown");
            element.put("text_align", "center");
            element.put("content", "**" + header + "**");
            elements.add(element);
            column.put("elements", elements);
            columns.add(column);
        }
        tableHeader.put("columns", columns);
        return tableHeader;
    }

    /**
     * 构建卡片消息多列布局表头(每列等长)
     *
     * @param tableHeaderList 表头
     * @param align           对齐方式
     * @return JSONObject
     */
    public static JSONObject buildMultiColumnTableHeader(List<String> tableHeaderList, String align) {
        if (StringUtils.isBlank(align)) {
            return buildMultiColumnTableHeader(tableHeaderList);
        }
        JSONObject tableHeader = new JSONObject();
        tableHeader.put("tag", "column_set");
        tableHeader.put("flex_mode", "none");
        tableHeader.put("background_style", "grey");
        tableHeader.put("horizontal_spacing", "default");
        JSONArray columns = new JSONArray();
        for (String header : tableHeaderList) {
            JSONObject column = new JSONObject();
            column.put("tag", "column");
            column.put("width", "weighted");
            column.put("weight", 1);
            JSONArray elements = new JSONArray();
            JSONObject element = new JSONObject();
            element.put("tag", "markdown");
            element.put("text_align", align);
            element.put("content", "**" + header + "**");
            elements.add(element);
            column.put("elements", elements);
            columns.add(column);
        }
        tableHeader.put("columns", columns);
        return tableHeader;
    }

    /**
     * 构建卡片消息多列布局表头（自定义没列占几格）
     *
     * @param tableHeaderList 表头
     * @return JSONObject
     */
    public static JSONObject buildMultiColumnTableHeaderWithWeight(List<String> tableHeaderList, List<Integer> weightList) {
        JSONObject tableHeader = new JSONObject();
        tableHeader.put("tag", "column_set");
        tableHeader.put("flex_mode", "none");
        tableHeader.put("background_style", "grey");
        tableHeader.put("horizontal_spacing", "default");
        JSONArray columns = new JSONArray();
        for (int i = 0; i < tableHeaderList.size(); i++) {
            JSONObject column = new JSONObject();
            column.put("tag", "column");
            column.put("width", "weighted");
            column.put("weight", weightList.get(i));
            JSONArray elements = new JSONArray();
            JSONObject element = new JSONObject();
            element.put("tag", "markdown");
            element.put("text_align", "center");
            element.put("content", "**" + tableHeaderList.get(i) + "**");
            elements.add(element);
            column.put("elements", elements);
            columns.add(column);
        }
        tableHeader.put("columns", columns);
        return tableHeader;
    }

    /**
     * 构建卡片消息多列布局单列(每列等长)
     *
     * @param contentListArr 内容
     * @return JSONObject
     */
    public static JSONObject buildMultiColumnData(List<List<String>> contentListArr) {
        JSONObject line = new JSONObject();
        line.put("tag", "column_set");
        line.put("flex_mode", "none");
        line.put("background_style", "default");
        line.put("horizontal_spacing", "default");
        JSONArray columns = new JSONArray();
        for (List<String> contentList : contentListArr) {
            StringBuilder sb = new StringBuilder();
            contentList.forEach(content -> sb.append(content).append("\n"));
            String content = sb.substring(0, sb.length() - 1);
            JSONObject column = new JSONObject();
            column.put("tag", "column");
            column.put("width", "weighted");
            column.put("weight", 1);
            JSONArray elements = new JSONArray();
            JSONObject element = new JSONObject();
            element.put("tag", "markdown");
            element.put("text_align", "center");
            element.put("content", content);
            elements.add(element);
            column.put("elements", elements);
            columns.add(column);
        }
        line.put("columns", columns);
        return line;
    }

    /**
     * 构建卡片消息多列布局单列（自定义没列占几格）
     *
     * @param contentListArr 内容
     * @return JSONObject
     */
    public static JSONObject buildMultiColumnDataWithWeight(List<List<String>> contentListArr, List<Integer> weightList) {
        JSONObject line = new JSONObject();
        line.put("tag", "column_set");
        line.put("flex_mode", "none");
        line.put("background_style", "default");
        line.put("horizontal_spacing", "default");
        JSONArray columns = new JSONArray();
        for (int i = 0; i < contentListArr.size(); i++) {
            StringBuilder sb = new StringBuilder();
            contentListArr.get(i).forEach(content -> sb.append(content).append("\n"));
            String content = sb.substring(0, sb.length() - 1);
            JSONObject column = new JSONObject();
            column.put("tag", "column");
            column.put("width", "weighted");
            column.put("weight", weightList.get(i));
            JSONArray elements = new JSONArray();
            JSONObject element = new JSONObject();
            element.put("tag", "markdown");
            element.put("text_align", "center");
            element.put("content", content);
            elements.add(element);
            column.put("elements", elements);
            columns.add(column);
        }
        line.put("columns", columns);
        return line;
    }

    /**
     * 构建可以跳转的按钮
     *
     * @param content 按钮展示文案
     * @param url     跳转地址
     * @return JSONObject
     */
    public static JSONObject buildButtonUrl(String content, String url) {
        JSONObject line = new JSONObject();
        line.put("tag", "action");
        JSONArray actions = new JSONArray();
        JSONObject action = new JSONObject();
        action.put("tag", "button");
        action.put("url", url);
        action.put("type", "primary");
        JSONObject text = new JSONObject();
        text.put("tag", "lark_md");
        text.put("content", content);
        action.put("text", text);
        actions.add(action);
        line.put("actions", actions);
        return line;
    }

    /**
     * 发送-卡片多列消息
     *
     * @param tenantAccessToken token
     * @param receiveId         群聊id
     * @param content           消息内容
     */
    public static String sendCardMultiColumnMessage(String tenantAccessToken, String receiveId, String content) {
        Map<String, String> headers = getHeader(tenantAccessToken);
        String requestUrl = String.format(FeiShuUrl.MESSAGE, "chat_id");
        Map<String, Object> params = new HashMap<>();
        params.put("receive_id", receiveId);
        params.put("msg_type", "interactive");
        params.put("content", content);
        log.info("requestUrl {} tenantAccessToken {} params {}", requestUrl, tenantAccessToken, JSON.toJSONString(params));
        return HttpClientUtil.doPost(requestUrl, params, headers);
    }

    /**
     * 发送普通消息
     *
     * @param tenantAccessToken token
     * @param receiveId         用户 open_id
     * @param content           消息内容
     * @return String
     */
    public static String sendMessage(String tenantAccessToken, String receiveId, String content) {
        Map<String, String> headers = getHeader(tenantAccessToken);
        String requestUrl = String.format(FeiShuUrl.MESSAGE, "open_id");
        Map<String, Object> params = new HashMap<>();
        params.put("receive_id", receiveId);
        params.put("msg_type", "text");
        params.put("content", content);
        log.info("requestUrl {} tenantAccessToken {} params {}", requestUrl, tenantAccessToken, JSON.toJSONString(params));
        String result = null;
        try {
            result = HttpClientUtil.doPost(requestUrl, params, headers);
        } catch (Exception e) {
            log.error("sendMessage fail {} {}", receiveId, content, e);
        }
        return result;
    }

    /**
     * 回复普通文本消息
     *
     * @param tenantAccessToken token
     * @param messageId         消息id
     * @param content           消息内容
     * @return String
     */
    public static String replyMessage(String tenantAccessToken, String messageId, String content) {
        Map<String, String> headers = getHeader(tenantAccessToken);
        String requestUrl = String.format(FeiShuUrl.REPLY_MESSAGE, messageId);
        Map<String, Object> params = new HashMap<>();
        params.put("msg_type", "text");
        params.put("content", content);
        log.info("requestUrl {} tenantAccessToken {} params {}", requestUrl, tenantAccessToken, JSON.toJSONString(params));
        return HttpClientUtil.doPost(requestUrl, params, headers);
    }

    /**
     * 完成任务
     *
     * @param tenantAccessToken token
     * @param taskId            任务id
     * @return String
     */
    public static String completeTask(String tenantAccessToken, String taskId) {
        Map<String, String> headers = getHeader(tenantAccessToken);
        String requestUrl = String.format(FeiShuUrl.COMPLETE_TASK, taskId);
        return HttpClientUtil.doPost(requestUrl, null, headers);
    }

    /**
     * 发送飞书的hook文本消息
     *
     * @param content 消息内容
     * @param token   获得群机器人的token
     * @return String
     */
    public static String sendHookText(String content, String token) {
        String response = null;
        try {
            String url = String.format(FeiShuUrl.HOOK_URL, token);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg_type", "text");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("text", content);
            jsonObject.put("content", jsonObject1);
            response = HttpClientUtil.doPostJson(url, JSON.toJSONString(jsonObject));
        } catch (Exception e) {
            log.warn("sendHookTextError : ", e);
        }
        return response;
    }

}

