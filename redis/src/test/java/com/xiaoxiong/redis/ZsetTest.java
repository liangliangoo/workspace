package com.xiaoxiong.redis;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * @Author 六月
 * @Date 2023/4/14 11:03
 * @Version 1.0
 */
@Slf4j
public class ZsetTest {

    private static Jedis jedis = null;

    @Before
    public void init() {
        jedis = new Jedis("lanling-redis-test.redis.rds.aliyuncs.com", 6379);
        jedis.auth("hc7xgLcLMY");
        jedis.connect();
    }

    @Test
    public void addDataToZset_test() {
        if (Objects.isNull(jedis)) {
            System.out.println("error");
            return;
        }
        Random random = new Random(1000);
        String key = "ump:gloryCeremony:selectionCompetition";
        ArrayList<String> familyIds = Lists.newArrayList(
                "329384",
                "329385",
                "329386",
                "329387",
                "329388",
                "329389",
                "329390",
                "329391",
                "329392",
                "329393",
                "329394",
                "329395",
                "329396",
                "329397",
                "329398",
                "329399",
                "329400",
                "329401",
                "329402",
                "329403");
        Integer score = 1000;
        for (String familyId : familyIds) {
            jedis.zincrby(key, score.doubleValue(), JSON.toJSONString(familyId));
            score++;
        }

    }

    @Test
    public void test() {
        if (Objects.isNull(jedis)) {
            System.out.println("error");
            return;
        }
        Random random = new Random(1000);
        //String key = "ump:gloryCeremony:selectionCompetition";
        ArrayList<String> familyIds = Lists.newArrayList(
                "329363",
                "329364",
                "329365",
                "329366",
                "329367",
                "329368",
                "329369",
                "329608",
                "329609",
                "329610",
                "329611",
                "329612",
                "329615",
                "329616",
                "329617",
                "329618",
                "329619",
                "329327",
                "326060",
                "326079",
                "326098");
        Integer score = 1000;
        ArrayList<String> keys = Lists.newArrayList(
                "ump:gloryCeremony:knockoutCompetition",
                "ump:gloryCeremony:promotionCompetition",
                "ump:gloryCeremony:groupCompetition",
                "ump:gloryCeremony:peakBreakoutCompetition",
                "ump:gloryCeremony:peakKnockoutCompetition",
                "ump:gloryCeremony:resurrectionCompetition",
                "ump:gloryCeremony:peakDuelCompetition");
        for (String key : keys) {
            for (String familyId : familyIds) {
                jedis.zincrby(key, score.doubleValue(), JSON.toJSONString(familyId));
                score++;
            }
        }
    }


}
