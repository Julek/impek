package Impek.Gen;

import java.util.Date;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class Planner extends Observable implements Observer {

	private WebServicesCom wsc = null;
	private Timer appTimer;
	private Settings set;
	
	public Planner(Context context) {
		set = new Settings(context);
		wsc = new WebServicesCom("http://4m9a.localtunnel.com/tfl");
		wsc.addObserver(this);
		appTimer = new Timer();
		appTimer.schedule(new PlannerTimerTask(), 100,1000);
	}
	
	public Settings getSettingsInstance() {
		return set;
	}
	
	private class PlannerTimerTask extends TimerTask {
		@Override
		public void run() {
			Iterator<SettingsNode> it = set.iterator();
			while(it.hasNext()) {
				SettingsNode sn = it.next();
				//if 30 m range and after departure
				// it.remove
				// notify ui of arrival!
				//if not in 30 m range and after departure
				// recalculate route
				//if date>arrival
				// send notification to ui
				// it.remove
			}
		}
	}
	
	public void addLocation(String src_post, 
							String dst_post) {
		wsc.addLocation("locator", src_post, "locator", dst_post);
	}
	
	public void addNewEntry(String locName, double latitude, double longitude,
							Date arrival) {
		if(!set.pushSettingsNode(locName, latitude, longitude, arrival, 
							false, new Date())) 
		{
			//notify ui of problem
		}
	}
	
	public void update(Observable observable, Object data) {
		if(observable instanceof WebServicesCom) {
			parseWebResponse(data);
		}
	}
	
	private void parseWebResponse(Object data) {
		Object[] respobj = (Object[]) data;
		int respcode = (Integer)respobj[0];
		switch(respcode) {
		case HttpConnection.DID_SUCCEED:
			try {
				if(respobj[1]!=null) {
					JSONArray js = (JSONArray)respobj[1];
					Route[] rs = new Route[js.length()];
					for(int i = 0;i<js.length();i++) {		
						rs[i] = new Route();
						JSONArray jsarrpath = js.getJSONObject(i).getJSONArray("option");
						for(int j = 0;j<jsarrpath.length();j++) {
							JourneyNode n = new JourneyNode();
							JSONObject jsobj = jsarrpath.getJSONObject(j);
							n.setOrigin(jsobj.getString("node"));
							String timeint = jsobj.getString("time");
							String[] v = timeint.split(" - ");
							Time t1 = new Time(v[0]);
							Time t2 = new Time(v[1]);
							n.setArrivalTime(t2);
							n.setPartialTime(t1.substract(t2));
							String type = jsobj.getString("type");
							if(type == "walk") {
								n.setMode(JourneyNode.Mode.Walk);
							}
							if(type == "bus") {
								n.setMode(JourneyNode.Mode.Bus);
							}
							if(type == "tube") {
								n.setMode(JourneyNode.Mode.Tube);
							}
							rs[i].addNodeToRoute(n);
						}
					}
					//do route thing...
				}
			} catch (JSONException e) {
				//should never get here :)
				Log.e(Planner.class.getName(), e.getMessage());
			}
			break;
		case HttpConnection.DID_ERROR:
			break;
		}
	}

}
