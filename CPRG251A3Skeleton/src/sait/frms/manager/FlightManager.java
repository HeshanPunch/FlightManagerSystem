package sait.frms.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import sait.frms.problemdomain.Flight;

public class FlightManager extends Flight {
	ArrayList<Flight> flights = new ArrayList<Flight>();
	ArrayList<String> airports = new ArrayList<String>(2);
	final static String FLIGHT_PATH = "res/flights.csv";
	final static String AIRPORT_PATH = "res/airports.csv";

	public void populateFlights() throws FileNotFoundException {
		Scanner in = new Scanner(new File(FLIGHT_PATH));
		while (in.hasNext()) {
			String line = in.nextLine();
			String[] fields = line.split(",");
			flights.add(new Flight(fields[0], fields[1], fields[2], fields[3], fields[4],
						Integer.parseInt(fields[5]), Double.parseDouble(fields[6])));
		
		}
		in.close();
		System.out.println(flights.toString());
	}

	public void populateAirports() throws FileNotFoundException {
		Scanner in = new Scanner(new File(AIRPORT_PATH));
		while (in.hasNextLine()) {
			String line = in.nextLine();
			String[] fields = line.split(",");
			for (int i = 0; i < fields.length; i++) {
				airports.add(fields[i]);
			}

		}
		in.close();
		System.out.println(airports);
	}

}
