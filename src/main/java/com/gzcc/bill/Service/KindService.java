package com.gzcc.bill.Service;


import com.gzcc.bill.domain.Kind;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@com.alibaba.dubbo.config.annotation.Service
public interface KindService {
boolean addKind(String kindName);
   Kind findKindBykindId(ObjectId kindId);
}
