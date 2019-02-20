package com.gzcc.bill.Service;


import com.gzcc.bill.domain.Kind;
import org.bson.types.ObjectId;

public interface KindService {
boolean addKind(String kindName);
   Kind findKindBykindId(ObjectId kindId);
}
