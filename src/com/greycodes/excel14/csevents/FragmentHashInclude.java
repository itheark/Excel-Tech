package com.greycodes.excel14.csevents;





import java.text.SimpleDateFormat;
import java.util.Date;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.greycodes.excel14.R;
import com.greycodes.excel14.database.ExcelDataBase;


public class FragmentHashInclude extends SherlockFragment implements OnClickListener {
/*TextView display,intro,rules,format;
String intro_text,rules_text,format_text,results,url;
ExcelDataBase edb;
String[] columns;
Cursor cursor;*/
	
	 ImageView intro,rules,format;
	 
	  static TextView tv;
      
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.cs_hash_include, container, false);
		 tv=(TextView)rootView.findViewById(R.id.txtinclude);
		 intro=(ImageView)rootView.findViewById(R.id.intro);
		 rules =(ImageView)rootView.findViewById(R.id.rules);
		 format =(ImageView)rootView.findViewById(R.id.event);
		 
		 intro.setOnClickListener(this);
		 rules.setOnClickListener(this);
		 format.setOnClickListener(this);
		 
		 /* TextView t= (TextView) rootView.findViewById(R.id.textView2);
		
		 final Context context = getActivity();
		
		t.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 setalarm(context);
			}
		});
		*/
		 /*	
		 edb = new ExcelDataBase(getActivity());
		 SQLiteDatabase db = edb.getSQLiteDataBase();
		 columns = new String[]{"INTRO","FORMAT","RULES","DATE"};
 cursor=	 db.query("COMPETITION", columns, "EID = 01", null, null, null, null);
		
    cursor.moveToFirst();
    
		display = (TextView) rootView.findViewById(R.id.hashinclude_diplay);
		intro = (TextView) rootView.findViewById(R.id.hashinclude_intro);
		rules = (TextView) rootView.findViewById(R.id.hashinclude_rules);
		format = (TextView) rootView.findViewById(R.id.hashinclude_format);
		display.setText(cursor.getString(cursor.getColumnIndex("INTRO")));
		display.setText("hash include");
		intro.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				display.setText(cursor.getString(cursor.getColumnIndex("INTRO")));
			}
		});
		
rules.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				display.setText(cursor.getString(cursor.getColumnIndex("RULES")));
				//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//String currentDateandTime = sdf.format(new Date());
				
				//display.setText(cursor.getString(cursor.getColumnIndex("DATE")));

			}
		});	
format.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		display.setText(cursor.getString(cursor.getColumnIndex("FORMAT")));


	}
});
		
		
	
	
	
	*/
		 
		 
		return rootView;
	}
	
	


@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	
	
	if(v.equals(intro))
	{
		updateTextValue("The lines have been drawn, sides have been taken. The war is to begin soon. If you are think you have what it takes to prove your C programming mettle, join the war and fight for glory. Battle with other coders and emerge as the champion of C. All it takes is perseverance and grit\n\n" );
		
	}
	if (v.equals(rules))
	{
		updateTextValue("");
		
	}
	
	if (v.equals(format))
	{
		updateTextValue("This is an online individual event that comprises of a total of 3 levels and will begin 6 days before Excel. Each participant can attempt questions from any of the 3 levels at the same time.\n\n");
		
	}
	
	
	
	
}

		
	
	

public static void updateTextValue(CharSequence newText) {
    tv.setText(newText);
	}
}




/*
	public void setalarm(Context context) {
		// TODO Auto-generated method stub
		
		
		// TODO Auto-generated method stub
		
		
		Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.clear();
        cal.set(2014,1,18,18,10);//SET TIME HERE
        Calendar cal_now =Calendar.getInstance();
        if( cal.compareTo(cal_now) == -1)
        {
        	 Toast.makeText(context, "Sorry.Event Over :P",
           		   Toast.LENGTH_LONG).show();
        	
        	
        }
        else
        {
 
        //Create a new PendingIntent and add it to the AlarmManager
       try
       {
    	   Intent intent = new Intent(context, AlarmReceiverActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,
            12345, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = 
            (AlarmManager)context.getSystemService(Activity.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                pendingIntent);
       
        Toast.makeText(context, "Alarm Set",
     		   Toast.LENGTH_LONG).show();
        
       }
       catch(Exception e)
       
       {

           Toast.makeText(context, "Alarm  NOT Set",
            Toast.LENGTH_LONG).show();
    	   
       }
        }
		
	}	*/

