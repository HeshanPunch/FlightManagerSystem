package sait.frms.problemdomain;

import java.io.IOException;

//import sait.frms.manager.*;

/**
 * 
 * @author Paolo Araujo, Lauren Sung, Heshan Punchihewa
 * @version 2022-03-28 Flight Reservation Class
 */

public class Reservation {
	private String code;
	private String flightCode;
	private String airline;
	private String name;
	private String citizenship;
	private double cost;
	private boolean active;
//private ReservationManager reservationManager;

	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor to create a reservation accordingly with parameters
	 * 
	 * @param code        Reservation code
	 * @param flightCode  Flight code
	 * @param airline     Airline name
	 * @param name        Customer name
	 * @param citizenship Customer citizenship
	 * @param cost        Cost of the flight seat
	 * @param active      Reservation is active or not
	 * @throws IOException
	 */
	public Reservation(String code, String flightCode, String airline, String name, String citizenship, double cost,
			boolean active) throws IOException {
		super();
		this.code = code;
		this.flightCode = flightCode;
		this.airline = airline;
		this.name = name;
		this.citizenship = citizenship;
		this.cost = cost;
		this.active = active;
		// reservationManager = new ReservationManager();
		// reservationManager.bookingConfirmation(this.code);
	}

	/**
	 * Method to get the reservation code
	 * 
	 * @return reservation code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Method to set reservation code
	 * 
	 * @param code reservation code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Method to get flight code
	 * 
	 * @return flight code
	 */
	public String getFlightCode() {
		return flightCode;
	}

	/**
	 * Method to set flight code
	 * 
	 * @param flightCode flight code
	 */
	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	/**
	 * Method to get the airline name
	 * 
	 * @return airline name
	 */
	public String getAirline() {
		return airline;
	}

	/**
	 * Method to set the airline name
	 * 
	 * @param airline airline name
	 */
	public void setAirline(String airline) {
		this.airline = airline;
	}

	/**
	 * Method to get customer name
	 * 
	 * @return customer name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method to set customer name
	 * 
	 * @param name customer name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method to get customer citizenship
	 * 
	 * @return customer citizenship
	 */
	public String getCitizenship() {
		return citizenship;
	}

	/**
	 * Method to set customer citizenship
	 * 
	 * @param citizenship customer citizenship
	 */
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	/**
	 * Method to get flight seat cost
	 * 
	 * @return flight seat cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * Method to set flight seat cost
	 * 
	 * @param cost flight seat cost
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * Method to check if the flight is active
	 * 
	 * @return flight active or not
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Method to set flight active
	 * 
	 * @param active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Override method to return a String with reservation informatio
	 */
	@Override
	public String toString() {
		return "Reservation: " + code + ", " + flightCode + ", " + airline + ", " + name + ", " + citizenship + ", "
				+ cost + ", " + active;
	}

}
