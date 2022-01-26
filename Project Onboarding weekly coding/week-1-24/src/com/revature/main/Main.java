package com.revature.main;

import java.text.NumberFormat;
import java.text.ParsePosition;

public class Main {

	public static void main(String[] args) {
		
		System.out.println(isNumeric("900876"));
		
	}
	
	public static boolean isNumeric(String str) {
		  ParsePosition pos = new ParsePosition(0);
		  NumberFormat.getInstance().parse(str, pos);
		  // if the string str contains a letter, then it will return false, it will return true if it's only numeric characters.
		  return str.length() == pos.getIndex();
		}

}
