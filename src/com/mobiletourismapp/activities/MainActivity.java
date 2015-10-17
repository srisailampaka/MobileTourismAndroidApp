package com.mobiletourismapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
//https://api.theprintful.com/countries
public class MainActivity extends Activity {
	private Button currentLocationButton;
	private Button searchHistoryPlaceButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	currentLocationButton=(Button)findViewById(R.id.cl_button);
	searchHistoryPlaceButton=(Button)findViewById(R.id.search_button);
	currentLocationButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity.this,HistoricalPlacesActivity.class);
			startActivity(intent);
			
		}
	});
	

searchHistoryPlaceButton.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(MainActivity.this,HistoricalPlacesActivity.class);
		startActivity(intent);
		
	}
});

	
	
	}
	
	
}
