package com.greycodes.excel14.excelgallery;

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
import android.widget.TextView;

public class LiveGalleryAdapter extends  BaseAdapter{
	String[] desc,auth;
	byte[][] bs;
	Context context;
	LayoutInflater liveGalleryInflator;
	
	

	public LiveGalleryAdapter(Context context,String[] desc,byte[][] bs,String[] auth){
		this.context = context;
		this.desc=desc;
		this.bs= bs;
		this.auth = auth;
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return desc.length;
	//	return cursor.getCount();
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
		TextView tvdesc,tvauth;
		liveGalleryInflator =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = liveGalleryInflator.inflate(R.layout.livegallery_listitem, parent, false);
		imgIcon=(ImageView) itemView.findViewById(R.id.livegallery_image);
		tvdesc = (TextView) itemView.findViewById(R.id.livegallery_desc);
		tvauth = (TextView) itemView.findViewById(R.id.livegallery_auth);
		Bitmap bitmap = BitmapFactory.decodeByteArray(bs[position], 0,
		bs[position].length);
		imgIcon.setImageBitmap(bitmap);
		tvdesc.setText(desc[position]);
		tvauth.setText(auth[position]);
		
		return itemView;
	}

}
