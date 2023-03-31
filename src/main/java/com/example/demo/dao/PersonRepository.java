package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT s FROM Person s WHERE s.id = ?1")
    Optional<Person> findPersonById(Long id);
    @Query("SELECT s FROM Person s WHERE s.username = :username")
    Optional<Person> findPersonByUsername(String username);

}
