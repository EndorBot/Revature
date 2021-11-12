package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.postgresql.Driver;

public class JDBCUtility {
	public static Connection getConnection() throws SQLException {
		// Using utility class to create a connection string
		
		//Credentials to connect to database
		String url= "jdbc:postgresqql://localhost:5432/postgres";
		String username = "postgres";
		String password = "dujPJ4HApt";
		
		Driver postgresDriver = new Driver();
		
		DriverManager.registerDriver(postgresDriver);
		
		Connection con = DriverManager.getConnection(url,username,password);
		
		return con;
	}
}
