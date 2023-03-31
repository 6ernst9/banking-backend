package com.example.demo.model;

import java.util.ArrayList;

public final class Currencies {
    private final static ArrayList<Currency> currencies = new ArrayList<>();
    static {
        currencies.add(new Currency("RON", 0.2, 'L'));
        currencies.add(new Currency("EUR", 1, '€'));
        currencies.add(new Currency("DOL", 0.97, '$'));
        currencies.add(new Currency("PND", 1.16, '£'));
        currencies.add(new Currency("YEN", 0.0069, '¥'));
    }
    public static Currency getCurrency(String currency){
        for(Currency c : currencies){
            if(c.getName().equals(currency)){
                return c;
            }
        }
        return null;
    }
}
