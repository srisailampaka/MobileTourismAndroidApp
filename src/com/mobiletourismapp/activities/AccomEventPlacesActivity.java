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

public class AccomEventPlacesActivity extends Activity implements ResponseHandler {
	private Button weatherButton;
	private ListView listview;
	private ProgressDialog progressDialog;
	private ArrayList<Tourism> tourismList = new ArrayList<Tourism>();
	private String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=17.3840500,78.4563600&radius=1000&types=hindu_temple&key=AIzaSyBoUm5FkU_OoADj6wzlKfdRy1NyLxT7Lv0";
private String type;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acc_event);
		if(getIntent()!=null&&getIntent().getExtras()!=null)
		{
			if(getIntent().getExtras().getString(type).equalsIgnoreCase("0"))
			{type="hotels";
			}else if(getIntent().getExtras().getString(type).equalsIgnoreCase("1"))
			{
			}
		}
		listview = (ListView) findViewById(R.id.list_view);
		(new GetResonseAsync(AccomEventPlacesActivity.this, this)).execute(url);

		
	}

	@Override
	public void onSuccess(Tourism list) {

		AccEventAdapter adapter = new AccEventAdapter(getApplicationContext(), list.getResults());
		listview.setAdapter(adapter);
	}

	@Override
	public void onFailure(String error) {

	}
}