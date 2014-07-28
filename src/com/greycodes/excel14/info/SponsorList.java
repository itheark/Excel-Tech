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
ExcelDataBase excelDataBase;
String[] columns;
Cursor cursor;
LayoutInflater sponsorInflator;

	public SponsorList(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		excelDataBase = new ExcelDataBase(context);
		columns = new String[]{"PCODE","IMAGE","URL"};
		 
	SQLiteDatabase sqLiteDatabase=	  excelDataBase.getSQLiteDataBase();
	 cursor=	sqLiteDatabase.query("SPONSOR", columns, null, null, null, null, "PCODE DESC");
	cursor.moveToFirst();	
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return cursor.getCount();
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
		byte[] bs =	cursor.getBlob(cursor.getColumnIndex("IMAGE"));
		
		Bitmap bitmap = BitmapFactory.decodeByteArray(bs, 0,
		bs.length);
		sponsorImage.setImageBitmap(bitmap);
		cursor.moveToNext();
		
		return itemView;
	}

}
