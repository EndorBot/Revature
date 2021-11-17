package com.revature.controller;

import java.util.List;

import com.revature.dto.AddOrUpdateAccountDTO;
import com.revature.model.Account;
import com.revature.service.AccountService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class AccountController {
	private AccountService accountService;

	public AccountController() {
		this.accountService = new AccountService();
	}

	private Handler getAllAccountsById = (ctx) -> {
		String clientId = ctx.pathParam("id");

		List<Account> accounts = this.accountService.getAllAccountsByClientId(clientId, ctx);

		ctx.json(accounts);
	};

	private Handler getAccountIdWithClientId = (ctx) -> {
		String clientId = ctx.pathParam("client_id");
		String accountId = ctx.pathParam("account_id");

		Account a = this.accountService.getAccountIdWithClientId(clientId, accountId);

		ctx.json(a);
	};

	private Handler addAccountByClientId = (ctx) -> {
		AddOrUpdateAccountDTO dto = ctx.bodyAsClass(AddOrUpdateAccountDTO.class);

		Account a = this.accountService.addAccountByClientId(dto);

		ctx.json(a);
		ctx.status(201);
	};

	private Handler editAccountWithClientId = (ctx) -> {
		// String clientId = ctx.pathParam("client_id");
		String accountId = ctx.pathParam("account_id");

		AddOrUpdateAccountDTO dto = ctx.bodyAsClass(AddOrUpdateAccountDTO.class);

		Account a = this.accountService.editAccountWithClientId(accountId, dto.getAccountNumber(),
				dto.getAccountAmount(), dto.getAccountType(), dto.getRefClientId());

		ctx.json(a);

	};

	private Handler deleteAccountWithClientId = (ctx) -> {
		String clientId = ctx.pathParam("client_id");
		String accountId = ctx.pathParam("account_id");

		this.accountService.deleteAccountIdWithClientId(clientId, accountId);

		ctx.json("Account with with an id of " + accountId);
	};

	public void registerEndpoints(Javalin app) {
		app.post("/clients/{id}/accounts", addAccountByClientId);
		app.get("/clients/{id}/accounts", getAllAccountsById);
		app.get("/clients/{client_id}/accounts/{account_id}", getAccountIdWithClientId);
		app.put("/clients/{client_id}/accounts/{account_id}", editAccountWithClientId);
		app.delete("/clients/{client_id}/accounts/{account_id}", deleteAccountWithClientId);

	}

}
