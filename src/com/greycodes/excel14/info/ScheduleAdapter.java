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
String[] ename,venue,stime,duration,time;
int[] level,cat;
Context context;
LayoutInflater scheduleInflator;
	public ScheduleAdapter(Context context,String[] ename,int[] level,int[] cat,String[] venue,String[] stime,String[] duration,String[] time ) {
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
		scename.setTypeface(null, Typeface.BOLD_ITALIC);
		scroom.setText(venue[position]);
		
		
		scstime.setText(stime[position]);
		scstime.setTypeface(null, Typeface.BOLD);
		scduration.setText(duration[position]);
		if(level[position]==1){
			sclevel.setText("Prelims");
		}else if(level[position]==2){
			sclevel.setText("Final");
		}else {
			sclevel.setVisibility(TextView.INVISIBLE);
		}
		sclevel.setTypeface(null,Typeface.ITALIC);
		switch (cat[position]) {
		case 1:
			icat.setImageResource(R.drawable.nfc);
			break;
		case 2:
			icat.setImageResource(R.drawable.nfec);
			break;
		case 3:
			icat.setImageResource(R.drawable.nfee);
			break;
		case 4:
			icat.setImageResource(R.drawable.nfr);
			break;
		case 5:
			icat.setImageResource(R.drawable.nfb);
			break;
		case 6:
			icat.setImageResource(R.drawable.nfg);
			break;
		case 7:
			icat.setImageResource(R.drawable.nfn);
			break;
		case 8:
			icat.setImageResource(R.drawable.nflogo);
			break;
			

		default:
			icat.setImageResource(R.drawable.nflogo);
			break;
		}
		
		return itemView;
	}
}
