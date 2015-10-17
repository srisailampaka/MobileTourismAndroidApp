package com.mobiletourismapp.activities;

import java.util.ArrayList;
import java.util.List;

import com.mobiletourismapp.activities.Tourism.Result;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AccEventAdapter extends ArrayAdapter<Result> {
	private ArrayList<Result> detailsList;
	private Context mContext;

	public AccEventAdapter(Context mContext, List<Result> detailList) {
		super(mContext, R.layout.item_acc_event);
		this.detailsList = (ArrayList<Result>) detailList;
		this.mContext = mContext;

	}

	@Override
	public int getCount() {
		return detailsList.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View view = inflater.inflate(R.layout.item_acc_event, null);
		Result details = detailsList.get(position);
		ImageView icon = (ImageView) view.findViewById(R.id.ts_pic);
		TextView name = (TextView) view.findViewById(R.id.name);
		TextView vicinicity = (TextView) view.findViewById(R.id.vicinicity_address);
		name.setText(details.getName());

		vicinicity.setText(details.getVicinity());
		Picasso.with(mContext).load(details.getIcon())

		.fit().into(icon);

		return view;
	}

}
