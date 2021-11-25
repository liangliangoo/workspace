package com.xl.designPattern.prototype.deptcopy;

/**
 * @author liangliang
 * @date 2021/2/2 15:55
 * @e-mail 1640432919@qq.com
 */
public class Client {

    public static void main(String[] args) throws CloneNotSupportedException {
        DeepProtoType p = new DeepProtoType();
        p.name = "xiaoxiong";
        p.deepCloneableTable = new DeepCloneableTable("xiaobai","xiaobeiClone");
        DeepProtoType clone = (DeepProtoType) p.deepClone();
        System.out.println(clone.deepCloneableTable.hashCode() == p.deepCloneableTable.hashCode());
    }

}
