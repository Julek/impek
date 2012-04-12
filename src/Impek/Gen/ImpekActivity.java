package Impek.Gen;

import java.util.Calendar;
import java.util.Date;

import Impek.Gen.GeoConversion.NoGeoConversion;
import Impek.Gen.GeoLocation.NoLocationError;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ImpekActivity extends Activity {
	/** Called when the activity is first created. */
	private Planner p;
	private Handler mHandler = new Handler();
	private int mProgressStatus=0;
	public static Context curr;

	ArrayAdapter<String> listAdapter;
	
	
	//TextView drop_list = ((TextView) findViewById(R.id.listView1));
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		curr = this;
		GeoLocation.setup_GeoLocation();
		p = new Planner(curr);
		
		         final ProgressBar mProgress = (ProgressBar) findViewById(R.id.ProgressBar);
		         // Start lengthy operation in a background thread
		         new Thread(new Runnable() {
		             public void run() {
		                 while (mProgressStatus < 100) {
		                     mProgressStatus = update();

		                     // Update the progress bar
		                     mHandler.post(new Runnable() {
		                         public void run() {
		                             mProgress.setProgress(mProgressStatus);
		                            
		                         }
		                     });
		                 }
		                 // Put a static cool glowing image when connected
		             
		             }
		     
		         }).start();
		         
		         	String [] test =new String[]{
		    			"Go running"+" hyde park in 1 hour",
		    			"Show up for work @SW17 6NE in 7 hours",
		    			"performance"+" @speaker's corner in 11 hours",
		    			"partaaaay"+" @XOYO in 17 hours",
		    			
		    			"Hackathon"+" @Imperial College"+" in 2 days",
		    			"Meeting with JPmorgan"+" @SW7 5NE in 1 week, 3 Days",
		    			"surprise valeria"+ " @valeria's place in 2 weeks",
		    			"bowling"," @SW15 8NE in 3 weeks"};
		    			
		                ListView t = (ListView)findViewById(R.id.content);
			    	listAdapter= new ArrayAdapter<String>(this,R.layout.calendarslot,test); 
			    	t.setAdapter(listAdapter);
	}
		 
		
		
		//fictitiousTask(); // i am trying to get to south ken tomorow at 9
		//p.addNewEntry("", latitude, longitude, arrival, postcode)
	
		
	//Commenting this out for my testing (Julek)
	
	private void fictitiousTask() {
		// TODO Auto-generated method stub
		// fork test for google maps API;
		Calendar d = Calendar.getInstance();
		d.add(Calendar.HOUR,1);
		
		//p.addNewEntry("UNI .. ", 51.49879, -0.17919, Date.parse(d.toString()),"SW7 1AZ");
		
		
		
	}

	public int update() {
		
		//TextView r = ((TextView) findViewById(R.id.textView1));
		//TextView v = ((TextView) findViewById(R.id.textView2));
		TextView s = ((TextView) findViewById(R.id.textView3));	
    		
		return 100;
	}
//		try {
//		    	
//			double latitude = GeoLocation.getLattitude();
//			double longitude = GeoLocation.getLongitude();
//			s.setText("Your Geographical cordinates are "+latitude+" "+longitude);
//			//v.setText("Long.: " + longitude);
//			//r.setText("Lat: " + latitude);
//			try {
//				try{
//					String postcode = GeoConversion.reverseGeocode(GeoLocation.getLocation());
//					s.setText("You were Located at: "+postcode);
//					p.notificationOfPosition(latitude, longitude, postcode);
//				}
//				catch(NoGeoConversion e)
//				{
//				    
//					s.setText("N/A");
//				}
//				
//			} catch (NoLocationError e) {
//				s.setText("N/A");
//			}
//		} catch (NoLocationError e) {
//			//r.setText("N/A");
//			//v.setText("N/A");
//		    return 100;
//		}
//		
//		return 100;
//	
//	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.item2:
	        	Intent f = new Intent(curr, Impeksettings.class);
				startActivity(f);
	            return true;
	        case R.id.item1:
	            Intent t = new Intent(curr,Impekadd.class);
	            startActivity(t);
	            return true;
	        case R.id.calendars:
	        	Intent c = new Intent(curr,Impekcals.class);
	        	startActivity(c);
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.mainmenu, menu);
	    return true;
	}
	
	
	public void reverseTest(String r){
		TextView rs = ((TextView) findViewById(R.id.textView3));
		rs.setText(r);
	}
		
}
