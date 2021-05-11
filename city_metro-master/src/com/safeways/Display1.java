package com.safeways;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Display1 extends Activity {
	String value1, result1, result2;
	Cursor c = null;
	Cursor c2 = null;
	int i = 0;
	ArrayList<String> list = new ArrayList<String>();
	ArrayList<String> arrlist = new ArrayList<String>();
	String[] res = { "Enter root not exist in metro express" };
	String[] arr = new String[] {};
	String[] str;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display1);
		ListView lv = (ListView) findViewById(R.id.listView1);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			value1 = extras.getString("re");
		}
		TextView tv1 = (TextView) findViewById(R.id.textView2);
		tv1.setText(value1);
		TextView tv2 = (TextView) findViewById(R.id.textView4);
		DBAdapter myDbHelper = new DBAdapter(Display1.this);
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

		Log.i("database ", "success");
		c = myDbHelper.query4(value1);
		if (c.getCount() > 0) {
			try {

				if (c.moveToFirst()) {
					result1 = c.getString(1);
					tv2.setText(result1);
					for (int i = 2; i < c.getColumnCount(); i++) {

						String[] str = new String[c.getColumnCount()];
						if (c.getString(i) != null) {
							str[i] = c.getString(i);
							list.add(str[i]);

						}
					}
				}

				lv.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1, list));
				myDbHelper.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			tv2.setText("");
			lv.setAdapter(new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, res));
		}
	}
}
