package Impek.Gen;

import java.util.Observable;

import org.json.JSONArray;
import org.json.JSONException;

import android.os.Handler;
import android.os.Message;

public class WebServicesCom extends Observable {

	private String server;

	public WebServicesCom(String server) {
		setServer(server);
	}

	private class LocationCommunicationHandler extends Handler {
		public void handleMessage(Message message) {
			Object[] respobj = new Object[2];
			switch (message.what) {
			case HttpConnection.DID_SUCCEED:
				String response = (String) message.obj;
				JSONArray js = null;
				try {
					js = new JSONArray(response);
					respobj[0] = HttpConnection.DID_SUCCEED;
				} catch (JSONException e1) {
					respobj[0] = HttpConnection.DID_ERROR;
					e1.printStackTrace();
				}
				respobj[1] = js;
				notifyObservers((Object) respobj);
				break;
			case HttpConnection.DID_ERROR:
				respobj[0] = HttpConnection.DID_ERROR;
				respobj[1] = null;
				Exception e = (Exception) message.obj;
				e.printStackTrace();
				notifyObservers((Object) respobj);
				break;
			default:
				break;
			}
		}
	}

	public void addLocation(String typesrc, String locsrc, String typedst,
			String locdst) {
		HttpConnection htcon = new HttpConnection(
				new LocationCommunicationHandler());
		String url = "/" + typesrc + "/" + locsrc + "/" + typedst + "/"
				+ locdst;
		htcon.get(getServer() + url);
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}
}
