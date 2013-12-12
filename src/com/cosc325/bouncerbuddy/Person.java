package com.cosc325.bouncerbuddy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.util.Scanner;
import java.util.Calendar;

public class Person implements Serializable {

	public String name;
	// male: 1, female:0
	public boolean gender;
	public String city;
	public String state;
	public String age;
	public String zip;
	public String address;
	public String location;
	public int ID;

	public Person(String barcodeData) {
		processData(barcodeData);
	}

	public Person() {

	}

	public void setID(int id) {
		ID = id;
	}

	public void setAge(String newAge) {
		age = newAge;
	}

	public void setName(String newName) {
		name = newName;
	}

	public void setZIP(String newZip) {
		zip = newZip;
	}

	public void setState(String newState) {
		name = newState;
	}

	/* lines 1-5 (or 0-4) of the barcode data are evil. Kill them */
	public String processData(String barcodeData) {

		BufferedReader data = new BufferedReader(new StringReader(barcodeData));

		try {
			name = null;
			city = null;
			state = null;
			zip = null;
			address = null;

			data.readLine();
			data.readLine();
			data.readLine();

			name = processLine(data.readLine());

			address = data.readLine();
			city = data.readLine();
			state = data.readLine();
			zip = data.readLine();
			data.readLine();
			data.readLine();
			data.readLine();
			data.readLine();
			data.readLine();
			data.readLine();
			String dob = data.readLine();

			name = processName(name);
			address = processLine(address);
			city = processLine(city);
			state = processLine(state);
			zip = processLine(zip);

			age = processAge(dob);

			return name + "\n" + address + "\n" + city + "\n" + state + "\n"
					+ zip;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERROR";
		}
	}

	public String toString() {
		return name + "\n" + age + "\n" + address + "\n" + city + "\n" + state
				+ "\n" + zip;
	}

	public String processAge(String date) {
		date = processLine(date);
		String year = date.substring(0, 4);
		String month = date.substring(4, 6);
		String day = date.substring(6, 8);

		System.out.println(year + " " + month + " " + day);

		Calendar now = Calendar.getInstance();
		Calendar dob = Calendar.getInstance();

		System.out.println(Integer.parseInt(year) + " "
				+ Integer.parseInt(month) + " " + Integer.parseInt(day));

		dob.set(Integer.parseInt(year), Integer.parseInt(month),
				Integer.parseInt(day));
		if (dob.after(now)) {
			throw new IllegalArgumentException("Can't be born in the future");
		}
		int year1 = now.get(Calendar.YEAR);
		int year2 = dob.get(Calendar.YEAR);
		int age = year1 - year2;
		System.out.println(age);
		int month1 = now.get(Calendar.MONTH);
		int month2 = dob.get(Calendar.MONTH);

		if (month2 > month1) {
			age--;
		} else if (month1 == month2) {
			int day1 = now.get(Calendar.DAY_OF_MONTH);
			int day2 = dob.get(Calendar.DAY_OF_MONTH);
			if (day1 > day2) {
				age--;
			}
		}
		
		return Integer.toString(age);
	}

	public String processName(String name) {
		Scanner s = new Scanner(name).useDelimiter(",");
		String last = s.next();
		String first = s.next();

		//last = last.substring(3, last.length());

		name = first + " " + last;

		s.close();
		return name;
	}

	public String processLine(String line) {
		line = line.substring(3, line.length());
		return line;
	}
	
	public String getLocation(){
		return (city + " "+state+", "+zip);
	}

	public String getName() {
		return name;
	}

	public String getZIP() {
		return zip;
	}

	public String getAge() {
		return age;
	}

	public String getState() {
		return state;
	}

	public int getID() {
		return ID;
	}
	
	public String getCity(){
		return city;
	}
	

}
