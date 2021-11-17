DROP TABLE IF EXISTS clients

-- Create table for clients
CREATE TABLE clients (
	client_id SERIAL PRIMARY KEY,
	client_first_name VARCHAR (255) NOT NULL,
	client_last_name VARCHAR (255) NOT NULL,
	client_phone_number VARCHAR(12) NOT NULL,
	client_age INTEGER NOT NULL

);

INSERT INTO clients (client_first_name, client_last_name, client_phone_number, client_age)
VALUES
('James','Donald','xxx-xxx-xxx', 20),
('Sam','Jupero','xxx-xxx-xxx', 37);

SELECT *
FROM clients

DROP TABLE IF EXISTS accounts

-- Create table for accounts
CREATE TABLE accounts (
	account_id SERIAL PRIMARY KEY,  
	account_number INTEGER NOT NULL,-- I believe it can be anywhere between 8 to 12 digits
	account_amount INTEGER NOT NULL, -- can use ISNUMERIC(account_amount) to check if the 
	account_type VARCHAR(20) NOT NULL,
	client_id INTEGER NOT NULL, -- links to clients
	
	CONSTRAINT fk_client FOREIGN KEY(client_id)
		REFERENCES clients(client_id)
);

SELECT *
FROM accounts
-- Remember that things in account need to be deleted first before things in client, due to referential integrity
-- and SQL not allowing orphan records

INSERT INTO accounts (account_number, account_amount, account_type, client_id)
VALUES
(379216084, 4000,'Checking',1),
(375829657, 300,'Savings', 2),
(174501654, 250,'Checking',2),
(771067105, 305,'Savings', 1);

SELECT account_amount
FROM accounts;

-- Supposed to check if amount is numeric or not, but currently WIP 
SELECT ISNUMERIC(4000.50) AS account_amount;