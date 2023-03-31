package com.example.demo.dao;

import com.example.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT s FROM Account s WHERE s.accountId = ?1")
    Optional<Account> findAccountById(Long id);

    @Query("SELECT s FROM Account s WHERE s.personId.id = :id")
    List<Account> findAccountByPersonId(Long id);

    @Query("SELECT s FROM Account s WHERE s.personId.username = :username")
    List<Account> findAccountByPersonUsername(String username);
}
