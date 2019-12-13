package com.bank.account.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.account.beans.TransactionBean;
import com.bank.account.dtos.TransactionDTO;
import com.bank.account.entities.Account;
import com.bank.account.entities.Transaction;
import com.bank.account.exceptions.AccountNotFoundException;
import com.bank.account.exceptions.AmountNotAllowedException;
import com.bank.account.exceptions.IncorrectParametersException;
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
	public TransactionDTO createTransaction(Long idAccount, TransactionBean transactionBean) {
		if (transactionBean.getAmount() < 0) {
			throw new AmountNotAllowedException();
		}
		final Transaction transaction;
		switch (transactionBean.getTransactionType()) {
		case TRANSFER_OPERATION:
			transaction = this.processTransferOperation(idAccount, transactionBean);
			break;
		case WITHDRAWAL_OPERATION:
			transaction = this.processWithdrawalOperation(idAccount, transactionBean);
			break;
		case DEPOSIT_OPERATION:
			transaction = this.processDepositOperation(idAccount, transactionBean);
			break;
		default:
			throw new IncorrectParametersException();
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

	private Transaction processTransferOperation(Long idAccount, TransactionBean transactionBean) {
		final Account account = accountRepository.findById(idAccount)
				.orElseThrow(AccountNotFoundException::new);

		if (idAccount != null && idAccount.equals(transactionBean.getSenderAccountId())) {
			final Account receiverAccount = accountRepository.findById(transactionBean.getReveiverAccountId())
					.orElseThrow(AccountNotFoundException::new);

			if (isTransactionAllowed(account, -1 * transactionBean.getAmount())) {
				account.setAmount(account.getAmount() - transactionBean.getAmount());
				receiverAccount.setAmount(receiverAccount.getAmount() + transactionBean.getAmount());
				final Transaction transaction = Transaction.builder()
						.transactionType(transactionBean.getTransactionType())
						.amount(transactionBean.getAmount())
						.motif(transactionBean.getMotif())
						.date(transactionBean.getDate())
						.currency(transactionBean.getCurrency())
						.senderAccount(account)
						.reveiverAccount(receiverAccount)
						.build();
				account.setLastUpdate(transactionBean.getDate());
				receiverAccount.setLastUpdate(transactionBean.getDate());
				accountRepository.save(account);
				accountRepository.save(receiverAccount);
				return transactionRepository.save(transaction);
			} else {
				throw new AmountNotAllowedException();
			}
		} else {
			throw new IncorrectParametersException();
		}
	}

	private Transaction processWithdrawalOperation(Long idAccount, TransactionBean transactionBean) {
		final Account account = accountRepository.findById(idAccount)
				.orElseThrow(AccountNotFoundException::new);
		if (isTransactionAllowed(account, -1 * transactionBean.getAmount())) {
			account.setAmount(account.getAmount() - transactionBean.getAmount());
			final Transaction transaction = Transaction.builder()
					.transactionType(transactionBean.getTransactionType())
					.amount(transactionBean.getAmount())
					.motif(transactionBean.getMotif())
					.date(transactionBean.getDate())
					.currency(transactionBean.getCurrency())
					.senderAccount(account)
					.build();
			account.setLastUpdate(transactionBean.getDate());
			accountRepository.save(account);
			return transactionRepository.save(transaction);
		} else {
			throw new AmountNotAllowedException();
		}
	}

	private Transaction processDepositOperation(Long idAccount, TransactionBean transactionBean) {
		final Account account = accountRepository.findById(idAccount)
				.orElseThrow(AccountNotFoundException::new);
		account.setAmount(account.getAmount() + transactionBean.getAmount());
		final Transaction transaction = Transaction.builder()
				.transactionType(transactionBean.getTransactionType())
				.amount(transactionBean.getAmount())
				.motif(transactionBean.getMotif())
				.date(transactionBean.getDate())
				.currency(transactionBean.getCurrency())
				.reveiverAccount(account)
				.build();
		account.setLastUpdate(transactionBean.getDate());
		accountRepository.save(account);
		return transactionRepository.save(transaction);

	}

}
