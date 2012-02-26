package Impek.Gen;

import java.util.ArrayList;

public class Route implements Comparable<Route>{
	// fields
	ArrayList<Node> nodes = new ArrayList<Node>(); //list of pitstops
	private Time duration; // Total duration

	public void addNodeToRoute(Node n) {
		nodes.add(n);
	}
	
	public Time getDuration() {
		return duration;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}

	public int compareTo(Route arg0) {
		return ((Route)arg0).getDuration().compareTo(getDuration());
	}

	
}
