package Impek.Gen;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;
/*
 * 
 */
/*
    Time for some commenting (Houssem ):
 	this class add and sets calendar profiles
 	ie, functionality:
    * adding a calendar ADT to store  . chose MySQL database for events ..
    * gonna have to run some queries for the display :D (for now only main screen accesses /wRites to it
    * for demoing
     
    * to each calendar setting particular calendar profile ( private , shared)
   this way users can have the luxury to maintain activity related calendar
	eg private/business/company/soccer club schedule 
	
	* added a nice search tab with suggestions too
	* 
	* set Active calendars.
	

 issues with the :
 	* how the hell do i manage synching ? (sigh)..
 	* clue: some standard format .. cross platform , use it!
 */



public class Impekcals extends TabActivity{
    boolean fullscreen = false;
    
    public void onCreate(Bundle savedInstanceState) {

		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.calendars);
		    Resources res = getResources(); // Resource object to get Drawables
		    TabHost tabHost = getTabHost();  // The activity TabHost
		    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
		    Intent intent;  // Reusable Intent for each tab
		    TabWidget e = getTabWidget();
		    if(fullscreen){
		            e.setVisibility(View.GONE);
		            fullscreen = true;
		            }
		            else{
		            e.setVisibility(View.VISIBLE);
		            fullscreen = false;
		            }
		    	
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


	
	 @Override
		public boolean onOptionsItemSelected(MenuItem item) {
		    // Handle item selection
		    switch (item.getItemId()) {
		        case R.id.fscreen:
		            
		            TabWidget e = getTabWidget();
		            if(!fullscreen){
		            e.setVisibility(View.GONE);
		            item.setTitle("collpase");
		            item.setTitleCondensed("tabs");
		            
		            }
		            else{
		            e.setVisibility(View.VISIBLE);
		            item.setTitle("fullscreen");
		            item.setTitleCondensed("full");
		            
		            }
		            fullscreen = !fullscreen;
		            // View tab = findViewById(R.layout.calendars);
		          // tab.setVisibility(View.INVISIBLE);
		            return true;
		        case R.id.helpcal:
		            
		            return true;
		        default:
		            return super.onOptionsItemSelected(item);
		    }
		}
		
		
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
		    MenuInflater inflater = getMenuInflater();
		    inflater.inflate(R.menu.calmenu, menu);
		    return true;
		}
}


