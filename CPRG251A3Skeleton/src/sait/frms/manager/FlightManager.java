package sait.frms.manager;

import java.io.*;
import java.util.*;

import sait.frms.problemdomain.Flight;

public class FlightManager {
	public final String WEEKDAY_ANY = "Any";// not sure if this is what the class diagram means
	public final String WEEKDAY_SUNDAY = "Sunday";
	public final String WEEKDAY_MONDAY = "Monday";
	public final String WEEKDAY_TUESDAY = "Tuesday";
	public final String WEEKDAY_WEDNESDAY = "Wednesday";
	public final String WEEKDAY_THURSDAY = "Thursday";
	public final String WEEKDAY_FRIDAY = "Friday";
	public final String WEEKDAY_SATURDAY = "Saturday";

	ArrayList<Flight> flights = new ArrayList<Flight>();
	ArrayList<String> airports = new ArrayList<String>();
	final static String FLIGHT_PATH = "res/flights.csv";
	final static String AIRPORT_PATH = "res/airports.csv";

//run the rest of the methods
	public FlightManager() throws IOException {
		populateFlights();
		// populateAirports();

		// test
		// System.out.println("Airport code YYC is: " + findAirportByCode("YYC"));;
		// System.out.println("Airport code pek is: " + findAirportByCode("pek") );
		// System.out.println("Flight with code 5943 is: " + findFlightByCode("5943"));
		System.out.println("Flight from FRA to YYC on Friday: " + findFlights("DXB", "YVR", "Monday"));
		;
	}

	public void populateFlights() throws FileNotFoundException {
		Scanner in = new Scanner(new File(FLIGHT_PATH));
		while (in.hasNext()) {
			String line = in.nextLine();
			String[] fields = line.split(",");
			String[] codeAirline = fields[0].split("-");
			flights.add(new Flight(codeAirline[1], codeAirline[0], fields[1], fields[2], fields[3], fields[4],
					Integer.parseInt(fields[5]), Double.parseDouble(fields[6])));

		}

		in.close();

		// for testing
		/*
		 * for (Flight f : flights) { System.out.println(f); }
		 */
	}

	public void populateAirports() throws FileNotFoundException {
		Scanner in = new Scanner(new File(AIRPORT_PATH));
		while (in.hasNextLine()) {
			airports.add(in.nextLine());

		}

		// I think airports need to be stored in 1 line
		/*
		 * while (in.hasNextLine()) { String line = in.nextLine(); String[] fields =
		 * line.split(","); for (int i = 0; i < fields.length; i++) {
		 * airports.add(fields[i]); }
		 * 
		 */

		in.close();

		// for testing
		// System.out.println(airports);
		/*
		 * for (String s : airports) { System.out.println(s); }
		 */

	}

	public String findAirportByCode(String code) {
		String airport = "";
		code = code.toUpperCase();

		for (int i = 0; i < airports.size(); i++) {
			String airportRead = airports.get(i).substring(0, 3);

			if (code.equals(airportRead)) {
				String[] fields = airports.get(i).split(",");
				airport = fields[1];
				break;

			}

		}
		return airport;
	}

	public Flight findFlightByCode(String code) {
		Flight foundFlight = null;

		for (int i = 0; i < flights.size(); i++) {
			String codeRead = flights.get(i).getCode();
			if (code.equals(codeRead)) {
				foundFlight = flights.get(i);
				break;
			}

		}

		return foundFlight;

	}

	public ArrayList<Flight> findFlights(String from, String to, String weekday) {
		ArrayList<Flight> matchingflights = new ArrayList<Flight>();

		for (int i = 0; i < flights.size(); i++) {
			if (from.equalsIgnoreCase(flights.get(i).getFrom()) && to.equalsIgnoreCase(flights.get(i).getTo())
					&& weekday.equalsIgnoreCase(flights.get(i).getWeekday())) {
				matchingflights.add(flights.get(i));

			}
		}
		return matchingflights;

	}

}
