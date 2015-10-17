package com.mobiletourismapp.activities;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class MyAdapter extends ArrayAdapter<Details>{
	private ArrayList<Details>detailsList;
	private Context mContext;
	

	public MyAdapter(Context mContext, ArrayList<Details>detailList) {
		super(mContext, R.layout.item_weatherdetails);
		this.detailsList=detailsList;
		this.mContext=mContext;
		
		
		
	}
@Override
public int getCount() {
	// TODO Auto-generated method stub
	return detailsList.size();
}

@Override
public View getView(int position, View convertView, ViewGroup parent) {
	// TODO Auto-generated method stub

LayoutInflater inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


View view = inflater.inflate(R.layout.item_weatherdetails, null);
Details details = detailsList.get(position);

return view;
}

}
	


