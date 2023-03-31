package com.example.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table
public class Transaction {
    @Id
    @SequenceGenerator(
            name = "transaction_sequence",
            sequenceName = "transaction_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_sequence"
    )
    private long id;

    @ManyToOne
    @JoinColumn(name = "sender_account", referencedColumnName = "account_id",  foreignKey = @ForeignKey(name = "sender_id_num_fk"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Account senderAccount;

    @ManyToOne
    @JoinColumn(name = "receiver_account", referencedColumnName = "account_id", foreignKey = @ForeignKey(name = "receiver_id_num_fk"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Account receiverAccount;

    @Column(nullable = false)
    private String currency;
    @Column(nullable = false, name = "time_of_transaction")
    private LocalDateTime timeOfTransaction;

    @Column(nullable = false, name = "amount")
    private double amount;

    public Transaction(Long id, Account sender, Account receiver, double amount, String currency) {
        this.id = id;
        timeOfTransaction = LocalDateTime.now();
        this.senderAccount = sender;
        this.currency = currency;
        this.receiverAccount = receiver;
        this.amount = amount;
    }
    public Transaction(Account sender, Account receiver, double amount, String currency) {
        this.senderAccount = sender;
        this.currency = currency;
        timeOfTransaction = LocalDateTime.now();
        this.receiverAccount = receiver;
        this.amount = amount;
    }
    public Transaction(Account sender, double amount, String currency) {
        this.senderAccount = sender;
        this.currency = currency;
        timeOfTransaction = LocalDateTime.now();
        this.receiverAccount = null;
        this.amount = amount;
    }
    public Transaction(double amount, Account receiver, String currency) {
        this.senderAccount = null;
        this.currency = currency;
        timeOfTransaction = LocalDateTime.now();
        this.receiverAccount = receiver;
        this.amount = amount;
    }
    public Transaction(Account sender, Account receiver, double amount) {
        this.senderAccount = sender;
        timeOfTransaction = LocalDateTime.now();
        this.receiverAccount = receiver;
        this.amount = amount;
    }

    public Transaction() {
        timeOfTransaction = LocalDateTime.now();
    }

    public LocalDateTime getTimeOfTransaction() {
        return timeOfTransaction;
    }
    public double getAmount() {
        return amount;
    }
    public String getCurrency() {
        return currency;
    }

    public Account getReceiverAccount() {
        return receiverAccount;
    }
    public Account getSenderAccount() {
        return senderAccount;
    }
    public long getId() {
        return id;
    }
}
