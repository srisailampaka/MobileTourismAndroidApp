package com.mobiletourismapp.activities;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

//https://api.theprintful.com/countries
public class MainActivity extends Activity implements LocationListener {
	private Button currentLocationButton;
	private Button searchHistoryPlaceButton;
	private Spinner states, citys;
	private LocationManager locationManager;
	private Location currentLocation;
	private String provider;
	private ArrayList<States> statesList=new ArrayList<States>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		currentLocationButton = (Button) findViewById(R.id.cl_button);
		searchHistoryPlaceButton = (Button) findViewById(R.id.search_button);
		states = (Spinner) findViewById(R.id.spinner_state);
		citys = (Spinner) findViewById(R.id.spinner_city);
		ArrayAdapter<String> statesSpinnerArrayAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, getAllTheStates());
		statesSpinnerArrayAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		states.setAdapter(statesSpinnerArrayAdapter);

		ArrayAdapter<String> citiesSpinnerArrayAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, getResources()
						.getStringArray(R.array.Alabama));
		citiesSpinnerArrayAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		citys.setAdapter(citiesSpinnerArrayAdapter);
		// Getting LocationManager object
		locationManager = (LocationManager) getApplicationContext()
				.getSystemService(Context.LOCATION_SERVICE);

		// Creating an empty criteria object
		Criteria criteria = new Criteria();

		// Getting the name of the provider that meets the criteria
		provider = locationManager.getBestProvider(criteria, false);

		if (provider != null && !provider.equals("")) {

			// Get the location from the given provider
			Location location = locationManager.getLastKnownLocation(provider);

			locationManager.requestLocationUpdates(provider, 20000, 1, this);

			if (location != null)
				onLocationChanged(location);

		} else {
			Toast.makeText(getApplicationContext(), "No Provider Found",
					Toast.LENGTH_SHORT).show();
		}

		states.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				String url = "http://api.sba.gov/geodata/city_links_for_state_of/"
						+ statesList.get(position).getAbbreviation() + ".json";
				GetCities(url, position);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		citys.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		currentLocationButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						HistoricalPlacesActivity.class);
				intent.putExtra("current", currentLocation.getLatitude() + ","
						+ currentLocation.getLongitude());
				startActivity(intent);

			}
		});

		searchHistoryPlaceButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						HistoricalPlacesActivity.class);
				intent.putExtra("state", states.getSelectedItem().toString().replace(" ", "%20"));
				intent.putExtra("city", citys.getSelectedItem().toString().replace(" ", "%20"));
				startActivity(intent);

			}
		});

	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		currentLocation = location;
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	public ArrayList<String> getAllTheStates() {
		ArrayList<String> states = new ArrayList<String>();
		InputStream inputStream = getResources().openRawResource(R.raw.states);
		// InputStream inputStream =
		// getResources().openRawResource(R.raw.internals);
		System.out.println(inputStream);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		int i;
		try {
			i = inputStream.read();
			while (i != -1) {
				byteArrayOutputStream.write(i);
				i = inputStream.read();
			}
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String info = byteArrayOutputStream.toString();
			JSONArray josn = new JSONArray(info);
			for (int j = 0; j < josn.length(); j++) {
				JSONObject jsonObject = josn.getJSONObject(j);
				States state = new States();
				state.setName(jsonObject.getString("name"));
				state.setAbbreviation(jsonObject.getString("abbreviation"));
				statesList.add(state);
				states.add(jsonObject.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return states;
	}

	public void GetCities(String url, final int position) {

		LoopJHttpClient.get(url, new RequestParams(),
				new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(int statusCode, Header[] headers,
							JSONArray response) {
						List<Cities> cityList = null;
						if (statusCode == 200) {
							Gson googleJson = new Gson();

							cityList = googleJson.fromJson(response.toString(),
									new TypeToken<List<Cities>>() {
									}.getType());
						}
						ArrayList<String> cityName = new ArrayList<String>();
						for (int i = 0; i < cityList.size(); i++) {
							cityName.add(cityList.get(i).getName());
						}
						// TODO Auto-generated method stub
						ArrayAdapter<String> citiesSpinnerArrayAdapter = new ArrayAdapter<String>(
								MainActivity.this,
								android.R.layout.simple_spinner_item, cityName);
						citiesSpinnerArrayAdapter
								.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						citys.setAdapter(citiesSpinnerArrayAdapter);
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							Throwable throwable, JSONArray errorResponse) {
						// TODO Auto-generated method stub

					}

				});
	}
}

// TODO Auto-generated method stub

