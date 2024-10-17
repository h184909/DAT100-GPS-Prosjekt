package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import java.util.Locale;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.TODO;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;

		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		return min;
		
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		double[] latitudes = new double[gpspoints.length];
		
		for (int i = 0; i < latitudes.length; i++) {
			latitudes[i] = gpspoints[i].getLatitude();
		}
		return latitudes;
		
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double[] longitudes = new double[gpspoints.length];
		
		for (int i = 0; i < longitudes.length; i++) {
			longitudes[i] = gpspoints[i].getLongitude();
		}
		return longitudes;
		

	}

	private static final int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double latitude1 = (gpspoint1.getLatitude()*Math.PI) /180;
		double longitude1 = (gpspoint1.getLongitude()*Math.PI) /180;
		double latitude2 = (gpspoint2.getLatitude()*Math.PI) /180;
		double longitude2 = (gpspoint2.getLongitude()*Math.PI) /180;
		
		double deltaLat = latitude2 - latitude1;
		double deltaLong = longitude2 - longitude1;
		
		double a = ((Math.sin(deltaLat/2)*(Math.sin(deltaLat/2)))) + (Math.cos(latitude1)*Math.cos(latitude2)* ((Math.sin(deltaLong/2)*(Math.sin(deltaLong/2)))));

		double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		double d = R*c; 
		
		return d;
	}
	
	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs = gpspoint2.getTime() - gpspoint1.getTime();
		double speed = distance(gpspoint1, gpspoint2) / secs;

		return speed;
	}

	public static String formatTime(int secs) {

		int timer = secs / 3600;
		int minutt = (secs % 3600) / 60;
		int sekund = secs % 60;
		
		
		String TIMESEP = ":";
		String timestr = String.format("%02d%s%02d%s%02d", timer, TIMESEP, minutt, TIMESEP, sekund);
		
		return String.format("%10s", timestr);
		
	}
	

	public static String formatDouble(double d) {

		 String str = String.format(Locale .US, "%10.2f", d);
		   
		 return str;
	}
}
