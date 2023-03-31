package com.example.demo.model;
public class TransactionRequest {
    private Long senderAccount;
    private Long receiverAccount;
    private String currency;
    private double amount;

    public TransactionRequest(Long senderAccount, Long receiverAccount, String currency, double amount) {
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.currency = currency;
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getSenderAccount() {
        return senderAccount;
    }

    public Long getReceiverAccount() {
        return receiverAccount;
    }

    public String getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }
}
