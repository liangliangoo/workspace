package com.xiaoxiong.dtsp;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2023/2/4  15:04
 */
@Slf4j
//@NacosPropertySource(dataId = "dtp-services", autoRefreshed = true)
//@EnableNacosConfig
@RefreshScope
@SpringBootApplication
public class DtpApplication {

    @NacosValue("${dtp.name:dtp-services}")
    private String dtpName;

    public static void main(String[] args) {
        SpringApplication.run(DtpApplication.class, args);
    }



}
