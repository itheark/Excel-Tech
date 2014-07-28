package com.greycodes.excel14;

import android.app.Application;

import com.greycodes.excel14.excelgallery.Gallery;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.PushService;

public class Excel14Application extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		ParseObject.registerSubclass(Gallery.class);

		
		Parse.initialize(this, "i8yPBMBNwy08qVPPrgLFswlvbYOv3LQXeebDl49a", "Hdvc9dDQNlSmFHOTRr1PeuOvxYB99wFTeWFAeXKp");
		PushService.setDefaultPushCallback(this, HomeNDActivity.class);

		
		ParseUser.enableAutomaticUser();

		
		ParseACL defaultACL = new ParseACL();

		defaultACL.setPublicReadAccess(true);

		ParseACL.setDefaultACL(defaultACL, true);
	}

}
