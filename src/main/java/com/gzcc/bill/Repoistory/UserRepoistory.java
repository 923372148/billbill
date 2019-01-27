package com.gzcc.bill.Repoistory;

import com.gzcc.bill.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepoistory extends MongoRepository<User,String > {

    User findByAccountId(String accountId);
}
