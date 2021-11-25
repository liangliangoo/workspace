package com.xl.designPattern.flywight;

/**
 * @author liangliang
 * @date 2021/2/17 16:53
 * @e-mail 1640432919@qq.com
 */
public class ConcreteWebSite extends WebSite {

    private String type = "";

    public ConcreteWebSite(String type) {
        this.type = type;
    }

    @Override
    public void use(User user) {
        System.out.println(user.getName() + "正在使用" + "网站发布类型：" + this.type);
    }
}
