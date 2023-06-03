package com.xiaoxiong.learning.serializable;

import java.io.*;

/**
 * @author xiongliang
 * @version 1.0
 * @description 序列化相关问题： 如果没有指定serialVersionUID 编译过程中会自动生成（生成过程会根据类的结构：实现的接口、继承的类、成员变量等信息）
 *                              其实，这个序列化ID起着关键的作用，它决定着是否能够成功反序列化！简单来说，
 *                              java的序列化机制是通过在运行时判断类的serialVersionUID来验证版本一致性的。
 *                              在进行反序列化时，JVM会把传来的字节流中的serialVersionUID与本地实体类中的serialVersionUID进行比较，
 *                              如果相同则认为是一致的，便可以进行反序列化，否则就会报序列化版本不一致的异常。
 * @since 2022/6/4  15:05
 */
public class UserSerial implements Serializable {

    private static final long serialVersionUID = -6069769474212188633L;

    private String name;
    private String age;

    public UserSerial(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public static void main(String[] args) throws IOException {
        UserSerial userSerial = new UserSerial("1", "2");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("G:\\a.txt"));
        objectOutputStream.writeObject(userSerial);
        objectOutputStream.close();
    }
}
