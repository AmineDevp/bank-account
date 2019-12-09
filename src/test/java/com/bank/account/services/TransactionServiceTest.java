package com.bank.account.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.junit4.SpringRunner;

import com.bank.account.beans.TransactionBean;
import com.bank.account.dtos.TransactionDTO;
import com.bank.account.entities.Account;
import com.bank.account.entities.Bank;
import com.bank.account.entities.Client;
import com.bank.account.entities.Transaction;
import com.bank.account.entities.WicketBank;
import com.bank.account.enums.Country;
import com.bank.account.enums.TransactionType;
import com.bank.account.exceptions.ServiceValidationException;
import com.bank.account.repositories.AccountRepository;
import com.bank.account.repositories.TransactionRepository;
import com.bank.account.services.impl.TransactionServiceImpl;

@RunWith(SpringRunner.class)
public class TransactionServiceTest {

	@InjectMocks
	private TransactionServiceImpl transactionServiceImpl;

	private ModelMapper modelMapper;

	@Mock
	private ModelMapper modelServiceMapper;

	@Mock
	private AccountRepository accountRepository;

	@Mock
	private TransactionRepository transactionRepository;

	private Account firstAccount, secondAccount;

	@Before
	public void init() {

		modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.STRICT);

		final Bank bank = new Bank.Builder().withBankCode(30003)
				.withBankName("SOCIETE GENERALE")
				.withBankIdentifierCode("SOGEFRPP")
				.withAddress("29 BD HAUSSMANN")
				.withZipCode("75009")
				.withCity("PARIS")
				.withCountry(Country.FRANCE)
				.build();

		final WicketBank wicketBank = new WicketBank.Builder().withIdWicketBank(1L)
				.withWicketCode(3360)
				.withWicketName("PARIS MESSINE")
				.withAddress("113 BD HAUSSMANN")
				.withZipCode("75008")
				.withCity("PARIS")
				.withCountry(Country.FRANCE)
				.withBank(bank)
				.build();

		final Client firstClient = new Client.Builder().withIdClient(1L)
				.withFirstname("AMINE")
				.withLastname("AMARIR")
				.withEmailAddress("AMINE.AMARIR@GMAIL.COM")
				.withAddress("52 RUE DE NEUILLY")
				.withZipCode("92110")
				.withCity("CLICHY")
				.withCountry(Country.FRANCE)
				.build();

		final Client secondClient = new Client.Builder().withIdClient(2L)
				.withFirstname("HIBAT ALLAH")
				.withLastname("AMARIR")
				.withEmailAddress("HIBAT.AMARIR@GMAIL.COM")
				.withAddress("52 RUE DE NEUILLY")
				.withZipCode("92110")
				.withCity("CLICHY")
				.withCountry(Country.FRANCE)
				.build();

		firstAccount = new Account.Builder().withIdAccount(1L)
				.withAccountNumber(50553980L)
				.withAmount(1000.0)
				.withWicketBank(wicketBank)
				.withClient(firstClient)
				.build();

		secondAccount = new Account.Builder().withIdAccount(2L)
				.withAccountNumber(50553981L)
				.withAmount(1000.0)
				.withWicketBank(wicketBank)
				.withClient(secondClient)
				.build();

		when(accountRepository.findById(1L)).thenReturn(Optional.of(firstAccount));

		when(accountRepository.findById(2L)).thenReturn(Optional.of(secondAccount));
	}

	@DisplayName("Withdraw Valid Amount From Account")
	@Test
	public void testWithdrawValidAmountFromAccount() throws Exception {

		final TransactionBean transactionBean = new TransactionBean();
		transactionBean.setTransactionType(TransactionType.WITHDRAWAL_OPERATION);
		transactionBean.setAmount(100.0);
		transactionBean.setMotif(null);
		transactionBean.setDate(new Date());
		transactionBean.setCurrency(Currency.getInstance(Locale.getDefault()));
		transactionBean.setSenderAccountId(1L);
		transactionBean.setReveiverAccountId(null);

		final Account firstAccountAmountUpdated = new Account.Builder().build();
		BeanUtils.copyProperties(firstAccount, firstAccountAmountUpdated);
		firstAccountAmountUpdated.setLastUpdate(transactionBean.getDate());
		firstAccountAmountUpdated.setAmount(firstAccount.getAmount() - 100.0);

		when(accountRepository.save(firstAccountAmountUpdated)).thenReturn(firstAccountAmountUpdated);

		final Transaction transaction = new Transaction.Builder().withIdTransaction(1L)
				.withTransactionType(transactionBean.getTransactionType())
				.withAmount(transactionBean.getAmount())
				.withMotif(transactionBean.getMotif())
				.withDate(transactionBean.getDate())
				.withCurrency(transactionBean.getCurrency())
				.withSenderAccount(firstAccountAmountUpdated)
				.withReveiverAccount(null)
				.build();

		when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

		final TransactionDTO transactionDTOExpected = modelMapper.map(transaction, TransactionDTO.class);

		when(modelServiceMapper.map(transaction, TransactionDTO.class)).thenReturn(transactionDTOExpected);

		final TransactionDTO transactionDTOResult = transactionServiceImpl.createTransaction(1L, transactionBean);

		assertEquals(transactionDTOExpected, transactionDTOResult);

		verify(accountRepository, times(1)).findById(1L);
		verify(accountRepository, times(1)).save(firstAccountAmountUpdated);
		verify(transactionRepository, times(1)).save(any(Transaction.class));

	}

	@DisplayName("Withdraw Invalid Amount From Account")
	@Test(expected = ServiceValidationException.class)
	public void testWithdrawInvalidAmountFromAccount() throws Exception {

		final TransactionBean transactionBean = new TransactionBean();
		transactionBean.setTransactionType(TransactionType.WITHDRAWAL_OPERATION);
		transactionBean.setAmount(100.0);
		transactionBean.setMotif(null);
		transactionBean.setDate(new Date());
		transactionBean.setCurrency(Currency.getInstance(Locale.getDefault()));
		transactionBean.setSenderAccountId(1L);
		transactionBean.setReveiverAccountId(null);

		firstAccount.setAmount(0.0);

		transactionServiceImpl.createTransaction(1L, transactionBean);

	}

	@DisplayName("Deposit Valid Amount In Account")
	@Test
	public void testDepositValidAmountInAccount() throws Exception {

		final TransactionBean transactionBean = new TransactionBean();
		transactionBean.setTransactionType(TransactionType.DEPOSIT_OPERATION);
		transactionBean.setAmount(100.0);
		transactionBean.setMotif(null);
		transactionBean.setDate(new Date());
		transactionBean.setCurrency(Currency.getInstance(Locale.getDefault()));
		transactionBean.setSenderAccountId(null);
		transactionBean.setReveiverAccountId(1L);

		final Account firstAccountAmountUpdated = new Account.Builder().build();
		BeanUtils.copyProperties(firstAccount, firstAccountAmountUpdated);
		firstAccountAmountUpdated.setLastUpdate(transactionBean.getDate());
		firstAccountAmountUpdated.setAmount(firstAccount.getAmount() + 100.0);

		when(accountRepository.save(firstAccountAmountUpdated)).thenReturn(firstAccountAmountUpdated);

		final Transaction transaction = new Transaction.Builder().withIdTransaction(1L)
				.withTransactionType(transactionBean.getTransactionType())
				.withAmount(transactionBean.getAmount())
				.withMotif(transactionBean.getMotif())
				.withDate(transactionBean.getDate())
				.withCurrency(transactionBean.getCurrency())
				.withSenderAccount(null)
				.withReveiverAccount(firstAccountAmountUpdated)
				.build();

		when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

		final TransactionDTO transactionDTOExpected = modelMapper.map(transaction, TransactionDTO.class);

		when(modelServiceMapper.map(transaction, TransactionDTO.class)).thenReturn(transactionDTOExpected);

		final TransactionDTO transactionDTOResult = transactionServiceImpl.createTransaction(1L, transactionBean);

		assertEquals(transactionDTOExpected, transactionDTOResult);

		verify(accountRepository, times(1)).findById(1L);
		verify(accountRepository, times(1)).save(firstAccountAmountUpdated);
		verify(transactionRepository, times(1)).save(any(Transaction.class));

	}

	@DisplayName("Deposit Invalid Amount In Account")
	@Test(expected = ServiceValidationException.class)
	public void testDepositInvalidAmountInAccount() throws Exception {

		final TransactionBean transactionBean = new TransactionBean();
		transactionBean.setTransactionType(TransactionType.DEPOSIT_OPERATION);
		transactionBean.setAmount(-100.0);
		transactionBean.setMotif(null);
		transactionBean.setDate(new Date());
		transactionBean.setCurrency(Currency.getInstance(Locale.getDefault()));
		transactionBean.setSenderAccountId(null);
		transactionBean.setReveiverAccountId(1L);

		firstAccount.setAmount(0.0);

		transactionServiceImpl.createTransaction(1L, transactionBean);

	}

	@DisplayName("Transfer Valid Amount From One Account To Another")
	@Test
	public void testTransferValidAmountFromOneAccountToAnother() throws Exception {

		final TransactionBean transactionBean = new TransactionBean();
		transactionBean.setTransactionType(TransactionType.TRANSFER_OPERATION);
		transactionBean.setAmount(100.0);
		transactionBean.setMotif(null);
		transactionBean.setDate(new Date());
		transactionBean.setCurrency(Currency.getInstance(Locale.getDefault()));
		transactionBean.setSenderAccountId(1L);
		transactionBean.setReveiverAccountId(2L);

		final Account firstAccountAmountUpdated = new Account.Builder().build();
		BeanUtils.copyProperties(firstAccount, firstAccountAmountUpdated);
		firstAccountAmountUpdated.setLastUpdate(transactionBean.getDate());
		firstAccountAmountUpdated.setAmount(firstAccount.getAmount() - 100.0);

		when(accountRepository.save(firstAccountAmountUpdated)).thenReturn(firstAccountAmountUpdated);

		final Account secondAccountAmountUpdated = new Account.Builder().build();
		BeanUtils.copyProperties(secondAccount, secondAccountAmountUpdated);
		secondAccountAmountUpdated.setLastUpdate(transactionBean.getDate());
		secondAccountAmountUpdated.setAmount(secondAccount.getAmount() + 100.0);

		when(accountRepository.save(secondAccountAmountUpdated)).thenReturn(secondAccountAmountUpdated);

		final Transaction transaction = new Transaction.Builder().withIdTransaction(1L)
				.withTransactionType(transactionBean.getTransactionType())
				.withAmount(transactionBean.getAmount())
				.withMotif(transactionBean.getMotif())
				.withDate(transactionBean.getDate())
				.withCurrency(transactionBean.getCurrency())
				.withSenderAccount(firstAccountAmountUpdated)
				.withReveiverAccount(secondAccountAmountUpdated)
				.build();

		when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

		final TransactionDTO transactionDTOExpected = modelMapper.map(transaction, TransactionDTO.class);

		when(modelServiceMapper.map(transaction, TransactionDTO.class)).thenReturn(transactionDTOExpected);

		final TransactionDTO transactionDTOResult = transactionServiceImpl.createTransaction(1L, transactionBean);

		assertEquals(transactionDTOExpected, transactionDTOResult);

		verify(accountRepository, times(1)).findById(1L);
		verify(accountRepository, times(1)).save(firstAccountAmountUpdated);
		verify(accountRepository, times(1)).findById(2L);
		verify(accountRepository, times(1)).save(secondAccountAmountUpdated);
		verify(transactionRepository, times(1)).save(any(Transaction.class));

	}

	@DisplayName("Transfer Invalid Amount From One Account To Another")
	@Test(expected = ServiceValidationException.class)
	public void testTransferInvalidAmountFromOneAccountToAnother() throws Exception {

		final TransactionBean transactionBean = new TransactionBean();
		transactionBean.setTransactionType(TransactionType.DEPOSIT_OPERATION);
		transactionBean.setAmount(-100.0);
		transactionBean.setMotif(null);
		transactionBean.setDate(new Date());
		transactionBean.setCurrency(Currency.getInstance(Locale.getDefault()));
		transactionBean.setSenderAccountId(1L);
		transactionBean.setReveiverAccountId(2L);

		firstAccount.setAmount(0.0);

		transactionServiceImpl.createTransaction(1L, transactionBean);

	}

	@DisplayName("Get Bank Account Transaction History")
	@Test
	public void testGetBankAccountTransactionHistory() throws Exception {

		List<Transaction> transactions = Arrays.asList(new Transaction.Builder().withIdTransaction(1L)
				.withTransactionType(TransactionType.TRANSFER_OPERATION)
				.withAmount(1000.0)
				.withMotif(null)
				.withDate(new Date())
				.withCurrency(Currency.getInstance(Locale.getDefault()))
				.withSenderAccount(firstAccount)
				.withReveiverAccount(secondAccount)
				.build(),
				new Transaction.Builder().withIdTransaction(1L)
						.withTransactionType(TransactionType.DEPOSIT_OPERATION)
						.withAmount(100.0)
						.withMotif(null)
						.withDate(new Date())
						.withCurrency(Currency.getInstance(Locale.getDefault()))
						.withSenderAccount(null)
						.withReveiverAccount(firstAccount)
						.build(),
				new Transaction.Builder().withIdTransaction(1L)
						.withTransactionType(TransactionType.WITHDRAWAL_OPERATION)
						.withAmount(100.0)
						.withMotif(null)
						.withDate(new Date())
						.withCurrency(Currency.getInstance(Locale.getDefault()))
						.withSenderAccount(firstAccount)
						.withReveiverAccount(null)
						.build());

		when(transactionRepository.findAllByAccountId(1L)).thenReturn(transactions);

		List<TransactionDTO> transactionDTOs = transactions.stream()
				.map(transaction -> modelMapper.map(transaction, TransactionDTO.class))
				.collect(Collectors.toList());

		when(modelServiceMapper.map(transactions.get(0), TransactionDTO.class)).thenReturn(modelMapper.map(transactions.get(0), TransactionDTO.class));
		when(modelServiceMapper.map(transactions.get(1), TransactionDTO.class)).thenReturn(modelMapper.map(transactions.get(1), TransactionDTO.class));
		when(modelServiceMapper.map(transactions.get(2), TransactionDTO.class)).thenReturn(modelMapper.map(transactions.get(2), TransactionDTO.class));

		List<TransactionDTO> result = transactionServiceImpl.getBankAccountTransactionHistory(1L);

		assertEquals(transactionDTOs, result);

		verify(transactionRepository, times(1)).findAllByAccountId(1L);

	}

}
