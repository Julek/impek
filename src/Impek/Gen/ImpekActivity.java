package Impek.Gen;

import Impek.Gen.GeoLocation.*;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

public class ImpekActivity extends Activity {
    /** Called when the activity is first created. */

	static Context curr;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        curr = this;
        GeoLocation.setup_GeoLocation();
        try {
			double latitude = GeoLocation.getLattitude();
			Log.e("Impek.Gen", "Latitude: " + latitude);
		} catch (NoLocationError e) {
			Log.e("Impek.Gen", "Unable to obtain GeoLocation");
		}
        try {
			double longitude = GeoLocation.getLongitude();
			Log.e("Impek.Gen", "Longitude: " + longitude);
		} catch (NoLocationError e) {
			Log.e("Impek.Gen", "Unable to obtain GeoLocation");
		}   
    }
}