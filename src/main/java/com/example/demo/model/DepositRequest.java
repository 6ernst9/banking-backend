package com.example.demo.model;

public class DepositRequest {
    private Long account;
    private String currency;
    private double amount;

    public Long getAccount() {
        return account;
    }

    public String getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }

    public DepositRequest(Long receiverAccount, String currency, double amount) {
        this.account = receiverAccount;
        this.currency = currency;
        this.amount = amount;
    }
}
