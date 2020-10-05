package com.capgi.addressbookusinggridle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MultipleAddressBook {
	Scanner sc = new Scanner(System.in);
	HashMap<String, ArrayList<Contacts>> addressBook = new HashMap<String, ArrayList<Contacts>>();
	AddressBook addressBookObj = new AddressBook();

	public void CreateAddressBook() {
		int choice = 0;

		System.out.println("AddressBook Created");
		System.out.println("Give this address book a name ");
		String bookName = sc.nextLine();
		ArrayList<Contacts> tempList = new ArrayList<Contacts>();

		while (choice != 6) {
			System.out.println("Pick an option");
			System.out.println("1. Add a contact");
			System.out.println("2. Edit an existing Contact");
			System.out.println("3. Delete a contact");
			System.out.println("4. See the contact list");
			System.out.println("5. Back to main menu");
			choice = Integer.parseInt(sc.nextLine());

			if (choice == 1) {
				tempList = addressBookObj.AddContact();
				addressBook.put(bookName, tempList);
			} else if (choice == 2) {
				System.out.println("Enter First Name :");
				String firstName = sc.nextLine();
				System.out.println("Enter Last Name :");
				String lastName = sc.nextLine();
				addressBookObj.EditContact(firstName, lastName);
			} else if (choice == 3) {
				System.out.println("Enter First Name :");
				String firstName = sc.nextLine();
				System.out.println("Enter Last Name :");
				String lastName = sc.nextLine();
				addressBookObj.DeleteContact(firstName, lastName);
			} else if (choice == 4) {
				addressBookObj.DisplayContacts();
			} else if (choice == 5) {
				System.out.println("Back to main menu");
				choice = 6;
			} else {
				System.out.println("Enter correct choice");
			}
		}

	}

	public void OpenAddressBook() {
		int choice = 0;
		if (addressBook.size() == 0) {
			System.out.println("Address Book is empty");
		} else {
			System.out.println("Existing addressBooks:");
			for (Map.Entry<String, ArrayList<Contacts>> mapElement : addressBook.entrySet()) {
				System.out.println(mapElement.getKey());
			}

			System.out.println("Please give the name of existing address book that you want your contact into");
			String TempBookName = sc.nextLine();

			boolean find = addressBook.containsKey(TempBookName);
			if (find) {
				ArrayList<Contacts> tempList = new ArrayList<Contacts>();

				while (choice != 6) {
					System.out.println("Pick an option");
					System.out.println("1. Add a contact");
					System.out.println("2. Edit an existing Contact");
					System.out.println("3. Delete a contact");
					System.out.println("4. See the contact list");
					System.out.println("5. Back to main menu");
					choice = Integer.parseInt(sc.nextLine());

					if (choice == 1) {
						tempList = addressBookObj.AddContact();
						addressBook.put(TempBookName, tempList);
					} else if (choice == 2) {
						System.out.println("Enter First Name :");
						String firstName = sc.nextLine();
						System.out.println("Enter Last Name :");
						String lastName = sc.nextLine();
						addressBookObj.EditContact(firstName, lastName);
					} else if (choice == 3) {
						System.out.println("Enter First Name :");
						String firstName = sc.nextLine();
						System.out.println("Enter Last Name :");
						String lastName = sc.nextLine();
						addressBookObj.DeleteContact(firstName, lastName);
					} else if (choice == 4) {
						addressBookObj.DisplayContacts();
					} else if (choice == 5) {
						System.out.println("Back to main menu");
						choice = 6;
					} else {
						System.out.println("Enter correct choice");
					}
				}

			} else {
				System.out.println("Book not found");
			}
		}
	}
}
