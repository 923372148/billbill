package com.gzcc.kindmanager.Repository;


import com.gzcc.kindmanager.domain.Kind;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KindRepository extends MongoRepository<Kind,String> {

}
