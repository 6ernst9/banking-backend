package com.example.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.Random;

@Entity
@Table
public class Account {
    @Id
    @SequenceGenerator(
            name = "account_sequence",
            sequenceName = "account_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_sequence"
    )
    @Column(nullable = false, name = "account_id")
    private Long accountId;
    @Column(nullable = false)
    private Long accountNumber;
    private int accountPin;
    @Column(nullable = false)
    private int accountCvv;
    @Column(nullable = false)
    private LocalDate expDate;
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "person_id_num_fk"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Person personId;
    @Column(nullable = false, name = "currency")
    private String currency;
    private double balance;

    public Account(Long accountId, Person personId,  String currency, int accountPin) {
        this.personId = personId;
        this.accountId = accountId;
        this.currency = currency;
        this.accountPin = accountPin;
        this.accountCvv = generateCvv();
        expDate = LocalDate.now().plusMonths(60);
        accountNumber = generateAccountNumber();
        this.balance = Currencies.getCurrency(currency).convertFrom(100, Currencies.getCurrency("EUR"));
    }
    public Account(  Person personId, String currency, int accountPin) {
        this.personId = personId;
        this.currency = currency;
        this.accountPin = accountPin;
        this.accountCvv = generateCvv();
        accountNumber = generateAccountNumber();
        expDate = LocalDate.now().plusMonths(60);
        this.balance = Currencies.getCurrency(currency).convertFrom(100, Currencies.getCurrency("EUR"));
    }

    public Account() {

    }

    public int getAccountCvv() {
        return accountCvv;
    }

    public int getAccountPin() {
        return accountPin;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountPin(int accountPin) {
        this.accountPin = accountPin;
    }
    private static Long generateAccountNumber(){
        Random random = new Random();
        int isVisa = random.nextInt(2);
        if(isVisa==0){
            return random.nextLong(5600000000000000L-5000000000000000L) + 5000000000000000L;
        }
        else{
            return random.nextLong(5000000000000000L - 4000000000000000L) + 4000000000000000L;
        }
    }
    private static int generateCvv(){
        Random random = new Random();
        return random.nextInt(100, 999);
    }
    public Person getPersonId() {
        return personId;
    }
    public Long getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }
}
