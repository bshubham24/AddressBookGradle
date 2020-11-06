package com.capgi.addressbookusinggridle;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AddressBookRestAPITest {
	@Before
	public void initialize() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 3000;
	}

	private Contacts[] getContactList() {
		Response response = RestAssured.get("/Contacts");
		Contacts[] contacts = new Gson().fromJson(response.asString(), Contacts[].class);
		return contacts;
	}

	private Response addContactToJSONServer(Contacts contact) {
		String contactJson = new Gson().toJson(contact);
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.header("Content-Type", "application/json");
		requestSpecification.body(contactJson);
		return requestSpecification.post("/Contacts");
	}

	@Test
	public void givenAddressBookDataInJsonServer_WhenRetrived_ShouldMatchCount() {
		Contacts[] contacts = getContactList();
		AddressBook addressBook;
		addressBook = new AddressBook(Arrays.asList(contacts));
		long noOfEntries = addressBook.contactList.size();
		Assert.assertEquals(3, noOfEntries);
	}

	@Test
	public void givenContacts_WhenAdded_ShouldMatchStatusAndCount() {
		Contacts[] contacts = getContactList();
		AddressBook addressBook;
		addressBook = new AddressBook(Arrays.asList(contacts));
		Contacts contact = new Contacts(0, "Shivendra", "rathore", "alpha", "devas", "mp", "145236", "91 9456565685",
				"shiv@gmail.com");
		Response response = addContactToJSONServer(contact);
		int statusCode = response.getStatusCode();
		Assert.assertEquals(201, statusCode);

		contact = new Gson().fromJson(response.asString(), Contacts.class);
		addressBook.addNewContact(contact);
		long entries = addressBook.contactList.size();
		Assert.assertEquals(4, entries);
	}

	@Test
	public void givenListOfContact_WhenAdded_ShouldMatch201ResponseAndCount() {
		Contacts[] contacts = getContactList();
		AddressBook addressBook;
		addressBook = new AddressBook(Arrays.asList(contacts));
		Contacts[] arrOfContacts = {
				new Contacts(0, "Munna", "Tripathi", "thripathi palace", "Mirzapur", "UP", "145236", "91 9478523699",
						"KingOfMirzapur@gmail.com"),
				new Contacts(0, "Guddu", "Bhaiya", "ghar", "Mirzapur", "UP", "145236", "91 9478523699",
						"prideOfMirzapur@gmail.com") };
		for (Contacts contact : arrOfContacts) {
			Response response = addContactToJSONServer(contact);
			int statusCode = response.getStatusCode();
			Assert.assertEquals(201, statusCode);

			contact = new Gson().fromJson(response.asString(), Contacts.class);
			addressBook.addNewContact(contact);
		}
		long entries = addressBook.contactList.size();
		Assert.assertEquals(6, entries);
	}
}
