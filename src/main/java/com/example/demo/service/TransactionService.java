package com.example.demo.service;

import com.example.demo.dao.AccountRepository;
import com.example.demo.dao.TransactionRepository;
import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService{
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }
    public void deposit(DepositRequest depositRequest){
        Optional<Account> receiver = accountRepository.findAccountById(depositRequest.getAccount());
        if(receiver.isEmpty()){
            throw new IllegalStateException("non existing account");
        }
        if(!receiver.get().getCurrency().equals(depositRequest.getCurrency())){
            Currency receiverCurrency = Currencies.getCurrency(receiver.get().getCurrency());
            Currency atmCurrency = Currencies.getCurrency(depositRequest.getCurrency());
            if(receiverCurrency == null || atmCurrency == null){
                throw new IllegalStateException("currency not valid");
            }
            receiver.get().setBalance(receiver.get().getBalance() + receiverCurrency.convertFrom(depositRequest.getAmount(), atmCurrency));
            transactionRepository.save(new Transaction( depositRequest.getAmount(),receiver.get(), depositRequest.getCurrency()));
        }
        else{
            receiver.get().setBalance(receiver.get().getBalance() + depositRequest.getAmount());
            transactionRepository.save(new Transaction(depositRequest.getAmount(), receiver.get(), receiver.get().getCurrency()));
        }
    }
    public void withdraw(WithdrawRequest withdrawRequest){
        Optional<Account> sender = accountRepository.findAccountById(withdrawRequest.getAccount());
        if(sender.isEmpty()){
            throw new IllegalStateException("non existing account");
        }
        if(sender.get().getBalance() < withdrawRequest.getAmount() && sender.get().getCurrency().equals(withdrawRequest.getCurrency())){
            throw new IllegalStateException("insufficient funds");
        }
        if(!sender.get().getCurrency().equals(withdrawRequest.getCurrency())){
            Currency senderCurrency = Currencies.getCurrency(sender.get().getCurrency());
            Currency atmCurrency = Currencies.getCurrency(withdrawRequest.getCurrency());
            if(senderCurrency == null || atmCurrency == null){
                throw new IllegalStateException("currency not valid");
            }
            if(sender.get().getBalance() < senderCurrency.convertFrom(withdrawRequest.getAmount(), atmCurrency)){
                throw new IllegalStateException("insufficient funds");
            }
            else{
                sender.get().setBalance(sender.get().getBalance() - senderCurrency.convertFrom(withdrawRequest.getAmount(), atmCurrency));
                transactionRepository.save(new Transaction(sender.get(), withdrawRequest.getAmount(), withdrawRequest.getCurrency()));
            }
        }
        if(sender.get().getBalance() > withdrawRequest.getAmount() && sender.get().getCurrency().equals(withdrawRequest.getCurrency())){
            sender.get().setBalance(sender.get().getBalance() - withdrawRequest.getAmount());
            transactionRepository.save(new Transaction(sender.get(), withdrawRequest.getAmount(), withdrawRequest.getCurrency()));
        }
    }
    public void addTransaction(TransactionRequest transaction){
        Optional<Account> sender = accountRepository.findAccountById(transaction.getSenderAccount());
        Optional<Account> receiver = accountRepository.findAccountById(transaction.getReceiverAccount());
        if(sender.isEmpty() || receiver.isEmpty()) {
            throw new IllegalStateException("non existing sender or receiver account");
        }
        if(!sender.get().getCurrency().equals(receiver.get().getCurrency())){
            Currency firstCurrency = Currencies.getCurrency(sender.get().getCurrency());
            Currency secondCurrency = Currencies.getCurrency(receiver.get().getCurrency());
            if(firstCurrency == null || secondCurrency == null){
                throw new IllegalStateException("currency not valid");
            }
            sender.get().setBalance(sender.get().getBalance() - transaction.getAmount());
            receiver.get().setBalance(receiver.get().getBalance() + secondCurrency.convertFrom(transaction.getAmount(), firstCurrency));
            transactionRepository.save(new Transaction(sender.get(), receiver.get(), transaction.getAmount(), firstCurrency.getName()));
        }
        else{
            sender.get().setBalance(sender.get().getBalance() - transaction.getAmount());
            receiver.get().setBalance(receiver.get().getBalance() + transaction.getAmount());
            transactionRepository.save(new Transaction(sender.get(), receiver.get(), transaction.getAmount(), transaction.getCurrency()));
        }
    }
    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }
    public List<Transaction> getAllPersonTransactionById(Long id){
        return transactionRepository.findPersonTransactionById(id);
    }
    public List<Transaction> getAllPersonWithdrawalsById(Long id){
        return transactionRepository.findPersonWithdrawalsById(id);
    }
    public List<Transaction> getAllPersonDepositsById(Long id){
        return transactionRepository.findPersonDepositsById(id);
    }
    public List<Transaction> getAllPersonTransactionByUsername(String username){
        return transactionRepository.findPersonTransactionByUsername(username);
    }
    public List<Transaction> getAllPersonDepositsByUsername(String username){
        return transactionRepository.findPersonDepositsByUsername(username);
    }
    public List<Transaction> getAllPersonWithdrawalsByUsername(String username){
        return transactionRepository.findPersonWithdrawalsByUsername(username);
    }
    public Optional<Transaction> getTransactionById(Long id){
        Optional<Transaction> transaction = transactionRepository.findTransactionById(id);
        if(transaction.isEmpty()){
            throw new IllegalStateException("non existing transaction");
        }
        return transaction;
    }
}
