package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.dto.AddOrUpdateClientDTO;
import com.revature.model.Client;
import com.revature.util.JDBCUtility;

public class ClientDAO {
// Part of the data access layer

	public Client addClient(AddOrUpdateClientDTO client) throws SQLException {
		try(Connection con = JDBCUtility.getConnection()){
			String sql = "INSERT INTO clients (client_first_name, client_last_name, client_phone_number, client_age)" 
							+ " VALUES (?, ?, ?, ?)";
			
			PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, client.getFirstName());
			pstmt.setString(2, client.getLastName());
			pstmt.setString(3, client.getPhoneNumber());
			pstmt.setInt(4, client.getAge());
			
			int numberOfRecordsInserted = pstmt.executeUpdate();
			
			// adding client was unsucceful
			if (numberOfRecordsInserted != 1) {
				throw new SQLException("Adding a new Client was unsucceful");
			}
			
			ResultSet rs = pstmt.getGeneratedKeys();
			
			rs.next();
			int automaticallyGeneratedId = rs.getInt(1); // grabs value from first row
			
			// return the Client made in the databse, and automatically add a id, which needs to be retrieved
			return new Client(automaticallyGeneratedId, client.getFirstName(), client.getLastName(), 
					client.getPhoneNumber(), client.getAge());
		}
	}
	
	public List<Client> getAllClients() throws SQLException {
		List<Client> listOfClients = new ArrayList<>();

		// query data
		// get connection object
		try (Connection con = JDBCUtility.getConnection()) {
			// get a Statement object from that connection object
			String sql = "SELECT * FROM clients";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			// execute query
			ResultSet rs = pstmt.executeQuery();
			
			// iterate over queried data with ResultSet while until there aren't any more rows in the table
			while (rs.next()) {
				// take info from current queried row and place it into a Client object
				int clientId = rs.getInt("client_id");
				String firstName = rs.getString("client_first_name");
				String lastName = rs.getString("client_last_name");
				String phoneNumber = rs.getString("client_phone_number");
				int age = rs.getInt("client_age");
				
				// creating client object
				Client c = new Client(clientId, firstName, lastName, phoneNumber, age);
				
				// adding object to the list
				listOfClients.add(c);
			}
		}
		return listOfClients;
	}
	
	public Client getClientById (int clientId) throws SQLException {
		try (Connection con = JDBCUtility.getConnection()){
			String sql = "SELECT * FROM clients WHERE client_id = ?"; // remember that the ? is a placeholder
					
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, clientId); // passing value of the clientId variable into the ?
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return new Client (rs.getInt("client_id"), rs.getString("client_first_name"), 
						rs.getString("client_last_name"), rs.getString("client_phone_number"), rs.getInt("client_age"));
			} else {
				return null;
			}
		}
	}
	
	// Update a client
	// take 2 arguments connected with the clientID who is going to be updated, and AddOrUpdateDTO object contains properties that will be updated
	public Client updateClient (int clientId, AddOrUpdateClientDTO client) throws SQLException {
		
		try (Connection con = JDBCUtility.getConnection()){
			String sql = "UPDATE clients" + " SET client_first_name = ?," + " client_last_name = ?," 
						+ " client_phone_number = ?," + " client_age = ? " + "WHERE " + "client_id = ?;";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, client.getFirstName());
			pstmt.setString(2, client.getLastName());
			pstmt.setString(3, client.getPhoneNumber());
			pstmt.setInt(4, client.getAge());
			pstmt.setInt(5, clientId);
			
			int numberOfRecordsUpdated = pstmt.executeUpdate();
			
			if (numberOfRecordsUpdated != 1) {
				throw new SQLException("Unable to update client record w/ id of " + clientId);
			}
		}
		return new Client(clientId, client.getFirstName(), client.getLastName(), client.getPhoneNumber(), client.getAge());
	}
	
	// deleting by id
	public void deleteClientById(int clientId) throws SQLException{
		
		try (Connection con = JDBCUtility.getConnection()){
			String sql = "DELETE FROM clients WHERE client_id = ?";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, clientId);
			
			int numberOfRecordsDeleted = pstmt.executeUpdate();
			
			if (numberOfRecordsDeleted != 1){
				throw new SQLException("Unable to delete client w/ id of " + clientId);
			}
		}
	}
	
	// delete all clients
	public void deleteAllClients() throws SQLException {
		
	try (Connection con = JDBCUtility.getConnection()){
		String sql = "DELETE FROM clients";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		int numberOfRecordsDeleted = pstmt.executeUpdate();
			
			if (numberOfRecordsDeleted == 0) {
				throw new SQLException("Unable to delete records (check if records exit in the table and if account was deleted first");
			}
	
			
		}
	}


}
