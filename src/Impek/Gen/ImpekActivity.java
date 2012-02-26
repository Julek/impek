package Impek.Gen;

import Impek.Gen.GeoConversion.NoGeoConversion;
import Impek.Gen.GeoLocation.NoLocationError;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ImpekActivity extends Activity {
	/** Called when the activity is first created. */

	static Context curr;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		curr = this;
		GeoLocation.setup_GeoLocation();
		update();
	}
		
	//Commenting this out for my testing (Julek)
	
	public void update() {
		
		
		TextView r = ((TextView) findViewById(R.id.textView1));
		TextView v = ((TextView) findViewById(R.id.textView2));

		try {
			double latitude = GeoLocation.getLattitude();
			double longitude = GeoLocation.getLongitude();
			v.setText("Lat: " + latitude + "\nLon: " + longitude);
			r.setText("Lat: " + latitude);
		} catch (NoLocationError e) {
			r.setText("N/A");
		}
		try {
			double longitude = GeoLocation.getLongitude();
			v.setText("Long.: " + longitude);
		} catch (NoLocationError e) {
			v.setText("N/A");
		}
		
		TextView s = ((TextView) findViewById(R.id.textView3));
		//EditText v = ((EditText) findViewById(R.id.editText2));
		//@testing and experimenting ui:
		//reverseTest("Reverse Lookup");
	
		
		Button j = ((Button)findViewById(R.id.button1));
	
		j.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent f = new Intent(v.getContext(), Impeksettings.class);
				startActivity(f);
			}
			
		});
		
		
		try {
			try{
				s.setText(GeoConversion.reverseGeocode(GeoLocation.getLocation()));
			}
			catch(NoGeoConversion e)
			{
				s.setText("N/A");
			}
			
		} catch (NoLocationError e) {
			s.setText("N/A");
		} 
	
	}
	
	
	
	
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
		
//	public void update() {
//		TextView s = ((TextView) findViewById(R.id.textView1));
//		//TextView v = ((TextView) findViewById(R.id.textView2));
//
//		/*try {
//			double latitude = GeoLocation.getLattitude();
//			double longitude = GeoLocation.getLongitude();
//			v.setText("Lat: " + latitude + "\nLon: " + longitude);
//			s.setText("Lat: " + latitude);
//		} catch (NoLocationError e) {
//			s.setText("N/A");
//		}
//		try {
//			double longitude = GeoLocation.getLongitude();
//			v.setText("Long.: " + longitude);
//		} catch (NoLocationError e) {
//			v.setText("N/A");
//		}*/
//

//		
//	}
}
