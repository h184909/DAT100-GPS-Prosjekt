package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;
import no.hvl.dat100ptc.TODO;

public class ShowSpeed extends EasyGraphics {
			
	private static int MARGIN = 50;
	private static int BARHEIGHT = 100; 

	private GPSComputer gpscomputer;
	
	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Speed profile", 
				2 * MARGIN + 
				2 * gpscomputer.speeds().length, 2 * MARGIN + BARHEIGHT);
		
		showSpeedProfile(MARGIN + BARHEIGHT);
	}
	
	public void showSpeedProfile(int ybase) {
		
		int x = MARGIN,y;
	
		 double[] speeds = gpscomputer.speeds(); // Hastigheter mellom punktene i km/t
		    double averageSpeed = gpscomputer.averageSpeed(); // Gjennomsnittshastighet i km/t

		    // Skaleringsfaktorer for å tilpasse stolpene til vinduet
		    int maxBarHeight = BARHEIGHT; // Maksimal høyde på stolpene i piksler
		    double maxSpeed = GPSUtils.findMax(speeds); // Finn maks hastighet for å skalere stolpene

		    // Tegn hastighetene som blå stolper
		    setColor(0, 0, 255); // Blå farge for hastighetsstolpene
		    for (int i = 0; i < speeds.length; i++) {
		        int barHeight = (int) ((speeds[i] / maxSpeed) * maxBarHeight); // Skaler høyden på stolpen
		        x = MARGIN + i * 2; // X-posisjon (stolpebredde = 2 piksler)
		        y = ybase - barHeight; // Y-posisjon basert på hastigheten
		        drawLine(x, ybase, x, y); // Tegn vertikale stolper
		    }

		    // Tegn gjennomsnittshastigheten som en grønn linje
		    setColor(0, 255, 0); // Grønn farge for gjennomsnittshastigheten
		    int avgYPos = ybase - (int) ((averageSpeed / maxSpeed) * maxBarHeight); // Beregn Y-posisjonen for gjennomsnittshastigheten
		    drawLine(MARGIN, avgYPos, MARGIN + speeds.length * 2, avgYPos); 
	}
}
