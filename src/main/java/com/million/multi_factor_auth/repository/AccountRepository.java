package com.million.multi_factor_auth.repository;

import com.million.multi_factor_auth.model.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AccountRepository extends ReactiveMongoRepository<Account, String> {

}
