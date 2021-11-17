package com.revature.controller;

import java.util.List;

import com.revature.dto.AddOrUpdateClientDTO;
import com.revature.model.Client;
import com.revature.service.ClientService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ClientController {
	// Directly communicates with Service layer
	
	private ClientService clientService;
	
	public ClientController() {
		this.clientService = new ClientService();
	}
	
	private Handler editClientFirstName = (ctx) ->{
		// get id from URI
		String clientId = ctx.pathParam("id");
		
		
		// extract info
		AddOrUpdateClientDTO dto = ctx.bodyAsClass(AddOrUpdateClientDTO.class);
		
		Client clientThatWasJustEdited = this.clientService.editFirstNameOfClient(clientId, dto.getFirstName());
		
		// place info into json
		ctx.json(clientThatWasJustEdited); 
	};
	
	private Handler addClient = (ctx) ->{
		// pathParam because, id is not required
		AddOrUpdateClientDTO dto = ctx.bodyAsClass(AddOrUpdateClientDTO.class);
		
		Client c = this.clientService.addClient(dto);
		
		ctx.json(c);
		ctx.status(201); // created, provided as raw, body JSON
	};
	
	private Handler getAllClients = (ctx) ->{
		List<Client> clients = this.clientService.getAllClients();
		
		ctx.json(clients);
	};
	
	private Handler getClientById = (ctx) -> {
		String clientId = ctx.pathParam("id");
		Client c = this.clientService.getClientById(clientId);
		
		ctx.json(c);
	};
	
	private Handler editClientById = (ctx) -> {
		String clientId = ctx.pathParam("id");	
		//int id = Integer.parseInt(clientId);
		
		AddOrUpdateClientDTO dto = ctx.bodyAsClass(AddOrUpdateClientDTO.class);
		
		Client c = this.clientService.editClientById(clientId, dto.getFirstName(), dto.getLastName(), dto.getPhoneNumber(), dto.getAge());
		
		ctx.json(c); 
	};
	
	private Handler deleteClientById = (ctx) ->{
		String clientId = ctx.pathParam("id");
		this.clientService.deleteClientById(clientId);
	};
	
//	private Handler deleteAllClients = (ctx) ->{
//		List<Client> clients = this.clientService.deleteAllClients();
//		
//		ctx.json(clients);
//	};
	
	public void registerEndpoint(Javalin app) {
		app.patch("/clients/{id}/firstname", editClientFirstName);
		app.post("/clients", addClient);
		app.get("/clients", getAllClients);
		app.get("/clients/{id}", getClientById);
		app.put("/clients/{id}", editClientById);
		app.delete("/clients/{id}",deleteClientById);
		//app.delete("/clients", deleteAllClients);
		
	}
}
