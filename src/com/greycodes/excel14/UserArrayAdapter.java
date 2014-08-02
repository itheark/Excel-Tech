package com.greycodes.excel14;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UserArrayAdapter extends ArrayAdapter<String> {
String[] name,username;
Bitmap image;
Context context;
	public UserArrayAdapter(Context context,String[] name,String[] username ,Bitmap image) {
		super(context,R.layout.user_details,R.id.user_name,username );
		this.name = name;
		this.username = username;
		this.image = image;
		this.context = context;
		// TODO Auto-generated constructor stub
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row = inflator.inflate(R.layout.user_details, parent,false);
		ImageView userImage = (ImageView) row.findViewById(R.id.user_image);
		TextView userName = (TextView) row.findViewById(R.id.user_name);
		TextView userUserName = (TextView) row.findViewById(R.id.user_username);

		userImage.setImageBitmap(image);
		userName.setText(name[position]);
		userUserName.setText(username[position]);

		
		return row;
	}
	

}