package com.xiaoxiong.webfluxclient.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/5/11  19:44
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServerInfo {

    private String url;

}
