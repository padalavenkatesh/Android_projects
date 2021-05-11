/*package com.example.dbtest;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.os.Build;

public class MainActivity extends Activity {

	Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addListenerOnButton();
		
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	public void addListenerOnButton() {
		 
		final Context context = this;
 
	button = (Button) findViewById(R.id.btn_login);
 
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			    Intent intent = new Intent(context, DBAdapter.class);
                            startActivity(intent);   
 
			}
 
		});
 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

 *//**
 * A placeholder fragment containing a simple view.
 */
/*
 public static class PlaceholderFragment extends Fragment {

 public PlaceholderFragment() {
 }

 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container,
 Bundle savedInstanceState) {
 View rootView = inflater.inflate(R.layout.fragment_main, container,
 false);
 return rootView;
 }

 }


 */

package com.example.dbtest;

import org.ksoap2.*;
import org.ksoap2.serialization.*;
import org.ksoap2.transport.*;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	//public static String SOAPACTION = "http://DefaultNamespace";
	public static String SOAPACTION = "hrmsService";
	public static String NAMESPACE = "http://DefaultNamespace";
	public static String URL = "http://localhost:8080/HRMS_DB_SERVICE/services/Hrms_Db_Service?wsdl";
	public static String METHODNAME = "hrmsService";

	Button button;
	EditText FirstName,LastName;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		/******************* Intialize Database *************/
		DBAdapter.init(this);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addListenerOnButton();

		// Inserting Contacts

	}

	private void addListenerOnButton() {
		// TODO Auto-generated method stub

		final Context context = this;

		button = (Button) findViewById(R.id.btn_login);
		/*FirstName=(EditText)findViewById(R.id.FirstName);
		LastName=(EditText)findViewById(R.id.LastName);*/

		button.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				/*
				 * Log.d("Insert: ", "Inserting .."); DBAdapter.addDBGETSET(new
				 * DBGETSET("FirstName", "LastName")); DBAdapter.addDBGETSET(new
				 * DBGETSET("Srinivas", "9199999999"));
				 * DBAdapter.addDBGETSET(new DBGETSET("Tommy", "9522222222"));
				 * DBAdapter.addDBGETSET(new DBGETSET("Karthik", "9533333333"));
				 * 
				 * // Reading all contacts Log.d("Reading: ",
				 * "Reading all contacts.."); List<DBGETSET> data =
				 * DBAdapter.getAllUserData(); for (DBGETSET dt : data) { String
				 * log = "Id: " + dt.getID() + " ,Name: " + dt.getName() +
				 * " ,Phone: " + dt.getEmail(); // Writing Contacts to log
				 * System.out.println(dt.getID()); Log.d("Name: ", log); }
				 * 
				 * }
				 * 
				 * });
				 */
				// Initialize soap request + add parameters
			
				SoapObject request = new SoapObject(NAMESPACE,METHODNAME);
                
				
				
				
				// Use this to add parameters
				request.addProperty("FirstName", FirstName);
				request.addProperty("LastName", LastName);
				
				
				
				System.out.println("helllo print me");
				// Declare the version of the SOAP request
				System.out.println(request);

				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
						SoapEnvelope.VER11);
				envelope.setOutputSoapObject(request);

				envelope.dotNet = true;

				try {

					HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

					// this is the actual part that will call the webservice

					androidHttpTransport.call(SOAPACTION, envelope);

					// Get the SoapResult from the envelope body.

					SoapObject result = (SoapObject) envelope.bodyIn;

					/*if (result != null)

					{

						// Get the first property and change the label text

						LastName.setText(result.getProperty(0).toString());

					}

					else

					{

						Toast.makeText(getApplicationContext(), "No Response",
								Toast.LENGTH_LONG).show();

					}*/

				} catch (Exception e) {

					e.printStackTrace();

				}

			}

		});
	}
}