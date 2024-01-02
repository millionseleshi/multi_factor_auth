package com.million.multi_factor_auth.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.million.multi_factor_auth.model.Account;
import com.million.multi_factor_auth.repository.AccountRepository;
import com.million.multi_factor_auth.utils.AccountType;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataMongoTest
public class AccountRepositoryTest {

        public static final String ACCOUNTNAME = "acc@gmail.com";
        @Autowired
        private AccountRepository accountRepository;
        UUID accountKey = UUID.randomUUID();
        UUID accountSecret = UUID.randomUUID();

        @Test
        public void shouldSaveAccount() {

                Mono<Account> savedAccount = accountRepository.save(Account.builder().accountName(ACCOUNTNAME)
                                .accountType(AccountType.TIME_BASED)
                                .authAccountKey(accountKey.toString())
                                .authIssuer("instagram")
                                .authAccountSecret(accountSecret.toString())
                                .build());

                StepVerifier.create(savedAccount)
                                .expectNextMatches(account -> account.getAccountName().equals(ACCOUNTNAME))
                                .verifyComplete();

        }

        @Test
        public void shouldFindAccountByAccountName() {

                Account savedAccount = accountRepository.save(Account.builder().accountName(ACCOUNTNAME)
                                .accountType(AccountType.TIME_BASED)
                                .authAccountKey(accountKey.toString())
                                .authIssuer("instagram")
                                .authAccountSecret(accountSecret.toString())
                                .build()).block();

                Mono<Account> foundAccount = accountRepository.findById(savedAccount.getAccountName());

                StepVerifier.create(foundAccount)
                                .expectNextMatches(account -> account.getAccountName()
                                                .equals(savedAccount.getAccountName()))
                                .verifyComplete();

        }

}
