package com.gzcc.bill.domain;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
@Document
public class RunningAccount implements Serializable  {
    private ObjectId kindId;
    private long money;


}
