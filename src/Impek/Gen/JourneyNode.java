package Impek.Gen;

public class JourneyNode {
	
	enum Mode{
		Bus,Tube,Walk
	}
	private Time partialTime;
	
	private String origin; 
	private String arrival;
	
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getArrival() {
		return arrival;
	}
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}
	public Time getPartialTime() {
		return partialTime;
	}
	public void setPartialTime(Time partialTime) {
		this.partialTime = partialTime;
	}
}
