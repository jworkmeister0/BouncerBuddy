package com.cosc325.bouncerbuddy;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
 
public class DatabaseHelper extends SQLiteOpenHelper {
 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "db";
    // Persons table name
    private static final String TABLE_PEOPLE = "people";
 
    // Persons Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_STATE= "states";
    private static final String KEY_ZIP = "zip";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PEOPLE + "( "
                + KEY_ID + " INTEGER PRIMARY KEY ASC," + KEY_NAME + " TEXT, "
                + KEY_ZIP + " TEXT, "
                + KEY_STATE + " TEXT " + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEOPLE);
 
        // Create tables again
        onCreate(db);
    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    // Adding new person
    void addPerson(Person person) {
    	System.out.println("ADD PERSON");
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, person.getName());
        values.put(KEY_ZIP, person.getZIP());
        values.put(KEY_STATE, person.getState());
 
        // Inserting Row
        db.insert(TABLE_PEOPLE, null, values);
        db.close(); // Closing database connection
    }
    
    Person getPerson(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_PEOPLE, new String[] { KEY_ID,
				KEY_NAME, KEY_ZIP }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Person person= new Person();
		person.setName(cursor.getString(1));
		person.setZIP(cursor.getString(2));
		// return contact
		return person;
	}
 
    
    // Getting All Persons
    public List<Person> getAllPersons() {
        List<Person> personList = new ArrayList<Person>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_PEOPLE;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Person person = new Person();
                person.setID(Integer.parseInt(cursor.getString(0)));
                person.setName(cursor.getString(1));
                person.setZIP(cursor.getString(2));
                person.setState(cursor.getString(3));
                // Adding person to list
                personList.add(person);
                
                System.out.println("-->DO<--");
            } while (cursor.moveToNext());
        }
 
        // return person list
        return personList;
    }
 
 
    // Getting persons Count
    public int getPersonsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PEOPLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
 
}
