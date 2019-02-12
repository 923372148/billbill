package com.gzcc.bill.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document
public class PersonalBill implements Serializable {
    private static final long serialVersionUID = -7816294302377473088L;
    @Id
    private ObjectId  personalBillId;
    private List<String> userListId;

    private long icome;
    private long expenditure;
    private List<ObjectId > runningAccountListId;
}
