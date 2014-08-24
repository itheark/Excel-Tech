package com.greycodes.excel14.newsfeed;

import com.greycodes.excel14.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NewsFeedArrayAdapter extends ArrayAdapter<String> {

	String[] subject,description;
	int[] pcode,cat;
	Context context;
		public NewsFeedArrayAdapter(Context context,String[] subject,String[] description ,int[] pcode,int[] cat) {
			super(context,R.layout.newsfeed_listview_items,R.id.newsfeed_subject,subject );
					this.context = context;
					this.subject=subject;
					this.description=description;
					this.pcode=pcode;
					this.cat=cat;
			// TODO Auto-generated constructor stub
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			LayoutInflater inflater = 	(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View row = inflater.inflate(R.layout.newsfeed_listview_items, parent,false);
			
			LinearLayout  ll = (LinearLayout) row.findViewById(R.layout.newsfeed_listview_items);
			TextView tvdescription = (TextView) row.findViewById(R.id.newsfeed_details);
			TextView tvsubject = (TextView) row.findViewById(R.id.newsfeed_subject);
			ImageView ipcode = (ImageView) row.findViewById(R.id.newsfeed_pcodeimage);
		ImageView	icat = (ImageView) row.findViewById(R.id.newsfeed_cat);
			tvdescription.setText(description[position]);
			tvsubject.setText(subject[position]);
			switch (pcode[position]) {
			case 1:
				ipcode.setImageResource(R.drawable.pcode0);

				break;
			case 2:
				ipcode.setImageResource(R.drawable.pcode1);

				break;
			case 3:
				ipcode.setImageResource(R.drawable.pcode2);

				break;
			
			}
			switch (cat[position]) {
			case 1:
				icat.setImageResource(R.drawable.nfc);
				break;
			case 2:
				icat.setImageResource(R.drawable.nfec);
				break;
			case 3:
				icat.setImageResource(R.drawable.nfee);
				break;
			case 4:
				icat.setImageResource(R.drawable.nfr);
				break;
			case 5:
				icat.setImageResource(R.drawable.nfb);
				break;
			case 6:
				icat.setImageResource(R.drawable.nfg);
				break;
			case 7:
				icat.setImageResource(R.drawable.nfn);
				break;
			case 8:
				icat.setImageResource(R.drawable.nflogo);
				break;
				

			default:
				icat.setImageResource(R.drawable.nflogo);
				break;
			}
			
			return row;
		}
	
		

}
