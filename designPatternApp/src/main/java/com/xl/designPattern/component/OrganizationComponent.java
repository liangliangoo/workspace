package com.xl.designPattern.component;

/**
 * @author liangliang
 * @date 2021/2/14 20:25
 * @e-mail 1640432919@qq.com
 */
public abstract class OrganizationComponent {

    private String name;

    private String description;

    public OrganizationComponent(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    protected void add(OrganizationComponent object) {
        throw new UnsupportedOperationException();
    }

    protected void remove(OrganizationComponent object) {
        throw new UnsupportedOperationException();
    }

    protected abstract void show();


}
