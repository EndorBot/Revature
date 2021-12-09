package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.dto.AddOrUpdateAccountDTO;
import com.revature.model.Account;
import com.revature.service.AccountService;
import com.revature.util.JDBCUtility;

public class AccountDAO {

	private Logger logger = LoggerFactory.getLogger(AccountDAO.class);

	
	public void deleteAllAccountsByIdClientId(int clientId) throws SQLException {
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "DELETE FROM accounts WHERE client_id = ?";

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, clientId);

			pstmt.executeUpdate();
		}

	}

	public void deleteAccountById(int accountId) throws SQLException {
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "DELETE FROM accounts WHERE account_id = ?";

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, accountId);

			pstmt.executeUpdate();
		}
	}

	public List<Account> getAllAccountsByClientId(int clientId, int greaterThan, int lessThan) throws SQLException {
		List<Account> accounts = new ArrayList<>();

		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "SELECT * FROM accounts WHERE client_id = ? AND account_amount >= ? AND account_amount <= ?";

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, clientId);
			pstmt.setInt(2, greaterThan);
			pstmt.setInt(3, lessThan);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("account_id");
				int accountNumber = rs.getInt("account_number");
				int accountAmount = rs.getInt("account_amount");
				String accountType = rs.getString("account_type");
				int refClientId = rs.getInt("client_id");

				Account a = new Account(id, accountNumber, accountAmount, accountType, refClientId);
				logger.info("getAllAccountsByClientId was processed");
				accounts.add(a);
				
			}
		}

		return accounts;
	}

	public Account getAccountByIdByClientId(int accountId) throws SQLException {
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "SELECT * FROM accounts WHERE client_id = ?";

			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, accountId);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new Account(rs.getInt("account_id"), rs.getInt("account_number"), rs.getInt("account_amount"),
						rs.getString("account_type"), rs.getInt("client_id"));
			} else {
				return null;
			}
		}
	}

	public Account updatedAccount(int accountId, AddOrUpdateAccountDTO account) throws SQLException {
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "UPDATE accounts" + " SET account_number = ?," + " account_amount = ?," + " account_type = ?," + " client_Id = ? "
					+ "WHERE " + "account_id = ?;";
			// + " client_Id = ? "
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, account.getAccountNumber());
			pstmt.setInt(2, account.getAccountAmount());
			pstmt.setString(3, account.getAccountType());
			pstmt.setInt(4, account.getRefClientId());
			pstmt.setInt(5, accountId);

			int numberOfRecordsUpdated = pstmt.executeUpdate();

			if (numberOfRecordsUpdated != 1) {
				throw new SQLException("Unable to update account record w/ id of " + accountId);
			}

		}
		return new Account(accountId, account.getAccountNumber(), account.getAccountAmount(), account.getAccountType(), account.getRefClientId());
	}

	public Account addAccountToClientId(AddOrUpdateAccountDTO account) throws SQLException {
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "INSERT INTO accounts (account_number, account_amount, account_type, client_id)"
					+ " VALUES (?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, account.getAccountNumber());
			pstmt.setInt(2, account.getAccountAmount());
			pstmt.setString(3, account.getAccountType());
			pstmt.setInt(4, account.getRefClientId());

			int numberOfRecordsInserted = pstmt.executeUpdate();

			// add account was unsucceful
			if (numberOfRecordsInserted != 1) {
				throw new SQLException("Adding a new account to this Client was unsucceful");
			}

			ResultSet rs = pstmt.getGeneratedKeys();

			rs.next();
			int automaticallyGeneratedId = rs.getInt(1);

			// return the account made
			return new Account(automaticallyGeneratedId, account.getAccountNumber(), account.getAccountAmount(),
					account.getAccountType(), account.getRefClientId());
			// , account.getRefClientId()

		}
	}

}
