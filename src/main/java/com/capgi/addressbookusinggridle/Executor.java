package com.capgi.addressbookusinggridle;

import java.util.Scanner;

public class Executor {

	public static void main(String[] args) {
		MultipleAddressBook multipleAddressBookObj = new MultipleAddressBook();
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		System.out.println("Welcome to the address book");
		while (choice != 3) {
			System.out.println("Choose an option");
			System.out.println("1. Create new Address Book");
			System.out.println("2. Open an existing Address Book");
			System.out.println("3. Quit");
			choice = Integer.parseInt(sc.nextLine());
			if (choice == 1) {
				multipleAddressBookObj.CreateAddressBook();
			} else if (choice == 2) {
				multipleAddressBookObj.OpenAddressBook();
			} else {
				System.out.println("Thank You!");
				break;
			}
		}
		sc.close();
	}
}