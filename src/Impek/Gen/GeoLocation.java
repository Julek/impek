package Impek.Gen;

import android.location.*;
import android.os.Bundle;
import android.util.Log;
import android.content.*;
import java.util.*;

public class GeoLocation {

	static LocationManager locationManager;
	static Location location;
	static LocListener listener;
	
	public static void setup_GeoLocation()
	{
		location = null;
		locationManager = (LocationManager) ImpekActivity.curr.getSystemService(Context.LOCATION_SERVICE);
		listener = new GeoLocation.LocListener();
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
	}
	
	static double getLattitude() throws NoLocationError
	{
		Location ret = null;
		
		if(location != null)
			ret = location;
		
		else
		{
			if(locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER) == null || locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER) == null)
			{
				if(locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER) == null && locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER) == null)
					ret = null;
				else if(locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER) == null)
					ret = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				else
					locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			}
			else if(locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getTime() < locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getTime())
				ret = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			else
				ret = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		}
		
		if(ret == null)
			throw new NoLocationError();
		
		return ret.getLatitude();
	}
	
	static double getLongitude() throws NoLocationError
	{
		Location ret = null;
		
		if(location != null)
			ret = location;
		
		else
		{
			if(locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER) == null || locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER) == null)
			{
				if(locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER) == null && locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER) == null)
					ret = null;
				else if(locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER) == null)
					ret = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				else
					locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			}
			else if(locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getTime() < locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getTime())
				ret = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			else
				ret = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		}
		
		if(ret == null)
				throw new NoLocationError();
		
		return ret.getLongitude();
	}
	
	static double getAccuracy()
	{
		return location.getAccuracy();
	}
	
	public static class LocListener implements LocationListener
	{

		public void onLocationChanged(Location loc) {
			((ImpekActivity) ImpekActivity.curr).update(); 
			location = loc;
		}

		public void onProviderDisabled(String arg0) {
			
		}

		public void onProviderEnabled(String arg0) {
			
		}

		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			
		}
		
	}
	
	static class NoLocationError extends Exception
	{
		private static final long serialVersionUID = 1L;
		
	}
	
}
