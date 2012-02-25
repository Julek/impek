package Impek.Gen;

import java.util.ArrayList;

import android.text.format.Time;

public class Route implements Comparable{
	// fields
	ArrayList<Node> nodes = new ArrayList<Node>(); //list of pitstops
	Time Duration; // Total duration
	
	public int compareTo(Object arg0) {
		return Time.compare(((Route)arg0).Duration, this.Duration);
	}

	
}
