package com.gzcc.kindmanager.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document
public class RunningAccount implements Serializable  {
    @Id
    private ObjectId runningAccountId;

    public ObjectId getRunningAccountId() {
        return runningAccountId;
    }

    public void setRunningAccountId(ObjectId runningAccountId) {
        this.runningAccountId = runningAccountId;
    }

    public RunningAccount(long money, boolean ifIncome,String comment ,Date date) {
        this.money = money;
        this.ifIncome = ifIncome;
  this.comment=comment ;
        this.date = date;
    }

    public RunningAccount(long money, String comment) {
        this.money = money;

        this.comment = comment;
    }

    public RunningAccount() {
    }

    public boolean isIfIncome() {
        return ifIncome;
    }

    public void setIfIncome(boolean ifIncome) {
        this.ifIncome = ifIncome;
    }

    private ObjectId kindId;
    private long money;
private boolean ifIncome;
private String kindName;
private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getKindName() {

        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    private String comment;

    public ObjectId getKindId() {
        return kindId;
    }

    public void setKindId(ObjectId kindId) {
        this.kindId = kindId;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
