package com.greycodes.excel14.info;

import com.greycodes.excel14.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ScheduleAdapter extends BaseAdapter {
String[] ename,cat,venue,stime,duration,time;
int[] level;
Context context;
LayoutInflater scheduleInflator;
	public ScheduleAdapter(Context context,String[] ename,int[] level,String[] cat,String[] venue,String[] stime,String[] duration,String[] time ) {
		// TODO Auto-generated constructor stub
		this.ename = ename;
		this.level = level;
		this.context= context;
		this.cat = cat;
		this.venue = venue;
		this.stime = stime;
		this.duration = duration;
		this.time = time;
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
		ImageView icat;
		TextView scename,scroom,sclevel,scstime,scduration;
		scheduleInflator =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = scheduleInflator.inflate(R.layout.schedule_listitem, parent, false);
		icat = (ImageView) itemView.findViewById(R.id.sccat);
		scename = (TextView) itemView.findViewById(R.id.scename);
		scroom = (TextView) itemView.findViewById(R.id.scroom);
		sclevel = (TextView) itemView.findViewById(R.id.sclevel);
		scstime = (TextView) itemView.findViewById(R.id.scstime);
		scduration = (TextView) itemView.findViewById(R.id.scduration);
		
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
		return itemView;
	}
}
