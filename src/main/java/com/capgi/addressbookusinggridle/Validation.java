package com.capgi.addressbookusinggridle;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

	public boolean ValidateFirstName(String fName) {

		Pattern pattern = Pattern.compile("([A-Z]{1})([a-zA-Z]{2,})");
		Matcher matcher = pattern.matcher(fName);
		boolean isValid = matcher.find();
		return isValid;
	}

	public boolean ValidateLastName(String lName) {
		Pattern pattern = Pattern.compile("([A-Z]{1})([a-zA-Z]{2,})");
		Matcher matcher = pattern.matcher(lName);
		boolean isValid = matcher.find();
		return isValid;
	}

	public boolean ValidateEmail(String email) {
		Pattern pattern = Pattern
				.compile("(^([a-z0-9+_-]+)([.][0-9a-z]+)*@([a-z0-9]+)([.]([a-z]{2,}))([.][a-z]{2})?$)");
		Matcher matcher = pattern.matcher(email);
		boolean isValid = matcher.find();
		return isValid;

	}

	public boolean ValidatePhoneNo(String phoneNo) {
		Pattern pattern = Pattern.compile("(^([0-9]+)( )([1-9]{1})([0-9]{9})$)");
		Matcher matcher = pattern.matcher(phoneNo);
		boolean isValid = matcher.find();
		return isValid;
	}

	public boolean ValidateAddress(String address) {
		return true;
	}

	public boolean ValidateCity(String city) {
		Pattern pattern = Pattern.compile("[a-zA-Z]+");
		Matcher matcher = pattern.matcher(city);
		Boolean isValid = matcher.find();
		return isValid;
	}

	public boolean ValidateZip(String zip) {
		Pattern pattern = Pattern.compile("^([0-9]){6}$");
		Matcher matcher = pattern.matcher(zip);
		Boolean isValid = matcher.find();
		return isValid;
	}

	public boolean ValidateState(String state) {

		Pattern pattern = Pattern.compile("[a-zA-Z]+");
		Matcher matcher = pattern.matcher(state);
		Boolean isValid = matcher.find();
		return isValid;
	}
}
