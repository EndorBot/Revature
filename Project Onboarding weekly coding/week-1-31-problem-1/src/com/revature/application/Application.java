package com.revature.application;

public class Application {

	public static void main(String[] args) {
//		Create a function that takes an array of non-negative integers and strings and returns a new array without the strings.

		// filterArray([1, 2, "a", "b"])
		String a[] = {"3", "4", "a", "b"}; //declaration, instantiation and initialization  
		int intArray[] = new int[2]; // of course this number needs to change, right now it's hard coded to be 2 since I know there'll be 2 numbers in there
		
		for (int i = 0; i < a.length; i ++) {
			try {
		        Integer stingToInt = Integer.parseInt(a[i]);
//		        System.out.println("the index of " + a[i] + " is numeric");
		        intArray[i] = stingToInt; 
		        System.out.println("the index " + intArray[i] + " is from the intArray array");
		        
		    } catch (NumberFormatException nfe) {
//		        System.out.println("the index of " + a[i] + " is not numeric");
		    }
		}
		
	}

}
