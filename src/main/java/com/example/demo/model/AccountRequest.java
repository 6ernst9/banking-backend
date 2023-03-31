package com.example.demo.model;

public class AccountRequest {
    private int accountPin;
    private Long personId;
    private String currency;

    public AccountRequest(int accountPin, Long personId, String currency) {
        this.accountPin = accountPin;
        this.personId = personId;
        this.currency = currency;
    }
    public AccountRequest(Long personId, String currency , int accountPin) {
        this.accountPin = accountPin;
        this.personId = personId;
        this.currency = currency;
    }
    public int getAccountPin() {
        return accountPin;
    }
    public Long getPersonId() {
        return personId;
    }

    public String getCurrency() {
        return currency;
    }

}
