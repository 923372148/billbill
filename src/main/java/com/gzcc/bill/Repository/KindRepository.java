package com.gzcc.bill.Repository;


import com.gzcc.bill.domain.Kind;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KindRepository extends MongoRepository<Kind,String> {

}
