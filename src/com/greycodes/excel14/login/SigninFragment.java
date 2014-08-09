package com.greycodes.excel14.login;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.facebook.android.Util;
import com.facebook.android.Facebook.DialogListener;
import com.greycodes.excel14.R;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class SigninFragment extends Fragment implements OnClickListener {
	
	SharedPreferences sp;
	Facebook fb;
	
	ImageView connectfb,signup;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.user_registration_activity, container, false);
		 
		 
		 
		 
		 connectfb= (ImageView)rootView.findViewById(R.id.fbconnect);
			signup= (ImageView)rootView.findViewById(R.id.signup);
			
		 
		 
		 
			connectfb.setOnClickListener(this);
		 
			update();	
		 
		 
		 
		 
		 
		return rootView;
	}
	private void update() {
		// TODO Auto-generated method stub
		
		if(fb.isSessionValid())
		{
			
			JSONObject obj=null;
				
				
				try {
					String jsonUser =fb.request("me");
					obj = Util.parseJson(jsonUser);
					String id =obj.optString("id");
					Toast.makeText(getActivity(),id,Toast.LENGTH_SHORT).show();				
					
					
					
				} catch (FacebookError e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			
		
		
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		
		if(v.equals(connectfb)){	
			if (fb.isSessionValid())
			{
			}
			else 
			{
				fb.authorize(getActivity(), new String[] {"email"} ,Facebook.FORCE_DIALOG_AUTH,new DialogListener() {
					
					@Override
					public void onFacebookError(FacebookError e) {
						// TODO Auto-generated method stub
						Toast.makeText(getActivity(),"FBerror",Toast.LENGTH_SHORT).show();
					}
					
					@Override
					public void onError(DialogError e) {
						// TODO Auto-generated method stub
						Toast.makeText(getActivity(),"Error",Toast.LENGTH_SHORT).show();
					}
					
					@Override
					public void onComplete(Bundle values) {
						// TODO Auto-generated method stub
						Editor editor=sp.edit();
						editor.putString("access_token", fb.getAccessToken());
						editor.putLong("access_expires", fb.getAccessExpires());
						editor.commit();
						
						Toast.makeText(getActivity(),"Logged IN",Toast.LENGTH_LONG).show();
								
					}
					
					@Override
					public void onCancel() {
						// TODO Auto-generated method stub
						Toast.makeText(getActivity(),"Canceled",Toast.LENGTH_SHORT).show();
					}
				});
			}
			
		}
		
		
		
		
		
		
		
	}
	
	
	

	
	
	
}
