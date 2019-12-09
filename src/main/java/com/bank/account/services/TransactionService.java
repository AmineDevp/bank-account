package com.bank.account.services;

import java.util.List;

import com.bank.account.beans.TransactionBean;
import com.bank.account.dtos.TransactionDTO;
import com.bank.account.exceptions.ServiceValidationException;

public interface TransactionService {

	public TransactionDTO createTransaction(Long idAccount, TransactionBean transactionBean) throws ServiceValidationException;

	public List<TransactionDTO> getBankAccountTransactionHistory(Long idAccount);

}
