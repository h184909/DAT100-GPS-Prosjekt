package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import no.hvl.dat100ptc.TODO;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	private double minlon, minlat, maxlon, maxlat;

	private double xstep, ystep;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		
		xstep = scale(MAPXSIZE, minlon, maxlon);
		ystep = scale(MAPYSIZE, minlat, maxlat);
		
		showRouteMap(MARGIN + MAPYSIZE);

		replayRoute(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	public double scale(int maxsize, double minval, double maxval) {

		double step = maxsize / (Math.abs(maxval - minval));

		return step;
	}

	public void showRouteMap(int ybase) {

		 setColor(0, 0, 255); // Set color for the route (blue)
		    
		    // Draw each segment of the route
		    for (int i = 0; i < gpspoints.length - 1; i++) {
		        // Get the current and next GPS points
		        GPSPoint start = gpspoints[i];
		        GPSPoint end = gpspoints[i + 1];
		        
		        // Convert GPS coordinates to pixel coordinates
		        int x1 = MARGIN + (int)((start.getLongitude() - minlon) * xstep);
		        int y1 = ybase - (int)((start.getLatitude() - minlat) * ystep);
		        int x2 = MARGIN + (int)((end.getLongitude() - minlon) * xstep);
		        int y2 = ybase - (int)((end.getLatitude() - minlat) * ystep);
		        
		        // Draw the line segment
		        drawLine(x1, y1, x2, y2);
		    }
	}

	public void showStatistics() {

		double totalDistance = gpscomputer.totalDistance();
	    double totalTime = gpscomputer.totalTime();
	    double averageSpeed = gpscomputer.averageSpeed();
	    
	    int yPosition = MARGIN; // Starting position for the text

	    setColor(0, 0, 0); // Set text color to black
	    setFont("Courier", 12);

	    // Display the statistics
	    drawString("Total Distance: " + String.format("%.2f", totalDistance) + " km", MARGIN, yPosition);
	    yPosition += 20; // Move down for next line
	    drawString("Total Time: " + String.format("%.2f", totalTime) + " hours", MARGIN, yPosition);
	    yPosition += 20; // Move down for next line
	    drawString("Average Speed: " + String.format("%.2f", averageSpeed) + " km/h", MARGIN, yPosition);
	
	}

	public void replayRoute(int ybase) {
 		
		 setColor(255, 0, 0); // Set color for the replay (red)
    
    // Replay the route point by point
    for (int i = 0; i < gpspoints.length; i++) {
        GPSPoint point = gpspoints[i];
        
        // Convert GPS coordinates to pixel coordinates
        int x = MARGIN + (int)((point.getLongitude() - minlon) * xstep);
        int y = ybase - (int)((point.getLatitude() - minlat) * ystep);
        
        // Draw the point
        fillCircle(x, y, 5); // Draw a filled circle for the point
        
        // Pause for a short moment to create an animation effect
        try {
            Thread.sleep(100); // Pause for 100 milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	}

}
