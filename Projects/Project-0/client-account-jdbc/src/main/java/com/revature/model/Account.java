package com.revature.model;

import java.util.Objects;

public class Account {
	private int accountId;
	private int accountNumber;
	private int accountAmount;
	private String accountType;
	private int refClientId;
	
	public Account() {
		
	}
	
	public Account(int accountId, int accountNumber, int accountAmount, String accountType, int refClientId) {
		this.accountId = accountId;
		this.accountNumber = accountNumber;
		this.accountAmount = accountAmount;
		this.accountType = accountType;
		this.refClientId = refClientId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(int accountAmount) {
		this.accountAmount = accountAmount;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getRefClientId() {
		return refClientId;
	}

	public void setRefClientId(int refClientId) {
		this.refClientId = refClientId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountAmount, accountId, accountNumber, accountType, refClientId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return accountAmount == other.accountAmount && accountId == other.accountId
				&& accountNumber == other.accountNumber && Objects.equals(accountType, other.accountType)
				&& refClientId == other.refClientId;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountNumber=" + accountNumber + ", accountAmount="
				+ accountAmount + ", accountType=" + accountType + ", refClientId=" + refClientId + "]";
	}

}
