package Impek.Gen;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


import android.location.Location;
import android.util.Log;

public class GeoConversion {

	  public static String reverseGeocode(Location loc) throws NoGeoConversion
	  {
	      HttpClient connection = null;
	      InputStream is = null;
	      StringBuilder sb = null;
	      String result = null;
	      String url = "http://maps.google.com/maps/geo?q=" + Double.toString(loc.getLatitude()) + "," + Double.toString(loc.getLongitude()) +
                  "&output=json&oe=utf8&sensor=true&key=0bocoaZY35Udsh9H0K4aAAdTVbjDylNei8qj8Lg";

	     
		  // Setting up the connection
		  connection = new DefaultHttpClient();
		  // Passing the URL
		  HttpGet request = new HttpGet(url);
		
		  try
		  {
			  // Making the connection
			  HttpResponse response = connection.execute(request);
			  // Getting the response into an InputStream
			  HttpEntity entity = response.getEntity();
			  is = entity.getContent();
			  
			  StatusLine status = response.getStatusLine();
		      if (status.getStatusCode() != 200) 
		      {
		    	  throw new NoGeoConversion();
		      }
		
			 
		  }
		  catch (Exception ex)
		  {
		      ex.printStackTrace();
		  }
		  
		  // Now we have the response as an InputStream
		  
		//convert response to string
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
			sb = new StringBuilder();
			sb.append(reader.readLine() + "\n");
			String line="0";
	     
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			
			is.close();
			result = sb.toString();
			
		}catch(Exception e){
			Log.e("Impek.httpresult", "Error converting result "+e.toString());
		}

		String ret = null;
		
		if(result.indexOf("\"PostalCodeNumber\"") != -1)
		{
			String p_trunc_top = result.substring(result.indexOf("\"PostalCodeNumber\"") + 18);
			String trunc_top = p_trunc_top.substring(p_trunc_top.indexOf("\"") + 1);
			ret = trunc_top.substring(0, trunc_top.indexOf("\""));
		}
		
		else if(result.indexOf("\"address\"") != -1)
		{
			String p_trunc_top = result.substring(result.indexOf("\"address\"") + 9);
			String trunc_top = p_trunc_top.substring(p_trunc_top.indexOf("\"") + 1);
			String address = trunc_top.substring(0, trunc_top.indexOf("\""));
			String address_trunc_b = address.substring(0, address.lastIndexOf(","));
			String post_bottom = address_trunc_b.substring(address_trunc_b.lastIndexOf(" ") + 1, address_trunc_b.length());
			String add_top = address_trunc_b.substring(0, address_trunc_b.lastIndexOf(" "));
			String post_top = add_top.substring(add_top.lastIndexOf(" "), add_top.length());
			
			boolean full_post = false;
			
			for(int i = 0; i < post_top.length(); ++i)
				if(post_top.charAt(i) <= '9' && post_top.charAt(i) >= '0')
				{
					full_post = true;
					break;
				}
			
			if(full_post)
				ret = post_top + " " + post_bottom;
			else
				ret = post_bottom + " 1AZ";
		}
		else
			throw new NoGeoConversion();
		
		return ret;
	  }

	  static class NoGeoConversion extends Exception
		{
		private static final long serialVersionUID = -8262622721071888856L;
			
		}
	  
}