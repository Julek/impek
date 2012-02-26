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
	private Planner p;

	public static Context curr;


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		curr = this;
		GeoLocation.setup_GeoLocation();
		p = new Planner(curr);
		update();
	}
		
	//Commenting this out for my testing (Julek)
	
	public void update() {
		
		TextView r = ((TextView) findViewById(R.id.textView1));
		TextView v = ((TextView) findViewById(R.id.textView2));
		TextView s = ((TextView) findViewById(R.id.textView3));	
		
		try {
			double latitude = GeoLocation.getLattitude();
			double longitude = GeoLocation.getLongitude();
			v.setText("Long.: " + longitude);
			r.setText("Lat: " + latitude);
			try {
				try{
					String postcode = GeoConversion.reverseGeocode(GeoLocation.getLocation());
					s.setText(postcode);
					p.notificationOfPosition(latitude, longitude, postcode);
				}
				catch(NoGeoConversion e)
				{
					s.setText("N/A");
				}
				
			} catch (NoLocationError e) {
				s.setText("N/A");
			}
		} catch (NoLocationError e) {
			r.setText("N/A");
			v.setText("N/A");
		}
		
		Button j = ((Button)findViewById(R.id.button1));
	
		j.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
				Intent f = new Intent(v.getContext(), Impeksettings.class);
				startActivity(f);
			}
			
		});
	
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
		
}
