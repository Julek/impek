package Impek.Gen;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class Impeksettings extends Activity{
	static Context curr;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		curr = this;
	}
	
	
	
}
