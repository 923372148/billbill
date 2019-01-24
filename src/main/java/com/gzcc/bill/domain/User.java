package com.gzcc.bill.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document
public class User implements Serializable {

@Id
    private String userId;
private String userName;
private List<ObjectId> personalBillId;

}
