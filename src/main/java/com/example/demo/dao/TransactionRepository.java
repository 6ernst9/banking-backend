package com.example.demo.dao;

import com.example.demo.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT s FROM Transaction s WHERE s.id = ?1")
    Optional<Transaction> findTransactionById(Long id);

    @Query("SELECT s FROM Transaction s WHERE s.senderAccount.personId.id = :id OR s.receiverAccount.personId.id = :id")
    List<Transaction> findPersonTransactionById(Long id);

    @Query("SELECT s FROM Transaction s WHERE s.senderAccount.personId.username = :username OR s.receiverAccount.personId.username = :username")
    List<Transaction> findPersonTransactionByUsername(String username);

    @Query("SELECT s FROM Transaction s WHERE s.receiverAccount.personId.username = :username AND s.senderAccount IS NULL")
    List<Transaction> findPersonDepositsByUsername(String username);

    @Query("SELECT s FROM Transaction s WHERE s.senderAccount.personId.username = :username AND s.receiverAccount IS NULL")
    List<Transaction> findPersonWithdrawalsByUsername(String username);

    @Query("SELECT s FROM Transaction s WHERE s.receiverAccount.personId.id = :id AND s.senderAccount IS NULL")
    List<Transaction> findPersonDepositsById(Long id);

    @Query("SELECT s FROM Transaction s WHERE s.senderAccount.personId.id = :id AND s.receiverAccount IS NULL")
    List<Transaction> findPersonWithdrawalsById(Long id);

}
