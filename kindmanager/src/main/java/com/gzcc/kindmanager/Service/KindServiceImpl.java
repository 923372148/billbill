package com.gzcc.kindmanager.Service;

import com.alibaba.dubbo.config.annotation.Service;
import com.gzcc.kindmanager.Repository.KindRepository;
import com.gzcc.kindmanager.domain.Kind;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

//
//@Service(version = "1.0.0")
@org.springframework.stereotype.Service("kindService")
public class KindServiceImpl  implements KindService{

private final KindRepository kindRepository;
@Autowired
    public KindServiceImpl(KindRepository kindRepository) {
        this.kindRepository = kindRepository;
    }



    @Override
    public boolean  addKind(String kindName) {
//Kind kind=new Kind(kindName);
        try {
            kindRepository.save(new Kind(kindName));
            return true;
        }
        catch(Exception e){
            return false;
        }

    }

    @Override
    public Kind findKindBykindId(ObjectId kindId) {
        return kindRepository.findKindBykindId(kindId);
    }
}
