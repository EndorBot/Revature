package com.revature.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.dao.AccountDAO;
import com.revature.dao.ClientDAO;
import com.revature.dto.AddOrUpdateClientDTO;
import com.revature.exceptions.ClientNotFoundException;
import com.revature.exceptions.InvalidParameterException;
import com.revature.exceptions.PhoneNumberInvalidExcpetion;
import com.revature.model.Client;

public class ClientService {

	private Logger logger = LoggerFactory.getLogger(ClientService.class);

	private AccountDAO accountDao;
	private ClientDAO clientDao; // client service HAS-A relationship to ClientDAO

	public ClientService() {
		this.clientDao = new ClientDAO();
		this.accountDao = new AccountDAO();
	}
	
	// mock clientDao
	public ClientService(ClientDAO clientDao) {
		this.clientDao = clientDao;
	}
	
	// mock clientDao and accountDao
	public ClientService(ClientDAO clientDao, AccountDAO accountDao) {
		this.clientDao = clientDao;
		this.accountDao = accountDao;
	}

	public Client editFirstNameOfClient(String clientId, String changedName)
			throws SQLException, ClientNotFoundException, InvalidParameterException {
		logger.info("editFirstNameOfClient() invoked");
		try {
			int id = Integer.parseInt(clientId);

			Client clientToEdit = this.clientDao.getClientById(id);

			if (clientToEdit == null) {
				throw new ClientNotFoundException("Client with an id of " + clientId + " was not found");
			}

			AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO(changedName, clientToEdit.getLastName(),
					clientToEdit.getPhoneNumber(), clientToEdit.getAge());

			Client updatedClient = this.clientDao.updateClient(id, dto);

			return updatedClient;

		} catch (NumberFormatException e) {
			throw new InvalidParameterException("Id provided is not an int convertable value");
		}
	}

	public Client addClient(AddOrUpdateClientDTO dto)
			throws InvalidParameterException, PhoneNumberInvalidExcpetion, SQLException {
		logger.info("addClient() invoked");
		try {
			if (dto.getFirstName().trim().equals("") || dto.getLastName().trim().equals("")) {
				throw new InvalidParameterException("First name and/or last name cannot be blank");
			}

			if (dto.getAge() < 18) {
				throw new InvalidParameterException("Age must be over 18");
			}
			if (dto.getPhoneNumber().length() != 12) {
				throw new PhoneNumberInvalidExcpetion("phone number must be 12 long characters such as xxx-xxx-xxx");
				// Testing of checking if changedPhoneNumber < 12 characters or less than that
				// (greater xxx-xxx-xxx)
			}
			if (dto.getPhoneNumber().trim().equals("")) {
				throw new PhoneNumberInvalidExcpetion("phone number must be 12 long characters such as xxx-xxx-xxx");
				// Testing of checking if changedPhoneNumber < 12 characters or less than that
				// (greater xxx-xxx-xxx)
			}

			// trim the leading and trailing whitespaces of first, last name, and
			// phoneNumber.
			dto.setFirstName(dto.getFirstName().trim());
			dto.setLastName(dto.getLastName().trim());
			dto.setPhoneNumber(dto.getPhoneNumber().trim());

			Client insertedClient = this.clientDao.addClient(dto);
			return insertedClient;
			
		} catch (InvalidParameterException e) {
			throw new InvalidParameterException("Client's age must be over 18");
		} catch (PhoneNumberInvalidExcpetion e) {
			throw new PhoneNumberInvalidExcpetion("Phone number must be 12 characters long");
		}
	}

	public List<Client> getAllClients() throws SQLException {
		logger.info("getAllClients() invoked");

		List<Client> clients = this.clientDao.getAllClients();

		return clients;
	}

	public Client getClientById(String clientId)
			throws SQLException, ClientNotFoundException, InvalidParameterException {
		logger.info("getClientById() invoked");
		try {
			int id = Integer.parseInt(clientId);

			Client c = this.clientDao.getClientById(id);
			if (c == null) {
				throw new ClientNotFoundException("Client with the id of " + clientId + " was not found");
			}
			return c;
		} catch (NumberFormatException e) {
			throw new InvalidParameterException("Id provided is not an int convertable value");
		}
	}

//	public List<Client> deleteAllClients() {
//		logger.info("deleteAllClients() invoked" );
//		
//		List<Client> clients = this.clientDao.deleteAllClients();
//		
//		return clients;
//	}

	public void deleteClientById(String clientId)
			throws SQLException, InvalidParameterException, ClientNotFoundException {
		logger.info("deleteClientById() invoked");
		try {
			int id = Integer.parseInt(clientId);

			// check if client with the id exists
			Client client = this.clientDao.getClientById(id);
			if (client == null) {
				throw new ClientNotFoundException("Client with an id of " + clientId + " was not found");
			}
			// delete all accounts that belong to this client
			this.accountDao.deleteAllAccountsByIdClientId(id);
			
			this.clientDao.deleteClientById(id);
		} catch (NumberFormatException e) {
			throw new InvalidParameterException("id supplied was not an in");
		}

	}

	public Client editClientById(String clientId, String changedFirstName, String changedLastName,
			String changedPhoneNumber, int changedAge)
			throws SQLException, InvalidParameterException, ClientNotFoundException, PhoneNumberInvalidExcpetion {
		logger.info("editClientById() invoked");
		try {
			int id = Integer.parseInt(clientId);
			Client clientToEdit = this.clientDao.getClientById(id);
			
			if (clientToEdit == null) {
				throw new ClientNotFoundException("Client with an id of " + clientId + " was not found");
			}
			
			if (changedFirstName.trim().equals("") || changedLastName.trim().equals("")) {
				throw new InvalidParameterException("First name and/or last name cannot be blank");
			}

			if (changedPhoneNumber.length() != 12) {
				throw new PhoneNumberInvalidExcpetion(
						"Edited phone number must be 12 long characters such as xxx-xxx-xxx");
				// Testing of checking if changedPhoneNumber < 12 characters or less than that
				// (greater xxx-xxx-xxx)
			}
			// Since the DTO contains the first name, last name, phone number, and age of
			// the client.
			// Using the id to find a client, the 'dto' will have the new changed values
			AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO(changedFirstName, changedLastName, changedPhoneNumber,
					changedAge);

			Client updatedClient = this.clientDao.updateClient(id, dto);

			return updatedClient;
		} catch (NumberFormatException e) {
			throw new InvalidParameterException("Id provided is not an int convertable value");
		} catch (PhoneNumberInvalidExcpetion e) {
			throw new PhoneNumberInvalidExcpetion("Phone number must be 12 characters long");
		} catch (InvalidParameterException e) {
			throw new InvalidParameterException("The names cannot be blank");
		}

	}

}
