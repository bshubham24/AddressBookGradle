package com.capgi.addressbookusinggridle;

import java.util.Scanner;

public class Executor {

	public static void main(String[] args) {
		Contacts contactObj = new Contacts();
		Scanner sc = new Scanner(System.in);
		boolean check = false;
		System.out.println("Welcome to the address book");

		while (!check) {
			try {
				System.out.println("Enter the first name");
				check = contactObj.setFirstName(sc.nextLine());
			} catch (AddressBookException e) {
				System.out.println(e.getMessage());
			}
		}
		check = false;

		while (!check) {
			try {
				System.out.println("Enter the last name");
				check = contactObj.setLastName(sc.nextLine());
			} catch (AddressBookException e) {
				System.out.println(e.getMessage());
			}
		}
		check = false;

		while (!check) {
			try {
				System.out.println("Enter the address");
				check = contactObj.setAddress(sc.nextLine());
			} catch (AddressBookException e) {
				System.out.println(e.getMessage());
			}
		}
		check = false;

		while (!check) {
			try {
				System.out.println("Enter the city");
				check = contactObj.setCity(sc.nextLine());
			} catch (AddressBookException e) {
				System.out.println(e.getMessage());
			}
		}
		check = false;

		while (!check) {
			try {
				System.out.println("Enter the state");
				check = contactObj.setState(sc.nextLine());
			} catch (AddressBookException e) {
				System.out.println(e.getMessage());
			}
		}
		check = false;

		while (!check) {
			try {
				System.out.println("Enter the zip code");
				check = contactObj.setZip(sc.nextLine());
			} catch (AddressBookException e) {
				System.out.println(e.getMessage());
			}
		}
		check = false;

		while (!check) {
			try {
				System.out.println("Enter the phone number");
				check = contactObj.setPhoneNo(sc.nextLine());
			} catch (AddressBookException e) {
				System.out.println(e.getMessage());
			}
		}
		check = false;

		while (!check) {
			try {
				System.out.println("Enter the email");
				check = contactObj.setEmail(sc.nextLine());

			} catch (AddressBookException e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println(contactObj.getFirstName() + " " + contactObj.getLastName());
		System.out.println(contactObj.getAddress() + " " + contactObj.getCity() + " " + contactObj.getState() + " "
				+ contactObj.getZip());
		System.out.println(contactObj.getPhoneNo());
		System.out.println(contactObj.getEmail());

	}
}