package com.cosc325.bouncerbuddy;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ResultsActivity extends Activity {

	int age;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results);
		Intent intent=getIntent();
		Person thisGuy= (Person)intent.getSerializableExtra("Person");
		
		String[] personArray=makeArray(thisGuy);
		ArrayAdapter adapter = new ArrayAdapter<String>(this, 
		        android.R.layout.simple_list_item_1, personArray);
		
		ListView listView = (ListView) findViewById(android.R.id.list);
		listView.setAdapter(adapter);
		

		
	}
	
	public ResultsActivity(int newAge){
		age=newAge;
	}
	public ResultsActivity(){
		
	}
	
	public void goBack(View view){
		Intent intent=new Intent(this, MainActivity.class);
		intent.putExtra(DISPLAY_SERVICE, 1);
		startActivity(intent);
	}
	
	//ORDER: Name, Age, Location
	public String[] makeArray(Person person){
		String[] personData={"Name:         "+person.getName(), "Age:            "+person.getAge(),
				"Location:   "+person.getLocation()};
		
		return personData;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.results, menu);
		return true;
	}

}
