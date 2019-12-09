package com.bank.account.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bank.account.beans.TransactionBean;
import com.bank.account.commun.Constants;
import com.bank.account.dtos.TransactionDTO;
import com.bank.account.entities.Account;
import com.bank.account.entities.Transaction;
import com.bank.account.enums.TransactionType;
import com.bank.account.exceptions.ServiceValidationException;
import com.bank.account.repositories.AccountRepository;
import com.bank.account.repositories.TransactionRepository;
import com.bank.account.services.TransactionService;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public TransactionDTO createTransaction(Long idAccount, TransactionBean transactionBean) throws ServiceValidationException {
		final Account account = accountRepository.findById(idAccount)
				.orElseThrow(() -> new ServiceValidationException(Constants.ACCOUNT_NOT_FOUND, HttpStatus.NOT_FOUND));
		if (transactionBean.getAmount() < 0) {
			throw new ServiceValidationException(Constants.AMOUNT_NOT_ALLOWED, HttpStatus.BAD_REQUEST);
		}
		final Transaction.Builder transactionBuilder = new Transaction.Builder().withTransactionType(transactionBean.getTransactionType())
				.withAmount(transactionBean.getAmount())
				.withMotif(transactionBean.getMotif())
				.withDate(transactionBean.getDate())
				.withCurrency(transactionBean.getCurrency());
		Transaction transaction;
		if (TransactionType.TRANSFER_OPERATION.equals(transactionBean.getTransactionType())) {
			if (idAccount != null && idAccount.equals(transactionBean.getSenderAccountId())) {
				final Account receiverAccount = accountRepository.findById(transactionBean.getReveiverAccountId())
						.orElseThrow(() -> new ServiceValidationException(Constants.ACCOUNT_NOT_FOUND, HttpStatus.NOT_FOUND));
				if (isTransactionAllowed(account, -1 * transactionBean.getAmount())) {
					account.setAmount(account.getAmount() - transactionBean.getAmount());
					receiverAccount.setAmount(receiverAccount.getAmount() + transactionBean.getAmount());
					transaction = transactionBuilder.withSenderAccount(account)
							.withReveiverAccount(receiverAccount)
							.build();
					account.setLastUpdate(transactionBean.getDate());
					receiverAccount.setLastUpdate(transactionBean.getDate());
					accountRepository.save(account);
					accountRepository.save(receiverAccount);
					transaction = transactionRepository.save(transaction);
				} else {
					throw new ServiceValidationException(Constants.AMOUNT_NOT_ALLOWED, HttpStatus.BAD_REQUEST);
				}
			} else {
				throw new ServiceValidationException(Constants.INCORRECT_PARAMETERS, HttpStatus.BAD_REQUEST);
			}
		} else if (TransactionType.WITHDRAWAL_OPERATION.equals(transactionBean.getTransactionType())) {
			if (isTransactionAllowed(account, -1 * transactionBean.getAmount())) {
				account.setAmount(account.getAmount() - transactionBean.getAmount());
				transaction = transactionBuilder.withSenderAccount(account)
						.build();
				account.setLastUpdate(transactionBean.getDate());
				accountRepository.save(account);
				transaction = transactionRepository.save(transaction);
			} else {
				throw new ServiceValidationException(Constants.AMOUNT_NOT_ALLOWED, HttpStatus.BAD_REQUEST);
			}
		} else if (TransactionType.DEPOSIT_OPERATION.equals(transactionBean.getTransactionType())) {
			account.setAmount(account.getAmount() + transactionBean.getAmount());
			transaction = transactionBuilder.withReveiverAccount(account)
					.build();
			account.setLastUpdate(transactionBean.getDate());
			accountRepository.save(account);
			transaction = transactionRepository.save(transaction);
		} else {
			throw new ServiceValidationException(Constants.INCORRECT_PARAMETERS, HttpStatus.BAD_REQUEST);
		}
		return modelMapper.map(transaction, TransactionDTO.class);
	}

	@Override
	public List<TransactionDTO> getBankAccountTransactionHistory(Long idAccount) {
		return transactionRepository.findAllByAccountId(idAccount)
				.stream()
				.map(transaction -> modelMapper.map(transaction, TransactionDTO.class))
				.collect(Collectors.toList());
	}

	private boolean isTransactionAllowed(Account account, Double amount) {
		return amount != null && account.getAmount() + account.getOverDraft() + amount >= 0;
	}

}
