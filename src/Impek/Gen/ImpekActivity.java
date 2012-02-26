package Impek.Gen;

import Impek.Gen.GeoConversion.NoGeoConversion;
import Impek.Gen.GeoLocation.NoLocationError;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;

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
		
	public void update() {
		EditText s = ((EditText) findViewById(R.id.editText1));
		//EditText v = ((EditText) findViewById(R.id.editText2));

		/*try {
			double latitude = GeoLocation.getLattitude();
			double longitude = GeoLocation.getLongitude();
			v.setText("Lat: " + latitude + "\nLon: " + longitude);
		} catch (NoLocationError e) {
			v.setText("N/A");
		}*/

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

}