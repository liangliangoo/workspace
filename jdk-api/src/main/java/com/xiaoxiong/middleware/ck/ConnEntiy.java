package com.xiaoxiong.middleware.ck;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author 六月
 * @Date 2023/8/18 12:17
 * @Version 1.0
 */
@AllArgsConstructor
@Data
public class ConnEntiy {

    // 驱动器
    private String driverName;
    // 连接对象地址
    private String url;
    // 用户
    private String user;
    // 密码
    private String password;

}
