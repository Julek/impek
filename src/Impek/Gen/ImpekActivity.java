package Impek.Gen;

import Impek.Gen.GeoLocation.*;
import android.app.Activity;
import android.content.Context;
import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.widget.EditText;

import android.util.Log;

public class ImpekActivity extends Activity {
	/** Called when the activity is first created. */

	static Context curr;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		curr = this;
		GeoLocation.setup_GeoLocation();
		update();}
		
	public void update() {
		EditText s = ((EditText) findViewById(R.id.editText1));
		EditText v = ((EditText) findViewById(R.id.editText2));

		try {
			double latitude = GeoLocation.getLattitude();
			s.setText("" + latitude);
		} catch (NoLocationError e) {
			s.setText("N/A");
		}
		try {
			double longitude = GeoLocation.getLongitude();
			v.setText("" + longitude);
		} catch (NoLocationError e) {
			v.setText("N/A");
		}

	}
}