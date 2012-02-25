package Impek.Gen;

import android.text.format.Time;

public class Node {
	
	enum Mode{
		Bus,Tube,Walk
	}
	Time partialTime; // watch out for the import
	
	GeoLocation Origin; 
	GeoLocation Arrival;
	
	
}
