package com.example.demo.model;

public class Currency {
    private final String name;
    private final double conversionValue;
    private final char acronym;
    public Currency(String name, double conversionValue, char acronym) {
        this.name = name;
        this.conversionValue = conversionValue;
        this.acronym = acronym;
    }
    public double getConversionValue() {
        return conversionValue;
    }
    public String getName() {
        return name;
    }
    public char getAcronym() {
        return acronym;
    }
    public double convertFrom(double amount, Currency currency){
        return ( amount * currency.getConversionValue()) / this.conversionValue;
    }
}
