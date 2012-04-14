package Impek.Gen;

import android.util.Log;


public class Event {
	private long id;
	private String comment;
	private int time;
	private String date;
	private int duration;
	private double longitude;
	private double lat;
	private String alias;
	private int groupid;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
	    	return time +":00      "+ comment +" "+date +" " + lat+" "+ longitude + " "+ alias.toString() + " " +duration + " "+groupid;
	}

	
	public int getTime() {
	    return time;
	}

	public void setTime(int time) {
	    this.time = time;
	}

	public String getDate() {
	    return date;
	}

	public void setDate(String date) {
	    this.date = date;
	}

	public int getDuration() {
	    return duration;
	}

	public void setDuration(int duration) {
	    this.duration = duration;
	}

	public double getLongitude() {
	    return longitude;
	}

	public void setLongitude(double longitude) {
	    this.longitude = longitude;
	}

	public String getAlias() {
	    return alias;
	}

	public void setAlias(String alias) {
	    this.alias = alias;
	}

	public double getLat() {
	    return lat;
	}

	public void setLat(double lat) {
	    this.lat = lat;
	}


	public int getGroupid() {
	    return groupid;
	}

	public void setGroupid(int groupid) {
	    this.groupid = groupid;
	}
}
