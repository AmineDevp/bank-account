package com.bank.account.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import com.bank.account.entities.Account;
import com.bank.account.entities.Bank;
import com.bank.account.entities.Client;
import com.bank.account.entities.Transaction;
import com.bank.account.entities.WicketBank;
import com.bank.account.enums.Country;
import com.bank.account.enums.TransactionType;

@Profile("!swagger")
@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private TransactionRepository transactionRepository;

	@DisplayName("When FindAllByAccountId then Return Transactions")
	@Test
	public void whenFindAllByAccountId_thenReturnTransactions() {

		final Bank bank = new Bank.Builder().withBankName("SOCIETE GENERALE")
				.withBankIdentifierCode("SOGEFRPP")
				.withAddress("29 BD HAUSSMANN")
				.withZipCode("75009")
				.withCity("PARIS")
				.withCountry(Country.FRANCE)
				.build();
		entityManager.persist(bank);

		final WicketBank wicketBank = new WicketBank.Builder().withWicketCode(3360)
				.withWicketName("PARIS MESSINE")
				.withAddress("113 BD HAUSSMANN")
				.withZipCode("75008")
				.withCity("PARIS")
				.withCountry(Country.FRANCE)
				.withBank(bank)
				.build();
		entityManager.persist(wicketBank);

		final Client firstClient = new Client.Builder().withFirstname("AMINE")
				.withLastname("AMARIR")
				.withEmailAddress("AMINE.AMARIR@GMAIL.COM")
				.withAddress("52 RUE DE NEUILLY")
				.withZipCode("92110")
				.withCity("CLICHY")
				.withCountry(Country.FRANCE)
				.build();
		entityManager.persist(firstClient);

		final Client secondClient = new Client.Builder().withFirstname("HIBAT ALLAH")
				.withLastname("AMARIR")
				.withEmailAddress("HIBAT.AMARIR@GMAIL.COM")
				.withAddress("52 RUE DE NEUILLY")
				.withZipCode("92110")
				.withCity("CLICHY")
				.withCountry(Country.FRANCE)
				.build();
		entityManager.persist(secondClient);

		final Account firstAccount = new Account.Builder().withAccountNumber(50553980L)
				.withAmount(1000.0)
				.withWicketBank(wicketBank)
				.withClient(firstClient)
				.build();
		entityManager.persist(firstAccount);

		final Account secondAccount = new Account.Builder().withAccountNumber(50553981L)
				.withAmount(1000.0)
				.withWicketBank(wicketBank)
				.withClient(secondClient)
				.build();
		entityManager.persist(secondAccount);

		final Transaction firstTransaction = new Transaction.Builder().withTransactionType(TransactionType.TRANSFER_OPERATION)
				.withAmount(1000.0)
				.withMotif(null)
				.withDate(new Date())
				.withCurrency(Currency.getInstance(Locale.getDefault()))
				.withSenderAccount(firstAccount)
				.withReveiverAccount(secondAccount)
				.build();
		final Transaction secondTransaction = new Transaction.Builder().withTransactionType(TransactionType.DEPOSIT_OPERATION)
				.withAmount(100.0)
				.withMotif(null)
				.withDate(new Date())
				.withCurrency(Currency.getInstance(Locale.getDefault()))
				.withSenderAccount(null)
				.withReveiverAccount(firstAccount)
				.build();
		final Transaction thirdTransaction = new Transaction.Builder().withTransactionType(TransactionType.WITHDRAWAL_OPERATION)
				.withAmount(100.0)
				.withMotif(null)
				.withDate(new Date())
				.withCurrency(Currency.getInstance(Locale.getDefault()))
				.withSenderAccount(firstAccount)
				.withReveiverAccount(null)
				.build();
		entityManager.persist(firstTransaction);
		entityManager.persist(secondTransaction);
		entityManager.persist(thirdTransaction);
		entityManager.flush();

		List<Transaction> result = transactionRepository.findAllByAccountId(firstAccount.getIdAccount());

		assertThat(result).hasSize(3);

	}

}
