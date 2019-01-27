package com.gzcc.bill.Repoistory;

import com.gzcc.bill.domain.PersonalBill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalBillRepoistory extends MongoRepository<PersonalBill,String > {


}
