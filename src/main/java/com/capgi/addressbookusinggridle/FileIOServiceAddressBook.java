package com.capgi.addressbookusinggridle;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileIOServiceAddressBook {

	public static String CONTACT_READ_FILE_NAME = "ContactsFileToRead.txt";
	public static String CONTACT_WRITE_FILE_NAME = "ContactsFileToWrite.txt";

	public List<Contacts> readData() {
		List<Contacts> contactsList = new ArrayList<>();
		try {
			Files.lines(new File(CONTACT_READ_FILE_NAME).toPath()).map(line -> line.trim()).forEach(line -> {
				String[] words = line.split("[\\s,:]+");

				Contacts contact = new Contacts();
				try {
					contact.setFirstName(words[0]);
					contact.setLastName(words[1]);
					contact.setAddress(words[2]);
					contact.setCity(words[3]);
					contact.setState(words[4]);
					contact.setZip(words[5]);
					contact.setPhoneNo(words[6]);
					contact.setEmail(words[7]);
				} catch (AddressBookException e) {
					System.out.println(e.getMessage());
				}
				contactsList.add(contact);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contactsList;
	}

	public void writeData(List<Contacts> contactList) {
		StringBuffer empBuffer = new StringBuffer();
		contactList.forEach(contact -> {
			String employeeDataString = contact.toString().concat("\n");
			empBuffer.append(employeeDataString);
		});
		try {
			Files.write(Paths.get(CONTACT_WRITE_FILE_NAME), empBuffer.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public long countEntries() {
		long entries = 0;
		try {
			entries = Files.lines(new File(CONTACT_WRITE_FILE_NAME).toPath()).count();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entries;
	}
}
