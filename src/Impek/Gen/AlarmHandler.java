package Impek.Gen;

import java.io.ObjectInput;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.app.LauncherActivity.ListItem;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class AlarmHandler extends Activity implements Observer{
	static Context curr;
	Planner s;
	
	public AlarmHandler(Planner p) {
		// TODO Auto-generated constructor stub
		s= p;
		s.addObserver(this);
	}
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ShowPath();
		setContentView(R.layout.alarmshow);
		curr = this;
		//GeoLocation.setup_GeoLocation();
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
