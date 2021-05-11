package com.safeways;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

public class Search extends Activity implements OnClickListener {
	String[] pre;
	String result, result2;
	Cursor c = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.search);
		pre = getResources().getStringArray(R.array.stations_array);
		Spinner sp = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.stations_array,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(adapter);
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onNothingSelected(AdapterView<?> arg0) {
			}

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				int index = arg0.getSelectedItemPosition();
				String stages = (String) arg0.getSelectedItem();
				result = stages;
			}
		});

		ImageButton b1 = (ImageButton) findViewById(R.id.getbus);
		b1.setOnClickListener(this);

	}

	public void onClick(View v) {

		DBAdapter myDbHelper = new DBAdapter(Search.this);
		try {

			myDbHelper.createDataBase();

		} catch (IOException ioe) {

			throw new Error("Unable to create database");

		}

		try {

			myDbHelper.openDataBase();

		} catch (SQLException sqle) {

			throw sqle;

		}

		c = myDbHelper.query1(result);
		if (c.moveToFirst()) {
			do {

				result2 = c.getString(0);

			} while (c.moveToNext());
		}

		Intent i = new Intent(Search.this, Display.class);
		Bundle ex = new Bundle();
		ex.putString("re", result);
		ex.putString("re2", result2);
		i.putExtras(ex);
		startActivityForResult(i, 1);
		myDbHelper.close();
	}
}
