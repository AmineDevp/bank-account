package com.bank.account.controllers;

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
import org.modelmapper.ModelMapper;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.bank.account.repositories.AccountRepository;
import com.bank.account.repositories.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionControllerTest {

	private static final ObjectMapper om = new ObjectMapper();

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private ModelMapper modelMapper;

	@MockBean
	private AccountRepository accountRepository;

	@MockBean
	private TransactionRepository transactionRepository;

	private Account firstAccount, secondAccount;

	@Before
	public void init() {

		final Bank bank = Bank.builder()
				.bankCode(30003)
				.bankName("SOCIETE GENERALE")
				.bankIdentifierCode("SOGEFRPP")
				.address("29 BD HAUSSMANN")
				.zipCode("75009")
				.city("PARIS")
				.country(Country.FRANCE)
				.build();

		final WicketBank wicketBank = WicketBank.builder()
				.idWicketBank(1L)
				.wicketCode(3360)
				.wicketName("PARIS MESSINE")
				.address("113 BD HAUSSMANN")
				.zipCode("75008")
				.city("PARIS")
				.country(Country.FRANCE)
				.bank(bank)
				.build();

		final Client firstClient = Client.builder()
				.idClient(1L)
				.firstname("AMINE")
				.lastname("AMARIR")
				.emailAddress("AMINE.AMARIR@GMAIL.COM")
				.address("52 RUE DE NEUILLY")
				.zipCode("92110")
				.city("CLICHY")
				.country(Country.FRANCE)
				.build();

		final Client secondClient = Client.builder()
				.idClient(2L)
				.firstname("HIBAT ALLAH")
				.lastname("AMARIR")
				.emailAddress("HIBAT.AMARIR@GMAIL.COM")
				.address("52 RUE DE NEUILLY")
				.zipCode("92110")
				.city("CLICHY")
				.country(Country.FRANCE)
				.build();

		firstAccount = Account.builder()
				.idAccount(1L)
				.accountNumber(50553980L)
				.amount(1000.0)
				.wicketBank(wicketBank)
				.client(firstClient)
				.build();

		secondAccount = Account.builder()
				.idAccount(2L)
				.accountNumber(50553981L)
				.amount(1000.0)
				.wicketBank(wicketBank)
				.client(secondClient)
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

		final Account firstAccountAmountUpdated = Account.builder()
				.build();
		BeanUtils.copyProperties(firstAccount, firstAccountAmountUpdated);
		firstAccountAmountUpdated.setLastUpdate(transactionBean.getDate());
		firstAccountAmountUpdated.setAmount(firstAccount.getAmount() - 100.0);

		when(accountRepository.save(firstAccountAmountUpdated)).thenReturn(firstAccountAmountUpdated);

		final Transaction transaction = Transaction.builder()
				.idTransaction(1L)
				.transactionType(transactionBean.getTransactionType())
				.amount(transactionBean.getAmount())
				.motif(transactionBean.getMotif())
				.date(transactionBean.getDate())
				.currency(transactionBean.getCurrency())
				.senderAccount(firstAccountAmountUpdated)
				.reveiverAccount(null)
				.build();

		when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

		final TransactionDTO transactionDTO = modelMapper.map(transaction, TransactionDTO.class);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(om.writeValueAsString(transactionBean), headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/v1/transactions/1", HttpMethod.POST, entity, String.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		JSONAssert.assertEquals(om.writeValueAsString(transactionDTO), response.getBody(), false);

		verify(accountRepository, times(1)).findById(1L);
		verify(accountRepository, times(1)).save(firstAccountAmountUpdated);
		verify(transactionRepository, times(1)).save(any(Transaction.class));

	}

	@DisplayName("Withdraw Invalid Amount From Account")
	@Test
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

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(om.writeValueAsString(transactionBean), headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/v1/transactions/1", HttpMethod.POST, entity, String.class);

		String expectedJson = "{\"status\":400,\"error\":\"Bad Request\",\"message\":\"Amount is not allowed.\"}";
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		JSONAssert.assertEquals(expectedJson, response.getBody(), false);

		verify(transactionRepository, times(0)).save(any(Transaction.class));

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

		final Account firstAccountAmountUpdated = Account.builder()
				.build();
		BeanUtils.copyProperties(firstAccount, firstAccountAmountUpdated);
		firstAccountAmountUpdated.setLastUpdate(transactionBean.getDate());
		firstAccountAmountUpdated.setAmount(firstAccount.getAmount() + 100.0);

		when(accountRepository.save(firstAccountAmountUpdated)).thenReturn(firstAccountAmountUpdated);

		final Transaction transaction = Transaction.builder()
				.idTransaction(1L)
				.transactionType(transactionBean.getTransactionType())
				.amount(transactionBean.getAmount())
				.motif(transactionBean.getMotif())
				.date(transactionBean.getDate())
				.currency(transactionBean.getCurrency())
				.senderAccount(null)
				.reveiverAccount(firstAccountAmountUpdated)
				.build();

		when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

		final TransactionDTO transactionDTO = modelMapper.map(transaction, TransactionDTO.class);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(om.writeValueAsString(transactionBean), headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/v1/transactions/1", HttpMethod.POST, entity, String.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		JSONAssert.assertEquals(om.writeValueAsString(transactionDTO), response.getBody(), false);

		verify(accountRepository, times(1)).findById(1L);
		verify(accountRepository, times(1)).save(firstAccountAmountUpdated);
		verify(transactionRepository, times(1)).save(any(Transaction.class));

	}

	@DisplayName("Deposit Invalid Amount In Account")
	@Test
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

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(om.writeValueAsString(transactionBean), headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/v1/transactions/1", HttpMethod.POST, entity, String.class);

		String expectedJson = "{\"status\":400,\"error\":\"Bad Request\",\"message\":\"Amount is not allowed.\"}";
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		JSONAssert.assertEquals(expectedJson, response.getBody(), false);

		verify(transactionRepository, times(0)).save(any(Transaction.class));

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

		final Account firstAccountAmountUpdated = Account.builder()
				.build();
		BeanUtils.copyProperties(firstAccount, firstAccountAmountUpdated);
		firstAccountAmountUpdated.setLastUpdate(transactionBean.getDate());
		firstAccountAmountUpdated.setAmount(firstAccount.getAmount() - 100.0);

		when(accountRepository.save(firstAccountAmountUpdated)).thenReturn(firstAccountAmountUpdated);

		final Account secondAccountAmountUpdated = Account.builder()
				.build();
		BeanUtils.copyProperties(secondAccount, secondAccountAmountUpdated);
		secondAccountAmountUpdated.setLastUpdate(transactionBean.getDate());
		secondAccountAmountUpdated.setAmount(secondAccount.getAmount() + 100.0);

		when(accountRepository.save(secondAccountAmountUpdated)).thenReturn(secondAccountAmountUpdated);

		final Transaction transaction = Transaction.builder()
				.idTransaction(1L)
				.transactionType(transactionBean.getTransactionType())
				.amount(transactionBean.getAmount())
				.motif(transactionBean.getMotif())
				.date(transactionBean.getDate())
				.currency(transactionBean.getCurrency())
				.senderAccount(firstAccountAmountUpdated)
				.reveiverAccount(secondAccountAmountUpdated)
				.build();

		when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

		final TransactionDTO transactionDTO = modelMapper.map(transaction, TransactionDTO.class);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(om.writeValueAsString(transactionBean), headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/v1/transactions/1", HttpMethod.POST, entity, String.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		JSONAssert.assertEquals(om.writeValueAsString(transactionDTO), response.getBody(), false);

		verify(accountRepository, times(1)).findById(1L);
		verify(accountRepository, times(1)).save(firstAccountAmountUpdated);
		verify(accountRepository, times(1)).findById(2L);
		verify(accountRepository, times(1)).save(secondAccountAmountUpdated);
		verify(transactionRepository, times(1)).save(any(Transaction.class));

	}

	@DisplayName("Transfer Invalid Amount From One Account To Another")
	@Test
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

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(om.writeValueAsString(transactionBean), headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/v1/transactions/1", HttpMethod.POST, entity, String.class);

		String expectedJson = "{\"status\":400,\"error\":\"Bad Request\",\"message\":\"Amount is not allowed.\"}";
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		JSONAssert.assertEquals(expectedJson, response.getBody(), false);

		verify(transactionRepository, times(0)).save(any(Transaction.class));

	}

	@DisplayName("Get Bank Account Transaction History")
	@Test
	public void testGetBankAccountTransactionHistory() throws Exception {

		List<Transaction> transactions = Arrays.asList(Transaction.builder()
				.idTransaction(1L)
				.transactionType(TransactionType.TRANSFER_OPERATION)
				.amount(1000.0)
				.motif(null)
				.date(new Date())
				.currency(Currency.getInstance(Locale.getDefault()))
				.senderAccount(firstAccount)
				.reveiverAccount(secondAccount)
				.build(),
				Transaction.builder()
						.idTransaction(1L)
						.transactionType(TransactionType.DEPOSIT_OPERATION)
						.amount(100.0)
						.motif(null)
						.date(new Date())
						.currency(Currency.getInstance(Locale.getDefault()))
						.senderAccount(null)
						.reveiverAccount(firstAccount)
						.build(),
				Transaction.builder()
						.idTransaction(1L)
						.transactionType(TransactionType.WITHDRAWAL_OPERATION)
						.amount(100.0)
						.motif(null)
						.date(new Date())
						.currency(Currency.getInstance(Locale.getDefault()))
						.senderAccount(firstAccount)
						.reveiverAccount(null)
						.build());

		when(transactionRepository.findAllByAccountId(1L)).thenReturn(transactions);

		List<TransactionDTO> transactionDTOs = transactions.stream()
				.map(transaction -> modelMapper.map(transaction, TransactionDTO.class))
				.collect(Collectors.toList());

		String expected = om.writeValueAsString(transactionDTOs);

		ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/transactions/1", String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), true);

		verify(transactionRepository, times(1)).findAllByAccountId(1L);

	}

}
