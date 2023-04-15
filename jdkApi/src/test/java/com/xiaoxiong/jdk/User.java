package com.xiaoxiong.jdk;

import lombok.*;

/**
 * @Author 六月
 * @Date 2023/4/13 17:33
 * @Version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class User {

    private String userName = "小熊";

    @Builder.Default
    private Long time = 1L;

}
