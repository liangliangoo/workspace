package com.xiaoxiong.springboot.file;

import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import okhttp3.*;
import org.apache.commons.io.IOUtils;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

/**
 * http 请求下载文件接口
 *
 * @Author 六月
 * @Date 2023/4/3 19:31
 * @Version 1.0
 */
public class DownloadUrlTest {

    private static final String getDownloadUrl = "https://api2.branch.io/v3/export";

    private static final String DOWNLOAD_URL = "https://branch-exports-web.s3.us-west-1.amazonaws.com/api_export/y%3D2023/m%3D04/d%3D10/app_id%3D1134320335663944013/topic%3Deo_install/1134320335663944013-2023-04-10-eo_install-v2-f37c0ad63fdadc7b94c613c2bca391d4e58d4b4a32c0262f2d1a4a853bc4a17a-wfQ6pC-0.csv.gz?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230411T041916Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604800&X-Amz-Credential=AKIA3HUFQARV6ESYJ4HX%2F20230411%2Fus-west-1%2Fs3%2Faws4_request&X-Amz-Signature=b95f817125e677810c63fba4f7ef6ba8a14b81705e91487e5b941dd769d2a368";

    @Test
    void test_getDownloadUrl() throws IOException {
        OkHttpClient client = new OkHttpClient();
        JSONObject postParams = new JSONObject();
        postParams.put("branch_key", "key_live_aj07HY6r5GVRutuD9x0b4cghsuosCn3A");
        postParams.put("branch_secret", "secret_live_G6CalBGfozhOXckeh1U3ElPXR97epYjM");
        postParams.put("export_date", "2023-03-31");
        Request request = new Request.Builder()
                .url(getDownloadUrl)
                .post(RequestBody.create(MediaType.parse("application/json"), postParams.toJSONString()))
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .build();
        System.out.println(client.newCall(request).execute().body().string());
    }

    @Test
    void test_requestDownloadUrl() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().get().url(DOWNLOAD_URL).build();
        Response response = client.newCall(request).execute();
        //System.out.println(response.body().string());
        GZIPInputStream inputStream = new GZIPInputStream(response.body().byteStream());
        //List<List<Object>> lists = FileHandler.parseExcel(inputStream);
        File file = new File("./a.csv");
        copyInputStreamToFile(inputStream, file);
        //file.createNewFile();
        CsvReader reader = CsvUtil.getReader();
        CsvData data = reader.read(file);
        List<CsvRow> rows = data.getRows();
        CsvRow row = rows.get(0);
        Object[] objects = row.stream().toArray();
        for (int i = 0; i < objects.length; i++) {
            System.out.println(i + " \t" + objects[i].toString());
        }
        // last_attributed_touch_data_tilde_advertising_partner_name
        // last_attributed_touch_data_tilde_channel
        //Long total = 0L;
        //Long hasId = 0L;
        //Set<String> sets = new HashSet<>();
        //for (CsvRow r : rows) {
        //    if ("ANDROID".equals(r.get(75))) {
        //        System.out.println(String.format(" id %s utm_source -> %s   channle -> %s",r.get(92), r.get(15), r.get(11)));
        //    }
        //    if (StringUtils.isNotBlank(r.get(92))) {
        //        hasId++;
        //    }
        //    total++;
        //    sets.add(r.get(9));
        //}
        // ios
        // user_data_idfv 87
        // user_data_idfa 86
        // 安卓
        // user_data_android_id 88
        // user_data_aaid 85
        //System.out.println(total + "-----" + hasId);
        //System.out.println(JSON.toJSON(sets));
        // user_data_developer_identity
        // user_data_os
    }


    // InputStream -> File
    private static FileOutputStream copyInputStreamToFile(InputStream inputStream, File file)
            throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            // 传统方式： inputStream -> outputStream
            //int read;
            //byte[] bytes = new byte[1024];
            //
            //while ((read = inputStream.read(bytes)) != -1) {
            //    outputStream.write(bytes, 0, read);
            //}

            // commons-io
            IOUtils.copy(inputStream, outputStream);
            return outputStream;
        }
    }

    @Test
    void urlDecode() {
        String json = "{\"ad_id\":\"undisclosed\",\"account_id\":\"undisclosed\",\"compaign_id\":\"undisclosed\",\"adset_name\":\"undisclosed\",\"adset_id\":\"undisclosed\",\"ad_name\":\"undisclosed\",\"compaign_name\":\"undisclosed\",\"utm_source\":\"undisclosed\"}\n";
        Map<String,Object> param = JSON.parseObject(json, Map.class);
        System.out.println(param);
    }

    @Test
    void test_jsonToArray() {
        String json = "{}";
        JSONArray jsonArray = JSONObject.parseObject(json).getJSONArray("1");
        System.out.println(jsonArray);
    }

    @Test
    void test() {
        ArrayList<Double> doubles = Lists.newArrayList(11d, 12d, 53d, 1d, 10d, 0d, 2d, 3d);
        List<Double> doubles1 = doubles.subList(0, 4);
        List<Double> doubles2 = doubles.subList(4, 8);
        List<Double> collect = doubles.stream().sorted(((o1, o2) -> o2.compareTo(o1))).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    void test11() {

        List<User> list = Lists.newArrayList(new User(1L), new User(2L), new User(3L), new User(4L));

        List<User> collect = list.stream().filter(item -> Objects.equals(item.getId(), 1L)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(collect)) {
            collect.get(0).setId(100L);
        }
        System.out.println(JSON.toJSON(list));
    }

    @Test
    void test12() {
        Map<String, Double> map = Maps.newHashMap("1", 1.0);
        map.put("2", 2.0);
        map.put("3", 3.0);
        //map.entrySet().stream().sorted((o1,o2) -> o)
    }

    @Test
    void test13() {
        String CHOOSE_OFFICIAL_COMBINE_MSG = "叮~对方想要邀请你官宣，官宣福利发放中，所有福利将会在完成官宣后发放到双方账户中，\uD83D\uDC49 " +
                "<a href%3D\"%s\">点击查收</a> \uD83D\uDC48";
        System.out.println(String.format(CHOOSE_OFFICIAL_COMBINE_MSG, 111));
    }


}
