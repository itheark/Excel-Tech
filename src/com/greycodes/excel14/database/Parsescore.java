package com.greycodes.excel14.database;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import com.greycodes.excel14.HomeNDActivity;
import com.greycodes.excel14.QuickOpenAdapter;
import com.greycodes.excel14.QuickOpenFragment;
import com.greycodes.excel14.R;
import com.greycodes.excel14.login.OnlineStatusFragment;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Parsescore extends Service {

String results,hmrank,hmlevel,hmpoints,krank,klevel,hirank,hisub,hipoints,drank,dworth,dshares,wwins,wloses,wrank;
int hack_flag,kryptos_flag,include_flag,dalal_flag,webbots_flag;

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		new parsequickopen().execute("http://excelapi.net84.net/onlinescore.json");
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public class parsequickopen extends AsyncTask<String, String, String>  {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			Toast.makeText(getApplicationContext(), "preexecute", Toast.LENGTH_LONG).show();

		}

		@Override
		protected String doInBackground(String... params)  {
			// TODO Auto-generated method stub
			
			DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
			HttpPost httppost = new HttpPost("http://excelapi.net84.net/onlinescore.json");
			httppost.setHeader("Content-type","application/json");
			InputStream inputstream = null;
			try{
				org.apache.http.HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity =  response.getEntity();
				inputstream = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream,"UTF-8"),8);
				StringBuilder theStringBuilder = new StringBuilder();
				String line = null;
				while((line= reader.readLine())!=null){
					theStringBuilder.append(line+ '\n');
					
				}
				results = theStringBuilder.toString();
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(inputstream!=null)
						inputstream.close();
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
			return results;
			
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			JSONObject jsonObject;
			
			try{
				jsonObject = new JSONObject(results);
				
				hack_flag = jsonObject.getJSONObject("score").getJSONObject("hackmaster").getInt("flag");
				kryptos_flag = jsonObject.getJSONObject("score").getJSONObject("kryptos").getInt("flag");
				include_flag = jsonObject.getJSONObject("score").getJSONObject("hashinclude").getInt("flag");
				dalal_flag = jsonObject.getJSONObject("score").getJSONObject("dalalbul").getInt("flag");
				webbots_flag = jsonObject.getJSONObject("score").getJSONObject("webbots").getInt("flag");
				if(hack_flag==2){
					hmrank= jsonObject.getJSONObject("score").getJSONObject("hackmaster").getString("rank");
					hmlevel = jsonObject.getJSONObject("score").getJSONObject("hackmaster").getString("level");
					hmpoints = jsonObject.getJSONObject("score").getJSONObject("hackmaster").getString("points");
				}
				
				if(kryptos_flag==2){
					krank= jsonObject.getJSONObject("score").getJSONObject("kryptos").getString("rank");
					klevel = jsonObject.getJSONObject("score").getJSONObject("kryptos").getString("level");
				}
				Toast.makeText(getApplicationContext(),"klevel"+ klevel, Toast.LENGTH_LONG).show();

				if(include_flag==2){
					hirank= jsonObject.getJSONObject("score").getJSONObject("hashinclude").getString("rank");
					hisub = jsonObject.getJSONObject("score").getJSONObject("hashinclude").getString("submission");
					hipoints = jsonObject.getJSONObject("score").getJSONObject("hashinclude").getString("points");
				}
				Toast.makeText(getApplicationContext(),"#include"+ hipoints, Toast.LENGTH_LONG).show();

				if(dalal_flag==2){
					drank= jsonObject.getJSONObject("score").getJSONObject("dalalbul").getString("rank");
					dworth = jsonObject.getJSONObject("score").getJSONObject("dalalbul").getString("worth");
					dshares = jsonObject.getJSONObject("score").getJSONObject("dalalbul").getString("share");
				}
				if(webbots_flag==2){
					wwins= jsonObject.getJSONObject("score").getJSONObject("webbots").getString("wins");
					wloses = jsonObject.getJSONObject("score").getJSONObject("webbots").getString("loses");
					wrank = jsonObject.getJSONObject("score").getJSONObject("webbots").getString("rank");
				}
				Toast.makeText(getApplicationContext(),"wloss"+ wloses, Toast.LENGTH_LONG).show();
				 
				

			}catch(JSONException e){
				
				Toast.makeText(getApplicationContext(),""+e, Toast.LENGTH_LONG).show();

			}catch(Exception e){
				Toast.makeText(getApplicationContext(), "No Internet Connectivity", Toast.LENGTH_LONG).show();
			stopSelf();
			}
			
			try {
				if (results.length()>10) {
onlineScoreAdapter scoreAdapter = new onlineScoreAdapter();
					HomeNDActivity.scoreAdapter = scoreAdapter;

					Fragment f;
					//FragmentManager fragmentManager;
					FragmentTransaction transaction;
					//fragmentManager = HomeNDActivity.
					//fragmentManager =  getApplicationContext().getSupportFragmentManager();
					Toast.makeText(getApplicationContext(), "FragmentManager SET", Toast.LENGTH_LONG).show();

					transaction = HomeNDActivity.fragmentManager.beginTransaction();
					f = new OnlineStatusFragment();
					transaction.replace(R.id.home_content_frame, f);
					
					// Add this transaction to the back stack
					transaction.addToBackStack("detail");
					transaction.commit();

				}else{
					Toast.makeText(getApplicationContext(), "No Internet Connectivity", Toast.LENGTH_LONG).show();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stopSelf();
				
		}

		
	}
	
public class	onlineScoreAdapter extends BaseAdapter{
LayoutInflater onlinescore;

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		int count =0;
		if(hack_flag==2)
			count++;
		if(kryptos_flag==2)
			count++;
		if(include_flag==2)
			count++;
		if(dalal_flag==2)
			count++;
		if(webbots_flag==2)
			count++;
		return count;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView tv1,tv2,tv3;
		ImageView imageView;
		onlinescore=(LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = onlinescore.inflate(R.layout.user_onlinescore_listitem, parent, false);
		tv1=(TextView) itemView.findViewById(R.id.textView1);
		tv2=(TextView) itemView.findViewById(R.id.textView2);
		tv3=(TextView) itemView.findViewById(R.id.textView3);
		imageView = (ImageView) itemView.findViewById(R.id.imageView1);
		switch(position){
		case 0:
			if(hack_flag==2){
				tv1.setText(hmrank);
				tv2.setText(hmlevel);
				tv3.setText(hmpoints);
				imageView.setImageResource(R.drawable.mp_hackmaster);
			}else if(kryptos_flag==0){
				tv1.setText(krank);
				tv2.setText(klevel);
				tv3.setText("");
				imageView.setImageResource(R.drawable.mp_csi);
			}else if(include_flag==2){
				tv1.setText(hirank);
				tv2.setText(hisub);
				tv3.setText(hipoints);
				imageView.setImageResource(R.drawable.mp_hashinclude);
			}else if(dalal_flag==2){
				tv1.setText(drank);
				tv2.setText(dworth);
				tv3.setText(dshares);
				imageView.setImageResource(R.drawable.mp_dalal);
			}else if(webbots_flag==2){
				tv1.setText(wwins);
				tv2.setText(wloses);
				tv3.setText(wrank);
				imageView.setImageResource(R.drawable.mp_webbots);
			}
			break;
		case 1:
			if(kryptos_flag==0){
				tv1.setText(krank);
				tv2.setText(klevel);
				tv3.setText("");
				imageView.setImageResource(R.drawable.mp_csi);
			}else if(include_flag==2){
				tv1.setText(hirank);
				tv2.setText(hisub);
				tv3.setText(hipoints);
			}else if(dalal_flag==2){
				tv1.setText(drank);
				tv2.setText(dworth);
				tv3.setText(dshares);
				imageView.setImageResource(R.drawable.mp_dalal);
			}else if(webbots_flag==2){
				tv1.setText(wwins);
				tv2.setText(wloses);
				tv3.setText(wrank);
				imageView.setImageResource(R.drawable.mp_webbots);
			}
			break;
		case 2:
			 if(include_flag==2){
				tv1.setText(hirank);
				tv2.setText(hisub);
				tv3.setText(hipoints);
				imageView.setImageResource(R.drawable.mp_hashinclude);
			}else if(dalal_flag==2){
				tv1.setText(drank);
				tv2.setText(dworth);
				tv3.setText(dshares);
				imageView.setImageResource(R.drawable.mp_dalal);
			}else if(webbots_flag==2){
				tv1.setText(wwins);
				tv2.setText(wloses);
				tv3.setText(wrank);
				imageView.setImageResource(R.drawable.mp_webbots);
			}
			break;
		case 3:
			  if(dalal_flag==2){
				tv1.setText(drank);
				tv2.setText(dworth);
				tv3.setText(dshares);
				imageView.setImageResource(R.drawable.mp_dalal);
			}else if(webbots_flag==2){
				tv1.setText(wwins);
				tv2.setText(wloses);
				tv3.setText(wrank);
				imageView.setImageResource(R.drawable.mp_webbots);
			}
			 break;
		case 4:
			 if(webbots_flag==2){
					tv1.setText(wwins);
					tv2.setText(wloses);
					tv3.setText(wrank);
					imageView.setImageResource(R.drawable.mp_webbots);
				}
			 break;
			
		}
		
		
		
		
		
		return itemView;
	}
	
	
}

}
