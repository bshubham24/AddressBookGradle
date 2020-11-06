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

	private Response updateContact(Contacts contact) {
		String empJson = new Gson().toJson(contact);
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.body(empJson);
		return request.put("/Contacts/" + contact.getId());
	}

	private Response deleteContact(Contacts contact) {
		String empJson = new Gson().toJson(contact);
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.body(empJson);
		return request.delete("/Contacts/" + contact.getId());
	}

	@Test
	public void givenAddressBookDataInJsonServer_WhenRetrived_ShouldMatchCount() {
		Contacts[] contacts = getContactList();
		AddressBook addressBook;
		addressBook = new AddressBook(Arrays.asList(contacts));
		long noOfEntries = addressBook.contactList.size();
		Assert.assertEquals(6, noOfEntries);
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

	@Test
	public void givenContact_WhenUpdated_ShouldMatch200Response() throws AddressBookException {
		Contacts[] contacts = getContactList();
		AddressBook addressBook;
		addressBook = new AddressBook(Arrays.asList(contacts));

		addressBook.updateContact("Guddu", "farrar");
		Contacts contact = addressBook.getContact("Guddu");
		Response response = this.updateContact(contact);
		int statusCode = response.getStatusCode();
		Assert.assertEquals(200, statusCode);
	}

	@Test
	public void givenContact_WhenDeleted_ShouldMatch200Response() {
		Contacts[] contacts = getContactList();
		AddressBook addressBook;
		addressBook = new AddressBook(Arrays.asList(contacts));

		Contacts contact = addressBook.getContact("Shivendra");
		Response response = deleteContact(contact);
		int statusCode = response.getStatusCode();
		Assert.assertEquals(200, statusCode);

		addressBook.deleteContact("Shivendra");
		Assert.assertEquals(5, addressBook.contactList.size());
	}
}
