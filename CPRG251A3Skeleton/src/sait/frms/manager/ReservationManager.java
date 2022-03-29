package sait.frms.manager;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

/**
 * Reservation Manager class creates a Reservation code, finds and modifies a
 * reservation.
 * 
 * @author Paolo Araujo, Lauren Sung, Heshan Punchihewa
 * @version 2022-03-28
 *
 */
public class ReservationManager extends FlightManager {

	private static final int RES_SIZE = 193;
	private ArrayList<Reservation> reservations = new ArrayList<Reservation>();
	RandomAccessFile raf;
	private static final String BINARY_FILE = "res/reservation.bin";

	public ReservationManager() throws IOException {
		this.raf = new RandomAccessFile(BINARY_FILE, "rw");

		// System.out.println(findByName("2Heshan"));

		// newReservationCode();

	}

	/**
	 * Method to create a new reservation code and a new reservation
	 * 
	 * @throws IOException
	 */
	public void newReservationCode(String flight, String name, String citizenship) throws IOException {

		String code;
		int random = 1000 + (int) (Math.random() * 9000);
		// System.out.println(random);
		char typeFlight;
		Flight f1 = findFlightByCode(flight); // Need to get this info from user

		if (f1.getFrom().charAt(0) == 'Y' && f1.getTo().charAt(0) == 'Y') {

			typeFlight = 'D';
		} else {
			typeFlight = 'I';
		}
		try {

			if (f1.getSeats() == 0) {
				errorAlert("No available seats!");
				throw new Exception("No seats");
			} else if (name == null || name.contentEquals("")) {
				errorAlert("Fill in with the name!");
				throw new Exception("No name");
			} else if (citizenship == null || citizenship.contentEquals("")) {
				errorAlert("Fill in with the citizenship!");
				throw new Exception("No citizenship");
			} else {
				code = typeFlight + String.valueOf(random);
				// String flightCode = flight;
				String airline = f1.getAirlineNameString();
				// String passengerName = name; // Need to get this info from user
				// String passengerCitizenship = citizenship; // Need to get this info from user
				double cost = f1.getCostPerSeat();
				boolean available;

				Reservation r1 = new Reservation(code, flight, airline, name, citizenship, cost, true);
				writeReservation(r1);
				System.out.println(r1);
			}

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("you are here");
		}

	}

	/**
	 * Method to write in random access file the reservations
	 * 
	 * @param r Reservation object
	 * @throws IOException
	 */
	private void writeReservation(Reservation r) throws IOException {

		this.raf.seek(this.raf.length());
		String code = String.format("%-10s", r.getCode());// 10+2
		this.raf.writeUTF(code);

		String flightCode = String.format("%-14s", r.getFlightCode()); // 14+2
		this.raf.writeUTF(flightCode);

		String airline = String.format("%-50s", r.getAirline());// 50+2
		this.raf.writeUTF(airline);

		String name = String.format("%-50s", r.getName());// 50+2
		this.raf.writeUTF(name);

		String citizenship = String.format("%-50s", r.getCitizenship());// 50+2
		this.raf.writeUTF(citizenship);

		this.raf.writeDouble(r.getCost());// 8 bytes

		this.raf.writeBoolean(r.isActive());// 1 byte

		// this.raf.close();

		bookingConfirmation(code);

	}

	/**
	 * Method to read the reservations from the file
	 * 
	 * @return Reservation
	 * @throws IOException
	 */
	private Reservation readReservation() throws IOException {
		this.raf.seek(0);
		String code = this.raf.readUTF().trim();
		String flightCode = this.raf.readUTF().trim();
		String airline = this.raf.readUTF().trim();
		String name = this.raf.readUTF().trim();
		String citizenship = this.raf.readUTF().trim();
		double cost = this.raf.readDouble();
		boolean available = this.raf.readBoolean();
		Reservation reservation = new Reservation(code, flightCode, airline, name, citizenship, cost, available);

		// this.raf.close();
		return reservation;

	}

	/**
	 * Method to populate an Array with reservations
	 * 
	 * @param code    Reservation code
	 * @param airline Airline name
	 * @param name    Customer name
	 * @return Array of reservation
	 * @throws IOException
	 */
	public ArrayList<Reservation> findReservation(String code, String airline, String name) throws IOException {
		ArrayList<Reservation> reservation = new ArrayList<Reservation>();
		reservation.clear();

		for (Reservation r : reservations) {
			if (r.getCode().equals(code) || r.getAirline().equals(airline) || r.getName().equals(name)) {
				reservation.add(r);
			}

		}
		return reservation;

//		for (long pos = 0; pos < this.raf.length(); pos += RES_SIZE) {
//			this.raf.seek(pos);
//			Reservation r1 = this.readReservation();
//			reservation.add(r1);
//
//		}
//		return reservation;
	}

	/**
	 * Method to search a reservation by code
	 * 
	 * @param code Reservation code
	 * @return Reservation
	 * @throws IOException
	 */
	public Reservation findByCode(String code) throws IOException {

		for (Reservation r : reservations) {
			if (r.getCode().equals(code)) {
				return r;
			}
//		
//		this.raf.seek(0);
//
//		for (long pos = 0; pos < this.raf.length(); pos += RES_SIZE) {
//			Reservation reservation = this.readReservation();
//			if (reservation.getCode().equals(code)) {
//				return reservation;
//			}
		}
		return null;
	}

	/**
	 * Method to search a reservation by customer name
	 * 
	 * @param name Customer name
	 * @return reservation
	 * @throws IOException
	 */
	public Reservation findByName(String name) throws IOException {
		this.raf.seek(0);

		for (long pos = 0; pos < this.raf.length(); pos += RES_SIZE) {
			Reservation reservation = this.readReservation();
			if (reservation.getName().equals(name)) {
				return reservation;
			}
		}
		return null;
	}

	/*
	 * public ArrayList<Reservation> findReservation(String code, String airline,
	 * String name) throws IOException {
	 * 
	 * ArrayList<Reservation> reservations = new ArrayList<Reservation>();
	 * reservations.clear();
	 * 
	 * this.raf.seek(0);
	 * 
	 * for (long pos = 0; pos < this.raf.length(); pos += RES_SIZE) {
	 * ArrayList<Reservation> reservation = this.readReservation(); if
	 * (reservation.getCode().equals(code) ||
	 * reservation.getAirline().equals(airline) ||
	 * reservation.getName().equals(name)) { reservations.add(pos); } } return null;
	 * }
	 */

	/**
	 * Method to create a message to inform the reservation code
	 * 
	 * @param code reservation code
	 */
	public void bookingConfirmation(String code) {
		String message = "Reservation confirmed - Code: " + code;
		JOptionPane.showMessageDialog(null, message);

	}

	public void errorAlert(String error) {
		;
		JOptionPane.showMessageDialog(null, error);

	}

}
