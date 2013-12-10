package com.cosc325.bouncerbuddy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

public class Person {

	public String name;
	//male: 1, female:0
	public boolean gender;
	public String city;
	public String state;
	public String zip;
	public String address;
	public int ID;
	
	public Person(String barcodeData) {
		processData(barcodeData);
	}
	
	public Person(){
		name=null;
		city=null;
		state=null;
		zip=null;
		address=null;
		ID=0;
	}
	
	public void setID(int id){
		ID=id;
	}
	
	public void setName(String newName){
		name=newName;
	}
	
	public void setZIP(String newZip){
		zip=newZip;
	}
	
	public void setState(String newState){
		name=newState;
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
	
	public String getName(){
		return name;
	}
	
	public String getZIP(){
		return zip;
	}
	
	public int getID(){
		return ID;
	}
}
