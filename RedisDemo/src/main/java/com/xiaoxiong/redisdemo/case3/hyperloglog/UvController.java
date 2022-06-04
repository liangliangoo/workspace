package com.xiaoxiong.redisdemo.case3.hyperloglog;

import com.xiaoxiong.redisdemo.response.Response;
import com.xiaoxiong.redisdemo.utils.IpUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/1/22 13:55
 */
@RestController
@RequestMapping("/case3/uv")
@Slf4j
public class UvController {

    public static final Map<Integer, User> data = new HashMap<>();

    private static final String UV_IP_KEY = "ipPV";

    @Autowired
    private RedisTemplate redisTemplate;

    @PostConstruct
    public void init() {
        data.put(1, new User(1, "user_1", 18));
        data.put(1, new User(2, "user_2", 19));
        data.put(1, new User(3, "user_3", 10));
        data.put(1, new User(4, "user_4", 11));
        data.put(1, new User(5, "user_5", 12));
        data.put(1, new User(6, "user_6", 13));
    }

    @GetMapping("/ip")
    public Response<String> ipAddressUV(HttpServletRequest request) {
        Long add = null;
        try {
            add = redisTemplate.opsForHyperLogLog().add(UV_IP_KEY, IpUtil.getIpAddress(request));
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("add ==> {}", add);
        return Response.success("ok");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class User {

        private Integer id;
        private String name;
        private Integer age;

    }

}
