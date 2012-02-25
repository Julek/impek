package Impek.Gen;

import android.location.*;
import android.os.Bundle;
import android.content.*;
import java.util.*;

public class GeoLocation extends Observable {

	LocationManager locationManager;
	Location location;
	LocListener listener;
	
	public GeoLocation()
	{
		LocationManager locationManager = (LocationManager) ImpekActivity.curr.getSystemService(Context.LOCATION_SERVICE);
		listener = new LocListener();
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 5, listener);
	}
	
	double getLattitude()
	{
		return location.getLatitude();
	}
	
	double getLongitude()
	{
		return location.getLongitude();
	}
	
	double getAccuracy()
	{
		return location.getAccuracy();
	}
	
	private class LocListener implements LocationListener
	{

		public void onLocationChanged(Location loc) {
			location = loc;
		}

		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub
			
		}

		public void onProviderEnabled(String arg0) {
			// TODO Auto-generated method stub
			
		}

		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
