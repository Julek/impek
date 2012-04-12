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

// Alex and andrejs magic
// TODO: test with ficticious task 
// TODO: Link everything!! (UI PENDING)

public class Planner extends Observable implements Observer {

	private WebServicesCom wsc = null;
	private Timer appTimer;
	private Settings set;
	
	private double current_lat;
	private double current_long;
	private String current_post;
	
	public Planner(Context context) {
		set = new Settings(context);
		wsc = new WebServicesCom("http://tfl-data.herokuapp.com/tfl");
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
	
	
	public void notificationOfPosition( double latitude, double longitude, 
										String postcode ) {
		current_lat = latitude;
		current_long = longitude;
		current_post = postcode;
		Iterator<SettingsNode> it = set.iterator();
		while(it.hasNext()) {
			SettingsNode sn = it.next();
			wsc.addLocation("locator", current_post, 
							"locator", sn.getPostcode());
		}
	}
	
	public void addNewEntry(String locName, double latitude, double longitude,
							Date arrival, String postcode) {
		if(!set.pushSettingsNode(locName, latitude, longitude, arrival, 
							false, new Date(), postcode)) 
		{
			//notify ui of problem
		}
		else {
			notificationOfPosition(current_lat, current_long, current_post);
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
						Time duration = new Time();
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
							duration = duration.addition(n.getArrivalTime());
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
						rs[i].setDuration(duration);
					}
					Iterator<SettingsNode> it = set.iterator();
					while(it.hasNext()) {
						SettingsNode sn = it.next();
						for(int j = 0;j<js.length();j++)
							if(sn.getPostcode().toLowerCase().equals(rs[j].getNode(rs[j].noNodes()-1).getOrigin().toLowerCase())) {
								JourneyNode jn = rs[j].getNode(rs[j].noNodes()-1);
								Date d = new Date();
								d.setHours(jn.getArrivalTime().getHours());
								d.setMinutes(jn.getArrivalTime().getMinutes());
								
								Time t = new Time(sn.getDate().getHours(), sn.getDate().getMinutes());
								t = t.substract(jn.getArrivalTime());
								
								
								if(sn.getDate().getDay() == d.getDay() && sn.getDate().getMonth() == d.getMonth() 
																	   && sn.getDate().getYear() == d.getYear())
									if(sn.getDate().compareTo(d) == -1 && t.getHours() == 0 
										&& t.getMinutes() <= 10) {
										sn.setScheduled(true);
										Time dur = rs[j].getDuration();
										d.setHours(d.getHours() - dur.getHours());
										d.setMinutes(d.getMinutes() - dur.getMinutes());
										sn.setDepDate(d);
										setChanged();
										notifyObservers((Object)rs[j]);
									}
								
							}
					}
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
