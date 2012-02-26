package Impek.Gen;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import android.os.Bundle;
 
public class AlarmReceiver extends BroadcastReceiver {
 
 @Override
 public void onReceive(Context context, Intent intent) {
   try {
	   	 Intent newIntent = new Intent(context, AlarmHandler.class);
	   //  newIntent.putExtra("alarm_message", );
	     newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	     context.startActivity(newIntent);
	     Bundle bundle = intent.getExtras();
	     //String message = bundle.getString("alarm_message");
	     //Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    } catch (Exception e) {
    	//Toast.makeText(context, "There was an error somewhere, but we still received an alarm", Toast.LENGTH_SHORT).show();
    	e.printStackTrace();
 
    }
 }
 
}