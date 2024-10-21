package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

import no.hvl.dat100ptc.TODO;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	public double totalDistance() {

		double totalDistance = 0.0;

		// Gå gjennom alle GPS-punktene og beregn distansen mellom påfølgende punkter
		for (int i = 0; i < gpspoints.length - 1; i++) {
			// Beregn distansen mellom punkt i og i+1
			double distanceBetweenPoints = GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
			
			// Legg til distansen mellom de to punktene i totalDistance
			totalDistance += distanceBetweenPoints;
		}
		
		return totalDistance; // Returner total distanse i kilometer

	}

	public double totalElevation() {

		double totalElevation = 0;

		for (int i = 0; i < gpspoints.length - 1; i++) {
			double currentElevation = gpspoints[i].getElevation();    // Høyden ved punkt i
			double nextElevation = gpspoints[i + 1].getElevation();   // Høyden ved punkt i+1
	
			// Sjekk om det er en oppoverbakke (når neste punkt er høyere)
			if (nextElevation > currentElevation) {
				totalElevation += (nextElevation - currentElevation); // Legg til bare når det går oppover
			}
		}
	
		return totalElevation; // Returner total oppovergående høydeforskjell
		
	}

	public int totalTime() {

		if (gpspoints.length < 2) {
            return 0; // Returner 0 hvis det ikke er nok punkter
        }
        int startTime = gpspoints[0].getTime();
        int endTime = gpspoints[gpspoints.length - 1].getTime();

        return endTime - startTime; // Totaltid i sekunder
		
	}
		

	public double[] speeds() {

		int n = gpspoints.length;
        double[] speeds = new double[n - 1];

        for (int i = 0; i < n - 1; i++) {
            double distance = GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
            int timeDifference = gpspoints[i + 1].getTime() - gpspoints[i].getTime();

            // Unngå deling med null
            speeds[i] = (timeDifference > 0) ? distance / timeDifference : 0;
        }

        return speeds;
		
	}
	
	public double maxSpeed() {
		
		double maxspeed = 0;

        for (double speed : speeds()) {
            if (speed > maxspeed) {
                maxspeed = speed;
            }
        }

        return maxspeed; // Returner maksimal hastighet i m/s
	}

	public double averageSpeed() {

    return (totalDistance() / totalTime());
	}


	// conversion factor m/s to miles per hour (mps)
	public static final double MS = 2.23;

	public double kcal(double weight, int secs, double speed) {

		double speedmph = speed * MS;
		double met;

		if (speedmph < 10) {
			met = 4.0;
		} else if (speedmph < 12) {
			met = 6.0;
		} else if (speedmph < 14) {
			met = 8.0;
		} else if (speedmph < 16) {
			met = 10.0;
		} else if (speedmph < 20) {
			met = 12.0;
		} else {
			met = 16.0;
		}

		return met * weight * (secs / 3600.0); // Beregn kcal
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;
		double[] speeds = speeds();
		int[] times = new int[gpspoints.length - 1];

		for (int i = 0; i < gpspoints.length - 1; i++) {
			times[i] = gpspoints[i + 1].getTime() - gpspoints[i].getTime();
		}

		for (int i = 0; i < speeds.length; i++) {
			totalkcal += kcal(weight, times[i], speeds[i]);
		}

		return totalkcal;
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		int totalTime = totalTime();
		double totalDistance = totalDistance();
		double totalElevation = totalElevation();
		double maxSpeed = maxSpeed() * 3.6; // Konverter til km/t
		double averageSpeed = averageSpeed();

		int hours = totalTime / 3600;
    int minutes = (totalTime % 3600) / 60;
    int seconds = totalTime % 60;

		// Utskrift i ønsket format
		System.out.println("==============================================");
    System.out.println("Total Time     : " + hours + "t " + minutes + "m " + seconds + "s");
    System.out.println("Total distance : " + String.format("%.2f", totalDistance) + " km");
    System.out.println("Total elevation: " + String.format("%.2f", totalElevation) + " m");
    System.out.println("Max speed      : " + String.format("%.2f", maxSpeed) + " km/h");
    System.out.println("Average speed  : " + String.format("%.2f", averageSpeed) + " km/h");
    System.out.println("Energy         : " + String.format("%.2f", totalKcal(WEIGHT)) + " kcal");
    System.out.println("==============================================");
		
	}

}
