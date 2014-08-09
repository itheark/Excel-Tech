package com.greycodes.excel14.database;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.greycodes.excel14.ConnectionDetector;
import com.greycodes.excel14.Misc;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class ParseResult {
String results;
String[] res,name,college;
int eid;
Context context;
boolean flag;
String resfinal;
Misc misc;
	public ParseResult(Context context) {
		// TODO Auto-generated constructor stub
		this.context= context;
		misc = new Misc(context);
		resfinal = " \n";
		//res[0]=name[0]=college[0]="\n";
		
	}
	public String getFinalResult(){
		return resfinal;
	}
	
	public void result(int eid){
		this.eid = eid;
		
		
		try {
			resfinal = "\n";
			Object object= new Result().execute("http://excelapi.net84.net/result.json").get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public class Result extends AsyncTask<String, String, String>  {

		@Override
		protected String doInBackground(String... params)  {
			// TODO Auto-generated method stub
			
			DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
			HttpPost httppost = new HttpPost("http://excelapi.net84.net/result.json");
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
				JSONObject jsonObject;
				try{
					jsonObject = new JSONObject(results);
					JSONObject event = jsonObject.getJSONObject("event");
					JSONObject eventid = event.getJSONObject(Integer.toString(eid));
					
					if(eventid.getInt("flag")==1){
						res = new String[3];
						college = new String[3];
						JSONArray winners = event.getJSONArray("winners");
						for(int i=0;i<3;i++){
							int len = winners.getJSONObject(i).getJSONArray("name").length();
							name = new String[len];
							res[i]="";
							for(int j=0;j<len;j++){
								name[j] = winners.getJSONObject(i).getJSONArray("name").getJSONObject(j).getString("name");
								res[i]= res[i]+"\n"+name[j];
							}
								college[i] = winners.getJSONObject(i).getString("college");
								switch(i){
								case 0:
									resfinal = resfinal +"  1st Prize  :"+res[0]+"\n "+college[0]+"\n";
								break;
								case 1:
									resfinal = resfinal +"  2nd Prize  :"+res[1]+"\n "+college[1]+"\n";
									break;
								case 2:
									resfinal = resfinal +"  3rd Prize  :"+res[2]+"\n "+college[2]+"\n\n";
									break;
								}
							
														
						}
						
					//	for(int i=0;i<winners.getJSONObject(0).getJSONArray("name").length();i++)
						
					}else
						if(eventid.getInt("flag")==2){
						JSONArray shortlist = eventid.getJSONArray("shortlisted");
						int len = shortlist.length();
						name = new String[len];
						college = new  String[len];
						
						
						for(int i=0;i<len;i++){
						name[i]=	shortlist.getJSONObject(i).getString("teamname");
							college[i] =shortlist.getJSONObject(i).getString("college");
						}
						for(int i=0;i<len;i++){
							resfinal = resfinal + "   " + Integer.toString(i+1)+ ". " +name[i]+"\n"+college[i]+"\n";
						}
						
					}else{
						resfinal = "We are Also Waiting";
					}
					
					
					
					
				 
					

				}catch(JSONException e){
					e.printStackTrace();
				}
			}
			return results;
			
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Toast.makeText(context, "ParseResult", Toast.LENGTH_LONG).show();
		misc.settext(eid, resfinal);
			
		}

		
	} 
}
