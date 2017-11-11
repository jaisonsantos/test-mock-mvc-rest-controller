package com.jaison.exemplo.controller.controller;


import com.jaison.exemplo.controller.exception.AccountNotFoundException;
import com.jaison.exemplo.controller.exception.InvalidAccountRequestException;
import com.jaison.exemplo.controller.model.Account;
import com.jaison.exemplo.controller.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@RestController
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = { "/api/account" }, method = { RequestMethod.POST })
    public Account createAccount(@RequestBody Account account,
                                 HttpServletResponse httpResponse,
                                 WebRequest request) {

        Long accountId = accountService.createAccount(account);
        account.setAccountId(accountId);

        httpResponse.setStatus(HttpStatus.CREATED.value());
        httpResponse.setHeader("Location", String.format("%s/api/account/%s",
                request.getContextPath(), accountId));
        return account;
    }

    @RequestMapping(value = "/api/account/{accountId}", method = RequestMethod.GET)
    public Account getAccount(@PathVariable("accountId") Long accountId) {

		/* validate account Id parameter */
        if (accountId < 9999) {
            throw new InvalidAccountRequestException();
        }

        Account account = accountService.loadAccount(accountId);
        if(null==account){
            throw new AccountNotFoundException();
        }

        return account;
    }

}