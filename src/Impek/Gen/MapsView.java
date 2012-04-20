package Impek.Gen;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

import android.app.Activity;
import android.os.Bundle;

public class MapsView extends MapActivity{

    
    
    @Override
    protected boolean isRouteDisplayed() {
	// TODO Auto-generated method stub
	return false;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapvi);
        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
    }
}