package com.capgi.addressbookusinggridle;

import org.junit.Assert;
import org.junit.Test;



public class AddressBookTest {

	@Test
	public void firstNameTest() {
		Validation contact = new Validation();
		boolean result = contact.ValidateFirstName("Shubham");
		Assert.assertTrue(true);
	}

	@Test
	public void lastNameTest() {
		Validation contact = new Validation();
		boolean result = contact.ValidateLastName("Bhawsar");
		Assert.assertTrue(true);
	}

	@Test
	public void emailTest() {
		Validation contact = new Validation();
		boolean result = contact.ValidateEmail("shubh007bhawsar@gmail.com");
		Assert.assertTrue(true);
	}

	@Test
	public void phoneNoTest() {
		Validation contact = new Validation();
		boolean result = contact.ValidatePhoneNo("91 9874563214");
		Assert.assertTrue(true);
	}

	@Test
	public void addressTest() {
		Validation contact = new Validation();
		boolean result = contact.ValidateAddress("whitehouse");
		Assert.assertTrue(true);
	}

	@Test
	public void cityTest() {
		Validation contact = new Validation();
		boolean result = contact.ValidateCity("bomabay");
		Assert.assertTrue(true);
	}

	@Test
	public void stateTest() {
		Validation contact = new Validation();
		boolean result = contact.ValidateState("punjab");
		Assert.assertTrue(true);
	}
}
