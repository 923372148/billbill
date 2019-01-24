package com.gzcc.bill.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document
public class PersonalBill implements Serializable {
    @Id
    private ObjectId  personalBillId;
    private List<String> userListId;
    private List<ObjectId> kindListId;
    private long icome;
    private long expenditure;
    private List<ObjectId > runningAccountListId;
}
