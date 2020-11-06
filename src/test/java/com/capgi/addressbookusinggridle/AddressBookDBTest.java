package com.capgi.addressbookusinggridle;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddressBookDBTest {

	private AddressBookDBService addressBookDBService;

	@Before
	public void initialize() {
		addressBookDBService = new AddressBookDBService();
	}

	@Test
	public void givenAddressBookDB_ShouldMatchCount() throws AddressBookDBException {
		List<Contacts> contactList = addressBookDBService.readContacts();
		Assert.assertEquals(5, contactList.size());
	}

	@Test
	public void givenAddressBookDB_WhenStateUpdated_ShouldSync() throws AddressBookDBException {
		addressBookDBService.updatePersonAddress("Terisa", "state", "MP");
		Contacts contact = addressBookDBService.isAddressBookInSyncWithDB("Terisa");
		Assert.assertEquals("MP", contact.getState());
	}

	@Test
	public void givenAddressBookDB_WhenRetrivedBasedOnDate_ShouldReturnCount() throws AddressBookDBException {
		LocalDate startDate = LocalDate.of(2020, 07, 01);
		LocalDate endDate = LocalDate.now();
		int noOfContacts = addressBookDBService.getContactsOnDateRange(startDate, endDate);
		Assert.assertEquals(4, noOfContacts);
	}

	@Test
	public void givenAddressBookDB_WhenRetrivedBasedOnCity_ShouldReturnCount() throws AddressBookDBException {
		int noOfContacts = addressBookDBService.retriveBasedOnField("city", "City 1");
		Assert.assertEquals(1, noOfContacts);
	}

	@Test
	public void givenAddressBookDB_WhenRetrivedBasedOnState_ShouldReturnCount() throws AddressBookDBException {
		int noOfContacts = addressBookDBService.retriveBasedOnField("state", "MP");
		Assert.assertEquals(1, noOfContacts);
	}

	@Test
	public void whenContactAddedToDB_ShouldMatchCount() throws AddressBookDBException {
		addressBookDBService.addContactToDB("victoria", "pedretti", "bly manor", "westminster", "UK", "412563",
				"97 1478523699", "notoriousvip@gmail.com", LocalDate.now());
		List<Contacts> contactList = addressBookDBService.readContacts();
		Assert.assertEquals(5, contactList.size());
	}

	@Test
	public void whenAddedMultipleContacts_ShouldMatchCount() throws AddressBookDBException {
		Contacts[] arrOfContacts = {
				new Contacts("victoria", "pedretti", "bly manor", "westminster", "UK", "412563", "97 1478523699",
						"notoriousvip@gmail.com", LocalDate.now()),
				new Contacts("amelia", "rose", "bly manor", "westminster", "UK", "412563", "97 1478523699",
						"ameliarose@gmail.com", LocalDate.now()),
				new Contacts("rahul", "kohli", "bly manor", "westminster", "UK", "412563", "97 1478523699",
						"rahulkohli@gmail.com", LocalDate.now()) };
		addressBookDBService.addMultipleContacts(Arrays.asList(arrOfContacts));
		List<Contacts> contactList = addressBookDBService.readContacts();
		Assert.assertEquals(52, contactList.size());
	}
}
