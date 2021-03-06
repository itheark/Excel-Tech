package com.greycodes.excel14;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.greycodes.excel14.database.ExcelDataBase;
import com.greycodes.excel14.database.ParseActivate;
import com.greycodes.excel14.login.AccountFragment;
import com.greycodes.excel14.login.LoginActivity;


public class HomeFragment extends Fragment implements OnClickListener {
	
	ImageView login;
	ImageView fbicon;
	ImageView navigate;
	SharedPreferences sharedPreferences;
	ImageView excelmecorg,npbuilder;
	ExcelDataBase excelDataBase;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.homend_home_fragment, container, false);
		 sharedPreferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
		login = (ImageView) rootView.findViewById(R.id.register_icon);
		navigate = (ImageView) rootView.findViewById(R.id.navigate_icon);
		fbicon= (ImageView) rootView.findViewById(R.id.fb_icon);
		excelmecorg=(ImageView)rootView.findViewById(R.id.excelmecorg);
		npbuilder=(ImageView)rootView.findViewById(R.id.npbuilder);
		 login.setOnClickListener(this);
		 navigate.setOnClickListener(this);
		 fbicon.setOnClickListener(this);
		 excelmecorg.setOnClickListener(this);
		 npbuilder.setOnClickListener(this);
		 	 excelDataBase = new ExcelDataBase(getActivity());
		 return rootView;
	}
	//228354903862639
	 public static Intent getOpenFacebookIntent(Context context) {

	       try {
	        context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
	        return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/228354903862639"));
	       } catch (Exception e) {
	        return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/228354903862639"));
	       }
	    }
	
	
		@Override
		public void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			
			
			
		}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(v.equals(login))
		{
			
			if(sharedPreferences.getBoolean("registered",false)){
				
				if(sharedPreferences.getBoolean("active",false)){
					Fragment f;
					FragmentManager fragmentManager ;
					FragmentTransaction transaction;
					fragmentManager = getActivity().getSupportFragmentManager();
					 transaction=fragmentManager.beginTransaction();
					 f = new AccountFragment();
					 transaction.replace(R.id.home_content_frame,f);
						// Add this transaction to the back stack
		               transaction.addToBackStack("detail");
		               transaction.commit();
				}else{
					Toast.makeText(getActivity(), "Account not activated", Toast.LENGTH_LONG).show();
					 getActivity().startService(new Intent(getActivity(),ParseActivate.class));
				}

			
			
			}else{
				Intent intent = new Intent(getActivity(),LoginActivity.class);
				startActivity(intent);
			}
			
			
			
		
			
			
		}
		
		 if(v.equals(navigate))
		{
			//call map fragment here
			 float mLat=(float) 10.028484;
			 float mLong =(float) 76.328749;
			 Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" +mLat+","+mLong));
			 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			 startActivity(intent);
			/*	Intent navigation = new Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.google.co.in/maps?q=Model+Engineering+College+Thrikkakara,+Ernakulam,+Kerala&hl=en&ll=10.028558,76.328748&spn=0.006772,0.009645&sll=10.027935,76.328716&sspn=0.006772,0.009645&t=h&z=17&iwloc=A"));
								
				navigation.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
				
				startActivity(navigation);
			 */
			
		}
		 
		 
		 if(v.equals(excelmecorg))
		 {
			 String url = "http://www.excelmec.org/excel2014/";
			 Intent i = new Intent(Intent.ACTION_VIEW);
			 i.setData(Uri.parse(url));
			 startActivity(i);
			 
			 
		 }
		 if (v.equals(fbicon))
		{
			
			// Intent fb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/excelmec"));
			//	startActivity(fb);
			 Intent fb = getOpenFacebookIntent(getActivity());
				startActivity(fb);
			 
		}
		if(v.equals(npbuilder))
		{
			 String url = "http://nucleusproperties.in/";
			 Intent i = new Intent(Intent.ACTION_VIEW);
			 i.setData(Uri.parse(url));
			 startActivity(i);
			
		}
		
		
	}


}
