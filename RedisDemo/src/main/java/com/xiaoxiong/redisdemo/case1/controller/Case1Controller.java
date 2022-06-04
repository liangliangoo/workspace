package com.xiaoxiong.redisdemo.case1.controller;

import com.google.common.collect.Maps;
import com.xiaoxiong.redisdemo.constant.Case1Constants;
import com.xiaoxiong.redisdemo.response.Response;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/1/17 11:38
 */
@Api
@RestController
@RequestMapping("case1")
public class Case1Controller {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 初始化数据
     */
    @GetMapping("/init")
    public void init() {
        Map<String, Point> map = Maps.newHashMap();
        map.put("XinYang", new Point(114.05857, 32.11683));
        map.put("WuHan", new Point(114.02919, 30.58203));
        map.put("LuoYang", new Point(112.51078, 34.70431));
        map.put("ZhengZhou", new Point(113.6401, 34.72468));
        map.put("KaiFeng", new Point(114.34816, 34.78861));
        map.put("NanYang", new Point(112.5396, 33.0036));
        this.redisTemplate.opsForGeo().add(Case1Constants.HeNanCity, map);
    }

    /**
     * 获取目标地点的经纬度
     *
     * @param member 目标地点
     * @return
     */
    @GetMapping("/position")
    public Response<Point> position(String member) {
        List<Point> list = redisTemplate.opsForGeo().position(Case1Constants.HeNanCity, member);
        return list.isEmpty() ? Response.fail("the " + member + "does exit") :
                Response.success(list.get(0));
    }

    /**
     * 获取位置的hash值
     *
     * @param member
     * @return Response<String>
     */
    @GetMapping("/hash")
    public Response<String> hash(String member) {
        List<String> list = redisTemplate.opsForGeo().hash(Case1Constants.HeNanCity, member);
        return list.isEmpty() ? Response.fail("the " + member + "does exit") :
                Response.success("server success", list.get(0));
    }

    /**
     * 计算两地之间的距离
     *
     * @param member1 地点1
     * @param member2 地点2
     * @return
     */
    @GetMapping("/distance")
    public Response<Distance> distance(String member1, String member2) {
        Distance distance = redisTemplate.opsForGeo().distance(Case1Constants.HeNanCity,
                member1, member2, Metrics.KILOMETERS);
        return Response.success(distance);
    }

    /**
     * 通过经纬度查找附近
     *
     * @return
     */
    @GetMapping("/radius")
    public Response<GeoResults> radius() {
        Circle circle = new Circle(114.071023, 32.146983, Metrics.KILOMETERS.getMultiplier());
        RedisGeoCommands.GeoRadiusCommandArgs limit = RedisGeoCommands.GeoRadiusCommandArgs
                .newGeoRadiusArgs()
                .includeDistance()
                .includeCoordinates()
                .sortAscending()
                .limit(50);
        GeoResults results = redisTemplate.opsForGeo().radius(Case1Constants.HeNanCity, circle, limit);
        return Response.success(results);
    }

    /**
     * 获取周边
     *
     * @param member
     * @return
     */
    @GetMapping("/radiusByMember")
    public Response<GeoResults> radiusByMember(String member) {
        Distance distance = new Distance(10000, Metrics.KILOMETERS);
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs
                .newGeoRadiusArgs()
                .includeDistance()
                .includeCoordinates()
                .sortAscending()
                .limit(100);
        GeoResults radius = redisTemplate.opsForGeo().radius(Case1Constants.HeNanCity, member, distance, args);
        return Response.success(radius);
    }


}
