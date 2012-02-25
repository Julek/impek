package Impek.Gen;

import java.util.Observable;
import java.util.Observer;

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
			break;
		case HttpConnection.DID_ERROR:
			break;
		}
	}

}
