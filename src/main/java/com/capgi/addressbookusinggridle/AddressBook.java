package com.capgi.addressbookusinggridle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;

public class AddressBook {
	Scanner sc = new Scanner(System.in);
	Contacts contactObj = new Contacts();
	String fName = null;
	String lName = null;

	public Contacts AddContact(String bookName, ArrayList<Contacts> tempList) {
		Contacts contactObj = new Contacts();
		boolean check = false;

		while (!check) {
			try {
				System.out.println("Enter the first name");
				fName = sc.nextLine();
				check = contactObj.setFirstName(fName);

			} catch (AddressBookException e) {
				System.out.println(e.getMessage());
			}
		}
		check = false;

		while (!check) {
			try {
				System.out.println("Enter the last name");
				lName = sc.nextLine();
				check = contactObj.setLastName(lName);
			} catch (AddressBookException e) {
				System.out.println(e.getMessage());
			}
		}
		check = false;
		/*
		 * boolean duplicate = false; //
		 * System.out.println(multipleAddressBook.getList(bookName).size()); if
		 * (multipleAddressBook.getList(bookName).size() != 0) { Predicate<Contacts> Obj
		 * = (item) -> item.getFirstName().equals(fName) &&
		 * item.getLastName().equals(lName); duplicate =
		 * multipleAddressBook.getList(bookName).stream().anyMatch(Obj);
		 * System.out.println("inside duplicate"); }
		 */
		boolean duplicate = false;
		if (tempList.size() != 0) {
			Predicate<Contacts> Obj = (item) -> item.getFirstName().equals(fName) && item.getLastName().equals(lName);
			duplicate = tempList.stream().anyMatch(Obj);

		}

		if (!duplicate) {
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
			return contactObj;
		} else {
			System.out.println("This person already exist. You can chooose edit or delete option!");
			return contactObj;
		}
	}

	public void EditContact(String firstName, String lastName, ArrayList<Contacts> tempList) {
		if (tempList.isEmpty()) {
			System.out.println("No contacts present");
		} else {
			boolean check = false;
			for (Contacts item : tempList) {
				if ((item.getFirstName().equals(firstName)) && (item.getLastName().equals(lastName))) {
					System.out.println("What do you want to edit");
					String choice = sc.nextLine().toLowerCase();
					if (choice.equals("address")) {
						while (!check) {
							try {
								System.out.println("Enter the address");
								check = item.setAddress(sc.nextLine());
							} catch (AddressBookException e) {
								System.out.println(e.getMessage());
							}
						}
						break;
					} else if (choice.equals("city")) {
						while (!check) {
							try {
								System.out.println("Enter the city");
								check = item.setCity(sc.nextLine());
							} catch (AddressBookException e) {
								System.out.println(e.getMessage());
							}
						}
						break;
					} else if (choice.equals("state")) {
						while (!check) {
							try {
								System.out.println("Enter the state");
								check = item.setState(sc.nextLine());
							} catch (AddressBookException e) {
								System.out.println(e.getMessage());
							}
						}
						break;
					} else if (choice.equals("zip")) {
						while (!check) {
							try {
								System.out.println("Enter the zip code");
								check = item.setZip(sc.nextLine());
							} catch (AddressBookException e) {
								System.out.println(e.getMessage());
							}
						}
						break;
					} else if (choice.equals("phone number")) {

						while (!check) {
							try {
								System.out.println("Enter the phone number");
								check = item.setPhoneNo(sc.nextLine());
							} catch (AddressBookException e) {
								System.out.println(e.getMessage());
							}
						}
						break;
					} else if (choice.equals("email")) {
						while (!check) {
							try {
								System.out.println("Enter the email");
								check = item.setEmail(sc.nextLine());

							} catch (AddressBookException e) {
								System.out.println(e.getMessage());
							}

						}
						break;
					}

				}
			}
			if (check)
				System.out.println("Contact edited Successfully");
			else
				System.out.println("No Such Person present");
		}
	}

	public void DeleteContact(String firstName, String lastName, ArrayList<Contacts> tempList) {
		if (tempList.isEmpty()) {
			System.out.println("No contacts present");
		} else {

			for (Contacts item : tempList) {
				if ((item.getFirstName().equals(firstName)) && (item.getLastName().equals(lastName))) {
					tempList.remove(item);
					break;
				}
			}
		}

	}

	public void DisplayContacts(ArrayList<Contacts> tempList) {
		ArrayList<Contacts> contactList1 = tempList;
		if (contactList1.isEmpty()) {
			System.out.println("No contacts present");
		} else {
			int count = 0;
			for (Contacts item : contactList1) {
				count++;
				System.out.println("Contact no." + count + " \n" + item.toString());

			}
		}
	}

	public void SearchCityOrStateByName(String fName, String lName, HashMap<String, ArrayList<Contacts>> addressBook) {
		boolean check = false;
		ArrayList<Contacts> tempList = new ArrayList<Contacts>();
		for (Map.Entry<String, ArrayList<Contacts>> mapElement : addressBook.entrySet()) {

			tempList = mapElement.getValue();
			check = tempList.stream()
					.anyMatch(obj -> obj.getFirstName().equals(fName) && obj.getLastName().equals(lName));
			if (check) {
				break;
			}
		}
		if (check) {
			tempList.stream()
					.forEach(obj -> System.out.println("City: " + obj.getCity() + " " + "State: " + obj.getState()));
		} else {
			System.out.println("Person not present");
		}
	}

}
