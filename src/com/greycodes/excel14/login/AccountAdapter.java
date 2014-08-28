package com.greycodes.excel14.login;

import com.greycodes.excel14.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AccountAdapter extends BaseAdapter {
	String[] ename;
	int[] tid;
	Context context;
	LayoutInflater acountlayout;
	
public AccountAdapter(Context context,String[] ename,int[] tid){
	this.context = context;
	this.tid = tid;
	this.ename= ename;
}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return tid.length;
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
		TextView tvename,tvtid;
		acountlayout=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = acountlayout.inflate(R.layout.account_listitem, parent, false);
		tvename=(TextView) itemView.findViewById(R.id.account_ename);
		tvtid=(TextView) itemView.findViewById(R.id.account_tid);
		tvename.setText(ename[position]);
		if (tid[position]!=1) {
			tvtid.setText(Integer.toString(tid[position]));
		}
		return itemView;
	}

}
