package com.greycodes.excel14.info;

import com.greycodes.excel14.R;
import com.greycodes.excel14.database.ExcelDataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class SponsorList extends BaseAdapter {
Context context;
byte[][] bs;
LayoutInflater sponsorInflator;

	public SponsorList(Context context,byte[][] bs) {
		// TODO Auto-generated constructor stub
		this.context = context;
		
		this.bs= bs;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return bs.length;
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
		ImageView sponsorImage;
		sponsorInflator=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = sponsorInflator.inflate(R.layout.sponsor_listview_items, parent, false);
		sponsorImage=(ImageView) itemView.findViewById(R.id.sponsor_image);
		
		
		Bitmap bitmap = BitmapFactory.decodeByteArray(bs[position], 0,
		bs[position].length);
		sponsorImage.setImageBitmap(bitmap);
		
		
		return itemView;
	}

}
