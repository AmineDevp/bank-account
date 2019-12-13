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

		final Bank bank = Bank.builder()
				.bankName("SOCIETE GENERALE")
				.bankIdentifierCode("SOGEFRPP")
				.address("29 BD HAUSSMANN")
				.zipCode("75009")
				.city("PARIS")
				.country(Country.FRANCE)
				.build();
		entityManager.persist(bank);

		final WicketBank wicketBank = WicketBank.builder()
				.wicketCode(3360)
				.wicketName("PARIS MESSINE")
				.address("113 BD HAUSSMANN")
				.zipCode("75008")
				.city("PARIS")
				.country(Country.FRANCE)
				.bank(bank)
				.build();
		entityManager.persist(wicketBank);

		final Client firstClient = Client.builder()
				.firstname("AMINE")
				.lastname("AMARIR")
				.emailAddress("AMINE.AMARIR@GMAIL.COM")
				.address("52 RUE DE NEUILLY")
				.zipCode("92110")
				.city("CLICHY")
				.country(Country.FRANCE)
				.build();
		entityManager.persist(firstClient);

		final Client secondClient = Client.builder()
				.firstname("HIBAT ALLAH")
				.lastname("AMARIR")
				.emailAddress("HIBAT.AMARIR@GMAIL.COM")
				.address("52 RUE DE NEUILLY")
				.zipCode("92110")
				.city("CLICHY")
				.country(Country.FRANCE)
				.build();
		entityManager.persist(secondClient);

		final Account firstAccount = Account.builder()
				.accountNumber(50553980L)
				.amount(1000.0)
				.wicketBank(wicketBank)
				.client(firstClient)
				.build();
		entityManager.persist(firstAccount);

		final Account secondAccount = Account.builder()
				.accountNumber(50553981L)
				.amount(1000.0)
				.wicketBank(wicketBank)
				.client(secondClient)
				.build();
		entityManager.persist(secondAccount);

		final Transaction firstTransaction = Transaction.builder()
				.transactionType(TransactionType.TRANSFER_OPERATION)
				.amount(1000.0)
				.motif(null)
				.date(new Date())
				.currency(Currency.getInstance(Locale.getDefault()))
				.senderAccount(firstAccount)
				.reveiverAccount(secondAccount)
				.build();
		final Transaction secondTransaction = Transaction.builder()
				.transactionType(TransactionType.DEPOSIT_OPERATION)
				.amount(100.0)
				.motif(null)
				.date(new Date())
				.currency(Currency.getInstance(Locale.getDefault()))
				.senderAccount(null)
				.reveiverAccount(firstAccount)
				.build();
		final Transaction thirdTransaction = Transaction.builder()
				.transactionType(TransactionType.WITHDRAWAL_OPERATION)
				.amount(100.0)
				.motif(null)
				.date(new Date())
				.currency(Currency.getInstance(Locale.getDefault()))
				.senderAccount(firstAccount)
				.reveiverAccount(null)
				.build();
		entityManager.persist(firstTransaction);
		entityManager.persist(secondTransaction);
		entityManager.persist(thirdTransaction);
		entityManager.flush();

		List<Transaction> result = transactionRepository.findAllByAccountId(firstAccount.getIdAccount());

		assertThat(result).hasSize(3);

	}

}
