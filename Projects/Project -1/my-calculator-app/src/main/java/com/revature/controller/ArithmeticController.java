package com.revature.controller;

import com.reavature.service.ArthimaticService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

// The purpose of the controller layer is to receive information from a request
// 		That is the role of the service layer
public class ArithmeticController {

	public ArthimaticService artihmaticService;
	
	// Controller
	public ArithmeticController()
	{
		this.artihmaticService = new ArthimaticService();
	}

	// This is what as known as a lambda
	// Think of it as similar to a method, but it is a method that can be passed around
	public Handler add = (ctx) -> {
		//System.out.println("add lambda invoked");
		
		// Double class
		// the Double class has a static method called parseDouble that can take a String and return a double primitive representation of that String
		String number1String = ctx.formParam("number1");
		String number2String = ctx.formParam("number2");
		
		// String + double > String + String = String
		ctx.result(artihmaticService.doAddition(number1String, number2String)); // Because number1 and number2 are just Strings, this will do String concatentation and not our actual adding of numbers
	};
	
	public Handler subtract = (ctx) -> {
		//System.out.println("subtract lambda invoked");
	
		String number1String = ctx.formParam("number1");
		String number2String = ctx.formParam("number2");
		
		ctx.result(artihmaticService.doSubtraction(number1String, number2String));
	};
	
	public Handler multiply = (ctx) -> {
		//System.out.println("multiply lambda invoked");
	
		String number1String = ctx.formParam("number1");
		String number2String = ctx.formParam("number2");
		
		ctx.result(artihmaticService.doMultiplication(number1String, number2String));
	};
	
	
	public Handler divide = (ctx) -> {
		//System.out.println("divide lambda invoked");
	
		String number1String = ctx.formParam("number1");
		String number2String = ctx.formParam("number2");
		
		ctx.result(artihmaticService.doDivision(number1String, number2String));
	};
	
	//Subtraction
	// Define an instance method here
		public void registerEndpoint(Javalin app1) {
			app1.post("/add", add); // We are mapping the add lambda, which will be invoked whenever a client sends a HTTP post request to "/add"
			app1.post("/subtract", subtract);
			app1.post("/multiply", multiply);
			app1.post("/divide", divide);
		}
	
}
