package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
@Entity
@Table
public class Person {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String username;
    private String password;
    private String name;
    private LocalDate dateOfBirth;
    @Transient
    private int age;

    public Person( Long id, String name , String username, String password, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.password = PasswordEncryptor.encryptor(password);
        this.username = username;
        this.id = id;
        this.age = Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }
    public Person( String name , String username, String password, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.password = PasswordEncryptor.encryptor(password);
        this.username = username;
        this.age = Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    public Person() {

    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return this.name;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }
    @Override
    public String toString() {
        return "User[" +
                "id " + id +
                ", name " + name + ']';
    }
}
