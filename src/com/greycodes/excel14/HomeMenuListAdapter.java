package com.greycodes.excel14;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class HomeMenuListAdapter extends BaseAdapter {
Context context;
int[] homeoptionicon;
LayoutInflater homeinflator;

public HomeMenuListAdapter(Context context, int[] icon) {
    this.context = context;
    this.homeoptionicon = icon;
}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return homeoptionicon.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView imgIcon;
		homeinflator=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = homeinflator.inflate(R.layout.home_drawer_listitem, parent, false);
		imgIcon=(ImageView) itemView.findViewById(R.id.optionicon);
		imgIcon.setImageResource(homeoptionicon[position]);
		
		return itemView;
	}

}
