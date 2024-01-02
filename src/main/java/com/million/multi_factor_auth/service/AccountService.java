package com.million.multi_factor_auth.service;

import com.million.multi_factor_auth.model.Account;
import com.million.multi_factor_auth.repository.AccountRepository;
import com.million.multi_factor_auth.utils.AuthAccountKeyGenerate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class AccountService {


  private AccountRepository accountRepository;
  private AuthAccountKeyGenerate authAccountKeyGenerate;


  public Mono<Account> getAccountByEmailId(String email) {
          return accountRepository.findById(email);
  }

  public Mono<Account> saveAccount(Account account) {
      String accountKey = authAccountKeyGenerate.generateAccountKey();
      Account entity = Account.builder()
              .accountName(account.getAccountName())
              .accountType(account.getAccountType())
              .authAccountKey(authAccountKeyGenerate.bcryptGeneratedAccountKey(accountKey))
              .authIssuer(account.getAuthIssuer())
              .authAccountSecret("SECRET")
              .build();
     return accountRepository.save(entity).flatMap(savedAccount -> {
         savedAccount.setAuthAccountKey(accountKey);
          return Mono.just(savedAccount);
      });

  }
}
