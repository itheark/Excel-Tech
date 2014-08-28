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

		
		Parse.initialize(this, "C51j5nzJGPzEey1ck1xplDyRgY3GDHoVuRhNdh2Q", "Qdf41XjsFJt687gtW9xXVvAPs14iYTNEhcY8aE8c");
		PushService.setDefaultPushCallback(this, HomeNDActivity.class);

		
		ParseUser.enableAutomaticUser();

		
		ParseACL defaultACL = new ParseACL();

		defaultACL.setPublicReadAccess(true);

		ParseACL.setDefaultACL(defaultACL, true);
		PushService.subscribe(this, "excel", HomeNDActivity.class, R.drawable.ic_launcher);
	}

}
