package com.greycodes.excel14.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dm.zbar.android.scanner.ZBarConstants;
import com.dm.zbar.android.scanner.ZBarScannerActivity;
import com.greycodes.excel14.R;

public class AccountActivity extends Activity {
	  private static final int ZBAR_SCANNER_REQUEST = 0;
		private static final int ZBAR_QR_SCANNER_REQUEST = 1;

		@Override
		public void onActivityResult(int requestCode, int resultCode, Intent data)
		{    
		    if (resultCode == RESULT_OK) 
		    {
		        // Scan result is available by making a call to data.getStringExtra(ZBarConstants.SCAN_RESULT)
		        // Type of the scan result is available by making a call to data.getStringExtra(ZBarConstants.SCAN_RESULT_TYPE)
		        Toast.makeText(getApplicationContext(), "Scan Result = " + data.getStringExtra(ZBarConstants.SCAN_RESULT), Toast.LENGTH_SHORT).show();
		        Toast.makeText(getApplicationContext(), "Scan Result Type = " + data.getIntExtra(ZBarConstants.SCAN_RESULT_TYPE, 0), Toast.LENGTH_SHORT).show();
		        // The value of type indicates one of the symbols listed in Advanced Options below.
		    } else if(resultCode == RESULT_CANCELED) {
		        Toast.makeText(getApplicationContext(), "Camera unavailable", Toast.LENGTH_SHORT).show();
		    }
		}
	
	@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
		
			super.onCreate(savedInstanceState);
			setContentView(R.layout.user_account_activity);
			Button qrCode =	 (Button) findViewById(R.id.qrcode);
			qrCode.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(getApplicationContext(), ZBarScannerActivity.class);
					startActivityForResult(intent, ZBAR_SCANNER_REQUEST);
				}
			});
		}


}
