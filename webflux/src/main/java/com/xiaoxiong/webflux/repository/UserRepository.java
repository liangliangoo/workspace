package com.xiaoxiong.webflux.repository;

import com.xiaoxiong.webflux.domain.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2022/5/9  21:56
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User,String> {



}
