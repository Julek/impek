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

public class Impekadd extends Activity{
	static Context curr;
	int time;
	String date;
	int group;
	double lat = 0;
	double longit = 0;
	String alias = "Ice cream shop";
	int duration = 1;
	EventsDataSource databaseaccess;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addmenu);
		curr = this;
		
			    	Bundle extra = getIntent().getExtras();
			    	 time =  extra.getInt("Time");
			    	 date = extra.getString("Date");
			    	 group = extra.getInt("Group");
			    	databaseaccess  =new EventsDataSource(curr);
			    	

			    	
			    	TextView t = (TextView) findViewById(R.id.datemaleable);
			    	t.setText("Schedule Event on " + date + "\n at " + time + "\n in Calendar " + group);
				//setAlarm(d.getTime(),52,"This is what's up");
				//GeoLocation.setup_GeoLocation();
				//calendarAddselector();
			    	//Log.e("dump",time + " " + date +  " " + group);
		registerControllers();
	}
	
	
private void registerControllers() {
    Button validate = (Button) findViewById(R.id.button1);
    final EditText t = (EditText) findViewById(R.id.editText1);
  
    validate.setOnClickListener(new View.OnClickListener() {
        
        public void onClick(View v) {
    	// TODO Auto-generated method stub
            createEvent( t.getText().toString(), time, date,duration, longit ,  lat ,  alias, group);
     
        }
    });
	    // TODO Auto-generated method stub
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



public void calendarAddselector(){ 
		final String [] items=new String[]{"Item1","Item2","Item3","Item4"};
		ArrayAdapter<String> ad=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,items);
		ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Spinner spin=(Spinner)findViewById(R.id.spinner1);
		spin.setAdapter(ad);
		//spin.setOnItemClickListener(new OnItemSelectedListener()

}

}