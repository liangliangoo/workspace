package com.xl.designPattern.flywight;

/**
 * @author liangliang
 * @date 2021/2/17 17:00
 * @e-mail 1640432919@qq.com
 */
public class Client {

    public static void main(String[] args) {
        WebSiteFactory webSiteFactory = new WebSiteFactory();
        WebSite category = webSiteFactory.getWebSiteCategory("博客");
        category.use(new User("小熊"));
    }

}
