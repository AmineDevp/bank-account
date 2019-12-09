package com.bank.account.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.account.entities.WicketBank;

@Repository
public interface WicketBankRepository extends JpaRepository<WicketBank, Integer> {

}
