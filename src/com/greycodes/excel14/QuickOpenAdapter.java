package com.greycodes.excel14;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class QuickOpenAdapter extends BaseAdapter {
	String[] ename,cat,venue,stime,duration;
	int[] level,hotness;
	Context context;
	LayoutInflater quickopenInflator;
	public QuickOpenAdapter(Context context,String[] ename,int[] level,String[] cat,String[] venue,String[] stime,String[] duration,int[] hotness ) {
		// TODO Auto-generated constructor stub
		this.ename = ename;
		this.level = level;
		this.context= context;
		this.cat = cat;
		this.venue = venue;
		this.stime = stime;
		this.duration = duration;
		this.hotness = hotness;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ename.length;
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
		ImageView icat,ihot;
		TextView scename,scroom,sclevel,scstime,scduration;
		quickopenInflator =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = quickopenInflator.inflate(R.layout.quickopen_listitem, parent, false);
		icat = (ImageView) itemView.findViewById(R.id.qocat);
		ihot =(ImageView) itemView.findViewById(R.id.hotness);
		scename = (TextView) itemView.findViewById(R.id.qoname);
		scroom = (TextView) itemView.findViewById(R.id.qoroom);
		sclevel = (TextView) itemView.findViewById(R.id.qolevel);
		scstime = (TextView) itemView.findViewById(R.id.qostime);
		scduration = (TextView) itemView.findViewById(R.id.qoduration);
		
		scename.setText(ename[position]);
		scroom.setText(venue[position]);
		if(level[position]==1){
			sclevel.setText("Prelims");
		}else
			sclevel.setText("Final");
		sclevel.setTypeface(null,Typeface.ITALIC);
		scstime.setText(stime[position]);
		scduration.setText(duration[position]);
		icat.setImageResource(R.drawable.comp_icons_cs);
		ihot.setImageResource(R.drawable.comp_icons_cs);
		return itemView;
	}
}

