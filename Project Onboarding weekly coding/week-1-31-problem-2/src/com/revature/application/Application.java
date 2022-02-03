package com.revature.application;

public class Application {

	public static void main(String[] args) {
//		Given a positive number as a string, multiply the number by 11 and also return it as a string. However, there is a catch:
//
//		You are NOT ALLOWED to simply cast the numeric string into an integer!
//
//		Now, how is this challenge even possible? Despite this, there is still a way to solve it, and it involves thinking about how someone might multiply by 11 in their head. See the tips below for guidance.

		System.out.println(multiplyBy11("9473745364784876253253263723"));
		
	}

	public static String multiplyBy11(String startingString) {

		String numberToInsert = "";

		if (startingString.length() == 1) {

			int result = Integer.parseInt(startingString) * 11;

			return String.valueOf(result);

		}

		else {

			for (int i = 0; i < startingString.length() - 1; i++) { // add up the sums of all adjacent digits

				int sum = Integer.parseInt(startingString.substring(i, i + 1)) + Integer.parseInt(startingString.substring(i + 1, i + 2));

				numberToInsert += sum;

			}

		}

		return startingString.charAt(0) + numberToInsert + startingString.charAt(startingString.length() - 1);

	}

}
