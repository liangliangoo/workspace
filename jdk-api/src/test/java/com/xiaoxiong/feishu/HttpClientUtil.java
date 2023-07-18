package com.xiaoxiong.feishu;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;

@Slf4j
public class HttpClientUtil {

    /** 设置连接超时时间，单位毫秒 */
    private static final int CONNECT_TIMEOUT = 10000;

    /** 请求获取数据的超时时间(即响应时间)，单位毫秒 */
    private static final int SOCKET_TIMEOUT = 60000;

    /** 从连接池获取连接超时时间，单位毫秒 */
    private static final int CONNECT_REQUEST_TIMEOUT = 6000;

    private static final String ENCODING = "UTF-8";

    private static HttpResponse sendRequest(CloseableHttpClient httpclient, HttpUriRequest httpost)
            throws IOException {
        HttpResponse response;
        response = httpclient.execute(httpost);
        return response;
    }

    private static String parseResponse(HttpResponse response) {
        log.info("get response from http server..");
        HttpEntity entity = response.getEntity();

        log.info("response status: " + response.getStatusLine());
        Charset charset = ContentType.getOrDefault(entity).getCharset();
        if (charset != null) {
            log.info(charset.name());
        }

        String body = null;
        try {
            body = EntityUtils.toString(entity, "utf-8");
            log.info("body " + body);
        } catch (IOException e) {
            log.warn("{}: cannot parse the entity", e.getMessage());
        }

        return body;
    }

    public static String get(String url) {
        return get(url, null);
    }
    public static String get(String url, Integer socketTimeout) {
        return get(url, null, null, socketTimeout);
    }
    public static String get(String url, Map<String, Object> param, Map<String, String> header) {
        return get(url, param, header, null);
    }

    /**
     * get请求
     * @param url 请求url
     * @param param 请求参数
     * @param header header
     * @param socketTimeout 超时时间
     * @return String
     */
    public static String get(String url, Map<String, Object> param, Map<String, String> header, Integer socketTimeout) {
        String body = null;
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            url = getUrl(url, param);
            log.info("create http get:" + url);
            HttpGet get = new HttpGet(url);
            if (Objects.nonNull(socketTimeout)){
                get.setConfig(RequestConfig.custom()
                        //创建连接时间
                        .setConnectTimeout(CONNECT_TIMEOUT)
                        //设置最久等待时间 创建连接后等待对方回应时间
                        .setSocketTimeout(socketTimeout)
                        //从connect Manager（连接池）获取连接的等待时长，这个版本是共享连接池的；
                        .setConnectionRequestTimeout(CONNECT_REQUEST_TIMEOUT)
                        .build());
            }
            get.addHeader("Accept-Charset","utf-8");
            addHeader(header, get);
            HttpResponse response = sendRequest(httpClient, get);
            body = parseResponse(response);
        } catch (IOException e) {
            log.error("send get request failed: {}", e.getMessage());
        }
        return body;
    }

    /**
     * 设置请求头信息
     * @param header 请求头信息
     * @param get httpGet
     */
    private static void addHeader(Map<String, String> header, HttpGet get) {
        if (null != header && !header.isEmpty()) {
            header.entrySet().forEach(entry -> get.addHeader(entry.getKey(), entry.getValue()));
        }
    }

    /**
     * 拼接url
     * @param url 请求url
     * @param param 参数
     * @return String
     * @throws UnsupportedEncodingException
     */
    private static String getUrl(String url, Map<String, Object> param) throws UnsupportedEncodingException {
        if (Objects.nonNull(param) && !param.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder(url);
            stringBuilder.append("?");
            param.entrySet().forEach(entry->{
                stringBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            });
            url = stringBuilder.substring(0, stringBuilder.toString().length() - 1);
        }
        return url;
    }

    /**
     * post发送json字符串
     * @param url       请求地址
     * @param paramMap  参数
     * @return String
     */
    public static String sendJsonStr(String url, Map paramMap) {
        if (null == paramMap) {
            return null;
        }
        String result = null;
        StringBuffer params = new StringBuffer();
        Set<String> keySet = paramMap.keySet();
        for (String key : keySet) {
            try {
                params.append(key).append("=").append(URLEncoder.encode(paramMap.get(key).toString(), "utf-8"));
                params.append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        log.info(url + " 请求参数: {}", params.substring(0, params.toString().length() - 1));
        HttpPost httpPost = new HttpPost(url + "?" + params.substring(0, params.toString().length() - 1));
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            if (null != responseEntity) {
                result = EntityUtils.toString(responseEntity);
            }
        } catch (Exception e) {
            log.error("调用 {} 异常: {}", url, e);
        } finally {
            try {
                // 释放资源
                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * POST请求，form表单格式
     *
     * @param url      请求地址
     * @param paramMap 参数
     * @return String
     */
    public static String doPostWithForm(String url, Map<String, Object> paramMap) {
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            if (Objects.isNull(entry.getValue())) {
                continue;
            }
            map.put(entry.getKey(), entry.getValue().toString());
        }
        return HttpClientUtil.doPost(url, map);
    }

    /**
     * post方式请求，请求为map
     * @param url   请求地址
     * @param param 参数
     * @return  String
     */
    public static String doPost(String url, Map<String, String> param) {
        log.info(url + " 请求参数: {}", JSON.toJSONString(param));
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
//            httpPost.setProtocolVersion(HttpVersion.HTTP_1_0);
//            httpPost.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "utf-8");
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            log.info(url + " 返回结果: {}", resultString);
        } catch (Exception e) {
            log.error(url + " 请求异常: ", e);
        } finally {
            try {
                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
                log.error(url + " 请求，关闭CloseableHttpResponse异常: ", e);
            }
        }

        return resultString;
    }

    public static String doPost(String url, Map<String, Object> param, Map<String, String> header) {
        return doPost(url, param, header, SOCKET_TIMEOUT);
    }
    public static String doPost(String url, Map<String, Object> param,Map<String,String> header, int socketTimeout) {
        return doPost(url, param, header, socketTimeout, null);
    }

    /**
     * 添加可以配置header的post请求
     *
     * @param url           请求地址
     * @param param         参数
     * @param header        请求头
     * @param socketTimeout 超时时间
     * @param retryCount    重试次数
     * @return String
     */
    public static String doPost(String url, Map<String, Object> param, Map<String, String> header, int socketTimeout, Integer retryCount) {
        log.info(url + " 请求参数: {}", JSON.toJSONString(param));
        CloseableHttpClient httpClient;
        if (Objects.isNull(retryCount) || retryCount <= 0) {
            httpClient = HttpClients.createDefault();
        } else {
            DefaultHttpRequestRetryHandler defaultHttpRequestRetryHandler = new DefaultHttpRequestRetryHandler(retryCount, false);
            httpClient = HttpClientBuilder.create().setRetryHandler(defaultHttpRequestRetryHandler).build();
        }
        CloseableHttpResponse response = null;
        String resultString = "";

        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(RequestConfig.custom()
                    //创建连接时间
                    .setConnectTimeout(CONNECT_TIMEOUT)
                    //设置最久等待时间 创建连接后等待对方回应时间
                    .setSocketTimeout(socketTimeout)
                    //从connect Manager（连接池）获取连接的等待时长，这个版本是共享连接池的；
                    .setConnectionRequestTimeout(CONNECT_REQUEST_TIMEOUT)
                    .build());
            if (null != header && !header.isEmpty()) {
                header.keySet().forEach(headerKey -> {
                    httpPost.addHeader(headerKey,header.get(headerKey));
                });
            }
            if (null != param && !param.isEmpty()){
                Iterator var7 = param.keySet().iterator();
                Map<String, Object> body = new HashMap<>();
                while(var7.hasNext()) {
                    String key = (String)var7.next();
                    body.put(key,param.get(key));
                }
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(body);
                HttpEntity httpEntity = new StringEntity(json, "utf-8");
                httpPost.setEntity(httpEntity);
            }

            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            log.info(url + " 返回结果: {}", resultString);
        } catch (Exception var17) {
            log.error(url + " 请求异常: ", var17);
        } finally {
            try {
                if (null != response) {
                    response.close();
                }
            } catch (IOException var16) {
                log.error(url + " 请求，关闭CloseableHttpResponse异常: ", var16);
            }

        }

        return resultString;
    }

    public static String doPost(String url) {
        return doPost(url, null);
    }

    /**
     * post请求，参数为json
     * @param url   请求地址
     * @param json  参数
     * @return  String
     */
    public static String doPostJson(String url, String json) {
        return doPost(url, json, ContentType.APPLICATION_JSON, -1, null);
    }
    public static String doPost(String url, String string, ContentType contentType,int socketTimeout, Integer retryCount) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient;
        if (Objects.isNull(retryCount) || retryCount <= 0) {
            httpClient = HttpClients.createDefault();
        } else {
            DefaultHttpRequestRetryHandler defaultHttpRequestRetryHandler = new DefaultHttpRequestRetryHandler(retryCount, false);
            httpClient = HttpClientBuilder.create().setRetryHandler(defaultHttpRequestRetryHandler).build();
        }
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            if (socketTimeout != -1) {
                httpPost.setConfig(RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(socketTimeout).setConnectionRequestTimeout(CONNECT_REQUEST_TIMEOUT).build());
            }
            // 创建请求内容
            StringEntity entity = new StringEntity(string, contentType);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            log.error(url + "请求异常: ", e);
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    log.error(url + "请求，关闭CloseableHttpResponse异常: ", e);
                }
            }
        }

        return resultString;
    }

    /**
     * post请求，参数为json，且支持设置请求头
     * @param url    url地址
     * @param json   jsonBody
     * @param headers 请求头
     * @return  String
     */
    public static String doPostJsonAndSetHeaders(String url, String json, Map<String,String> headers) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 设置请求头
            if (!CollUtil.isEmpty(headers)) {
                headers.keySet().forEach(headerKey -> httpPost.addHeader(headerKey, headers.get(headerKey)));
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            log.error(url + "请求异常: ", e);
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    log.error(url + "请求，关闭CloseableHttpResponse异常: ", e);
                }
            }
        }

        return resultString;
    }

    public static void main2(String[] args) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        headers.put("Authorization", "Bearer t-06e0f13da246e44d62d11960aa992eea329084a3");

        String url = "https://open.feishu.cn/open-apis/message/v4/send/";

        String jsonBody = "{\"chat_id\":\"oc_398688a4cacfd5d6c39a8f37aad228f8\",\"msg_type\":\"interactive\",\"update_multi\":false,\"card\":{\"config\":{\"wide_screen_mode\":true},\"header\":{\"title\":{\"tag\":\"plain_text\",\"content\":\"约会活动\"},\"template\":\"Green\"},\"elements\":[{\"tag\":\"div\",\"text\":{\"tag\":\"plain_text\",\"content\":\"\"}},{\"tag\":\"div\",\"fields\":[{\"is_short\":true,\"text\":{\"tag\":\"lark_md\",\"content\":\"**用户名:** 听风39\\n\"}},{\"is_short\":true,\"text\":{\"tag\":\"lark_md\",\"content\":\"**id:** 102513\\n\"}}]},{\"tag\":\"div\",\"fields\":[{\"is_short\":true,\"text\":{\"tag\":\"lark_md\",\"content\":\"**性别:** 男\\n\"}},{\"is_short\":true,\"text\":{\"tag\":\"lark_md\",\"content\":\"**榜单:** 第一\\n\"}}]},{\"tag\":\"div\",\"fields\":[{\"is_short\":true,\"text\":{\"tag\":\"lark_md\",\"content\":\"**时间:** 2021-03-11 12:36\\n\"}}]}]}}";
        String s = doPostJsonAndSetHeaders(url, jsonBody, headers);
        System.out.println(s);
    }

    /**
     * 发送 http put 请求，参数以原生字符串进行提交
     * @param url       连接地址
     * @param jsonStr   请求参数
     * @return  String
     */
    public static String doPutJson(String url, String jsonStr){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setConnectionRequestTimeout(CONNECT_REQUEST_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
        httpPut.setConfig(requestConfig);
        httpPut.setHeader("Content-type", "application/json");
        httpPut.setHeader("DataEncoding", "UTF-8");
        CloseableHttpResponse httpResponse = null;
        String result = "";
        try {
            httpPut.setEntity(new StringEntity(jsonStr));
            httpResponse = httpClient.execute(httpPut);
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            log.error(url + "请求 ClientProtocol 异常 -> ", e);
        } catch (IOException e) {
            log.error(url + "请求 IO 异常 -> ", e);
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    log.error(url + "请求 IO 异常 -> ", e);
                }
            }
        }
        return result;
    }

    /**
     * put
     *
     * @param url 请求地址
     * @return String
     */
    public static String doPut(String url) {
        return doPut(url, null, null);
    }

    /**
     * put (带参数)
     *
     * @param url    请求地址
     * @param params 参数
     * @return String
     */
    public static String doPut(String url, Map<String, Object> params) {
        return doPut(url, params, null);
    }

    /**
     * put (带参数、请求头)
     *
     * @param url     请求地址
     * @param params  参数
     * @param headers 请求头
     * @return String
     */
    public static String doPut(String url, Map<String, Object> params, Map<String, String> headers) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);
        RequestConfig config = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).setConnectionRequestTimeout(CONNECT_REQUEST_TIMEOUT).build();
        httpPut.setConfig(config);
        addHeader(httpPut, headers);
        addParam(httpPut, params);
        return execute(httpClient, httpPut, url);
    }

    /**
     * patch (带参数、请求头
     * )
     * @param url     请求地址
     * @param params  参数
     * @param headers 请求头
     * @return String
     */
    public static String doPatch(String url, Map<String, Object> params, Map<String, String> headers) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPatch httpPatch = new HttpPatch(url);
        RequestConfig config = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).setConnectionRequestTimeout(CONNECT_REQUEST_TIMEOUT).build();
        httpPatch.setConfig(config);
        addHeader(httpPatch, headers);
        addParam(httpPatch, params);
        return execute(httpClient, httpPatch, url);
    }

    public static void addHeader(HttpUriRequest httpUriRequest, Map<String, String> headers) {
        if (CollUtil.isEmpty(headers)) {
            return;
        }
        headers.forEach(httpUriRequest::addHeader);
    }

    public static void addParam(HttpEntityEnclosingRequestBase httpMethod, Map<String, Object> params) {
        if (CollUtil.isEmpty(params)) {
            return;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(params);
            HttpEntity httpEntity = new StringEntity(json, ENCODING);
            httpMethod.setEntity(httpEntity);
        } catch (JsonProcessingException e) {
            log.error("setEntity error", e);
        }
    }

    public static String execute(CloseableHttpClient httpClient, HttpUriRequest request, String url) {
        CloseableHttpResponse httpResponse = null;
        String result = "";
        try {
            httpResponse = httpClient.execute(request);
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            log.error(url + "请求 ClientProtocol 异常 -> ", e);
        } catch (IOException e) {
            log.error(url + "请求 IO 异常 -> ", e);
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    log.error(url + "请求 IO 异常 -> ", e);
                }
            }
        }
        return result;
    }

}
