package com.gzcc.bill.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document
public class PersonalBill implements Serializable {
    public PersonalBill(String personalBillName, String description) {
        this.personalBillName = personalBillName;
        this.description = description;
    }

    public PersonalBill() {
    }

    private static final long serialVersionUID = -7816294302377473088L;
    @Id
    private ObjectId  personalBillId;
    private List<String> userListId;
    private long icome=0;
    private long expenditure=0;
    private List<ObjectId > runningAccountListId;

    private String personalBillName;
private  boolean ifDefault;

    public boolean isIfDefault() {
        return ifDefault;
    }

    public void setIfDefault(boolean ifDefault) {
        this.ifDefault = ifDefault;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public ObjectId getPersonalBillId() {
        return personalBillId;
    }

    public void setPersonalBillId(ObjectId personalBillId) {
        this.personalBillId = personalBillId;
    }

    public List<String> getUserListId() {
        return userListId;
    }

    public void setUserListId(List<String> userListId) {
        this.userListId = userListId;
    }

    public long getIcome() {
        return icome;
    }

    public void setIcome(long icome) {
        this.icome = icome;
    }

    public long getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(long expenditure) {
        this.expenditure = expenditure;
    }

    public List<ObjectId> getRunningAccountListId() {
        return runningAccountListId;
    }

    public void setRunningAccountListId(List<ObjectId> runningAccountListId) {
        this.runningAccountListId = runningAccountListId;
    }

    public String getPersonalBillName() {
        return personalBillName;
    }

    public void setPersonalBillName(String personalBillName) {
        this.personalBillName = personalBillName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;
}
