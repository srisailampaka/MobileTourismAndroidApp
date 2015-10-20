package com.mobiletourismapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SplashActivity extends Activity{

	
		// TODO Auto-generated constructor stub
private Button nextButton;
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_splash);

	nextButton = (Button) findViewById(R.id.next_button);
	nextButton.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(SplashActivity.this,MainActivity.class);
			startActivity(intent);	

		}
	});




}

}
