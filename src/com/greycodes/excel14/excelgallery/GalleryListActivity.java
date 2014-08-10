package com.greycodes.excel14.excelgallery;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;

import com.actionbarsherlock.app.ActionBar;
import com.greycodes.excel14.R;
import com.greycodes.excel14.database.ExcelDataBase;
import com.greycodes.excel14.login.AccountFragment;

public class GalleryListActivity extends ListActivity {
	LiveGalleryAdapter adapter;
	String[] desc,author,columns;
	byte[][] bs;
	Cursor cursor;
	ExcelDataBase excelDataBase;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.excellive_activity);
		android.app.ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0e1215")));
        bar.setTitle(Html.fromHtml("<font color=\"#e6f3ea\">" + getString(R.string.app_name) + "</font>"));
      /*  Fragment f;
		FragmentManager fragmentManager ;
		FragmentTransaction transaction;
		fragmentManager = getSupportFragmentManager();
		 transaction=fragmentManager.beginTransaction();
		 f = new LiveGalleryList();
		 transaction.replace(R.id.excellive_content_frame,f);
			// Add this transaction to the back stack
           
           transaction.commit();*/
        
        
        excelDataBase = new ExcelDataBase(getApplicationContext());
    	columns = new String[]{"DESC","IMAGE","AUTHOR"};
    	 
    	SQLiteDatabase sqLiteDatabase=	  excelDataBase.getSQLiteDataBase();
    	 cursor=	sqLiteDatabase.query("GALLERY", columns, null, null, null, null, "GID DESC");
    	 cursor.moveToFirst();
    	 
    author = new String[cursor.getCount()];
    desc = new String[cursor.getCount()];
    bs = new byte[cursor.getCount()][];
    for(int i=0;i<cursor.getCount();i++,cursor.moveToNext()){
    	desc[i]= cursor.getString(cursor.getColumnIndex("AUTHOR"));
    	desc[i]= desc[i]+" : "+ cursor.getString(cursor.getColumnIndex("DESC"));
    	bs[i]=cursor.getBlob(cursor.getColumnIndex("IMAGE"));
    }
    	
    	adapter = new LiveGalleryAdapter(this,desc,bs);
    	setListAdapter(adapter);

		// Subclass of ParseQueryAdapter
		
		
		

		
		// Default view is all meals
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_meal_list, menu);
		return true;
	}

	/*
	 * Posting meals and refreshing the list will be controlled from the Action
	 * Bar.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		

		

		case R.id.action_new: {
			newMeal();
			break;
		}
		}
		return super.onOptionsItemSelected(item);
	}

	

	

	private void newMeal() {
		Intent i = new Intent(this, NewImageActivity.class);
		startActivityForResult(i, 0);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			// If a new post has been added, update
			// the list of posts
			
		}
	}

}
