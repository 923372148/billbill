package com.gzcc.kindmanager.Service;


import com.gzcc.kindmanager.domain.Kind;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@com.alibaba.dubbo.config.annotation.Service
public interface KindService {
   boolean addKind(String kindName);
   Kind findKindBykindId(ObjectId kindId);
}
