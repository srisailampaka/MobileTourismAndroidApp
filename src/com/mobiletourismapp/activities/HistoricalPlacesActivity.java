package com.mobiletourismapp.activities;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class HistoricalPlacesActivity extends Activity implements ResponseHandler{
	private Button weatherButton;
	private ListView listview;
	private ProgressDialog progressDialog;
	private ArrayList<Tourism> tourismList = new ArrayList<Tourism>();
	private String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=17.3840500,78.4563600&radius=1000&types=hindu_temple&key=AIzaSyBoUm5FkU_OoADj6wzlKfdRy1NyLxT7Lv0";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weatherdetails);
		weatherButton = (Button) findViewById(R.id.weather_button);
		listview = (ListView) findViewById(R.id.list_view);
		(new GetResonseAsync(HistoricalPlacesActivity.this,this)).execute(url);

		
				
			
		
	

		weatherButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent intent=new Intent(HistoricalPlacesActivity.this,WeatherInfoActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public void onSuccess(Tourism list) {
		
		TourismAdapter adapter = new TourismAdapter(HistoricalPlacesActivity.this, list.getResults());
		listview.setAdapter(adapter);
	}

	@Override
	public void onFailure(String error) {
		
	}
}