package com.xiaoxiong.designPattern.prototype.deptcopy;

import java.io.*;

/**
 * @author liangliang
 * @date 2021/2/2 15:45
 * @e-mail 1640432919@qq.com
 */
public class DeepProtoType implements Serializable,Cloneable {

    public String name;

    public DeepCloneableTable deepCloneableTable;

    public DeepProtoType() {
    }

    //深拷贝实现1:使用clone方法
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object deep = null;
        //完成基本数据类型的克隆
        deep = super.clone();
        //处理引用数据类型
        DeepProtoType deepProtoType = (DeepProtoType) deep;
        deepProtoType.deepCloneableTable = (DeepCloneableTable) deepCloneableTable.clone();
        return deepProtoType;
    }

    // 实现方式二：序列化
    public Object deepClone() {
        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        DeepProtoType deepProtoType = null;
        try {
            //序列化
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(this);//将当前对象以对象流的形式输出

            // 反序列化
            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            deepProtoType = (DeepProtoType) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                byteArrayOutputStream.close();
                objectOutputStream.close();
                byteArrayInputStream.close();
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return deepProtoType;
    }

}
