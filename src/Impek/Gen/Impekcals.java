package Impek.Gen;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
/*
 * 
 */
/*
    Time for some commenting (Houssem ):
 	this class add and sets calendar profiles
 	ie, functionality:
    * adding a calendar ADT to store (yet to be defined!) . chose MySQL database for events ..
    * gonna have to run some queries for the display :D
     
    * to each calendar setting particular calendar profile ( private , shared)
   this way users can have the luxury to maintain activity related calendar
	eg private/business/company/soccer club schedule 
	
	* set Active calendars.
	

 issues with the :
 	* how the hell do i manage synching ? (sigh)..
 */



public class Impekcals extends TabActivity{
		
	public void onCreate(Bundle savedInstanceState) {

		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.calendars);
		    Resources res = getResources(); // Resource object to get Drawables
		    TabHost tabHost = getTabHost();  // The activity TabHost
		    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
		    Intent intent;  // Reusable Intent for each tab

		    // Create an Intent to launch an Activity for the tab (to be reused)
		    intent = new Intent().setClass(this, LocalCal.class);

		    // Initialize a TabSpec for each tab and add it to the TabHost
		    spec = tabHost.newTabSpec("global").setIndicator("",
			    res.getDrawable(R.drawable.globalcal))
			    .setContent(intent);
		    tabHost.addTab(spec);

		    // Do the same for the other tabs
		    intent = new Intent().setClass(this, GoogleCal.class);
		    spec = tabHost.newTabSpec("Googlecal").setIndicator("",
			    res.getDrawable(R.drawable.googlecal))
			    .setContent(intent);
		    tabHost.addTab(spec);

		    intent = new Intent().setClass(this, SettingsCal.class);
		    spec = tabHost.newTabSpec("Settings").setIndicator("",
			    res.getDrawable(R.drawable.localcal))
			    .setContent(intent);
		    tabHost.addTab(spec);


		    intent = new Intent().setClass(this, SearchEvent.class);
		    spec = tabHost.newTabSpec("search").setIndicator("",
			    res.getDrawable(R.drawable.searchcal))
			    .setContent(intent);
		    tabHost.addTab(spec);

		    tabHost.setCurrentTab(0);

	}



}


