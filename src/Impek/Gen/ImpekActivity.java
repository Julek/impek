package Impek.Gen;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;

public class ImpekActivity extends Activity {
    /** Called when the activity is first created. */

	static Context curr;
	
	static GeoLocation locator;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        curr = this;
        locator = new GeoLocation();
        EditText s = ((EditText)findViewById(R.id.editText1));
        s.setText(""+locator.getLattitude());
        EditText v = ((EditText)findViewById(R.id.editText1));
        s.setText(""+locator.getLongitude());
        //System.out.println("Latitude: " + locator.getLattitude() + "\nLongitude: " + locator.getLongitude());
    }
}