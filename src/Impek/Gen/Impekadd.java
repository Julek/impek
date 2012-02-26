package Impek.Gen;

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
		//GeoLocation.setup_GeoLocation();
	}
	
	
	public void setAlarm (Date d,int label,String desc){

		AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);	
		//PendingIntent pendingintent = PendingIntent.getBroadcast(Activity.this, 0, intent, Intent.FLAG_GRANT_READ_URI_PERMISSION);
		//am.set(AlarmManager.RTC_WAKEUP, d.getTime(), pendingintent);
		// getting there .. 
	}
	
	
}
