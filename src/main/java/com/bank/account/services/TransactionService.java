package com.bank.account.services;

import java.util.List;

import com.bank.account.beans.TransactionBean;
import com.bank.account.dtos.TransactionDTO;

public interface TransactionService {

	public TransactionDTO createTransaction(Long idAccount, TransactionBean transactionBean);

	public List<TransactionDTO> getBankAccountTransactionHistory(Long idAccount);

}
