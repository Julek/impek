package Impek.Gen;

import java.util.Comparator;
import java.util.Date;

public class SettingsNode {

	private String locName;
	private double lat;
	private double lon;
	private Date date;
	
	private boolean scheduled;
	private Date depDate;
	
	public SettingsNode(String locName, double lat, double lon,
						Date date, boolean scheduled, Date depDate) {
		setLocationName(locName);
		setLatitude(lat);
		setLongitude(lon);
		setDate(date);
		setScheduled(scheduled);
		setDepDate(depDate);
	}
	
	public static class SettingsNodeComparator implements Comparator<SettingsNode> {

		public int compare(SettingsNode arg0, SettingsNode arg1) {
			return arg0.getDate().compareTo(arg1.getDate());
		}
		
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date dt) {
		this.date = dt;
	}

	public double getLongitude() {
		return lon;
	}

	public void setLongitude(double lon) {
		this.lon = lon;
	}

	public double getLatitude() {
		return lat;
	}

	public void setLatitude(double lat) {
		this.lat = lat;
	}

	public String getLocationName() {
		return locName;
	}

	public void setLocationName(String locName) {
		this.locName = locName;
	}
	
	@Override
	public String toString() {
		return "{ \"name\":\"" + getLocationName() +
				"\",\n\"latitude\":\"" + getLatitude() + 
				"\",\n\"longitude\":\"" + getLongitude() + 
				"\",\n\"arrival\":\"" + Settings.dateFormat.format(getDate()).toString() +
				"\",\n\"scheduled\":\"" + String.valueOf(isScheduled()) +
				"\",\n\"departure\":\"" + Settings.dateFormat.format(getDepDate()).toString() +
				"\n }";
	}

	public boolean isScheduled() {
		return scheduled;
	}

	public void setScheduled(boolean scheduled) {
		this.scheduled = scheduled;
	}

	public Date getDepDate() {
		return depDate;
	}

	public void setDepDate(Date depDate) {
		this.depDate = depDate;
	}
}
