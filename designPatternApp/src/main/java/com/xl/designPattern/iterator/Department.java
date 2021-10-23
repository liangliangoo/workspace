package com.xl.designPattern.iterator;

/**
 * @author liangliang
 * @date 2021/3/6 21:56
 * @e-mail 1640432919@qq.com
 */
public class Department {

    private String name;
    private String description;

    public Department(String name, String description) {
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
}
