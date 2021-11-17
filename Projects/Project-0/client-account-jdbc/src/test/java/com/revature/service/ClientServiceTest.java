package com.revature.service;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.revature.dao.ClientDAO;
import com.revature.dto.AddOrUpdateClientDTO;
import com.revature.exceptions.ClientNotFoundException;
import com.revature.exceptions.InvalidParameterException;
import com.revature.exceptions.PhoneNumberInvalidExcpetion;
import com.revature.model.Client;

public class ClientServiceTest {

	private ClientService sut;

	// ClientService's getAllClients() tests
	// Positive test
	@Test
	public void testGetAllClientsPositivet() throws SQLException {
		// arrange
		ClientDAO mockClientDao = mock(ClientDAO.class);

		Client client1 = new Client(4, "Jonas", "Billyong", "734-673-7830", 19);
		Client client2 = new Client(6, "Jane", "Wells", "510-678-3672", 18);
		Client client3 = new Client(12, "Alex", "Shoemaker", "174-739-1672", 22);

		// fake clients
		List<Client> clientFromDao = new ArrayList<>();
		clientFromDao.add(client1);
		clientFromDao.add(client2);
		clientFromDao.add(client3);

		when(mockClientDao.getAllClients()).thenReturn(clientFromDao);

		ClientService clientService = new ClientService(mockClientDao);

		// act
		List<Client> actual = clientService.getAllClients();

		// assert
		List<Client> expected = new ArrayList<>();
		expected.add(new Client(4, "Jonas", "Billyong", "734-673-7830", 19));
		expected.add(new Client(6, "Jane", "Wells", "510-678-3672", 18));
		expected.add(new Client(12, "Alex", "Shoemaker", "174-739-1672", 22));

		Assertions.assertEquals(expected, actual);
	}

	// Negative test
	@Test
	public void testGetAllClientsExceptionOccurs() throws SQLException {
		// arrange
		ClientDAO mockClientDao = mock(ClientDAO.class);

		when(mockClientDao.getAllClients()).thenThrow(SQLException.class);

		ClientService clientService = new ClientService(mockClientDao);
		// act & assert
		Assertions.assertThrows(SQLException.class, () -> {
			clientService.getAllClients();
		});
	}

	// ClientService's getClientById(int id)
	// Positive
	@Test
	public void testGetClientByIdPositive() throws SQLException, ClientNotFoundException, InvalidParameterException {
		// arrange
		ClientDAO mockClientDao = mock(ClientDAO.class);

		when(mockClientDao.getClientById(eq(5))).thenReturn(new Client(5, "Luke", "Boulder", "681-676-7682", 43));

		ClientService clientService = new ClientService(mockClientDao);

		// act
		Client actual = clientService.getClientById("5");

		// assert
		Assertions.assertEquals(new Client(5, "Luke", "Boulder", "681-676-7682", 43), actual);
	}

	// Negative test
	// ClientNotFoundException is thrown
	@Test
	public void testGetClientByIdNegative() throws SQLException, ClientNotFoundException, InvalidParameterException {
		// arrange
		ClientDAO mockClientDao = mock(ClientDAO.class);

		ClientService clientService = new ClientService(mockClientDao);
		// act & assert
		Assertions.assertThrows(ClientNotFoundException.class, () -> {// assert
			clientService.getClientById("1");
		});
	}

	// Negative test
	// InvalidParameterException is thrown
	@Test
	public void testGetClientByIdAlphabeticalIdNegative() {
		// arrange
		ClientDAO mockClientDao = mock(ClientDAO.class);

		ClientService clientService = new ClientService(mockClientDao);
		// act & assert
		Assertions.assertThrows(InvalidParameterException.class, () -> {
			clientService.getClientById("abc");
		});
	}

	// Negative test
	// InvalidParameterException is thrown
	@Test
	public void testGetClientByIdDecimalIdNegative() {
		// arrange
		ClientDAO mockClientDao = mock(ClientDAO.class);

		ClientService clientService = new ClientService(mockClientDao);
		// act & assert
		Assertions.assertThrows(InvalidParameterException.class, () -> {
			clientService.getClientById("1.0");
		});
	}

	// ClientService's editFirstNameOfClient(String clientId, String changedName)
	// Positive
	@Test
	public void testEditFirstNameOfClientPositive()
			throws SQLException, ClientNotFoundException, InvalidParameterException {
		// arrange
		ClientDAO mockClientDao = mock(ClientDAO.class);
		
		when(mockClientDao.getClientById(eq(4))).thenReturn(new Client(4, "Richard", "Regan", "175-205-2754", 24));
		
		AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("Marci", "Regan", "175-205-2754", 24);
		
		when(mockClientDao.updateClient(eq(4), eq(dto))).thenReturn(new Client(4, "Marci", "Regan", "175-205-2754", 24));
		
		ClientService clientService = new ClientService(mockClientDao);
		// act
		Client actual = clientService.editFirstNameOfClient("4", "Marci");

		// assert
		Client expected = new Client(4, "Marci", "Regan", "175-205-2754", 24);

		Assertions.assertEquals(expected, actual);
	}

	// Negative
	// ClientNotFoundException
	@Test
	public void testEditFirstNameOfClientButClientWithId10DoesNotExist() {
		// arrange
		ClientDAO mockClientDao = mock(ClientDAO.class);

		ClientService clientSertivce = new ClientService(mockClientDao);
		// act & assert
		Assertions.assertThrows(ClientNotFoundException.class, () -> {
			clientSertivce.editFirstNameOfClient("10", "Bill");
		});
	}

	// Negative
	// InvalidParameterException is thrown
	@Test
	public void testEditFirstNameOfClientButIdProvidedIsNotInt() {
		// arrange
		ClientDAO mockClientDao = mock(ClientDAO.class);

		ClientService clientSertivce = new ClientService(mockClientDao);
		// act & assert
		Assertions.assertThrows(InvalidParameterException.class, () -> {
			clientSertivce.editFirstNameOfClient("acihkbdaaklncbj", "Test");
		});
	}

	// ClientService's addClient(AddOrUpdateClientDTO dto) method
	// Positive
	@Test
	public void testAddClientAllInformationCorrectInDto()
			throws SQLException, InvalidParameterException, PhoneNumberInvalidExcpetion {
		// arrange
		ClientDAO clientDao = mock(ClientDAO.class);

		AddOrUpdateClientDTO dtoIntDao = new AddOrUpdateClientDTO("Handsome", "Jack", "375-127-5725", 36);

		when(clientDao.addClient(eq(dtoIntDao))).thenReturn(new Client(100, "Handsome", "Jack", "375-127-5725", 36));

		ClientService clientService = new ClientService(clientDao);
		// act
		AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("Handsome", "Jack", "375-127-5725", 36);
		Client actual = clientService.addClient(dto);

		// assert
		Client expected = new Client(100, "Handsome", "Jack", "375-127-5725", 36);
		Assertions.assertEquals(expected, actual);
	}

	// Negative
	// Scenario: everything valid except the first name is blank
	@Test
	public void testAddClientFirstNameBlankEverythingElseValid() throws SQLException, InvalidParameterException {
		// arrange
		ClientDAO clientDao = mock(ClientDAO.class);

		ClientService clientService = new ClientService(clientDao);
		// act & assert
		AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("", "Jack", "375-127-5725", 36);

		Assertions.assertThrows(InvalidParameterException.class, () -> {
			clientService.addClient(dto);
		});
	}

	// Negative
	// Scenario: everything valid except the last name is blank
	@Test
	public void testAddClientLastNameBlankEverythingElseValid() throws SQLException, InvalidParameterException {
		// arrange
		ClientDAO clientDao = mock(ClientDAO.class);

		ClientService clientService = new ClientService(clientDao);
		// act & assert
		AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("Handsome", "", "375-127-5725", 36);

		Assertions.assertThrows(InvalidParameterException.class, () -> {
			clientService.addClient(dto);
		});
	}

	// Negative
	// Scenario: everything valid except both names are blank
	@Test
	public void testAddClientBothNamesBlankEverythingElseValid() throws SQLException, InvalidParameterException {
		// arrange
		ClientDAO clientDao = mock(ClientDAO.class);

		ClientService clientService = new ClientService(clientDao);
		// act & assert
		AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO(" ", " ", "375-127-5725", 36);

		Assertions.assertThrows(InvalidParameterException.class, () -> {
			clientService.addClient(dto);
		});
	}

	// Negative
	// Scenario: everything valid except the phone is blank
	@Test
	public void testAddClientPhoneNumberIsBlankEverythingElseValid() throws SQLException, PhoneNumberInvalidExcpetion {
		// arrange
		ClientDAO clientDao = mock(ClientDAO.class);

		ClientService clientService = new ClientService(clientDao);
		// act & assert
		AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("Handsome", "Jack", " ", 36);

		Assertions.assertThrows(PhoneNumberInvalidExcpetion.class, () -> {
			clientService.addClient(dto);
		});
	}

	// Negative
	// Scenario: everything valid except the phone has less than 12 characters
	@Test
	public void testAddClientPhoneNumberLessThen12EverythingElseValid() throws SQLException, PhoneNumberInvalidExcpetion {
		// arrange
		ClientDAO clientDao = mock(ClientDAO.class);

		ClientService clientService = new ClientService(clientDao);
		// act & assert
		AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("Handsome", "Jack", "375-127-572", 36);

		Assertions.assertThrows(PhoneNumberInvalidExcpetion.class, () -> {
			clientService.addClient(dto);
		});
	}

	// Negative
	// Scenario: everything valid except the phone has less than 12 characters
	@Test
	public void testAddClientPhoneNumberMoreThen12EverythingElseValid(){
		// arrange
		ClientDAO clientDao = mock(ClientDAO.class);

		ClientService clientService = new ClientService(clientDao);
		// act & assert
		AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("Handsome", "Jack", "375-1274-5725", 36);

		Assertions.assertThrows(PhoneNumberInvalidExcpetion.class, () -> {
			clientService.addClient(dto);
		});
	}

	// Negative
	// Scenario: everything valid age is negative
	@Test
	public void testAddClientAgeIsNegativeEverythingElseValid() throws SQLException, InvalidParameterException {
		// arrange
		ClientDAO clientDao = mock(ClientDAO.class);

		ClientService clientService = new ClientService(clientDao);
		// act & assert
		AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("Handsome", "Jack", "375-127-5725", -36);

		Assertions.assertThrows(InvalidParameterException.class, () -> {
			clientService.addClient(dto);
		});
	}

	// ClientService's editClientById(String clientId, String changedFirstName,
	// String changedLastName,
	// String changedPhoneNumber, int changedAge)
	// Positive
	@Test
	public void testEditClientByIdPositive()
			throws SQLException, InvalidParameterException, ClientNotFoundException, PhoneNumberInvalidExcpetion {
		// arrange
		ClientDAO mockClientDao = mock(ClientDAO.class);

		when(mockClientDao.getClientById(eq(1))).thenReturn(new Client(1, "Blob", "Giovani", "178-638-6744", 19));

		AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("Jacob", "Lawrence", "681-676-7682", 43);
		
		when(mockClientDao.updateClient(eq(1),eq(dto))).thenReturn(new Client(1, "Jacob", "Lawrence", "681-676-7682", 43));

		ClientService clientService = new ClientService(mockClientDao);
		// act
		Client actual = clientService.editClientById("1", "Jacob", "Lawrence", "681-676-7682", 43);
		// assert

		Client expected = new Client(1, "Jacob", "Lawrence", "681-676-7682", 43);

		Assertions.assertEquals(expected, actual);

	}

	// Negative
	// ClientNotFoundException
	@Test
	public void testEditClientByIdButClientWithId10DoesNotExist() {
		// arrange
		ClientDAO mockClientDao = mock(ClientDAO.class);

		ClientService clientSertivce = new ClientService(mockClientDao);
		// act & assert
		Assertions.assertThrows(ClientNotFoundException.class, () -> {
			clientSertivce.editClientById("10", "Paul", "Jackron", "275-175-1702", 28);
		});
	}

	// Negative
	// PhoneNumberInvalidExcpetion
	@Test
	public void testEditClientByIdButClientWithLessThan12PhoneNumberDigitsInvalid() {
		// arrange
		ClientDAO mockClientDao = mock(ClientDAO.class);

		ClientService clientSertivce = new ClientService(mockClientDao);
		// act & assert
		Assertions.assertThrows(ClientNotFoundException.class, () -> {
			clientSertivce.editClientById("4", "Paul", "Jackron", "275-175-170", 28);
		});
	}

	// Negative
	// PhoneNumberInvalidExcpetion more than 12
	@Test
	public void testEditClientByIdButClientWithMoreThan12PhoneNumberDigitsInvalid() {
		// arrange
		ClientDAO mockClientDao = mock(ClientDAO.class);

		ClientService clientSertivce = new ClientService(mockClientDao);
		// act & assert
		Assertions.assertThrows(ClientNotFoundException.class, () -> {
			clientSertivce.editClientById("4", "Paul", "Jackron", "275-1755-1708", 28);
		});
	}

	// Negative
	// everything valid except the first name is blank
	@Test
	public void testEditClientByIdFirstNameBlankEverythingElseValid() throws SQLException, ClientNotFoundException {
		// arrange
		ClientDAO clientDao = mock(ClientDAO.class);

		ClientService clientService = new ClientService(clientDao);
		// act & assert

		Assertions.assertThrows(ClientNotFoundException.class, () -> {
			clientService.editClientById("4", "", "Lawrence", "681-676-7682", 28);
		});
	}

	// Negative
	// everything valid except the last name is blank
	@Test
	public void testEditClientByIdLastNameBlankEverythingElseValid() throws SQLException, ClientNotFoundException {
		// arrange
		ClientDAO clientDao = mock(ClientDAO.class);

		ClientService clientService = new ClientService(clientDao);
		// act & assert

		Assertions.assertThrows(ClientNotFoundException.class, () -> {
			clientService.editClientById("4", "Roach", "", "681-676-7682", 28);
		});
	}

	// Negative
	// everything valid except both names are blank
	@Test
	public void testEditClientByIdBothNamesBlankEverythingElseValid() throws SQLException, ClientNotFoundException {
		// arrange
		ClientDAO clientDao = mock(ClientDAO.class);

		ClientService clientService = new ClientService(clientDao);
		// act & assert

		Assertions.assertThrows(ClientNotFoundException.class, () -> {
			clientService.editClientById("4", "", "", "681-676-7682", 28);
		});
	}

	// Negative
	// everything valid except phone number has letters
	@Test
	public void testEditClientByIdPhoneNumberHasLettersEverythingElseValid()
			throws SQLException, ClientNotFoundException {
		// arrange
		ClientDAO clientDao = mock(ClientDAO.class);

		ClientService clientService = new ClientService(clientDao);
		// act & assert

		Assertions.assertThrows(ClientNotFoundException.class, () -> {
			clientService.editClientById("4", "Shane", "DeMagio", "abc-zxy-rst", 43);
		});
	}

	// Negative
	// Scenario: everything valid except the phone has less than 12 characters
	@Test
	public void testEditClientByIdPhoneNumberHasLessThan12EverythingElseValid() {
		// arrange
		ClientDAO clientDao = mock(ClientDAO.class);

		ClientService clientService = new ClientService(clientDao);
		// act & assert

		Assertions.assertThrows(ClientNotFoundException.class, () -> {
			clientService.editClientById("4", "Shane", "DeMagio", "610-127-051", 43);
		});
	}

	// Negative
	// Scenario: everything valid except the phone has more than 12 characters
	@Test
	public void testEditClientByIdPhoneNumberHasMoreThan12EverythingElseValid() {
		// arrange
		ClientDAO clientDao = mock(ClientDAO.class);

		ClientService clientService = new ClientService(clientDao);
		// act & assert

		Assertions.assertThrows(ClientNotFoundException.class, () -> {
			clientService.editClientById("4", "Shane", "DeMagio", "610-127-05178", 43);
		});
	}

	// Negative
	// Scenario: everything valid age is Less than 18
	@Test
	public void testEditClientByAgeIsLessThan18EverythingElseValid() throws SQLException, ClientNotFoundException{
		// arrange
		ClientDAO clientDao = mock(ClientDAO.class);

		ClientService clientService = new ClientService(clientDao);
		// act & assert

		Assertions.assertThrows(ClientNotFoundException.class, () -> {
			clientService.editClientById("5", "Aang", "Avatar", "380-751-5725", 14);
		});
	}

	// Negative
	// Scenario: everything valid age is negative
	@Test
	public void testEditClientByAgeIsNegativeEverythingElseValid() throws SQLException, ClientNotFoundException {
		// arrange
		ClientDAO clientDao = mock(ClientDAO.class);

		ClientService clientService = new ClientService(clientDao);
		// act & assert

		Assertions.assertThrows(ClientNotFoundException.class, () -> {
			clientService.editClientById("4", "Samurai", "Jack", "375-751-5725", -36);
		});
	}
}
