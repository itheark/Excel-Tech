package com.greycodes.excel14.database;



import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.Toast;


public class ParseCompetition {
	Context context;
	String[] intro,cat,ename,format,cordinator,rules,date,mob,tstopic,tsintro,tsdate,tscordinator,tsmob;
	String results;
	int[] eid,wsid;
	//TSID ,INTRO ,TOPIC ,MODEARATOR ,MODDESC MODIMAGE ,S1NAME ,S1DESC ,S1IMAGE ,S2NAME,S2DESC ,S2IMAGE ,S3NAME ,S3DESC ,S3IMAGE ,S4NAME ,S4DESC ,S4IMAGE ,S5NAME ,S5DESC   ,S5IMAGE   ,EVENTDETAILS   ,DATE   ,CORDINATOR   ,MOB   
	String  dsintro,dstopic,dsmod,dsmoddesc,dss1name,dss1desc,dss2name,dss2desc,dss3name,dss3desc,dss4name,dss4desc,dss5name,dss5desc,dseventdetails,dsdate,dscordinator,dsmob;
	int c,i,dsid;
  long inserted;
    String url="http://excelapi.net84.net/excelevents.json";
	public ParseCompetition(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		Toast.makeText(context, "parsecompetition constructor", Toast.LENGTH_SHORT).show();
		

	}
  public  long executeparse() {
	// TODO Auto-generated constructor stub
	//new PCompetition().execute(url);
	  inserted= -1;
	  c=0;
		Toast.makeText(context, "executeparse", Toast.LENGTH_SHORT).show();

	/*	ExcelDataBase exceldb;
int[] eid= new  int[]{1,2,3};
String[] cat= new String[]{"cs","ec","eee"};
String[] ename= new String[]{"ename1","ename2","ename3"};
String[] intro= new String[]{"intro1","intro2","intro3"};
String[] format= new String[]{"format1","format2","format3"};
String[] rules= new String[]{"rules1","rules2","rules3"};
String[] cordinator= new String[]{"cordinator1","cordinator3","cordinator3"};
long[] mob = new long[]{9020,2040,3040};


			exceldb = new ExcelDataBase(context);
		Toast.makeText(context, "back to parsecompetition", Toast.LENGTH_SHORT).show();

			for(int i=0;i<3;i++){
				Toast.makeText(context, "insertion loop", Toast.LENGTH_SHORT).show();
				exceldb.insetCompetition(eid[i], cat[i], ename[i], intro[i], format[i], rules[i], cordinator[i], mob[i]);

			}
			*/
			new PCompetition().execute(url);
			
			return inserted;
}

  private class PCompetition extends AsyncTask<String, String, String>{
		

		protected String doInBackground(String... params)  {
			// TODO Auto-generated method stub
			
			DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
			HttpPost httppost = new HttpPost(url);
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
					JSONObject event = jsonObject.getJSONObject("events");
					JSONArray jsonarray= event.getJSONArray("competition");
					eid = new int[jsonarray.length()];
					cat= new String[jsonarray.length()];
					ename= new String[jsonarray.length()];
					intro= new String[jsonarray.length()];
					rules= new String[jsonarray.length()];
					format= new String[jsonarray.length()];
					cordinator= new String[jsonarray.length()];
					date= new String[jsonarray.length()];
					mob = new String[jsonarray.length()];
					c=jsonarray.length();
					
					
					for(int i=0;i<jsonarray.length();i++){
						JSONObject competition = jsonarray.getJSONObject(i);
						eid[i] = competition.getInt("eid");
						cat[i] = competition.getString("cat");
						ename[i] = competition.getString("ename");
						intro[i]	= competition.getString("introduction");
						rules[i] = competition.getString("rules");
						date[i] = competition.getString("date");
						format[i] = competition.getString("format");
						cordinator[i] = competition.getJSONArray("contact").getJSONObject(0).getString("cordinator");
						mob[i] = competition.getJSONArray("contact").getJSONObject(0).getString("mob");
						
						
					}
					
					//.issue
					/*
					 * "tsid":1,
		"introduction":"introduction",
		"topic":"topic",
		"moderator":"name",
		"description":"description",
		"image":"image",
		"speakers":[{
			"name":"name",
			"description":"description",
			"image":"image"
			}],
		"eventdetails":"eventdetails",
		"date":"yyyy-MM-dd HH:mm:ss",
		"cordinator":"cordinator",
		"mob":"9020404022"
					 */
					JSONObject dotissue = jsonObject.getJSONObject("talkseries").getJSONObject("talkseries");
					dsid= dotissue.getInt("tsid");
					dsintro = dotissue.getString("introduction");
					dsmod = dotissue.getString("moderator");
					dsmoddesc = dotissue.getString("description");
					//mod image
					dss1name = dotissue.getJSONArray("speakers").getJSONObject(0).getString("name");
					dss1desc = dotissue.getJSONArray("speakers").getJSONObject(0).getString("description");
					//s1 image
					dss2name = dotissue.getJSONArray("speakers").getJSONObject(1).getString("name");
					dss2desc = dotissue.getJSONArray("speakers").getJSONObject(1).getString("description");
					//s2 image
					dss3name = dotissue.getJSONArray("speakers").getJSONObject(2).getString("name");
					dss3desc = dotissue.getJSONArray("speakers").getJSONObject(2).getString("description");
					//s3 image
					dss4name = dotissue.getJSONArray("speakers").getJSONObject(3).getString("name");
					dss4desc = dotissue.getJSONArray("speakers").getJSONObject(3).getString("description");
					//s4 image
					dss5name = dotissue.getJSONArray("speakers").getJSONObject(4).getString("name");
					dss5desc = dotissue.getJSONArray("speakers").getJSONObject(4).getString("description");
					//s5 image
					
					
					
					//workshop and semianr
					
					wsid = new int[2];
					tstopic= new String[2];
					tsintro = new String[2];
					tsdate= new String[2];
					tscordinator= new String[2];
					tsmob= new String[2];
					
				
					
					
					JSONObject talkseires = jsonObject.getJSONObject("talkseries");
					for(i=0;i<2;i++){
						wsid[i] = talkseires.getJSONObject("seminar").getInt("tsid");
						tstopic[i] = talkseires.getJSONObject("seminar").getString("topic");
						tsintro[i]= talkseires.getJSONObject("seminar").getString("introduction");
						tsdate[i] = talkseires.getJSONObject("seminar").getString("date");
						tscordinator[i]= talkseires.getJSONObject("seminar").getString("cordinator");
						tsmob[i]= talkseires.getJSONObject("seminar").getString("mob");
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
			ExcelDataBase exceldb;
			/*
int[] eid= new  int[]{1,2,3};
String[] cat= new String[]{"cs","ec","eee"};
String[] ename= new String[]{"ename1","ename2","ename3"};
String[] intro= new String[]{"intro1","intro2","intro3"};
String[] format= new String[]{"format1","format2","format3"};
String[] rules= new String[]{"rules1","rules2","rules3"};
String[] cordinator= new String[]{"cordinator1","cordinator3","cordinator3"};
long[] mob = new long[]{9020,2040,3040};

*/
		exceldb = new ExcelDataBase(context);
		/*	
		SQLiteDatabase sqLiteDatabase=	exceldb.getSQLiteDataBase();
		ContentValues contentValues  = new ContentValues();
		for(i=0;i<2;i++){
			//TSID     TOPIC   INTRO   ,DATE   ,CORDINATOR   MOB 
			contentValues.put("TSID", wsid[i]);
			contentValues.put("TOPIC", tstopic[i]);
			contentValues.put("INTRO", tsintro[i]);
			contentValues.put("DATE", tsdate[i]);
			contentValues.put("CORDINATOR", tscordinator[i]);
			contentValues.put("MOB", tsmob[i]);
			sqLiteDatabase.insert("WORKSHOPSEMINAR", null, contentValues);
		}
		*/
			for(int i=0;i<c;i++){
			inserted=	exceldb.insetCompetition(eid[i], cat[i], ename[i], intro[i], format[i], rules[i],date[i], cordinator[i], mob[i]);

			}
			
			ParseSponsor parseSposor = new ParseSponsor(context);
			Object sponsor=    parseSposor.parseSponsorImage();
		}
		
		
	}

}

