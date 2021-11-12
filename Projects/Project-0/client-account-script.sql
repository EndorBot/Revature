DROP TABLE IF EXISTS clients

-- Create table for clients
CREATE TABLE clients (
	client_id SERIAL PRIMARY KEY,
	client_first_name VARCHAR (255) NOT NULL,
	client_last_name VARCHAR (255) NOT NULL,
	client_phone_number INTEGER NOT NULL,

);

DROP TABLE IF EXISTS accounts

-- Create table for accounts
CREATE TABLE accounts (
	account_id SERIAL PRIMARY KEY,
	account_type VARCHAR(20) NOT NULL,
	account_ammount INTEGER NOT NULL,
	client_id INTEGER NOT NUL, -- links to clients
	
	CONSTRAINT fk_client FOREIGN KEY(client_id)
		REFERENCES clients(client_id)
);

SELECT *
FROM clients

SELECT *
FROM accounts
-- Remember that things in account need to be deleted first before things in client, due to referential integrity
-- and SQL not allowing orphan records
