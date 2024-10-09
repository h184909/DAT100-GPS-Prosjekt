package no.hvl.dat100ptc.oppgave1;

public class Main {

	public static void main(String[] args) {
		
		GPSPoint g1 = new GPSPoint(1, 2.0, 3.0, 5.0);
		
		System.out.println(g1.getTime());
		System.out.println(g1.getLatitude());
		System.out.println(g1.getLongitude());
		System.out.println(g1.getElevation());
		
		System.out.println("------------------------");
		
		g1.setTime(2);
		
		System.out.println(g1.toString());
		
	}

}
