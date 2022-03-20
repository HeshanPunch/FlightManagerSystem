package sait.frms.application;

import sait.frms.gui.MainWindow;
import sait.frms.manager.FlightManager;

import java.io.FileNotFoundException;

import sait.frms.gui.*;

/**
 * Application driver.
 * 
 */
public class AppDriver extends FlightManager{

	/**
	 * Entry point to Java application.
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		//MainWindow mainWindow = new MainWindow();
		//mainWindow.display();
		FlightManager flight = new FlightManager();
		flight.populateFlights();
		flight.populateAirports();
				
	}

}
