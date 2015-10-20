package com.mobiletourismapp.activities;

import java.util.ArrayList;
import java.util.List;

import com.mobiletourismapp.activities.Tourism.Result;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TourismAdapter extends ArrayAdapter<Result> {
	private ArrayList<Result> detailsList;
	private Context mContext;
	private String  current;

	public TourismAdapter(Context mContext, List<Result> detailList, String currrentlttlang) {
		super(mContext, R.layout.activity_tourist_spot);
		this.detailsList = (ArrayList<Result>) detailList;
		this.mContext = mContext;
		current=currrentlttlang;

	}

	@Override
	public int getCount() {
		return detailsList.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View view = inflater.inflate(R.layout.activity_tourist_spot, null);
		Result details = detailsList.get(position);
		ImageView icon = (ImageView) view.findViewById(R.id.ts_pic);
		TextView name = (TextView) view.findViewById(R.id.name);
		TextView vicinicity = (TextView) view.findViewById(R.id.vicinicity_address);
		Button accomodation = (Button) view.findViewById(R.id.accomodation_button);
		Button event = (Button) view.findViewById(R.id.event_button);
		name.setText(details.getName());
		accomodation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
Intent intent=new Intent(mContext,AccomEventPlacesActivity.class);
intent.putExtra("from", "0");
intent.putExtra("accod", current);
mContext.startActivity(intent);
			}
		});
		event.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(mContext,AccomEventPlacesActivity.class);
				intent.putExtra("from", "1");
				intent.putExtra("accod", current);
				mContext.startActivity(intent);
			}
		});
		vicinicity.setText(details.getVicinity());
		Picasso.with(mContext).load(details.getIcon())

		.fit().into(icon);

		return view;
	}

}
