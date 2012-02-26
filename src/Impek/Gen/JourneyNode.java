package Impek.Gen;

public class JourneyNode {
	
	enum Mode{
		Bus,Tube,Walk
	}
	
	private Mode mode;
	
	private Time partialTime;
	private Time arrivalTime;
	
	private String origin; 
	
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public Time getPartialTime() {
		return partialTime;
	}
	public void setPartialTime(Time partialTime) {
		this.partialTime = partialTime;
	}
	public Mode getMode() {
		return mode;
	}
	public void setMode(Mode mode) {
		this.mode = mode;
	}
	public Time getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
}
