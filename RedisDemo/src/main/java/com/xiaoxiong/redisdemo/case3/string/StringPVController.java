package com.xiaoxiong.redisdemo.case3.string;

import com.xiaoxiong.redisdemo.constant.Case3Constant;
import com.xiaoxiong.redisdemo.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/1/21 16:12
 */
@RestController
@RequestMapping("/case3/string")
@Slf4j
public class StringPVController {

    private static final String KEY_PV = Case3Constant.CASE3_STRING_KEY;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostConstruct
    public void init() {
        stringRedisTemplate.opsForValue().append(Case3Constant.CASE3_STRING_KEY, "0");
    }

    @GetMapping("/view")
    public Response<String> view(Integer pageID) {
        Assert.notNull(pageID, "pageId must not be null");
        // TODO: 2022/1/21 判断文章ID是否存在
        String key = KEY_PV + ":" + pageID;
        try {
            Boolean flag = stringRedisTemplate.hasKey(key);
            if (flag) {
                Long count = stringRedisTemplate.opsForValue().increment(key);
                log.info("key：{},阅读量是：{}", key, count);
            } else {
                stringRedisTemplate.opsForValue().append(key, "1");
                log.info("key：{},阅读量是：{}", key, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.success("ok");
    }


}
