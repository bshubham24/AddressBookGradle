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
	List<Contacts> contactList = new ArrayList<Contacts>();

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

	public void addContactToDB(int user_id, String firstName, String lastName, String address, String city,
			String state, String zip, String phoneNo, String email, LocalDate start) throws AddressBookDBException {

		int id = -1;
		Contacts contact = null;
		Connection connection = null;
		try {
			connection = this.getConnection();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			throw new AddressBookDBException(AddressBookDBException.ExceptionType.CONNECTION_ERROR, e.getMessage());
		}
		try (Statement statement = connection.createStatement()) {
			String sql = String.format(
					"INSERT INTO user_info (user_id, first_name, last_name, start)" + "VALUES ('%s','%s','%s','%s')",
					user_id, firstName, lastName, Date.valueOf(start));
			int rowAffected = statement.executeUpdate(sql);
			if (rowAffected == 1) {
				connection.close();
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {

				throw new AddressBookDBException(AddressBookDBException.ExceptionType.CONNECTION_ERROR, e.getMessage());
			}
			throw new AddressBookDBException(AddressBookDBException.ExceptionType.INCORRECT_INFO, e.getMessage());
		}
		try (Statement statement = connection.createStatement()) {
			String sql = String.format("INSERT INTO contact (user_id, phone, email)" + "VALUES ('%s','%s','%s')",
					user_id, phoneNo, email);
			int rowAffected = statement.executeUpdate(sql);
			if (rowAffected != 1) {
				connection.close();
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {

				throw new AddressBookDBException(AddressBookDBException.ExceptionType.CONNECTION_ERROR, e.getMessage());
			}
			throw new AddressBookDBException(AddressBookDBException.ExceptionType.INCORRECT_INFO, e.getMessage());
		}
		try (Statement statement = connection.createStatement()) {
			String sql = String.format(
					"INSERT INTO address (user_id, address, city, state, zip)" + "VALUES ('%s','%s','%s','%s','%s')",
					user_id, address, city, state, zip);
			int rowAffected = statement.executeUpdate(sql);
			if (rowAffected == 1) {
				contactList.add(new Contacts(firstName, lastName, address, city, state, zip, phoneNo, email));
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new AddressBookDBException(AddressBookDBException.ExceptionType.CONNECTION_ERROR, e.getMessage());
			}
		}
		try {
			connection.commit();
		} catch (SQLException e) {
			throw new AddressBookDBException(AddressBookDBException.ExceptionType.CONNECTION_ERROR, e.getMessage());
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					throw new AddressBookDBException(AddressBookDBException.ExceptionType.CONNECTION_ERROR,
							e.getMessage());
				}
		}
	}
}
