package com.mobiletourismapp.activities;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
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
import android.widget.TextView;

public class AccomEventPlacesActivity extends Activity implements ResponseHandler {
	private Button weatherButton;
	private ListView listview;
	private ProgressDialog progressDialog;
	private ArrayList<Tourism> tourismList = new ArrayList<Tourism>();
	private String type;
	private String lattlang;
	private TextView noLocations;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acc_event);
		noLocations = (TextView) findViewById(R.id.no_details);
		if (getIntent() != null && getIntent().getExtras() != null) {
			lattlang = getIntent().getExtras().getString("accod");
			if (getIntent().getExtras().getString("from").equalsIgnoreCase("0")) {
				type = "hotel";
			} else if (getIntent().getExtras().getString("from").equalsIgnoreCase("1")) {
				type = "event";
			}
		}
		String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + lattlang
				+ "+&radius=50000&name=" + type + "&key=AIzaSyCCPYEQStKfydPLRx4SS139okRUDg_enO4";
		listview = (ListView) findViewById(R.id.list_view);
		(new GetResonseAsync(AccomEventPlacesActivity.this, this)).execute(url);

	}

	@Override
	public void onSuccess(String jsonStr) {
		Tourism tourismList = null;

		if (jsonStr != null) {
			try {
				JSONObject jsonObj = new JSONObject(jsonStr);

				Gson googleJson = new Gson();

				tourismList = googleJson.fromJson(jsonStr.toString(), Tourism.class);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			if (tourismList != null && tourismList.getResults().size() > 0) {

				noLocations.setVisibility(View.GONE);
				listview.setVisibility(View.VISIBLE);
				AccEventAdapter adapter = new AccEventAdapter(getApplicationContext(), tourismList.getResults());
				listview.setAdapter(adapter);
			} else {
				noLocations.setVisibility(View.VISIBLE);
				listview.setVisibility(View.GONE);
			}
		}
	}

	@Override
	public void onFailure(String error) {

	}
}