package com.capgi.addressbookusinggridle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class FileIOAddressBookTest {
	@Test
	public void givenContactsInFileShouldRead() {
		FileIOServiceAddressBook fileIOServiceAddressBook = new FileIOServiceAddressBook();
		List<Contacts> contactList = new ArrayList<>();
		contactList = fileIOServiceAddressBook.readData();
		System.out.println(contactList);
		Assert.assertEquals(2, contactList.size());
	}

	@Test
	public void writeContactsToFile() {
		FileIOServiceAddressBook fileIOServiceAddressBook = new FileIOServiceAddressBook();
		List<Contacts> contactList = new ArrayList<>();
		Contacts contact1 = new Contacts("Shubham", "Bhawsar", "mp", "bhopal", "mp", "123456", "14 9478523699",
				"shubham@gami.com");
		Contacts contact2 = new Contacts("Wolverine", "Logan", "pune", "pune", "mh", "452136", "91 4785236998",
				"afsdv@gmail.com");
		contactList.add(contact1);
		contactList.add(contact2);
		fileIOServiceAddressBook.writeData(contactList);
		Assert.assertEquals(18, fileIOServiceAddressBook.countEntries());
	}

	@Test
	public void givenContactsInCSVFileShouldRead() throws IOException {
		FileIOServiceAddressBook fileIOServiceAddressBook = new FileIOServiceAddressBook();
		List<Contacts> contactList = new ArrayList<>();
		contactList = fileIOServiceAddressBook.readCsvFile();
		System.out.println(contactList);
		Assert.assertEquals(2, contactList.size());
	}

	@Test
	public void writeContactsToCSVFile() throws IOException {
		FileIOServiceAddressBook fileIOServiceAddressBook = new FileIOServiceAddressBook();

		boolean result = fileIOServiceAddressBook.writeCsvFile();
		Assert.assertEquals(true, result);
	}
}
