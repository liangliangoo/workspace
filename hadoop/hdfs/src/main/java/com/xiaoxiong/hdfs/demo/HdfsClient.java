package com.xiaoxiong.hdfs.demo;

import lombok.Getter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/3  10:19
 */
public class HdfsClient {

    public static final String URIADDR = "hdfs://192.168.44.131:8020";
    public static final String USER    = "root";
    @Getter
    private  FileSystem  fileSystem;

    public static void main(String[] args) {
        HdfsClient hdfsClient = new HdfsClient();
        try {
            hdfsClient.initClient();
            hdfsClient.put_test();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                hdfsClient.destroyClient();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建文件夹
     * @throws IOException
     */
    public void createDir_test() throws IOException {
        fileSystem.mkdirs(new Path("/test_hdfs"));
    }


    public void put_test() throws IOException {
        fileSystem.copyFromLocalFile(false,true,new Path("E:\\DownLoad\\迅雷下载\\hadoop-winutils-master\\3.3.0\\Readme-CN.md"), new Path("/test_hdfs/"));
    }

    public void get_test() throws IOException {
        fileSystem.copyToLocalFile(new Path("a"), new Path("/"));
    }

    private void initClient() throws URISyntaxException, IOException, InterruptedException {
        Configuration configuration = new Configuration();
        fileSystem = FileSystem.get(new URI(URIADDR), configuration, USER);
    }

    private void destroyClient() throws IOException {
        fileSystem.close();
    }

}
