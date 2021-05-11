package com.safeways;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Display extends Activity {
	String value1, value2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display);
		TextView tv1 = (TextView) findViewById(R.id.busName);
		TextView tv2 = (TextView) findViewById(R.id.noofStages);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			value1 = extras.getString("re");
			value2 = extras.getString("re2");
		}
		tv1.setText(value1);
		tv2.setText(value2);
	}
}