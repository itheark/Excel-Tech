package com.greycodes.excel14.biomedevents;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.greycodes.excel14.ConnectionDetector;
import com.greycodes.excel14.Misc;
import com.greycodes.excel14.R;
import com.greycodes.excel14.database.ExcelDataBase;
import com.greycodes.excel14.database.InsertParticipant;
import com.greycodes.excel14.database.ParseResult;


public class FragmentLifeLine extends SherlockFragment implements OnClickListener,OnLongClickListener {
	ImageView intro,rules,format;
	ImageView call,result,participate;
	public  static TextView tv;
	Misc  misc;
	 ConnectionDetector connectionDetector;
	 Handler h ;
	 int eid=902;
	 ProgressDialog progressDialog;
	 ExcelDataBase excelDataBase;
	 String Ename;
	 boolean team=false;
	 int tid =0;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.biomed_lifeline, container, false);
		 tv=(TextView)rootView.findViewById(R.id.txtinclude);
		 intro=(ImageView)rootView.findViewById(R.id.intro);
		 rules =(ImageView)rootView.findViewById(R.id.rules);
		 format =(ImageView)rootView.findViewById(R.id.event);
		 
		 intro.setOnClickListener(this);
		 rules.setOnClickListener(this);
		 format.setOnClickListener(this);
		 misc = new Misc(getActivity());
		 excelDataBase   = new ExcelDataBase(getActivity());
		 call=(ImageView)rootView.findViewById(R.id.imageView4);
	        result=(ImageView)rootView.findViewById(R.id.imageView2);
	        participate=(ImageView)rootView.findViewById(R.id.imageView3);
	        connectionDetector = new ConnectionDetector(getActivity());
	        
	        call.setOnClickListener(this);
	        result.setOnClickListener(this);
	        participate.setOnClickListener(this);
	        call.setOnLongClickListener(this);
		 
		return rootView;
		
		
		
		
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
	
	
		
		switch (v.getId()) {
		case R.id.intro:
			updateTextValue("A unique event that lets you envisage yourself as a life saver with a quest to prove your adroitness in debugging. Examine and 'operate' the impaired medical instrument still connected to the patient. It's time to act. It's time to save.\n\n" );
			break;
		case R.id.rules:
			updateTextValue("1.	The competition is held for teams of 2 members.\n2.	Cross-college teams ARE allowed.\n3.	Failing to save the 'patient' in given time results in immediate elimination.\n4.	The coordinators of the event reserve the right to take appropriate decisions in case of any issues/conflicts.\n5.	Rules are subject to change at any point in time. All rules will be explained at the event as well.\n6.	The decision of the judges would be final and binding.\n\n");
		break;
		case R.id.event:
			updateTextValue("Lifeline consists of 3 stages and will test participants on their knowledge of electronics, its applications in the field of biomedical engineering and their ability to diagnose and debug problems related to a circuit as quickly as possible.\nFIRST LEVEL:\n1.	This round consists of a 30 minute MCQ test.\n2.	the test will contain electronics and biomed related questions.\n3.	Six teams selected from this round will proceed to the next round.\nSEMI FINALS:\n\nFollowing your instincts is crucial when you are dealing with a life waiting to be saved and all you have is limited time. This round tests your ability to make decisions and execute them in the given time.• The round consists of three biomedical circuit level tasks which tests your practical electronics knowledge.\n• Time allotted for each task is 10 minutes.\n• Failure to complete all the tasks within the allotted time will lead to your elimination.\n• Only four teams which complete the tasks first will make their way to the next round.\nFINALS\n\nCareful what you do, because one mistake from the biomedical engineer, its not an IC burnt, but a life lost.\nSmall innovative designs from you today could be the technology of tomorrow. This round brings out the scientist in you.\n• In this round, you will be asked to design and implement a circuit which will be the solution for a real life problem.\n• Time allotted will be 1hr 30min.\n• Winner will be selected depending on the perfection and implementation of the circuit.\n\n");
			break;
		case R.id.imageView2:
			Intent service = new Intent(getActivity(), ParseResult.class);
			service.putExtra("eid", eid);
			getActivity().startService(service);
			Toast.makeText(getActivity(), "Please wait...fetching result", Toast.LENGTH_LONG).show();
	       
			
			break;
		case R.id.imageView3:
			Toast.makeText(getActivity(), "Please wait", Toast.LENGTH_LONG).show();

			eid =904;
			Ename="Lifeline";
			h = new Handler() {
	            @Override
	            public void handleMessage(Message msg) {

	                if (msg.what != 1) { // code if not connected
	               
	                	connectionDetector.noNetworkAlert();;
	               
	                	
	                	
	            				
	                } else { // code if connected
		       
					
	                	if(excelDataBase.Isregistered()){
	                		Intent service1 = new Intent(getActivity(), ParseResult.class);
	            			service1.putExtra("eid", eid);
	            			getActivity().startService(service1);
	            			Toast.makeText(getActivity(), "Please wait...fetching result", Toast.LENGTH_LONG).show();
	        					
	        	}else{
	        					Toast.makeText(getActivity(), "Already Registered", Toast.LENGTH_SHORT).show();
	        				}       	
	               	 
	                }   
	            }
	        };
	        
	        

	        connectionDetector.isNetworkAvailable(h,5000);
			
			
			break;
			
		case R.id.imageView4:
			Toast.makeText(getActivity(), "Press & Hold to call", Toast.LENGTH_LONG).show();

			updateTextValue("Call Sabith ?");
			break;
			
			

		
		}
		
		
	}
	

	public void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
	    tv.setAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in));
		}

	   @Override
		public void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
			getActivity().stopService(new Intent(getActivity(), ParseResult.class));
		}
	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		misc.call("09526814385");	
		return true;
	}
	

}	