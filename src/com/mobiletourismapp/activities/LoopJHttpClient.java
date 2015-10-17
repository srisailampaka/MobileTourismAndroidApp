package com.mobiletourismapp.activities;

import org.apache.http.HttpVersion;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.params.CoreProtocolPNames;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class LoopJHttpClient {
	public static int TIME_OUT_CONNECTION = 10000;
	 public static int TIME_OUT_SOCKET = 30000;
	 private static AsyncHttpClient client = new AsyncHttpClient();
	
	static {
		 client.setTimeout(TIME_OUT_SOCKET);
		 client.setConnectTimeout(TIME_OUT_CONNECTION);
		 client.getHttpClient().getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, false);
		 client.getHttpClient().getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, false);
		 client.getHttpClient().getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
	 }

	  public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
	      client.get(url, params, responseHandler);
	  }

	  public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
	      client.post(url, params, responseHandler);
	       
	  }
	  public static void put(String url,RequestParams params, AsyncHttpResponseHandler responseHandler) {
		  client.put(null, url, null, "application/json", responseHandler);
	    }

	 }

