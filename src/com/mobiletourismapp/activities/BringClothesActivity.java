package com.mobiletourismapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class BringClothesActivity extends  Activity{
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clothes);
		WebView wv = (WebView) findViewById(R.id.webView1);
	   
	    
	    wv.loadUrl("file:///android_asset/cold.html");
		
	}
}
