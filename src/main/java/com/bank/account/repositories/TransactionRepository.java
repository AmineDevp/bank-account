package com.bank.account.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bank.account.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	@Query("SELECT transaction FROM Transaction transaction WHERE senderAccount.idAccount = :accountId OR reveiverAccount.idAccount = :accountId")
	public List<Transaction> findAllByAccountId(@Param("accountId") Long accountId);

}
