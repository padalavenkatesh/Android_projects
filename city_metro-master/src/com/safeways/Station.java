package com.safeways;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Station extends Activity {
	String data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.station);
		ImageButton b1 = (ImageButton) findViewById(R.id.getstage);
		b1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				TextView tv1 = (TextView) findViewById(R.id.editText1);
				data = tv1.getText().toString();
				String data1 = data.toUpperCase();
				if (data1.length() == 0) {
					Toast.makeText(Station.this, "please enter a root",
							Toast.LENGTH_LONG).show();
				} else {

					Intent i = new Intent(Station.this, Display1.class);
					Bundle ex = new Bundle();
					ex.putString("re", data1);
					i.putExtras(ex);
					startActivityForResult(i, 1);
				}
			}
		});
	}
}