package com.example.demo.model;

public class WithdrawRequest {
    private Long account;
    private String currency;

    public Long getAccount() {
        return account;
    }

    public String getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }

    private double amount;

    public WithdrawRequest(Long senderAccount, String currency, double amount) {
        this.account = senderAccount;
        this.currency = currency;
        this.amount = amount;
    }
}
