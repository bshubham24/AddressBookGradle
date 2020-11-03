package com.capgi.addressbookusinggridle;

import java.time.LocalDate;
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
		Assert.assertEquals(3, contactList.size());
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
		Assert.assertEquals(2, noOfContacts);
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
}
