package Impek.Gen;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class Impekadd extends Activity{
	static Context curr;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addmenu);
		curr = this;
		Calendar d = Calendar.getInstance();
		d.add(Calendar.SECOND,15);
		setAlarm(d.getTime(),52,"This is what's up");
		//GeoLocation.setup_GeoLocation();
	}
	
	
	public void setAlarm (Date d,int uid,String desc){

		 Intent intent = new Intent(curr, AlarmReceiver.class);
		 intent.putExtra("alarm_message", desc);

		 // In reality, you would want to have a static variable for the request code instead of 192837
		 PendingIntent sender = PendingIntent.getBroadcast(this,192837 , intent, PendingIntent.FLAG_UPDATE_CURRENT);
		 
		 AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		 am.set(AlarmManager.RTC_WAKEUP, d.getTime(), sender);
	//	PendingIntent pendingintent = PendingIntent.getBroadcast(Activity.this, 0, intent, Intent.FLAG_GRANT_READ_URI_PERMISSION);
	//	am.set(AlarmManager.RTC_WAKEUP, d.getTime(), pendingintent);
		
		// getting there .. 
	
}
}
