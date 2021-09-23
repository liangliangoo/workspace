package com.xiaoxiong.mockdata.enity;

import com.github.houbb.data.factory.api.annotation.DataFactory;
import lombok.Data;

import java.util.Date;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/9/21  22:35
 */
public class User {

    @DataFactory()
    private Integer     id;
    @DataFactory
    private String      userName;
    @DataFactory
    private Integer     age;
    @DataFactory
    private Date        brith;
    @DataFactory
    private String      phone;
    @DataFactory
    private Date        gmtModified;
    @DataFactory
    private Date        gmtCreated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBrith() {
        return brith;
    }

    public void setBrith(Date brith) {
        this.brith = brith;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }
}
