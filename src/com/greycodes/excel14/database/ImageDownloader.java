package com.greycodes.excel14.database;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

import org.apache.http.util.ByteArrayBuffer;

import android.os.AsyncTask;
import android.util.Log;

public class ImageDownloader {
	 int accId;
	    byte[] accImage;

	    byte[] logoImage;
	    byte[] photo;

	    
	    
	    public byte[] Download(String url){
	    	
	    	try {
				boolean ob =new ImageDownload().execute(url).get() != null;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return photo;
	    	
	    }

private byte[] getLogoImage(String url) {
    try {
    URL imageUrl = new URL(url);
    URLConnection ucon = imageUrl.openConnection();
    System.out.println("11111");
    InputStream is = ucon.getInputStream();
    System.out.println("12121");

    BufferedInputStream bis = new BufferedInputStream(is);
    System.out.println("22222");

    ByteArrayBuffer baf = new ByteArrayBuffer(500);
    int current = 0;
    System.out.println("23333");

    while ((current = bis.read()) != -1) {
        baf.append((byte) current);

    }
    photo = baf.toByteArray();
    System.out.println("photo length" + photo);


} catch (Exception e) {
    Log.d("ImageManager", "Error: " + e.toString());
}
return accImage;
}

private class ImageDownload extends AsyncTask<String, Void, Void> {



    @Override
    protected Void doInBackground(String... param) {

        
         logoImage = getLogoImage(param[0]);
		return null;

         
    }

    @Override
    protected void onPreExecute() {

        

    }

    @Override
    protected void onPostExecute(Void result) {
       

    }

}
}