package com.mobiletourismapp.activities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

public class HistoricalPlacesActivity extends Activity implements
		ResponseHandler {
	private Button weatherButton;
	private ListView listview;
	private ProgressDialog progressDialog;
	private ArrayList<Tourism> tourismList = new ArrayList<Tourism>();
	//private String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=17.3840500,78.4563600&radius=1000&types=hindu_temple&key=AIzaSyBoUm5FkU_OoADj6wzlKfdRy1NyLxT7Lv0";
	private String address;
	String addressUrl;
	private boolean historical;
	private LocationManager locationManager;
	private Location currentLocation;
	private String provider;
	private boolean current, first;
	String state, city;
	private String currrentlttlang;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weatherdetails);
		weatherButton = (Button) findViewById(R.id.weather_button);
		listview = (ListView) findViewById(R.id.list_view);
		if (getIntent() != null && getIntent().getExtras() != null)
		{	state = getIntent().getExtras().getString("state");
		city = getIntent().getExtras().getString("city");
		currrentlttlang=getIntent().getExtras().getString("current");
		}
		
		if (currrentlttlang==null) {
			address = city + "," + state;
			addressUrl = "http://maps.googleapis.com/maps/api/geocode/json?address="
					+ address + "+CA&sensor=false";
			(new GetResonseAsync(HistoricalPlacesActivity.this, this))
					.execute(addressUrl);
		}else
		{
			historical = true;
			String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="
					+ currrentlttlang
					+ "&radius=500000&types=museum&key=AIzaSyCCPYEQStKfydPLRx4SS139okRUDg_enO4";
			(new GetResonseAsync(HistoricalPlacesActivity.this, this))
					.execute(url);
		}

		weatherButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(HistoricalPlacesActivity.this,
						WeatherInfoActivity.class);
				intent.putExtra("address", address);
				startActivity(intent);
			}
		});

	}

	@Override
	public void onSuccess(String jsonStr) {
		if (!historical) {
			String lattlang = "17.3840500,78.4563600";
			try {
				JSONObject json = new JSONObject(jsonStr);
				JSONArray childJSon = json.getJSONArray("results");
				JSONObject valueJson = childJSon.getJSONObject(0);
				JSONObject geometryJSon = valueJson.getJSONObject("geometry");
				JSONObject locationJSon = geometryJSon
						.getJSONObject("location");
				Double latt = locationJSon.getDouble("lat");
				Double lang = locationJSon.getDouble("lng");
				lattlang = latt+","+lang ;
				currrentlttlang=lattlang;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			historical = true;
			String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="
					+ lattlang
					+ "&radius=500000&types=mesum&key=AIzaSyCCPYEQStKfydPLRx4SS139okRUDg_enO4";
			(new GetResonseAsync(HistoricalPlacesActivity.this, this))
					.execute(url);
		} else {
			Tourism tourismList = null;

			if (jsonStr != null) {
				try {
					JSONObject jsonObj = new JSONObject(jsonStr);

					Gson googleJson = new Gson();

					tourismList = googleJson.fromJson(jsonStr.toString(),
							Tourism.class);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				TourismAdapter adapter = new TourismAdapter(
						HistoricalPlacesActivity.this, tourismList.getResults(),currrentlttlang);
				listview.setAdapter(adapter);
			}
		}
	}

	@Override
	public void onFailure(String error) {

	}


}