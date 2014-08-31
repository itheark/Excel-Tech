package com.greycodes.excel14.info;

import java.io.ByteArrayOutputStream;

import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greycodes.excel14.R;
import com.greycodes.excel14.R.drawable;
import com.greycodes.excel14.database.ExcelDataBase;
import com.greycodes.excel14.database.ImageDownloader;
import com.greycodes.excel14.database.ParseSponsor;

public class SponsorFragment extends ListFragment  {
	
	SponsorList sponsorList;
	
	String url,results;
	
	String[] imageurl,companyurl;
	int[] sid,pcode;
	int n;
	byte[][] imagebyte,bs;
	 int sponsor_flag;
	ExcelDataBase excelDataBase;
	ImageDownloader imageDownloader;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.info_sponsor, container, false);
		 
		
					bs = new byte[8][];
					
					int drawabale[] = new int[]{R.drawable.sponsor7,R.drawable.ebay,R.drawable.sponsor_eben,R.drawable.sponsor2,
							R.drawable.sponsor3,R.drawable.sponsor4,R.drawable.cobot,R.drawable.sponsor6};
					
							
					Drawable[] d = new Drawable[8];
					for(int i=0;i<8;i++){
						
						d[i] = getResources().getDrawable(drawabale[i]);
					}
					
					
					for (int i = 0; i <8; i++) {
						Bitmap bitmap = ((BitmapDrawable)d[i]).getBitmap();
						ByteArrayOutputStream stream = new ByteArrayOutputStream();
						bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
						bs[i] = stream.toByteArray();
					}
				
				
			
			sponsorList = new SponsorList(getActivity(),bs);
			setListAdapter(sponsorList);
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
	}


	
	
	
	

	
	
/*

	public void assign(){
		ExcelDataBase excelDataBase= new ExcelDataBase(getActivity());
		SQLiteDatabase sqLiteDatabase=	  excelDataBase.getSQLiteDataBase();
		String[] columns = new String[]{"PCODE","IMAGE","URL"};
		Cursor cursor=	sqLiteDatabase.query("SPONSOR", columns, null, null, null, null, "PCODE DESC");
		if (cursor.getCount()!=0) {
			cursor.moveToFirst();
			bs = new byte[cursor.getCount()][];
			for (int i = 0; i < cursor.getCount(); i++, cursor.moveToNext()) {

				bs[i] = cursor.getBlob(cursor.getColumnIndex("IMAGE"));
			}
		}else{
			try {
				bs = new byte[7][];
				
				int drawabale[] = new int[]{R.drawable.sponsor_eben,R.drawable.sponsor2,
						R.drawable.sponsor3,R.drawable.sponsor4,R.drawable.sponsor5,R.drawable.sponsor6};
				
						
				Drawable[] d = new Drawable[7];
				for(int i=0;i<7;i++){
					
					d[i] = getResources().getDrawable(drawabale[i]);
				}
				
				
				for (int i = 0; i < cursor.getCount(); i++, cursor.moveToNext()) {
					Bitmap bitmap = ((BitmapDrawable)d[i]).getBitmap();
					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
					bs[i] = stream.toByteArray();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
*/

}
