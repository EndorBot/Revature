package com.revature.service;

// The service layer is responsible for the processing of data. We would perform business logic inside of the service layer.
// Ex. Imaigne you are developing the backend for FB. The service layer of Facebook's backend would contain business logic related to providing friend
// recommendations, ad recommendations, etc. for a particular FB user.
// All sorts of complex algorithms could be developed, and those would ultimately be part of this "service" layer.
// Sometimes, the service layer will be pretty simple (especially for CRUD applications)
// CRUD = create, read, update, delete
// CRUD applications are applications that are primarily designed to keep records of data
public class ArthimaticService {

	// Purpose of this method is to check the inputs and whether we have any blank inputs
	// We want to return an int corresponding to which inputs are missing
	// 0: FINE, 1: LEFT input is missing, 2: RIGHT input is missing, 3: BOTH inputs are missing
	// Use the .trim() to trim put blank spaces from a comment
	public int checkInputs(String input1, String input2) {
		if (input1.trim().equals("") && input2.trim().equals("")) {
			return 3;
		}
		
		// To reach this point, we already know that one of the inputs is NOT blank
		
		// If this block of code executes, then it MUST be only the left side that is blank
		if (input1.trim().equals("")){
			return 1;
		}
		
		// If this block of code executes, then it MUST be only the right side that is blank
		if (input2.trim().equals("")) {
			return 2;
		}
		
		// Neither are blank
		return 0;
	}
	
	public String doOperation(String input1, String input2, char operator) {
		int condition = checkInputs(input1, input2);
		
		switch (condition) {
		case 1:
				return "Left input is missing";
		case 2: 
				return "Right input is missing";
		case 3:
				return "Both inputs are missing";
		}
		
		// If you make it past the switch statements, (for example you get a value of 0), then you are good to go
		switch (operator) {
		case '+':
				return doAddition(input1, input2);
		case '-':
				return doSubtraction(input1, input2);
		case '*':
				return doMultiplication(input1, input2);
		case '/':
				return doDivision(input1, input2);
		}
		
		return "Something went wrong because we somehow didn't execute one of those return statements above";
	}
	
	public String doAddition(String number1String, String number2String) {
		double number1 = Double.parseDouble(number1String);
		double number2 = Double.parseDouble(number2String);

		double sum = number1 + number2;

		String result = "" + sum; // Convert from double representation of a number to a
									// String representation

		return result;
	}

	public String doSubtraction(String number1String, String number2String) {
		double number1 = Double.parseDouble(number1String);
		double number2 = Double.parseDouble(number2String);

		double difference = Math.ceil(number1 - number2);

		String result = "" + difference; // Convert from double representation of
											// a number to a String representation

		return result;
	}

	public String doMultiplication(String number1String, String number2String) {
		double number1 = Double.parseDouble(number1String);
		double number2 = Double.parseDouble(number2String);

		double product = number1 * number2;

		String result = "" + product; // Convert from double representation of a
										// number to a String representation

		return result;
	}

	public String doDivision(String number1String, String number2String) {
		double number1 = Double.parseDouble(number1String);
		double number2 = Double.parseDouble(number2String);

		double quotient = (number1 / number2);

		String result = "" + quotient; // Convert from double representation of a
										// number to a String representation

		return result;
	}

}
