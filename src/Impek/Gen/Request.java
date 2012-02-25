package Impek.Gen;

import java.util.Date;

public interface Request {
	
	
	 void setDestination(GeoLocation x);	
	 void setArrivalTime(Date x);
	 int getLabel();
	 void setLabel();
}
