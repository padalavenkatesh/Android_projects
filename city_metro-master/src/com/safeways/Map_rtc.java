package com.safeways;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class Map_rtc extends MapActivity {
	MapView mapview;
	Uri uri;

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.map_rtc);
		mapview = (MapView) findViewById(R.id.mapdisplay);
uri = Uri.parse("http://maps.google.com/maps?&saddr=17.388036,78.485967&daddr=17.484335,78.549482");
		//uri = Uri.parse("http://maps.google.com/maps?&saddr=&daddr=");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
		finish();
	}

}
