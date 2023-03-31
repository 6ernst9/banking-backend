package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin
@RequestMapping("people")
@RestController
public class PersonController {
    private final PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @PostMapping
    public void addUsers(@RequestBody Person person){
        personService.addPerson(person);
    }
    @GetMapping
    public List<Person> getAllUsers(){
        return personService.getAllPeople();
    }
    @GetMapping(path = "{id}")
    public Person getUserById(@PathVariable("id") Long id){
        return personService.getPersonById(id).orElse(null);
    }
    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable("id") Long id){
        personService.deletePerson(id);
    }


}
