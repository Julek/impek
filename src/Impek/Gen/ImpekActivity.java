package Impek.Gen;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class ImpekActivity extends Activity {
<<<<<<< HEAD
    /** Called when the activity is first created. */

	static Context curr;
	
	static GeoLocation locator;
=======
>>>>>>> 55e41697c156e25b184594f1eee0c0582319d7d8
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        curr = this;
        locator = new GeoLocation();
        System.out.println("Latitude: " + locator.getLattitude() + "\nLongitude: " + locator.getLattitude());
    }
}