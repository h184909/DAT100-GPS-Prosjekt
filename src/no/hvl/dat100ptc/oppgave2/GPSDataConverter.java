package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import static java.lang.Integer.*;

public class GPSDataConverter {

	
	private static int TIME_STARTINDEX = 11; 

	public static int toSeconds(String timestr) {
		
		int secs;
		int hr, min, sec;
		
		hr = parseInt(timestr.substring(TIME_STARTINDEX, TIME_STARTINDEX + 2));
		min = parseInt(timestr.substring(TIME_STARTINDEX + 3, TIME_STARTINDEX + 5));
		sec = parseInt(timestr.substring(TIME_STARTINDEX + 6, TIME_STARTINDEX + 8));
		
		secs = hr*3600 + min * 60 + sec;
		
		return secs;
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		GPSPoint gpspoint;

		int time = toSeconds(timeStr);
		
		double latitude = Double.parseDouble(latitudeStr);
		double longitude = Double.parseDouble(longitudeStr);
		double elevation = Double.parseDouble(elevationStr);
		
		gpspoint = new GPSPoint(time, latitude, longitude, elevation);
		
		return gpspoint;
		
	}
	
}
