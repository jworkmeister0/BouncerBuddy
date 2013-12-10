package com.cosc325.bouncerbuddy;


import java.util.List;

import net.photopay.base.*;
import mobi.pdf417.activity.Pdf417ScanActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;


public class MainActivity extends Activity {
	public static final String dbname="db";
	int id=0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void startScanner(View view){
		// Intent for Pdf417ScanActivity
		Intent intent = new Intent(this, Pdf417ScanActivity.class);

		// USE VARIABLE FOR ID (request code=1)
		startActivityForResult(intent, 1);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		id++;
		DatabaseHelper db=new DatabaseHelper(this);

	    super.onActivityResult(requestCode, resultCode, data);

	    if(requestCode==1 && resultCode==BaseBarcodeActivity.RESULT_OK) {
	        // read scanned barcode type (PDF417 or QR code)
	        String barcodeType = data.getStringExtra(BaseBarcodeActivity.EXTRAS_BARCODE_TYPE);
	        
	        if (barcodeType!="QR code"){
	        	
	        // read the data contained in barcode
	        String barcodeData = data.getStringExtra(BaseBarcodeActivity.EXTRAS_RESULT);
	        Person person=new Person(barcodeData);
	        
	        System.out.println("Persontostring" +person.toString());
	        
	        db.addPerson(person);
	        //System.out.println(db.getAllPersons().toString());
	        System.out.println("GET PERSON BELOW");
	        //System.out.println(db.getPerson(0).toString());
	        List<Person> personList=db.getAllPersons();
	        for (Person asdfads: personList) {
	            System.out.println(person.toString());
	            System.out.println("-->Loop again<--");
	        }
	        // ask user what to do with data
	        Intent intent = new Intent(Intent.ACTION_SEND);
	        intent.setType("text/plain");
	        intent.putExtra(Intent.EXTRA_TEXT, person.toString() );
	        //CHANGED "UseWith" to "PPUse"
	        startActivity(Intent.createChooser(intent, getString(R.string.PPUse)));
	        }
	    }
	}
	

	
	public void startSettings(View view){
		Intent intent=new Intent(this, SettingsActivity.class);
		startActivity(intent);
	}
}
