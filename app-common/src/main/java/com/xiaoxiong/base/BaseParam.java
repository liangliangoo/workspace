package com.xiaoxiong.base;

/**
 * @Author 六月
 * @Date 2023/1/25 18:48
 * @Version 1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Objects;

/**
 * 基础入参
 *
 * @author: lianghu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseParam implements Serializable {

    /**
     * 应用组 ID（必填）
     */
    private Long appId;
    /**
     * 应用 ID（必填）
     */
    private String unionId;
    /**
     * 应用渠道（非必填）
     */
    private String vestChannel;
    /**
     * 用户 ID（必填）
     */
    private Long uid;

    public Boolean check() {
        if (Objects.isNull(appId) || Objects.isNull(uid) || StringUtils.isBlank(unionId)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public void checkWithException() {
        if (!check()) {
            throw new RuntimeException();
        }
    }

}

