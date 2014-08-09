package com.greycodes.excel14;

import com.greycodes.excel14.csevents.FragmentHashInclude;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Misc {
Context context;
	public Misc(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public void call(String number){
		Intent intent = new Intent(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:"+number));
		context.startActivity(intent);
	}
	
	public void settext(int eid,String text){
		switch(eid){
		case 100:
			FragmentHashInclude.tv.setText(text);
			break;
		}
	}
}
