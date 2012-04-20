package Impek.Gen;



import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


public class Impekedit extends Activity{
	static Context curr;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.edittimeslot);
		curr = this;
		Intent t = this.getIntent();
		  
		//Bundle extra = t.getBundleExtra("Time");
		///Log.e ("Time!",""+extra.getInt("Time"));
		
	}
	

}