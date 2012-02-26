package Impek.Gen;

import java.util.Observable;
import java.util.Observer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Planner implements Observer {

	private WebServicesCom wsc = null;
	
	public Planner() {
		wsc = new WebServicesCom("http://4m9a.localtunnel.com/tfl");
		wsc.addObserver(this);
	}
	
	public void addLocation(String src_post, 
							String dst_post) {
		wsc.addLocation("locator", src_post, "locator", dst_post);
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
