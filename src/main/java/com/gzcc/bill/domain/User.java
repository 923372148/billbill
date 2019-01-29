package com.gzcc.bill.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Document
public class User implements Serializable {

@Id
    private String userId;
private String openId;
private String userName;
private List<ObjectId> personalBillId;
private String password;
private Date lastTimeLogin;

    public Date getLastTimeLogin() {
        return lastTimeLogin;
    }

    public void setLastTimeLogin(Date lastTimeLogin) {
        this.lastTimeLogin = lastTimeLogin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String phone;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<ObjectId> getPersonalBillId() {
        return personalBillId;
    }

    public void setPersonalBillId(List<ObjectId> personalBillId) {
        this.personalBillId = personalBillId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
