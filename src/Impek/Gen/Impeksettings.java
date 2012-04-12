package Impek.Gen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Impeksettings extends Activity{
	static Context curr;

	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		curr = this;
		
		Button j = (Button) findViewById(R.id.button1);
		j.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			 Intent newIntent = new Intent(curr, ImpekActivity.class);
			 newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			 curr.startActivity(newIntent);
			}
	});
	}
	
	
	
}
