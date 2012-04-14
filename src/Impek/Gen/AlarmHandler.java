package Impek.Gen;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class AlarmHandler extends Activity implements Observer{
	static Context curr;
	Planner s;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ShowPath();
		
		
		
		setContentView(R.layout.alarmshow);
		
		curr = this;
		
		rageAgainst();
	 	if(true){
	   		//SoundManager
	   	  SoundManager.play(curr, R.raw.siriusmo);
	   	  }
	}

	

	private void rageAgainst() {
		// TODO Auto-generated method stub
		final Button j =((Button) findViewById(R.id.button1));
		j.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				SoundManager.stop();
				j.setText("ZZZ  a few minutes won't hurt , thank god for Impek!");
			}
		});
		
		final Button r =((Button) findViewById(R.id.button2));
		r.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SoundManager.stop();
				 Intent newIntent = new Intent(curr, ImpekActivity.class);
				 newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				 curr.startActivity(newIntent);
			}
		});
	}



	private void ShowPath() {
		Settings r = s.getSettingsInstance();
		Iterator<SettingsNode> i = r.iterator();
		ListView s = new ListView(curr);
		
		while(i.hasNext()){
			SettingsNode currNode = i.next();
			
			if(currNode.isScheduled()){
				TextView d = new TextView(curr);
				d.setText(currNode.getLocationName()+ " in "+currNode.getDepDate());
				s.addView(d);
			}
			//currNode.
		}
	}
	public void update(Observable arg0, Object arg1) {
		Route r = (Route)arg1;
		for(int i = 0;i<r.noNodes();i++) {
			JourneyNode jn = r.getNode(i);
			
		}
	}
	
	
	
	
	
}
