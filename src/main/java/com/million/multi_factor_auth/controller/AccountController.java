package com.million.multi_factor_auth.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.million.multi_factor_auth.model.Account;
import com.million.multi_factor_auth.service.AccountService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/account")
@AllArgsConstructor
public class AccountController {

  private AccountService accountService;

  @GetMapping("/{accountName}")
  public Mono<Account> getAccount(@PathVariable String accountName) {
    return accountService.getAccountByEmailId(accountName);
  }

  @PostMapping(produces = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Account> addAccount(@RequestBody Account account) {
    return accountService.saveAccount(account);
  }
}
