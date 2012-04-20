package Impek.Gen;

import java.util.Calendar;
import java.util.Date;
import android.widget.EditText;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class Impekadd extends Activity{
	static Context curr;
	int time;
	String date;
	int group;
	double lat = 0;
	double longit = 0;
	String alias = "No alias";
	int duration = 1;
	EventsDataSource databaseaccess;
	
	
	
	 int yearraw ;
    	 int monthraw ;
    	 int dateraw;
    	 int dayofweek;
	String [] Days = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
	String [] Months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"};
	 String presentable = "Intent error";
	 final String [] items={"Global","Work","Play","kids football"};    	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addmenu);
		curr = this;
		
			    	Bundle extra = getIntent().getExtras();
			    	
			    	 time =  extra.getInt("Time");
			    	 yearraw =  extra.getInt("Year");
			    	 monthraw =  extra.getInt("Month");
			    	 dateraw =  extra.getInt("Date");
			    	 dayofweek = extra.getInt("Dayweek");
			    	 date = toSQL(yearraw,monthraw,dateraw);
			    	 group = extra.getInt("Group");
			    	databaseaccess  =new EventsDataSource(curr);
			    	
			    	presentable = topresentable(dateraw,monthraw,yearraw,dayofweek);
			    	
			    	TextView t = (TextView) findViewById(R.id.datemaleable);
			    	t.setText("Schedule Event on \n" + presentable + "\n at " + time + "\n in Calendar " + items[group]);
				//setAlarm(d.getTime(),52,"This is what's up");
				//GeoLocation.setup_GeoLocation();
				//calendarAddselector();
			    	//Log.e("dump",time + " " + date +  " " + group);
		registerControllers();
	}
	
	
private String topresentable(int dateraw, int monthraw,
		int yearraw, int dayofweek) {
	    return Days[dayofweek-1] + ", "  + dateraw +" " + Months[monthraw] + " " +yearraw;
	}


private void registerControllers() {
    Button validate = (Button) findViewById(R.id.button1);
    final EditText t = (EditText) findViewById(R.id.editText1);
  
    validate.setOnClickListener(new View.OnClickListener() {
        
        public void onClick(View v) {
    	// TODO Auto-generated method stub
            createEvent( t.getText().toString(), time, date,duration, longit ,  lat ,  alias, group);
            onBackPressed();
            Context context = getApplicationContext();
            CharSequence text = "The Event is now created\n You won't be late for this event!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    });


    Button placeOnmap = (Button) findViewById(R.id.button2);
    placeOnmap.setOnClickListener(new View.OnClickListener() {
	public void onClick(View v) {
    	// TODO Auto-generated method stub
    	Intent t = new Intent(curr,MapsView.class);
        startActivity(t);
	}
    });
}




private String toSQL(int yearraw, int monthraw , int dateraw) {
	// returns an SQL representation of the current date
	return  yearraw+"-"+monthraw+"-"+dateraw;
    }









private void createEvent(String event, int time,String date,int duration,double longitude , double lat , String alias,int groupId) {
		databaseaccess.open();
		 databaseaccess.createEvent(event, time, date, duration, longitude, lat, alias, groupId);
		Log.e("gor","here");
	databaseaccess.close();

}
	
	
	static int requestId = 192837;
	public void setAlarm (Date d,int uid,String desc){
		 Intent intent = new Intent(curr, AlarmReceiver.class);
		// intent.putExtra("alarm_message", desc);
		 // In reality, you would want to have a static variable for the request code instead of 192837
		 PendingIntent sender = PendingIntent.getBroadcast(this,requestId++ , intent, PendingIntent.FLAG_UPDATE_CURRENT);
		 AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		 am.set(AlarmManager.RTC_WAKEUP, d.getTime(), sender);
		}


}