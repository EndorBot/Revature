package com.revature.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.dao.AccountDAO;
import com.revature.dao.ClientDAO;
import com.revature.dto.AddOrUpdateAccountDTO;
import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.ClientNotFoundException;
import com.revature.exceptions.InvalidParameterException;
import com.revature.model.Account;

import io.javalin.http.Context;

public class AccountService {

	private Logger logger = LoggerFactory.getLogger(AccountService.class);

	private AccountDAO accountDao;
	private ClientDAO clientDAO;

	public AccountService() {
		this.accountDao = new AccountDAO();
		this.clientDAO = new ClientDAO();
	}

	// for mocking
	public AccountService(AccountDAO accountDao, ClientDAO clientDAO) {
		this.accountDao = accountDao;
		this.clientDAO = clientDAO;
	}

	public List<Account> getAllAccountsByClientId(String clientId, Context ctx)
			throws InvalidParameterException, ClientNotFoundException, SQLException {
		
		logger.info("getAllAccountsByClientId() invoked");
		
		List<Account> accounts = new ArrayList<>();
		
		int id = Integer.parseInt(clientId);

		if (ctx.queryParam("greaterThan") != null && ctx.queryParam("lessThan") != null) {
			int greaterThan = Integer.parseInt(ctx.queryParam("greaterThan"));
			int lessThan = Integer.parseInt(ctx.queryParam("lessThan"));

			accounts = this.accountDao.getAllAccountsByClientId(id, greaterThan, lessThan);
		} else if (ctx.queryParam("lessThan") != null) { // only less
			int lessThan = Integer.parseInt(ctx.queryParam("lessThan"));

			accounts = this.accountDao.getAllAccountsByClientId(id, 0, lessThan);
		} else if (ctx.queryParam("greaterThan") != null) { // only greater than
			int greaterThan = Integer.parseInt(ctx.queryParam("greaterThan"));

			accounts = this.accountDao.getAllAccountsByClientId(id, greaterThan, 1000);
		} else {
			accounts = this.accountDao.getAllAccountsByClientId(id, 0, 10000);
		}
		return accounts;

	}

	public Account getAccountIdWithClientId(String clientId, String accountId)
			throws SQLException, InvalidParameterException, AccountNotFoundException {
		logger.info("getAccountIdWithClientId() invoked");
		try {
			int id = Integer.parseInt(clientId);

			Account a = this.accountDao.getAccountByIdByClientId(id);
			if (a == null) {
				throw new AccountNotFoundException(
						"Account with an id of " + accountId + " belonging to " + clientId + " was not found");
			}
			return a;
		} catch (NumberFormatException e) {
			throw new InvalidParameterException("Id provided is not an int convertable value");
		}
	}

	public Account addAccountByClientId(AddOrUpdateAccountDTO dto)
			throws SQLException, InvalidParameterException{
		logger.info("addAccountByClientId() invoked");
		try {
			if (dto.getAccountAmount() < 1) {
				throw new InvalidParameterException("The account's amount must be greater than 0");
			}

			if (dto.getAccountNumber() < 1) {
				throw new InvalidParameterException("The account's number must be greater than 0");
			}

			if (dto.getAccountType().trim().equals("")) {
				throw new InvalidParameterException("The account's type cannot be blank");
			}

			dto.setAccountType(dto.getAccountType().trim());

			Account insertedAccount = this.accountDao.addAccountToClientId(dto);
			return insertedAccount;
		} catch (InvalidParameterException e) {
			throw new InvalidParameterException("account's number must not be blank");
		}

	}

	public Account editAccountWithClientId(String accountId, int accountNumber, int accountAmount, String accountType,
			int clientId) throws SQLException, InvalidParameterException, AccountNotFoundException {
		logger.info("editAccountWithClientId() invoked");
		try {
			//int c_id = Integer.parseInt(clientId);
			int a_id = Integer.parseInt(accountId);

			Account accountToEdit = this.accountDao.getAccountByIdByClientId(a_id);

			if (accountToEdit == null) {
				throw new AccountNotFoundException(
						"Account with an id of " + accountId + " that belongs to " + clientId + " was not found");
			}

			AddOrUpdateAccountDTO dto = new AddOrUpdateAccountDTO(accountNumber, accountAmount, accountType, clientId);

			Account updatedAccount = this.accountDao.updatedAccount(a_id, dto);

			return updatedAccount;
		} catch (NumberFormatException e) {
			throw new InvalidParameterException("Id provided is not an int convertable value");
		}

	}

	public void deleteAccountIdWithClientId(String clientId, String accountId)
			throws SQLException, InvalidParameterException, AccountNotFoundException {
		logger.info("deleteAccountWithClientId() invoked");
		try {
			int c_id = Integer.parseInt(clientId);
			int a_id = Integer.parseInt(accountId);

			// check if account exists
			Account account = this.accountDao.getAccountByIdByClientId(a_id);
			if (account == null) {
				throw new AccountNotFoundException(
						"Account with an id of " + accountId + " that belongs to " + clientId + " was not found");
			}
			this.accountDao.deleteAccountById(a_id);
		} catch (NumberFormatException e) {
			throw new InvalidParameterException("id supplied was not an in");
		}

	}

}
