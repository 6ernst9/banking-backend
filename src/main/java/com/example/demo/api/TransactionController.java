package com.example.demo.api;

import com.example.demo.model.DepositRequest;
import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionRequest;
import com.example.demo.model.WithdrawRequest;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("transactions")
@RestController
public class TransactionController {
    private final TransactionService transactionService;
    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @PostMapping
    public void addTransaction(@RequestBody TransactionRequest transaction){
        transactionService.addTransaction(transaction);
    }
    @PostMapping(path = "deposit")
    public void depositTo(@RequestBody DepositRequest depositRequest){
        transactionService.deposit(depositRequest);
    }
    @PostMapping(path = "withdraw")
    public void withdrawFrom(@RequestBody WithdrawRequest withdrawRequest){
        transactionService.withdraw(withdrawRequest);
    }
    @GetMapping
    public List<Transaction> getAllTransactions(){
        return transactionService.getAllTransactions();
    }
    @GetMapping(path = "{id}")
    public Optional<Transaction> getTransactionById(@PathVariable("id") Long id){
        return transactionService.getTransactionById(id);
    }
    @GetMapping(path = "person/withdraw/id")
    public List<Transaction> getAllPersonWithdrawalsById(@RequestParam Long id){
        return transactionService.getAllPersonWithdrawalsById(id);
    }
    @GetMapping(path = "person/deposit/id")
    public List<Transaction> getAllPersonDepositsById(@RequestParam Long id){
        return transactionService.getAllPersonDepositsById(id);
    }
    @GetMapping(path = "person/id")
    public List<Transaction> getAllPersonTransactionById(@RequestParam Long id){
        return transactionService.getAllPersonTransactionById(id);
    }
    @GetMapping(path = "person/username")
    public List<Transaction> getAllPersonTransactionByUsername(@RequestParam String username){
        return transactionService.getAllPersonTransactionByUsername(username);
    }
    @GetMapping(path = "person/withdraw/username")
    public List<Transaction> getAllPersonWithdrawalsByUsername(@RequestParam String username){
        return transactionService.getAllPersonWithdrawalsByUsername(username);
    }
    @GetMapping(path = "person/deposit/username")
    public List<Transaction> getAllPersonDepositsByUsername(@RequestParam String username){
        return transactionService.getAllPersonDepositsByUsername(username);
    }

}
