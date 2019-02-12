package com.gzcc.bill.Repoistory;


import com.gzcc.bill.domain.Kind;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KindRepoistory extends MongoRepository<Kind,String> {

}
