package sait.frms.application;


import sait.frms.manager.*;
import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

import java.io.IOException;


import sait.frms.gui.*;

/**
 * Application driver.
 * 
 */
public class AppDriver {

	/**
	 * Entry point to Java application.
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		MainWindow mainWindow = new MainWindow();
		mainWindow.display();
		//commented out for now

		//FlightManager f1 = new FlightManager();
		
		ReservationManager r1 = new ReservationManager();
		r1.newReservationCode("TB-8477", "Joe", "Canadian");
		r1.findByName("Paolo");
		
		//Flight f5 = new Flight();

		
		
		//f5.parseCode("8A-7296");
		
//		for(Reservation r: r1.getAll()) {
//			System.out.println(r);
//		}
		//Reservation r2 = r1.findByName("TB-8477", "Joe", "Canadian");
		//System.out.println(r2);

	}
	
	

}
