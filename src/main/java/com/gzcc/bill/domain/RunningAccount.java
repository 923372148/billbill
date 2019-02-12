package com.gzcc.bill.domain;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document
public class RunningAccount implements Serializable  {
    private ObjectId kindId;
    private long money;
    private ObjectId kindListId;

    public void setKindListId(ObjectId kindListId) {
        this.kindListId = kindListId;
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
