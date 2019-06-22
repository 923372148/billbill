package com.gzcc.kindmanager.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class Kind implements Serializable  {
    @Id
    private ObjectId kindId;

    private static final long serialVersionUID = -2806344238638420043L;

    private String kindName;



    public ObjectId getKindId() {
        return kindId;
    }

    public void setKindId(ObjectId kindId) {
        this.kindId = kindId;
    }
public Kind(){}

    public Kind(String kindName) {
        this.kindName = kindName;

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }


}
