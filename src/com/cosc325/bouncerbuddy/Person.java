package com.cosc325.bouncerbuddy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

public class Person {

	public String name;
	public String city;
	public String state;
	public String zip;
	public String address;
	
	public Person(String barcodeData) {
		processData(barcodeData);
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

			name = data.readLine();

			address = data.readLine();
			city = data.readLine();
			state = data.readLine();
			zip = data.readLine();

			name = processName(name);
			address = processLine(address);
			city = processLine(city);
			state = processLine(state);
			zip = processLine(zip);

			data.close();

			return name + "\n" + address + "\n" + city + "\n" + state + "\n"
					+ zip;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERROR";
		}
	}

	public String toString(){
		return name + "\n" + address + "\n" + city + "\n" + state + "\n"
				+ zip;
	}
	public String processName(String name) {
		Scanner s = new Scanner(name).useDelimiter(",");
		String last = s.next();
		String first = s.next();

		last = last.substring(3, last.length());

		name = first + " " + last;

		s.close();
		return name;
	}

	public String processLine(String line) {
		line = line.substring(3, line.length());
		return line;
	}
	
}
