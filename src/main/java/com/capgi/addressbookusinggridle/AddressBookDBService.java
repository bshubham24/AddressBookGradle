package com.capgi.addressbookusinggridle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService {

	private Connection getConnection() throws AddressBookDBException {
		String jdbcURL = "jdbc:mysql://localhost:3306/address_book_service?characterEncoding=utf8";
		String userName = "root";
		String password = "CL@Liv#6@RM#13";
		try {
			return DriverManager.getConnection(jdbcURL, userName, password);
		} catch (SQLException e) {
			throw new AddressBookDBException(AddressBookDBException.ExceptionType.CONNECTION_ERROR, e.getMessage());
		}
	}

	public List<Contacts> readContacts() throws AddressBookDBException {
		String sql = "select u.first_name, u.last_name, a.address, a.city, a.state, a.zip, c.phone, c.email"
				+ " from user_info u inner join contact c on c.user_id = u.user_id inner join address a on u.user_id = a.user_id;";
		List<Contacts> contactList = new ArrayList<Contacts>();
		try (Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			contactList = getContactData(result);
		} catch (SQLException e) {
			throw new AddressBookDBException(AddressBookDBException.ExceptionType.CONNECTION_ERROR, e.getMessage());
		}
		return contactList;
	}

	private List<Contacts> getContactData(ResultSet result) throws AddressBookDBException {
		List<Contacts> contactList = new ArrayList<Contacts>();
		try {
			while (result.next()) {
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String address = result.getString("address");
				String city = result.getString("city");
				String state = result.getString("state");
				String zip = result.getString("zip");
				String phoneNo = result.getString("phone");
				String email = result.getString("email");
				contactList.add(new Contacts(firstName, lastName, address, city, state, zip, phoneNo, email));
			}
		} catch (SQLException e) {
			throw new AddressBookDBException(AddressBookDBException.ExceptionType.CONNECTION_ERROR, e.getMessage());
		}
		return contactList;
	}

	public int updatePersonAddress(String firstName, String column, String value) throws AddressBookDBException {
		String sql = String.format(
				"update address a,user_info u set a.%s ='%s' where a.user_id = u.user_id and u.first_name = '%s';",
				column, value, firstName);
		try (Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			return statement.executeUpdate(sql);
		} catch (SQLException e) {
			throw new AddressBookDBException(AddressBookDBException.ExceptionType.CONNECTION_ERROR, e.getMessage());
		}
	}

	public Contacts isAddressBookInSyncWithDB(String firstName) throws AddressBookDBException {
		List<Contacts> tempList = this.readContacts();
		return tempList.stream().filter(contact -> contact.getFirstName().contentEquals(firstName)).findFirst()
				.orElse(null);
	}

	public int getContactsOnDateRange(LocalDate startDate, LocalDate endDate) throws AddressBookDBException {
		String sql = String.format("SELECT user_id FROM user_info WHERE start BETWEEN '%s' AND '%s';",
				Date.valueOf(startDate), Date.valueOf(endDate));
		int noOfContacts = 0;
		try (Connection connection = getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				noOfContacts++;
			}
		} catch (SQLException e) {
			throw new AddressBookDBException(AddressBookDBException.ExceptionType.CONNECTION_ERROR, e.getMessage());
		}
		return noOfContacts;
	}

	public int retriveBasedOnField(String field, String value) throws AddressBookDBException {
		String sql = String.format("SELECT user_id FROM address WHERE %s = '%s';", field, value);
		int noOfContacts = 0;
		try (Connection connection = getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				noOfContacts++;
			}
		} catch (SQLException e) {
			throw new AddressBookDBException(AddressBookDBException.ExceptionType.CONNECTION_ERROR, e.getMessage());
		}
		return noOfContacts;
	}

}
