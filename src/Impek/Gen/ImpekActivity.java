package Impek.Gen;

import java.sql.Date;
import java.util.Calendar;
import Impek.Gen.GeoConversion.NoGeoConversion;
import Impek.Gen.GeoLocation.NoLocationError;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ImpekActivity extends Activity {
	/** Called when the activity is first created. */
	private Planner p;
	private Handler mHandler = new Handler();
	private int mProgressStatus=0;
	public static Context curr;

	String [] Days = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
	String[]Months = {"January","February","March","April","May","June","July","August","October","November","December"};
	
	
	ArrayAdapter<Event> listAdapter;
	EventsDataSource databaseaccess;
	
	//TextView drop_list = ((TextView) findViewById(R.id.listView1));
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		curr = this;
		GeoLocation.setup_GeoLocation();
		p = new Planner(curr);
		TextView date = (TextView)findViewById(R.id.datetoday);
		Calendar cal  = Calendar.getInstance();
		date.setText(Days[cal.get(Calendar.DAY_OF_WEEK)-1]+", "
		+cal.get(Calendar.DATE) + " "
		+Months[cal.get(Calendar.MONTH)-1] +" "
		+ (cal.get(Calendar.YEAR))
			);
		
		         final ProgressBar mProgress = (ProgressBar) findViewById(R.id.ProgressBar);
		         // Start lengthy operation in a background thread
		         new Thread(new Runnable() {
		             public void run() {
		                 while (mProgressStatus < 100) {
		                     //mProgressStatus=update();

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
		         // demonstrates addition and access to the Database:D
		         registerControllers();
		         //DatabaseTest();
		                	
	}
		 
	
 private void registerControllers() {
	    // TODO Auto-generated method stub
     	databaseaccess= new EventsDataSource(this);
     	databaseaccess.open();
     	
	ListView t = (ListView)findViewById(R.id.content);
    	listAdapter= new ArrayAdapter<Event>(this,R.layout.calendarslot,databaseaccess.getAllEvents()); 
    	t.setAdapter(listAdapter);

     	t.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    	    public void onItemClick(android.widget.AdapterView<?> arg0, View arg1, int arg2, long arg3) {
    		Intent c = new Intent(curr,Impekedit.class);
        	c.putExtra("Time", arg0.getSelectedItemId());
    		startActivity(c);

    	    }
    	});
	t.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
    	    public boolean onItemLongClick(android.widget.AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
    		
    		
    		AlertDialog.Builder builder = new AlertDialog.Builder(curr);
    		builder.setMessage("Are you sure you want to cancel this event?")
    		       .setCancelable(false)
    		       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    		           public void onClick(DialogInterface dialog, int id) {
    		        	
    		               Event event  = (Event)listAdapter.getItem(arg2);
    		               databaseaccess.open();
    		               
    		        	databaseaccess.deleteEvent(event);
				listAdapter.remove(event);
			      databaseaccess.close();
    		           
    		           }
    		       
    		       
    		       
    		       })
    		       .setNegativeButton("No", new DialogInterface.OnClickListener() {
    		           public void onClick(DialogInterface dialog, int id) {
    		                dialog.cancel();
    		           }
    		       });
    		AlertDialog alert = builder.create();
		alert.show();
    		return true;
    	    }
    	});
    	  

	}

private static int testcase ;
public void DatabaseTest(){	
    databaseaccess.open();
 	String [] test =new String[]{
			"Go running",
			"Show up for work ",
			"performance",
			"partaaaay",
			
			"Hackathon",
			"Meeting with JPmorgan",
			"surprise valeria",
			"bowling"};
			

 	    databaseaccess.createEvent(test[testcase],testcase,"2012-11-01"
		    ,3,testcase+30.5,testcase +55,"Sloane Square", testcase);
	
 	
    	
 	 databaseaccess.close();
    	 listAdapter.notifyDataSetChanged();
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
		TextView s = ((TextView) findViewById(R.id.status));	
		s.setText("fetching location");
		try {
		    	
			double latitude = GeoLocation.getLattitude();
			double longitude = GeoLocation.getLongitude();
			
			s.setText("Your Geographical cordinates are "+latitude+" "+longitude);
			//v.setText("Long.: " + longitude);
			//r.setText("Lat: " + latitude);
				//try{
					//String postcode = GeoConversion.reverseGeocode(GeoLocation.getLocation());
					s.setText("You were Located at: "+"djerba midoun");//postcode);
					mProgressStatus=100;
					mHandler.notify();
					//p.notificationOfPosition(latitude, longitude, postcode);
				//}
//				catch(NoGeoConversion e)
//				{
//				    
//					s.setText("reverse lookup ..");
//				}
//				
			} catch (NoLocationError e) {
			    s.setText("no fix, check GPS settings");	
			   
			}
		
		
		return 100;
	
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.settingsmain:
	        	Intent f = new Intent(curr, Impeksettings.class);
				startActivity(f);
	            return true;
	        case R.id.nouveau:
	            Intent t = new Intent(curr,Impekadd.class);
	            startActivity(t);
	            return true;
	        case R.id.calendars:
	        	Intent c = new Intent(curr,Impekcals.class);
	        	startActivity(c);
	        	return true;
	        case R.id.helpmenu:
	            DatabaseTest();
	            //update();
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
		TextView rs = ((TextView) findViewById(R.id.status));
		rs.setText(r);
	}
		
}
