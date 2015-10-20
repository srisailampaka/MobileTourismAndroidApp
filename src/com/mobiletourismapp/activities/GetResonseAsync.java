package com.mobiletourismapp.activities;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
public class GetResonseAsync extends AsyncTask<String, Void,String> {
private ProgressDialog pDialog;
private Context mContext;
private ResponseHandler handler;
	public GetResonseAsync(Context mContext,ResponseHandler handler) {
	this.mContext=mContext;
	this.handler=handler;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pDialog = new ProgressDialog(mContext	);
		pDialog.setMessage("Please wait...");
		pDialog.setCancelable(false);
		pDialog.show();
	}

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		ServiceHandler sh = new ServiceHandler();
		Tourism	tourismList = null ;
		 
        // Making a request to url and getting response
        String jsonStr = sh.makeServiceCall(params[0], ServiceHandler.GET);

        Log.d("Response: ", "> " + jsonStr);

        
		return jsonStr;

	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		pDialog.dismiss();
		handler.onSuccess(result);
	}

}
