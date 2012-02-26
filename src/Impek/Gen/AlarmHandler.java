package Impek.Gen;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class AlarmHandler extends Activity{
	static Context curr;
	Settings s = new Settings(this);
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ShowPath();
		setContentView(R.layout.alarmshow);
		curr = this;
		//GeoLocation.setup_GeoLocation();
	}

	private void ShowPath() {
		
		Iterator i = Settings.
		
	}
	
	
	
	
	
}
