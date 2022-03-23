package sait.frms.manager;

import java.io.IOException;
import java.io.RandomAccessFile;

import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

public class ReservationManager extends FlightManager {
	
	RandomAccessFile raf;
	private static final String BINARY_FILE = "res/reservation.bin";

	public ReservationManager() throws IOException {
		this.raf = new RandomAccessFile(BINARY_FILE, "rw");

		newReservationCode();

	}

	/**
	 * Create a new reservation code
	 * @throws IOException 
	 */
	private void newReservationCode() throws IOException {
		int random = 1000 + (int) (Math.random() * 9000);
		//System.out.println(random);
		char typeFlight;
		Flight f1 = findFlightByCode("5943"); // Need to get this info from user
		if (f1.getFrom().charAt(0) == 'Y' && f1.getTo().charAt(0) == 'Y') {
			typeFlight = 'D';
		} else {
			typeFlight = 'I';
		}
		String code = typeFlight + String.valueOf(random);
		String flightCode = f1.getCode();
		String airline = f1.getAirlineNameString();
		String passengerName = "name"; // Need to get this info from user
		String passengerCitizenship = "citizenship"; // Need to get this info from user
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
		

		String code = String.format("%-10s", r.getCode());// 10+2
		this.raf.writeUTF(code);

		String flightCode = String.format("%-4s", r.getFlightCode()); // 4+2
		this.raf.writeUTF(flightCode);

		String airline = String.format("%-2s", r.getAirline());// 2+2
		this.raf.writeUTF(airline);
		
		String name = String.format("%-100s", r.getName());// 100+2
		this.raf.writeUTF(name);
		
		String citizenship = String.format("%-100s", r.getCitizenship());// 100+2
		this.raf.writeUTF(citizenship);
		
		this.raf.writeDouble(r.getCost());// 8 bytes
		
		this.raf.writeBoolean(r.isActive());// 1 byte
	}
	
	private Reservation readReservation() throws IOException {
		String code = this.raf.readUTF().trim();
		String flightCode = this.raf.readUTF().trim();
		String airline = this.raf.readUTF().trim();
		String name = this.raf.readUTF().trim();
		String citizenship = this.raf.readUTF().trim();
		double cost = this.raf.readDouble();
		boolean available = this.raf.readBoolean();
		
		Reservation reservation = new Reservation(code, flightCode, airline, name, citizenship, cost,
				available); 
		//book.setDeleted(deleted);
		return reservation;
	}

}
