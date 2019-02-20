package com.gzcc.bill.Repository;

import com.gzcc.bill.domain.PersonalBill;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalBillRepository extends MongoRepository<PersonalBill,ObjectId > {

PersonalBill findByPersonalBillId(ObjectId personalBillId );

}
