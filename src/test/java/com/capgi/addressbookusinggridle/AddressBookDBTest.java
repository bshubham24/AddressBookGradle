package com.capgi.addressbookusinggridle;

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
}
