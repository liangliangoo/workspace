package com.xiaoxiong.entity;

import com.xiaoxiong.drools.entity.Order;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @Author 六月
 * @Date 2022/9/20 22:16
 * @Version 1.0
 */
public class DroolsOrderTest {

    @Test
    public void test1() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();

        // 创建会话对象
        KieSession kieSession = kieClasspathContainer.newKieSession();

        // 构造订单对象
        Order order = new Order();
        order.setOriginalPrice(Double.parseDouble("99.99"));

        // 将数据提供给模版引擎 规则引擎会根据提供的数据进行规则匹配
        kieSession.insert(order);

        // 激活规则引擎 如果规则匹配成功 则执行
        kieSession.fireAllRules();

        // 关闭会话
        kieSession.dispose();

        System.out.println(order);
    }

    @Test
    public void test2() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        //会话对象，用于和规则引擎交互
        KieSession kieSession = kieClasspathContainer.newKieSession();

        //构造订单对象，设置原始价格，由规则引擎根据优惠规则计算优惠后的价格
        Order order = new Order();
        order.setOriginalPrice(210D);

        //将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配
        kieSession.insert(order);

        //激活规则引擎，如果规则匹配成功则执行规则
        kieSession.fireAllRules();
        //关闭会话
        kieSession.dispose();

        System.out.println("优惠前原始价格：" + order.getOriginalPrice() +
                "，优惠后价格：" + order.getRealPrice());
    }

}
