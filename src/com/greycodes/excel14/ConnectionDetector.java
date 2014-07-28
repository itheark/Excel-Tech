package com.greycodes.excel14;



import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.os.Handler;
 
public class ConnectionDetector {
     
    private Context context;
     
    public ConnectionDetector(Context context){
        this.context = context;
        
    }

  
 
    @SuppressWarnings("deprecation")
	public void noNetworkAlert(){
    	AlertDialog alertDialog = new AlertDialog.Builder(
                context).create();

// Setting Dialog Title
alertDialog.setTitle("Excel");

// Setting Dialog Message
alertDialog.setMessage("No data connection");

// Setting Icon to Dialog
alertDialog.setIcon(R.drawable.alert);

// Setting OK Button
alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
        public void onClick(final DialogInterface dialog, final int which) {
        // Write your code here to execute after dialog closed
        }
});

// Showing Alert Message
alertDialog.show();
    	
    }
    
   
public static void isNetworkAvailable(final Handler handler, final int timeout) {
        

        new Thread() {
            private boolean responded = false;   
            @Override
            public void run() { 
                // set 'responded' to TRUE if is able to connect with google mobile (responds fast) 
                new Thread() {      
                    @Override
                    public void run() {
                        HttpGet requestForTest = new HttpGet("http://m.google.com");
                        try {
                            new DefaultHttpClient().execute(requestForTest); // can last...
                            responded = true;
                        } 
                        catch (Exception e) {
                        }
                    } 
                }.start();

                try {
                    int waited = 0;
                    while(!responded && (waited < timeout)) {
                        sleep(100);
                        if(!responded ) { 
                            waited += 100;
                        }
                    }
                } 
                catch(InterruptedException e) {} // do nothing 
                finally { 
                    if (!responded) { handler.sendEmptyMessage(0); } 
                    else { handler.sendEmptyMessage(1); }
                }
            }
        }.start();
    }


	
	
    
}
