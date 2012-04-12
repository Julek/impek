package Impek.Gen;

import java.util.Calendar;
import java.util.Date;

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

public class Impekadd extends Activity{
	static Context curr;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addmenu);
		curr = this;
		
		Button alarmMe = (Button) findViewById(R.id.button3);
		alarmMe.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Calendar d = Calendar.getInstance();
				d.add(Calendar.SECOND,5);
				setAlarm(d.getTime(),52,"This is what's up");
				//GeoLocation.setup_GeoLocation();
				calendarAddselector();
			}
		});

		
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
//{
//
//public void onItemSelected(AdapterView arg0, View arg1,
//int arg2, long arg3) {
//	TextView txt=(TextView)findViewById(R.id.txt);
//	TextView temp=(TextView)arg1;
//	txt.setText(temp.getText());
//
//}
//
//public void onNothingSelected(AdapterView arg0) {
//// TODO Auto-generated method stub
//
//}
//
//});

}

}