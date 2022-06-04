package com.xiaoxiong.redisdemo.case4;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/1/22 18:06
 */
@Service
public class UserService {

    private static final List<User> data = new ArrayList<>();

    @PostConstruct
    public void initData() {
        data.add(new User(1, "小明", 89.0));
        data.add(new User(2, "小熊", 81.0));
        data.add(new User(3, "小亮", 80.0));
        data.add(new User(4, "小姜", 90.0));
        data.add(new User(5, "小红", 89.0));
        data.add(new User(6, "小萍", 100.0));
        data.add(new User(7, "小旭", 78.0));
    }

    public List<User> selectAllUser() {
        return data;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class User implements Serializable {

        private Integer id;
        private String userName;
        private Double score;

    }
}
