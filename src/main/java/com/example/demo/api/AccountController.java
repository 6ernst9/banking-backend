package com.example.demo.api;

import com.example.demo.model.Account;
import com.example.demo.model.AccountRequest;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("accounts")
@RestController
public class AccountController {
    private final AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping
    public void addAccount(@RequestBody AccountRequest account){
        accountService.addAccount(account);
    }
    @GetMapping
    public List<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }
    @GetMapping(path = "/person/{id}")
    public List<Account> getAllPersonAccounts(@PathVariable("id") Long id){
        return accountService.getAllPersonAccountsById(id);
    }
    @GetMapping(path = "/person/{username}")
    public List<Account> getAllPersonAccounts(@PathVariable("username") String username){
        return accountService.getAllPersonAccountsByUsername(username);
    }
    @GetMapping(path = "{id}")
    public Optional<Account> getAccountById(@PathVariable("id") Long id){
        return accountService.getAccountById(id);
    }
}
