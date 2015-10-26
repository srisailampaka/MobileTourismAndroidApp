/*
 * Copyright (C) 2011 The Android Open Source Project
 * Copyright (C) 2014 Zhenghong Wang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mobiletourismapp.activities;

import zh.wang.android.apis.yweathergetter4a.WeatherInfo;
import zh.wang.android.apis.yweathergetter4a.WeatherInfo.ForecastInfo;
import zh.wang.android.apis.yweathergetter4a.YahooWeather;
import zh.wang.android.apis.yweathergetter4a.YahooWeather.SEARCH_MODE;
import zh.wang.android.apis.yweathergetter4a.YahooWeather.UNIT;
import zh.wang.android.apis.yweathergetter4a.YahooWeatherExceptionListener;
import zh.wang.android.apis.yweathergetter4a.YahooWeatherInfoListener;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class WeatherInfoActivity extends Activity implements YahooWeatherInfoListener, YahooWeatherExceptionListener {

	private ImageView mIvWeather0;
	private TextView mTvWeather0;
	private TextView mTvErrorMessage;
	private TextView mTvTitle;
	private EditText mEtAreaOfCity;
	private Button mBtSearch;
	private Button mBtGPS;
	private LinearLayout mWeatherInfosLayout;
	private String address;
	private Button clothes;
	private YahooWeather mYahooWeather = YahooWeather.getInstance(5000, 5000, true);

	private ProgressDialog mProgressDialog;
	private String details;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather_info);

		mYahooWeather.setExceptionListener(this);

		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.show();
		if (getIntent() != null && getIntent().getExtras() != null) {
			address = getIntent().getExtras().getString("address");
		}
		mTvTitle = (TextView) findViewById(R.id.textview_title);
		mTvWeather0 = (TextView) findViewById(R.id.textview_weather_info_0);
		mTvErrorMessage = (TextView) findViewById(R.id.textview_error_message);
		mIvWeather0 = (ImageView) findViewById(R.id.imageview_weather_info_0);

		mEtAreaOfCity = (EditText) findViewById(R.id.edittext_area);

		mBtSearch = (Button) findViewById(R.id.search_weatherbutton);
		clothes = (Button) findViewById(R.id.clothes_button);
		clothes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(WeatherInfoActivity.this, BringClothesActivity.class);
				intent.putExtra("details", details);
				startActivity(intent);
			}
		});
		if (address == null) {
			searchByGPS();
		} else {
			searchByPlaceName(address.replace("%20", " "));
		}
		mBtSearch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String _location = mEtAreaOfCity.getText().toString();
				if (!TextUtils.isEmpty(_location)) {
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(mEtAreaOfCity.getWindowToken(), 0);
					searchByPlaceName(_location);
					showProgressDialog();
				} else {
					Toast.makeText(getApplicationContext(), "location is not inputted", 1).show();
				}
			}
		});

		/*
		 * mBtGPS = (Button) findViewById(R.id.gps_button);
		 * mBtGPS.setOnClickListener(new View.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { searchByGPS();
		 * showProgressDialog(); } });
		 */

		mWeatherInfosLayout = (LinearLayout) findViewById(R.id.weather_infos);

		// searchByGPS();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		hideProgressDialog();
		mProgressDialog = null;
		super.onDestroy();
	}

	@Override
	public void gotWeatherInfo(WeatherInfo weatherInfo) {
		// TODO Auto-generated method stub
		hideProgressDialog();
		if (weatherInfo != null) {
			setNormalLayout();
			if (mYahooWeather.getSearchMode() == SEARCH_MODE.GPS) {
				mEtAreaOfCity.setText("YOUR CURRENT LOCATION");
			}
			mWeatherInfosLayout.removeAllViews();
			details = weatherInfo.getCurrentText();
			mTvTitle.setText(weatherInfo.getTitle() + "\n" + weatherInfo.getWOEIDneighborhood() + ", "
					+ weatherInfo.getWOEIDCounty() + ", " + weatherInfo.getWOEIDState() + ", "
					+ weatherInfo.getWOEIDCountry());
			mTvWeather0.setText("====== CURRENT ======" + "\n" + "date: " + weatherInfo.getCurrentConditionDate() + "\n"
					+ "weather: " + weatherInfo.getCurrentText() + "\n" + "temperature in ºF: "
					+ convertToFahrenheit(weatherInfo.getCurrentTemp()) + "\n" + "wind chill: " + weatherInfo.getWindChill() + "\n"
					+ "wind direction: " + weatherInfo.getWindDirection() + "\n" + "wind speed: "
					+ weatherInfo.getWindSpeed() + "\n" + "Humidity: " + weatherInfo.getAtmosphereHumidity() + "\n"
					+ "Pressure: " + weatherInfo.getAtmospherePressure() + "\n" + "Visibility: "
					+ weatherInfo.getAtmosphereVisibility());
			if (weatherInfo.getCurrentConditionIcon() != null) {
				mIvWeather0.setImageBitmap(weatherInfo.getCurrentConditionIcon());
			}
			for (int i = 0; i < YahooWeather.FORECAST_INFO_MAX_SIZE; i++) {
				final LinearLayout forecastInfoLayout = (LinearLayout) getLayoutInflater()
						.inflate(R.layout.forecastinfo, null);
				final TextView tvWeather = (TextView) forecastInfoLayout.findViewById(R.id.textview_forecast_info);
				final ForecastInfo forecastInfo = weatherInfo.getForecastInfoList().get(i);
				tvWeather.setText("====== FORECAST " + (i + 1) + " ======" + "\n" + "date: "
						+ forecastInfo.getForecastDate() + "\n" + "weather: " + forecastInfo.getForecastText() + "\n"
						+ "low  temperature in ºF: " + convertToFahrenheit(forecastInfo.getForecastTempLow()) + "\n"
						+ "high temperature in ºF: " +convertToFahrenheit( forecastInfo.getForecastTempHigh()) + "\n"
				// "low temperature in ºF: " +
				// forecastInfo.getForecastTempLowF() + "\n" +
				// "high temperature in ºF: " +
				// forecastInfo.getForecastTempHighF() + "\n"
				);
				final ImageView ivForecast = (ImageView) forecastInfoLayout.findViewById(R.id.imageview_forecast_info);
				if (forecastInfo.getForecastConditionIcon() != null) {
					ivForecast.setImageBitmap(forecastInfo.getForecastConditionIcon());
				}
				mWeatherInfosLayout.addView(forecastInfoLayout);
			}
		} else {
			setNoResultLayout();
		}
	}

	private int convertToFahrenheit(int weatherInfo) {

		int fahrenheit = (int) ((9.0 / 5.0) *weatherInfo + 32);
		return fahrenheit;
	}

	@Override
	public void onFailConnection(final Exception e) {
		// TODO Auto-generated method stub
		setNoResultLayout();
		Toast.makeText(getApplicationContext(), "Fail Connection", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onFailParsing(final Exception e) {
		// TODO Auto-generated method stub
		setNoResultLayout();
		Toast.makeText(getApplicationContext(), "Fail Parsing", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onFailFindLocation(final Exception e) {
		// TODO Auto-generated method stub
		setNoResultLayout();
		Toast.makeText(getApplicationContext(), "Fail Find Location", Toast.LENGTH_SHORT).show();
	}

	private void setNormalLayout() {
		mWeatherInfosLayout.setVisibility(View.VISIBLE);
		mTvTitle.setVisibility(View.VISIBLE);
		mTvErrorMessage.setVisibility(View.INVISIBLE);
	}

	private void setNoResultLayout() {
		mTvTitle.setVisibility(View.INVISIBLE);
		mWeatherInfosLayout.setVisibility(View.INVISIBLE);
		mTvErrorMessage.setVisibility(View.VISIBLE);
		mTvErrorMessage.setText("Sorry, no result returned");
		mProgressDialog.cancel();
	}

	private void searchByGPS() {
		mYahooWeather.setNeedDownloadIcons(true);
		mYahooWeather.setUnit(UNIT.CELSIUS);
		mYahooWeather.setSearchMode(SEARCH_MODE.GPS);
		mYahooWeather.queryYahooWeatherByGPS(getApplicationContext(), this);
	}

	private void searchByPlaceName(String location) {
		mYahooWeather.setNeedDownloadIcons(true);
		mYahooWeather.setUnit(UNIT.CELSIUS);
		mYahooWeather.setSearchMode(SEARCH_MODE.PLACE_NAME);
		mYahooWeather.queryYahooWeatherByPlaceName(getApplicationContext(), location, WeatherInfoActivity.this);
	}

	private void showProgressDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.cancel();
		}
		mProgressDialog = new ProgressDialog(WeatherInfoActivity.this);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.show();
	}

	private void hideProgressDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.cancel();
		}
	}
}
