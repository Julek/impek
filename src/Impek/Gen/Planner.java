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
					Route[] rs = new Route[js.length()/5];
					for(int i = 0;i<js.length();i+=5) {
						JSONObject jso = js.getJSONObject(i+3);//duration - i+3;
						Time totalDur = new Time((String)jso.get("val"));
						rs[i/5] = new Route();
						rs[i/5].setDuration(totalDur);
						
						JSONArray jsarrpath = jso.getJSONArray("path");
						for(int j = 0;j<jsarrpath.length();j++) {
							
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
