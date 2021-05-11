package com.safeways;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class Start extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.start);
		ImageButton b1 = (ImageButton) findViewById(R.id.imageButton1);
		b1.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v1) {
				Intent in1 = new Intent(Start.this, Search.class);
				startActivity(in1);
			}
		});
		ImageButton b2 = (ImageButton) findViewById(R.id.imageButton2);
		b2.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v2) {
				Intent in2 = new Intent(Start.this, Station.class);
				startActivity(in2);
			}
		});
		ImageButton b3 = (ImageButton) findViewById(R.id.imageButton3);
		b3.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v3) {
				Intent in3 = new Intent(Start.this, Map_rtc.class);
				startActivity(in3);
			}

		});
		ImageButton b4 = (ImageButton) findViewById(R.id.imageButton4);
		b4.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v4) {
				Intent in4 = new Intent(Start.this, Info.class);
				startActivity(in4);
			}

		});

	}

}
