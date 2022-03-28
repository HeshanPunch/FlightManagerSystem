package sait.frms.manager;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

public class ReservationManager extends FlightManager {

	private static final int RES_SIZE = 194;

	RandomAccessFile raf;
	private static final String BINARY_FILE = "res/reservation.bin";

	public ReservationManager() throws IOException {
		this.raf = new RandomAccessFile(BINARY_FILE, "rw");
		
		//System.out.println(findByName("2Heshan"));
		// newReservationCode();

	}

	/**
	 * Create a new reservation code
	 * 
	 * @throws IOException
	 */
	public void newReservationCode(String flight, String name, String citizenship) throws IOException {
		int random = 1000 + (int) (Math.random() * 9000);
		// System.out.println(random);
		char typeFlight;
		Flight f1 = findFlightByCode(flight); // Need to get this info from user
		if (flight.charAt(0) == 'Y' && flight.charAt(0) == 'Y') { //check -- repeat boolean check, both charAt(0)
			typeFlight = 'D';
		} else {
			typeFlight = 'I';
		}
		String code = typeFlight + String.valueOf(random);
		String flightCode = flight;
		String airline = f1.getAirlineNameString();
		String passengerName = name; // Need to get this info from user
		String passengerCitizenship = citizenship; // Need to get this info from user
		double cost = f1.getCostPerSeat();
		boolean available;
		if (f1.getSeats() > 0) {
			available = true;
		} else {
			available = false;
		}

		Reservation r1 = new Reservation(code, flightCode, airline, passengerName, passengerCitizenship, cost,
				available);
		System.out.println(r1);
		writeReservation(r1);

	}

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
	}

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

	public ArrayList<Reservation> getAll() throws IOException {
		ArrayList<Reservation> reservation = new ArrayList<Reservation>();

		for (long pos = 0; pos < this.raf.length(); pos += RES_SIZE) {
			this.raf.seek(pos);
			Reservation r1 = this.readReservation();
			reservation.add(r1);
		}
		return reservation;
	}

	public Reservation findByCode(String code) throws IOException {
		this.raf.seek(this.raf.length());

		for (long pos = 0; pos < this.raf.length(); pos += RES_SIZE) {
			Reservation reservation = this.readReservation();
			if (reservation.getCode().equals(code)) {
				return reservation;
			}
		}
		return null;
	}

	public Reservation findByName(String name) throws IOException {
		this.raf.seek(this.raf.length());

		for (long pos = 0; pos < this.raf.length(); pos += RES_SIZE) {
			Reservation reservation = this.readReservation();
			if (reservation.getName().equals(name)) {
				return reservation;
			}
		}
		return null;
	}

}
