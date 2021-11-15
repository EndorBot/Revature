package com.revature.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.dao.ClientDAO;
import com.revature.dto.AddOrUpdateClientDTO;
import com.revature.exceptions.ClientNotFoundException;
import com.revature.exceptions.InvalidParameterException;
import com.revature.model.Client;

public class ClientService {
	
	private Logger logger = LoggerFactory.getLogger(ClientService.class);
	
	private ClientDAO clientDao; // client service HAS-A relationship to ClientDAO
	
	public ClientService() {
		this.clientDao = new ClientDAO();
	}
	
	public ClientService(ClientDAO clientDao) {
		this.clientDao = clientDao;
	}

	public Client editFirstNameOfClient(String clientId, String changedName)
		throws SQLException, ClientNotFoundException, InvalidParameterException {
			try {
				int id = Integer.parseInt(clientId);
				
				Client clientToEdit = this.clientDao.getClientById(id);
				
				if(clientToEdit == null) {
					throw new ClientNotFoundException("Client with an id of " + clientId + " was not found");
				}
				
				AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO(changedName, clientToEdit.getLastName(), 
						clientToEdit.getPhoneNumber(), clientToEdit.getAge());
				
				Client updatedClient = this.clientDao.updateClient(id, dto);
				
				return updatedClient;
				
			}catch(NumberFormatException e) {
				throw new InvalidParameterException("Id provided is not an int convertable value");
		}
	}

	public Client addClient(AddOrUpdateClientDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Client> getAllClients() throws SQLException {
		logger.info("getAllClients() invoked");
		
		List<Client> clients = this.clientDao.getAllClients();
		
		return clients;
	}

	public Client getClientById(String clientId) throws SQLException, ClientNotFoundException, InvalidParameterException {
		try {
			int id = Integer.parseInt(clientId);
			
			Client c = this.clientDao.getClientById(id);
			if (c == null) {
				throw new ClientNotFoundException("Client with the id of " + clientId + " was not found");
			}
			return c;
		}catch(NumberFormatException e){
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

	public void deleteClientById(String clientId) throws SQLException, InvalidParameterException, ClientNotFoundException {
		try {
			int id = Integer.parseInt(clientId);
			
			// check if client with the id exists
			Client client = this.clientDao.getClientById(id);
			if(client == null) {
				throw new ClientNotFoundException("Client with an id of " + clientId +" was not found");
			}
			this.clientDao.deleteClientById(id);
		}catch(NumberFormatException e) {
			throw new InvalidParameterException("id supplied was not an in");
		}
		
	}

	public Client editClientById(String clientId, String changedFirstName, String changedLastName, String changedPhoneNumber, int changedAge) 
			throws SQLException, InvalidParameterException, ClientNotFoundException{
		try {
			int id = Integer.parseInt(clientId);
			Client clientToEdit = this.clientDao.getClientById(id);
			
			if (clientToEdit == null) {
				throw new ClientNotFoundException("Client with an id of " + clientId + " was not found");
			}
			// Since the DTO contains the first name, last name, phone number, and age of the client. 
			//Using the id to find a client, the 'dto' will have the new changed values
			AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO(changedFirstName, changedLastName, changedPhoneNumber, changedAge);
			
			Client updatedClient = this.clientDao.updateClient(id, dto);
			
			return updatedClient;
		}catch(NumberFormatException e) {
			throw new InvalidParameterException("Id provided is not an int convertable value");
		}
		
	}

}
