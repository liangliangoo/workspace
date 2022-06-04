package com.xiaoxiong.redisdemo.case4;

import com.alibaba.fastjson.JSON;
import com.xiaoxiong.redisdemo.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/1/22 18:03
 */
@RestController
@RequestMapping("/case4")
@Slf4j
public class ZSetController {

    private static final String RANK_LIST = "rankList";

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserService userService;

    @GetMapping("/rankList")
    public Response<String> rankingList() {
        Set<ZSetOperations.TypedTuple<String>> tuples = new HashSet<>();
        List<UserService.User> users = userService.selectAllUser();
        if (!CollectionUtils.isEmpty(users)) {
            for (UserService.User u : users) {
                DefaultTypedTuple<String> typedTuple = new DefaultTypedTuple<>(u.getUserName(), u.getScore());
                tuples.add(typedTuple);
            }
        }
        redisTemplate.opsForZSet().add(RANK_LIST, tuples);
        Set<ZSetOperations.TypedTuple<String>> data = redisTemplate.opsForZSet().reverseRangeByScore(RANK_LIST, 0, 100);
        return Response.success("ok", JSON.toJSONString(data));
    }

}
