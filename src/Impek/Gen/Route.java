package Impek.Gen;

import java.util.ArrayList;

public class Route implements Comparable<Route>{
	// fields
	private ArrayList<JourneyNode> nodes = new ArrayList<JourneyNode>(); //list of pitstops
	private Time duration; // Total duration

	public void addNodeToRoute(JourneyNode n) {
		nodes.add(n);
	}
	
	public JourneyNode getNode(int i) {
		return nodes.get(i);
	}
	
	public int noNodes(){
		return nodes.size();
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
