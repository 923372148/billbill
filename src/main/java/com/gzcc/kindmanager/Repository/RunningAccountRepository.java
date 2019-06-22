package com.gzcc.kindmanager.Repository;

import com.gzcc.kindmanager.domain.RunningAccount;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RunningAccountRepository extends MongoRepository<RunningAccount,ObjectId> {

}
