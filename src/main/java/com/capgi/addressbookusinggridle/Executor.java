package com.capgi.addressbookusinggridle;

import java.util.Scanner;

public class Executor {

	public static void main(String[] args) {
		MultipleAddressBook multipleAddressBookObj = new MultipleAddressBook();
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		System.out.println("Welcome to the address book");
		while (choice != 9) {
			System.out.println("Choose an option");
			System.out.println("1. Create new Address Book");
			System.out.println("2. Open an existing Address Book");
			System.out.println("3. Search a person's city or state");
			System.out.println("4. View person's by city or state");
			System.out.println("5. Person's count by city or state");
			System.out.println("6. Sort entries of address book alphabetically based on person name");
			System.out.println("7. Sort entries of address book based on city/state/zip");
			System.out.println("9. Quit");
			choice = Integer.parseInt(sc.nextLine());
			if (choice == 1) {
				multipleAddressBookObj.CreateAddressBook();
			} else if (choice == 2) {
				multipleAddressBookObj.OpenAddressBook();
			} else if (choice == 3) {
				multipleAddressBookObj.SearchCityOrStateByName();
			} else if (choice == 4) {
				multipleAddressBookObj.ViewPersonByStateOrCity();
			} else if (choice == 5) {
				multipleAddressBookObj.CountOfPersonInStateOrCity();
			} else if (choice == 6) {
				multipleAddressBookObj.SortAddressBookByName();
			} else if (choice == 7) {
				multipleAddressBookObj.SortAddressBookByStateOrCityOrZip();
			} else {
				System.out.println("Thank You!");
				break;
			}
		}
		sc.close();
	}
}