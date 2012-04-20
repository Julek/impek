package Impek.Gen;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.Inflater;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import android.widget.ListView;
import android.widget.TextView;

import android.os.Bundle;


/*
 * 
 * This is the Awesome Calendar UI I designed :D 
 * 
 * sorry for lack of comments ,documentation will come when no exams/deadlines are due :D
 * 
 */

public class LocalCal extends Activity implements OnGestureListener {
		static Context curr;
		Calendar currentView;
		String [] Days = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
		String[]Months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"};
		
		boolean [] eventExists = new boolean [24];
		private GestureDetector gestureScanner; 
		ArrayAdapter<Event> listAdapter;
		
		int groupId;
		
		EventsDataSource databaseaccess;
		
		
		
		
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.calendargeneric);
			curr = this;
		
			databaseaccess = new EventsDataSource(this);
			registerControllers();
			setCurrentDay(Calendar.getInstance());
			generateCurrentView();
			
			
		}
		
		
		private void registerControllers(){
	
		ImageButton backmonth= (ImageButton)(findViewById(R.id.lastmonth));
		ImageButton backweek = (ImageButton)(findViewById(R.id.lastweek));
		ImageButton nextweek = (ImageButton)(findViewById(R.id.nextweek));
		ImageButton nextmonth= (ImageButton)(findViewById(R.id.nextmonth));	
		backmonth.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar t = currentView;
				t.add(Calendar.MONTH, -1);
				setCurrentDay(t);
			}
			
			
		});
		backweek.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar t = currentView;
				t.add(Calendar.DATE, -7);
				setCurrentDay(t);
			}
			
			
		});
		
		
		
	      gestureScanner = new GestureDetector(this); 
			
	        nextweek.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar t = currentView;
				t.add(Calendar.DATE, +7);
				setCurrentDay(t);
			}
		});
		  
		   
	  
		nextmonth.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar t = currentView;
				t.add(Calendar.MONTH, +1);
				setCurrentDay(t);
			}
		});
		final String [] items=new String[]{"Global","Work","Play","kids football"};
		ArrayAdapter<String> ad=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,items);
		ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Spinner spin=(Spinner)findViewById(R.id.spinner1);
		spin.setAdapter(ad);
		spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		        groupId = pos;
		    }
		    public void onNothingSelected(AdapterView<?> parent) {
		    }
		});
		
		}
		
		private void generateCurrentView(){
			//some db sttaff
		    	databaseaccess.open();
		    	ListView t = (ListView)findViewById(R.id.all_stuff);
		    	String today = currentView.get(Calendar.YEAR)+"-"+(currentView.get(Calendar.MONTH))+"-"+
		    	currentView.get(Calendar.DATE);
		    	
		    	Log.e("today",today);
		    	listAdapter= new ArrayAdapter<Event>(this,R.layout.calendarslot,databaseaccess.getEventsByDay(today)); 
		    	t.setAdapter(listAdapter);
		    	databaseaccess.close();
		    	t.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		    	    public void onItemClick(android.widget.AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
		    		if(eventExists[arg2]){
		    		Intent c = new Intent(curr,Impekedit.class);
		    		
		        	c.putExtra("Time", arg2);
		        	Log.e("dump", currentView.get(Calendar.YEAR)+"");
		        	c.putExtra("Year",currentView.get(Calendar.YEAR));
		        	c.putExtra("Month", currentView.get(Calendar.MONTH));
		        	c.putExtra("Date",currentView.get(Calendar.DATE));
		        	c.putExtra("Dayweek",currentView.get(Calendar.DAY_OF_WEEK));
		        	c.putExtra("Group",groupId);
		        	startActivity(c);
		    		}
		    		else{
		    		Intent c = new Intent(curr,Impekadd.class);
		    	    	c.putExtra("Time", arg2);
		        	c.putExtra("Year",currentView.get(Calendar.YEAR));
		        	c.putExtra("Month", currentView.get(Calendar.MONTH));
		        	c.putExtra("Date",currentView.get(Calendar.DATE));
		        	c.putExtra("Dayweek",currentView.get(Calendar.DAY_OF_WEEK));
		        	c.putExtra("Group",groupId);
		        	startActivity(c);
		    		}
		    	    }

			  
		    	});
			t.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
		    	    public boolean onItemLongClick(android.widget.AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
		    		
		    		if(eventExists[arg2]){
		    		AlertDialog.Builder builder = new AlertDialog.Builder(curr);
		    		builder.setMessage("Are you sure you want to cancel this event?")
		    		       .setCancelable(false)
		    		       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		    		           public void onClick(DialogInterface dialog, int id) {
		    		                
		    		           }
		    		       })
		    		       .setNegativeButton("No", new DialogInterface.OnClickListener() {
		    		           public void onClick(DialogInterface dialog, int id) {
		    		                dialog.cancel();
		    		           }
		    		       });
		    		AlertDialog alert = builder.create();
				alert.show();
		    		}
		    		return true;
		    	    }
		    	});
		    	  

		}
		
		
		private int getWeek(Calendar t){
			return t.get(Calendar.WEEK_OF_YEAR);
		}
		
		
		private void setCurrentDay(Calendar t){
			currentView=t;	
			
			TextView generic = (TextView)(findViewById(R.id.generalDate));
			
			String genText = t.get(Calendar.DAY_OF_MONTH)+" "
				+Months[t.get(Calendar.MONTH)]+" "+t.get(Calendar.YEAR);
			
			generic.setText(genText);
			TextView spec = (TextView)(findViewById(R.id.Dayoftheweek));		
			
			
			genText = Days[t.get(Calendar.DAY_OF_WEEK)-1] ;
			
			spec.setText(genText); //reuse of variable purpose only ..
			
			TextView cal = (TextView)(findViewById(R.id.calendarselection));
			genText = "Week "+ getWeek(t);
			cal.setText(genText);
			
			
			// this is where the SQL magic should happen .. but no we'll just settle with
			// this for now
			//mockData();
			generateCurrentView();
		}



		private void previousDay() {
			
			Calendar t = currentView;
			t.add(Calendar.DATE, -1);
			setCurrentDay(t);
			animatePrevDay();
			
		}

		    	
		

		private void nextDay() {
			Calendar t = currentView;
			t.add(Calendar.DATE, +1);
			setCurrentDay(t);
			animatenextDay();
			
			
		}

		
		
		
		// motion control for the cool ass flipping .. :DD
		public boolean onDown(MotionEvent e) {
			// TODO Auto-generated method stub
			return true;
		}


		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
		     if(velocityX >= 1500){ 
		          previousDay(); 
		     } 
		     if(velocityX <= -1500){ 
		                nextDay(); 
		     } 
		     return true;
		}



		public void onLongPress(MotionEvent e) {
			// TODO Auto-generated method stub
			
		}


		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			// TODO Auto-generated method stub
			return true;
		}


		public void onShowPress(MotionEvent e) {
			// TODO Auto-generated method stub
			
		}


		public boolean onSingleTapUp(MotionEvent e) {
			// TODO Auto-generated method stub
			return true;
		}
		
		
		
		// EXTREMELY IMPORTANT , PLEASE DO NOT TOUCH IF NOT HOUSSEM :p
		 @Override 
		    public boolean dispatchTouchEvent(MotionEvent ev){ 
		        super.dispatchTouchEvent(ev); 
		        return gestureScanner.onTouchEvent(ev); 
		    } 
		
		//extra stuff for doing things fancy: :D
		 private void animatenextDay(){
		     final RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout);
 
		     ListView origin = (ListView) findViewById(R.id.all_stuff);
		     flipAnimator animator = new flipAnimator(origin, origin,
	                        origin.getWidth() / 2, origin.getHeight() / 2);
	                if (origin.getVisibility() == View.GONE) {
	                    animator.reverse();
	                }
	                layout.startAnimation(animator);
	                
		 }
		 private void animatePrevDay(){
		     
		     final RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout);
		     
		     ListView origin = (ListView) findViewById(R.id.all_stuff);
		     flipAnimator animator = new flipAnimator(origin, origin,
	             
			     origin.getWidth() / 2, origin.getHeight() / 2);
		     animator.reverse();
		     if (origin.getVisibility() == View.GONE) {
	                    animator.reverse();
	                }
	                layout.startAnimation(animator);
	                
		 }
		 
		
		
}