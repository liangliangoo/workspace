package com.xl.designPattern.prototype;

/**
 * @author liangliang
 * @date 2021/2/2 10:00
 * @e-mail 1640432919@qq.com
 */
public class Sheep implements Cloneable {

    private String name;

    private int age;

    private String color;

    public Sheep a;

    public Sheep(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    protected Object clone()  {
        Sheep sheep = null;

        try {
            sheep = (Sheep) super.clone();

        } catch (CloneNotSupportedException e) {

            System.out.println(e.getMessage());
        }
        return sheep;
    }

}
