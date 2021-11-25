package com.xl.designPattern.prototype.deptcopy;

import java.io.Serializable;

/**
 * @author liangliang
 * @date 2021/2/2 15:37
 * @e-mail 1640432919@qq.com
 */
public class DeepCloneableTable implements Serializable, Cloneable {

    private static final  long serialVersionUID = 1L;

    private String cloneName;

    private String cloneClass;

    public DeepCloneableTable(String cloneName, String cloneClass) {
        this.cloneName = cloneName;
        this.cloneClass = cloneClass;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
