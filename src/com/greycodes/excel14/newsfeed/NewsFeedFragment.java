package com.greycodes.excel14.newsfeed;
import org.json.JSONArray;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockListFragment;
import com.greycodes.excel14.R;
import com.greycodes.excel14.database.ExcelDataBase;
public class NewsFeedFragment extends SherlockListFragment{
int count;
String[] subject,message,columns;
int[] pcode,cat;

int n,i;
static String url = "http://www.excelapi.net84.net/newsfeed.json";
NewsFeedArrayAdapter newsFeedArrayAdapter;
static String[] description ;
@Override
public void onPause() {
// TODO Auto-generated method stub
super.onPause();
getActivity().stopService(new Intent(getActivity(), Checkflag.class));
}
int[] nid;
String results = null;
JSONArray jsonarray;
ExcelDataBase excelDataBase;
@Override
public void onViewCreated(View view, Bundle savedInstanceState) {
// TODO Auto-generated method stub
super.onViewCreated(view, savedInstanceState);
ViewGroup viewGroup = (ViewGroup) view;
getActivity().startService(new Intent(getActivity(), Checkflag.class));

columns = new String[]{"SUBJECT","MESSAGE","PCODE","CAT"};
ExcelDataBase excelDataBase = new ExcelDataBase(getActivity());
SQLiteDatabase sqLiteDatabase=	excelDataBase.getSQLiteDataBase();
Cursor cursor=	sqLiteDatabase.query("NEWSFEED", columns, null, null, null, null, "NID DESC");
cursor.moveToFirst();
count = cursor.getCount();
Toast.makeText(getActivity(), ""+count, Toast.LENGTH_LONG).show();
if(count==0){
subject = new String[]{"SUB1","SUB2","SUB3","SUB4"};
message = new String[]{"MESSAGE1","MESSAGE2","MESSAGE3","MESSAGE4"};
pcode = new int[]{1,2,3,2};
cat = new int[]{1,2,3,2};
}else{
subject = new String[count];
message = new String[count];
pcode = new int[count];
cat = new int[count];
for(i=0;i<count;i++,cursor.moveToNext()){
subject[i] = cursor.getString(cursor.getColumnIndex("SUBJECT"));
message[i] = cursor.getString(cursor.getColumnIndex("MESSAGE"));
pcode[i] = cursor.getInt(cursor.getColumnIndex("PCODE"));
cat[i] = cursor.getInt(cursor.getColumnIndex("CAT"));
}
}
newsFeedArrayAdapter = new NewsFeedArrayAdapter(getActivity(), subject, message, pcode,cat);
setListAdapter(newsFeedArrayAdapter);
}
@Override
public void onActivityCreated(Bundle savedInstanceState) {
// TODO Auto-generated method stub
super.onActivityCreated(savedInstanceState);
}
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
Bundle savedInstanceState) {
View rootView = inflater.inflate(R.layout.homend_newsfeed, container, false);
return rootView;
}


public void setadapter(){
newsFeedArrayAdapter = new NewsFeedArrayAdapter(getActivity(), subject, message, pcode,cat);
setListAdapter(newsFeedArrayAdapter);
}
}