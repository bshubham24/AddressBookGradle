package com.capgi.addressbookusinggridle;

import java.util.Scanner;

public class Executor {

	public static void main(String[] args) {
		AddressBook addressBookObj = new AddressBook();
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		System.out.println("Welcome to the address book");
		while (choice != 4) {
			System.out.println("Pick an option");
			System.out.println("1. Add a contact");
			System.out.println("2. Edit an existing Contact");
			System.out.println("3. See the contact list");
			System.out.println("4. Exit");
			choice = Integer.parseInt(sc.nextLine());

			if (choice == 1) {
				addressBookObj.AddContact();
			}
			if (choice == 2) {
				System.out.println("Enter First Name :");
				String firstName = sc.nextLine();
				System.out.println("Enter Last Name :");
				String lastName = sc.nextLine();
				addressBookObj.EditContact(firstName, lastName);
			}
			if (choice == 3) {
				addressBookObj.DisplayContacts();
			}
		}

	}
}