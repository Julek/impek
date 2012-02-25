package Impek.Gen;

import java.util.Observable;
import java.util.Observer;

import org.json.JSONArray;
import org.json.JSONException;

public class Planner implements Observer {

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
				JSONArray js = new JSONArray((String)respobj[1]);
				
			} catch (JSONException e) {
				//should never get here :)
				e.printStackTrace();
			}
			break;
		case HttpConnection.DID_ERROR:
			break;
		}
	}

}
