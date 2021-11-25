package com.xl.designPattern.component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liangliang
 * @date 2021/2/14 20:39
 * @e-mail 1640432919@qq.com
 */
public class Department extends OrganizationComponent{

    List<OrganizationComponent> organizationComponents = new ArrayList<>();

    public Department(String name, String description) {
        super(name, description);
    }

    //输出University包含的组织
    @Override
    protected void show() {
        System.out.println("----------" + getName() + "----------");
    }

}
