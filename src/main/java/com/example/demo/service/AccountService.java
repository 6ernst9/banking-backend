package com.example.demo.service;

import com.example.demo.dao.AccountRepository;
import com.example.demo.dao.PersonRepository;
import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final PersonRepository personRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository, PersonRepository personRepository) {
        this.accountRepository = accountRepository;
        this.personRepository = personRepository;
    }
    public void addAccount(AccountRequest account){
        Optional<Person> person = personRepository.findPersonById(account.getPersonId());
        if(person.isEmpty()){
            throw new IllegalStateException("person not found");
        }
        if(Currencies.getCurrency(account.getCurrency())==null){
            throw new IllegalStateException("currency not found");
        }
        accountRepository.save(new Account(person.get(), account.getCurrency(), account.getAccountPin()));
    }
    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }
    public List<Account> getAllPersonAccountsById(Long id){
        return accountRepository.findAccountByPersonId(id);
    }
    public List<Account> getAllPersonAccountsByUsername(String username){
        return accountRepository.findAccountByPersonUsername(username);
    }
    public Optional<Account> getAccountById(Long id){
        Optional<Account> account = accountRepository.findAccountById(id);
        if(account.isEmpty()){
            throw new IllegalStateException("non existing account");
        }
        return account;
    }
    public void deleteAccountById(Long id){
        Optional<Account> account = accountRepository.findAccountById(id);
        if(account.isEmpty()){
            throw new IllegalStateException("non existing account");
        }
        accountRepository.deleteById(account.get().getAccountId());
    }
}
