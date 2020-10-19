package com.capgi.addressbookusinggridle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class FileIOServiceAddressBook {

	public static String CONTACT_READ_FILE_NAME = "ContactsFileToRead.txt";
	public static String CONTACT_WRITE_FILE_NAME = "ContactsFileToWrite.txt";
	public static final String SAMPLE_CSV_FILE_PATH = "F:\\Capgemini_training1\\java_eclipse\\AddressBookUsingGridle\\FileRead.csv";
	public static final String SAMPLE_CSV_FILE_WRITTER_PATH = "FileWrite.csv";
	public static final String SAMPLE_JSON_FILE_PATH = "F:\\Capgemini_training1\\java_eclipse\\AddressBookUsingGridle\\ReadOnlyJSON.json";
	public static final String SAMPLE_JSON_FILE_WRITER_PATH = "FilesWriter.json";

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

	public List<Contacts> readCsvFile() throws IOException {
		List<Contacts> contactList = new ArrayList<>();
		try (Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));) {

			CsvToBean<Contacts> csvToBean = new CsvToBeanBuilder<Contacts>(reader).withType(Contacts.class)
					.withIgnoreLeadingWhiteSpace(true).build();

			contactList = csvToBean.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contactList;
	}

	public boolean writeCsvFile() throws IOException {
		try (Writer writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE_WRITTER_PATH));
				CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER,
						CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);) {
			String[] headerRecord = { "FirstName", "LastName", "Address", "City", "State", "Zip", " Phone no",
					"Email" };
			csvWriter.writeNext(headerRecord);
			csvWriter.writeNext(new String[] { "Shubham", "Bhawsar", "mp", "bhopal", "mp", "123456", "14 9478523699",
					"shubham@gami.com" });
			csvWriter.writeNext(new String[] { "Wolverine", "Logan", "pune", "pune", "mh", "452136", "91 4785236998",
					"afsdv@gmail.com" });
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean writeIntoJsonFile(Contacts contacts) {
		Gson gson = new Gson();
		String json = gson.toJson(contacts);
		try {
			FileWriter fileWriter = new FileWriter(SAMPLE_JSON_FILE_WRITER_PATH);
			fileWriter.write(json);
			fileWriter.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean readJsonFile() {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_JSON_FILE_PATH));
			JsonParser jsonParser = new JsonParser();
			JsonElement obj = jsonParser.parse(reader);
			JsonArray contactList = (JsonArray) obj;
			System.out.println(contactList);

			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
