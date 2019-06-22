package com.gzcc.kindmanager.Repository;

import com.gzcc.kindmanager.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String > {

    User findByOpenId(String openId);
}
