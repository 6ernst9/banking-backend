package com.example.demo.model;

import java.util.Random;

public class PasswordEncryptor {
    private static final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*?:<>;~";
    public static String encryptor(String password){
        String newPassword="";
        Random rand = new Random();
        for(int i=0;i<password.length();i++){
            for(int j = 0; j < password.charAt(i)%10;j++){
                newPassword+= alphabet.charAt(rand.nextInt(alphabet.length()));
            }
            newPassword+=password.charAt(i);
        }
        newPassword+=alphabet.charAt(rand.nextInt(alphabet.length()));
        return newPassword;
    }
    public static String decrytor(String password){
        String encripted = password;
        String newPassword = "";
        encripted = encripted.substring(0, encripted.length()-1);
        int index = encripted.length()-1;
        while(index>=0){
            char aux = encripted.charAt(index);
            newPassword += aux;
            encripted = encripted.substring(0, index);
            index -=aux%10;
            encripted = encripted.substring(0, index);
            index--;
        }
        String finalPassword = "";
        for(int i=newPassword.length()-1;i>=0;i--){
            finalPassword+=newPassword.charAt(i);
        }
        return finalPassword;
    }
}
