package com.xl.designPattern.component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liangliang
 * @date 2021/2/14 20:36
 * @e-mail 1640432919@qq.com
 */
public class College extends OrganizationComponent{

    List<OrganizationComponent> organizationComponents = new ArrayList<>();

    public College(String name, String description) {
        super(name, description);
    }

    @Override
    protected void add(OrganizationComponent object) {
        organizationComponents.add(object);
    }

    @Override
    protected void remove(OrganizationComponent object) {
        organizationComponents.remove(object);
    }

    //输出University包含的组织
    @Override
    protected void show() {
        System.out.println("----------" + getName() + "----------");
        for (OrganizationComponent component : organizationComponents) {
            component.show();
        }
    }
}
