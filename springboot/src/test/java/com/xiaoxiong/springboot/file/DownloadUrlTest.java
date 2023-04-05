package com.xiaoxiong.springboot.file;

import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import com.alibaba.fastjson2.JSONObject;
import okhttp3.*;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
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

    private static final String DOWNLOAD_URL = "https://branch-exports-web.s3.us-west-1.amazonaws.com/api_export/y%3D2023/m%3D03/d%3D31/app_id%3D1134320335663944013/topic%3Deo_open/1134320335663944013-2023-03-31-eo_open-v2-76f7dee6264df95426cf296e21224f9cde42a6ee974ff42bef84fd23a729741c-8W2Ejg-0.csv.gz?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230401T041859Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604800&X-Amz-Credential=AKIA3HUFQARV6ESYJ4HX%2F20230401%2Fus-west-1%2Fs3%2Faws4_request&X-Amz-Signature=27f307cd305dec6780d63d1f1ab85b8838837ead1014f37d952a7ca617852277";

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
        for (CsvRow r : rows) {
            System.out.println(r);
        }
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

}
