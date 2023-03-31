package com.example.demo.model;

import com.example.demo.dao.PersonRepository;
import com.example.demo.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class PersonConfig {
    @Bean
    CommandLineRunner commandLineRunner(PersonRepository personRepository, AccountService accountService){
        return args -> {
            Person person1 = new Person(
                    "Ernst Robert",
                    "6ernst9",
                    "ssback2022",
                    LocalDate.of(2002, Month.OCTOBER, 24)
            );
            Person person2 = new Person(
                    "Andrada Hruban",
                    "andradahub",
                    "andrada123",
                    LocalDate.of(2002, Month.OCTOBER, 14)
            );
            personRepository.saveAll(List.of(person2, person1));

            AccountRequest account1 = new AccountRequest(1l, "EUR", 2345);
            AccountRequest account2 = new AccountRequest(2l, "RON", 1231);
            accountService.addAccount(account1);
            accountService.addAccount(account2);
        };
    }
}
