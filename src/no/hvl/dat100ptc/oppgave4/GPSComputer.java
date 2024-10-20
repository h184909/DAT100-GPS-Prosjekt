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

		// Gå gjennom GPS-punktene og beregn distansen mellom påfølgende punkter
		for (int i = 0; i < gpspoints.length - 1; i++) {
			// Bruker GPSUtils til å beregne distanse mellom to punkter
			totalDistance += GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
		}
		
		return totalDistance/1000; // Returner total distanse i km

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

		double totalDistance = totalDistance() * 1000; // Total distance in meters
        int totalTime = totalTime(); // Total time in seconds

        return (totalTime > 0) ? (totalDistance / totalTime) * 3.6 : 0; // Konverter til km/t
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

		// Utskrift i ønsket format
		System.out.println("==============================================");
		System.out.printf("Total Time     : %02d:%02d:%02d\n", 
			totalTime / 3600, (totalTime % 3600) / 60, totalTime % 60);
		System.out.printf("Total distance : %10.2f km\n", totalDistance);
		System.out.printf("Total elevation: %10.2f m\n", totalElevation);
		System.out.printf("Max speed      : %10.2f km/t\n", maxSpeed);
		System.out.printf("Average speed  : %10.2f km/t\n", averageSpeed);
		System.out.printf("Energy         : %10.2f kcal\n", totalKcal(WEIGHT));
		System.out.println("==============================================");
		
	}

}
