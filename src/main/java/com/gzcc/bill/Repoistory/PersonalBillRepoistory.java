package com.gzcc.bill.Repoistory;

import com.gzcc.bill.domain.PersonalBill;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalBillRepoistory extends MongoRepository<PersonalBill,String > {

PersonalBill findByPersonalBillId(ObjectId personalBillId );
}
