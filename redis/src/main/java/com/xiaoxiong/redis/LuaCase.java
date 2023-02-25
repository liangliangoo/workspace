package com.xiaoxiong.redis;

import redis.clients.jedis.Jedis;

import java.util.Objects;

/**
 * @Author 六月
 * @Date 2023/2/8 21:40
 * @Version 1.0
 */
public class LuaCase {

    private static final String redPacketScript = "local tmp1 = redis.call('rpop',KEYS[]);" +
            "if tmp1 then  ";
    private static Jedis jedis;

    //public static void main(String[] args) {
    //    LuaCase luaCase = new LuaCase();
    //    luaCase.testLua1();
    //}

    public static void main(String[] args) {
        Long a = new Long(1);

        System.out.println(Objects.equals(a, 1L));
        System.out.println(a == 1);

        Long aLong = new Long(12);
        System.out.println(aLong > 1000L);
    }

    private static Jedis getJedis() {
        jedis = new Jedis("127.0.0.1", 6379);
        jedis.connect();
        return jedis;
    }

    private void testLua1() {
        // 1、加载lua 脚本
        //String sha = jedis.scriptLoad("");
        jedis = getJedis();
        Object eval = jedis.eval("local tmp={}\n tmp[1]=ARGV[1]\n tmp[2]=ARGV[2]\n " +
                "return tmp", 0, "1", "2");
        System.out.println(eval.toString());

    }

}
