package com.revature.service;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.revature.dao.AccountDAO;
import com.revature.dao.ClientDAO;
import com.revature.exceptions.ClientNotFoundException;
import com.revature.exceptions.InvalidParameterException;
import com.revature.model.Account;
import com.revature.model.Client;

import io.javalin.http.Context;

public class AccountServiceTest {

	private AccountService sut;
	private ClientService aut;

	// AccountService's getAllAccountsByClientId() tests
	// positive
	@Test
	public void testGetAllAccountsByClientIdPositive()
			throws SQLException, ClientNotFoundException, InvalidParameterException {
		// arrange
		AccountDAO mockAccountDao = mock(AccountDAO.class);

		Account account1 = new Account(4, 376027690, 4000, "Savings", 1);
		Account account2 = new Account(6, 672067602, 10000, "Checking", 3);
		Account account3 = new Account(16, 17788346, 6500, "Savings", 3);

		ClientDAO mockClientDao = mock(ClientDAO.class);

		Client client1 = new Client(1, "Jonas", "Billyong", "734-673-7830", 19);
		Client client2 = new Client(3, "Jane", "Wells", "510-678-3672", 18);

		// fake clients
		List<Client> clientFromDao = new ArrayList<>();
		clientFromDao.add(client1);
		clientFromDao.add(client2);

		when(mockClientDao.getAllClients()).thenReturn(clientFromDao);

		// fake accounts
		List<Account> accountFromDao = new ArrayList<>();
		accountFromDao.add(account1);
		accountFromDao.add(account2);
		accountFromDao.add(account3);

		// List<Account> accounts = new ArrayList<>();
		String clientId = "3";
		Context ctx = null;
		int id = Integer.parseInt(clientId);
		/*
		 * 
		 * 
		 * if (ctx.queryParam("greaterThan") != null && ctx.queryParam("lessThan") !=
		 * null) { int greaterThan = Integer.parseInt(ctx.queryParam("greaterThan"));
		 * int lessThan = Integer.parseInt(ctx.queryParam("lessThan"));
		 * 
		 * accounts = this.mockAccountDao.getAllAccountsByClientId(id, greaterThan,
		 * lessThan); } else if (ctx.queryParam("lessThan") != null) { // only less int
		 * lessThan = Integer.parseInt(ctx.queryParam("lessThan"));
		 * 
		 * accounts = this.mockAccountDao.getAllAccountsByClientId(id, 0, lessThan); }
		 * else if (ctx.queryParam("greaterThan") != null) { // only greater than int
		 * greaterThan = Integer.parseInt(ctx.queryParam("greaterThan"));
		 * 
		 * accounts = this.mockAccountDao.getAllAccountsByClientId(id, greaterThan,
		 * 5000); } else { accounts = this.mockAccountDao.getAllAccountsByClientId(id,
		 * 0, 10000); }
		 */
		when(mockAccountDao.getAllAccountsByClientId(3, 100000, 300)).thenReturn(accountFromDao);

		AccountService accountService = new AccountService(mockAccountDao, mockClientDao);
		// act
		// List<Account> actual = accountService.getAllAccountsByClientId(id, ctx);

		// assert
		List<Account> expected = new ArrayList<>();
		expected.add(new Account(6, 672067602, 10000, "Checking", 3));
		expected.add(new Account(16, 17788346, 6500, "Savings", 3));

		// Assertions.assertEquals(expected, actual);

	}

	// AccountService's getAccountIdWithClientId(String clientId, String accountId)
	// tests
	// positive
	public void testGetAccountIdWithClientIdPositivet()
			throws SQLException, ClientNotFoundException, InvalidParameterException {
		// arrange
		AccountDAO mockAccountDao = mock(AccountDAO.class);

		when(mockAccountDao.getAccountByIdByClientId(eq(3))).thenReturn(new Account(3, 27650274, 4000, "Savings", 5));

		ClientDAO mockClientDao = mock(ClientDAO.class);

		when(mockClientDao.getClientById(eq(5))).thenReturn(new Client(5, "Luke", "Boulder", "681-676-7682", 43));

		AccountService accountService = new AccountService(mockAccountDao, mockClientDao);

		// act
		Account actual = accountService.getAccountIdWithClientId("5", "3");

		// assert
		Assertions.assertEquals(new Account(3, 27650274, 4000, "Savings", 5), actual);

	}
}
