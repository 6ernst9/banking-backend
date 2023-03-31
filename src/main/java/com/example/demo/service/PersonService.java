package com.example.demo.service;

import com.example.demo.dao.AccountRepository;
import com.example.demo.dao.PersonRepository;
import com.example.demo.model.Account;
import com.example.demo.model.Person;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, AccountRepository accountRepository) {
        this.personRepository = personRepository;
        this.accountRepository = accountRepository;
    }
    public void addPerson(Person person){
        Optional<Person> personOptional = personRepository.findPersonByUsername(person.getUsername());
        if(personOptional.isPresent()){
            throw new IllegalStateException("Username already exists");
        }
        if(person.getPassword().length()<6){
            throw new IllegalStateException("Password too short");
        }
        personRepository.save(person);
    }

    public List<Person> getAllPeople(){
        return personRepository.findAll();
    }
    public Optional<Person> getPersonById(Long id){
        Optional<Person> personOptional = personRepository.findPersonById(id);
        if(personOptional.isEmpty()){
            throw new IllegalStateException("Id not found");
        }
        return personRepository.findPersonById(id);
    }
    public Optional<Person> getPersonByUsername(String username){
        Optional<Person> personOptional = personRepository.findPersonByUsername(username);
        if(personOptional.isEmpty()){
            throw new IllegalStateException("Username not found");
        }
        return personRepository.findPersonByUsername(username);
    }
    public void deletePerson(Long id){
        Optional<Person> personOptional = personRepository.findPersonById(id);
        if(personOptional.isEmpty()){
            throw new IllegalStateException("Username not found");
        }
        personRepository.deleteById(personOptional.get().getId());
    }
}
