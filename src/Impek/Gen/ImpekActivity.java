package Impek.Gen;

import Impek.Gen.GeoLocation.*;
import android.app.Activity;
import android.content.Context;
import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;
import android.widget.TextView;

import android.util.Log;

public class ImpekActivity extends Activity {
	/** Called when the activity is first created. */

	static Context curr;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		curr = this;
		GeoLocation.setup_GeoLocation();
		update();
		reverseTest("Reverse Lookup");
	}
	
//public void testHoussem1(){
//		
//		for(int i = 0 ; i < serv.length;i++){
//		
//		TextView r = new TextView(this);
//		
//		r.setText(serv[i]);
//			
//		}
//		
//	}
//	
//	
	
	public void reverseTest(String r){
		TextView rs = ((TextView) findViewById(R.id.textView3));
		rs.setText(r);
	}
		
	public void update() {
		TextView s = ((TextView) findViewById(R.id.textView1));
		TextView v = ((TextView) findViewById(R.id.textView2));

		try {
			double latitude = GeoLocation.getLattitude();
			s.setText("Lat: " + latitude);
		} catch (NoLocationError e) {
			s.setText("N/A");
		}
		try {
			double longitude = GeoLocation.getLongitude();
			v.setText("Long.: " + longitude);
		} catch (NoLocationError e) {
			v.setText("N/A");
		}

	}
}