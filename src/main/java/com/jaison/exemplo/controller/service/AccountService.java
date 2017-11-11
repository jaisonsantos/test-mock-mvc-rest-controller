package com.jaison.exemplo.controller.service;

import com.jaison.exemplo.controller.model.Account;

public interface AccountService {

    public Account loadAccount(Long accountId);
    public Long createAccount(Account account);
}
