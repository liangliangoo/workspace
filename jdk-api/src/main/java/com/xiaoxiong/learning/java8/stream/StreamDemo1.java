package com.xiaoxiong.learning.java8.stream;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/9/8 22:57
 */
public class StreamDemo1 {

    public static void main(String[] args) {
        System.out.println("hello world");
    }


}

@Data
@Accessors(chain = true)
class User {
    private Integer id;
    private String userName;
    private List<String> parent;
    private Integer age;
}
