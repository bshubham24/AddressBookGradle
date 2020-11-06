
package com.capgi.addressbookusinggridle;

import java.time.LocalDate;

import com.opencsv.bean.CsvBindByName;

public class Contacts {
	@CsvBindByName
	private String firstName;
	@CsvBindByName
	private String lastName;
	@CsvBindByName
	private String address;
	@CsvBindByName
	private String city;
	@CsvBindByName
	private String state;
	@CsvBindByName
	private String zip;
	@CsvBindByName
	private String phoneNo;
	@CsvBindByName
	private String email;
	private int id;

	private LocalDate startDate;

	public Contacts(String firstName, String lastName, String address, String city, String state, String zip,
			String phoneNo, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneNo = phoneNo;
		this.email = email;
	}

	public Contacts(String firstName, String lastName, String address, String city, String state, String zip,
			String phoneNo, String email, LocalDate startDate) {
		this(firstName, lastName, address, city, state, zip, phoneNo, email);
		this.startDate = startDate;
	}

	public Contacts(int id, String firstName, String lastName, String address, String city, String state, String zip,
			String phoneNo, String email) {
		this(firstName, lastName, address, city, state, zip, phoneNo, email);
		this.setId(id);
	}

	public Contacts(int id, String firstName, String lastName, String address, String city, String state, String zip,
			String phoneNo, String email, LocalDate startDate) {
		this(id, firstName, lastName, address, city, state, zip, phoneNo, email);
		this.startDate = startDate;
	}

	public Contacts() {

	}

	public String getFirstName() {
		return firstName;
	}

	public boolean setFirstName(String firstName) throws AddressBookException {
		Validation validateContact = new Validation();
		boolean b = validateContact.ValidateFirstName(firstName);
		if (b) {
			this.firstName = firstName;

		} else {
			throw new AddressBookException("Please enter a valid first name");

		}
		return b;
	}

	public String getLastName() {
		return lastName;
	}

	public boolean setLastName(String lastName) throws AddressBookException {
		Validation validateContact = new Validation();
		boolean b = validateContact.ValidateLastName(lastName);
		if (b)
			this.lastName = lastName;

		else {
			throw new AddressBookException("Please enter a valid last name");

		}
		return b;
	}

	public String getAddress() {
		return address;
	}

	public boolean setAddress(String address) throws AddressBookException {
		Validation validateContact = new Validation();
		boolean b = validateContact.ValidateAddress(address);
		if (b)
			this.address = address;

		else {
			throw new AddressBookException("Please enter a valid address");

		}
		return b;
	}

	public String getCity() {
		return city;
	}

	public boolean setCity(String city) throws AddressBookException {
		Validation validateContact = new Validation();
		boolean b = validateContact.ValidateCity(city);
		if (b)
			this.city = city;

		else {
			throw new AddressBookException("Please enter a valid city");

		}
		return b;
	}

	public String getState() {
		return state;
	}

	public boolean setState(String state) throws AddressBookException {
		Validation validateContact = new Validation();
		boolean b = validateContact.ValidateState(state);
		if (b)
			this.state = state;

		else {
			throw new AddressBookException("Please enter a valid state");

		}
		return b;
	}

	public String getZip() {
		return zip;
	}

	public boolean setZip(String zip) throws AddressBookException {
		Validation validateContact = new Validation();
		boolean b = validateContact.ValidateZip(zip);
		if (b)
			this.zip = zip;

		else {
			throw new AddressBookException("Please enter a valid zip");

		}
		return b;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public boolean setPhoneNo(String phoneNo) throws AddressBookException {
		Validation validateContact = new Validation();
		boolean b = validateContact.ValidatePhoneNo(phoneNo);
		if (b)
			this.phoneNo = phoneNo;

		else {
			throw new AddressBookException("Please enter a valid Phone number");

		}
		return b;
	}

	public String getEmail() {
		return email;
	}

	public boolean setEmail(String email) throws AddressBookException {
		Validation validateContact = new Validation();
		boolean b = validateContact.ValidateEmail(email);
		if (b)
			this.email = email;

		else {
			throw new AddressBookException("Please enter a valid email");

		}
		return b;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "\nFirst Name :" + firstName + "\nLast Name :" + lastName + "\nAddress :" + address + "\nCity :" + city
				+ "\nState :" + state + "\nZip :" + zip + "\nPhone No :" + phoneNo + "\nEmail :" + email;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Contacts that = (Contacts) o;
		return this.firstName == that.firstName && this.lastName == that.lastName && this.address == that.address
				&& this.city == that.city && this.state == that.state && this.zip == that.zip
				&& this.phoneNo == that.phoneNo && this.email == that.email;
	}
}
