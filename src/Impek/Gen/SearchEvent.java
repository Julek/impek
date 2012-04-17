package Impek.Gen;
import java.util.List;


import android.app.Activity;
import android.app.SearchManager;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;



public class SearchEvent extends Activity{
		static Context curr;
		EventsDataSource database = new EventsDataSource(this); 
		
		public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    
		    
		 setContentView(R.layout.search);
		   
		    curr = this;
		    // Get the intent, verify the action and get the query
		    Intent intent = getIntent();
		    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
		      String query = intent.getStringExtra(SearchManager.QUERY);
		      search(query);
		      }
		    
		}

		
		


		
		private void search(String query) {
		    // TODO Auto-generated method stub
		    database.open();
		   ListView t = (ListView) findViewById(R.id.searchres);
		    ArrayAdapter<Event> listAdapter= new ArrayAdapter<Event>(curr,R.layout.calendarslot,database.runsearch(query)); 
		    t.setAdapter(listAdapter);
		    database.close();
		    
		}






		public Activity getActivityContext() {
		
		    if (getParent() instanceof TabActivity) {
		
		        return getParent();
		
		    } else {
		
		        return this;
		
		    }
		
		}

		
		
		public boolean onSearchRequested() {
		
		    Activity context = getActivityContext();
		
		    if (context == this) {
		
		        return super.onSearchRequested();
		
		    } else {
		
		        return context.onSearchRequested();
		
		    }
		
		}


}