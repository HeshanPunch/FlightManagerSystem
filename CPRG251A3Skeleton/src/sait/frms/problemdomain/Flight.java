package sait.frms.problemdomain;

/**
 * 
 * @author Paolo Araujo, Lauren Sung, Heshan Punchihewa
 * @version 2022-03-28 Flight Object Class
 */
public class Flight {
	private String code;
	private String airlineNameString;
	private String from;
	private String to;
	private String weekday;
	private String timeString;
	private int seats;
	private double costPerSeat;

	public Flight() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Default constructor for the Flight object
	 * 
	 * @param code              Fligh code
	 * @param airlineNameString Name of the Airline
	 * @param from              Departure airport code
	 * @param to                Arrival airport code
	 * @param weekday           Weekday
	 * @param timeString        Time of the flight
	 * @param seats             Quantity of available seats
	 * @param costPerSeat       Cost per seat
	 * @throws InvalidFlightCodeException
	 */
	public Flight(String code, String airlineNameString, String from, String to, String weekday, String timeString,
			int seats, double costPerSeat) throws InvalidFlightCodeException {

		super();
		this.code = code;
		this.airlineNameString = airlineNameString;
		this.from = from;
		this.to = to;
		this.weekday = weekday;
		this.timeString = timeString;
		this.seats = seats;
		this.costPerSeat = costPerSeat;
	}

	/**
	 * Method to return the flight code
	 * 
	 * @return Fight code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Method to set the flight code
	 * 
	 * @param code Flight code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Method to return the Airline name
	 * 
	 * @return Airline name
	 */
	public String getAirlineNameString() {
		return airlineNameString;
	}

	/**
	 * Method to set the Airline name
	 * 
	 * @param airlineNameString Airline name
	 */
	public void setAirlineNameString(String airlineNameString) {
		this.airlineNameString = airlineNameString;
	}

	/**
	 * Method to get departure airport code
	 * 
	 * @return Departure airport code
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * Method to get set airport code
	 * 
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * Method to get Arrival airport code
	 * 
	 * @return Arrival airport code
	 */
	public String getTo() {
		return to;
	}

	/**
	 * Method to set arrival airport
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * Method to get the day of the flight
	 * 
	 * @return day of the flight
	 */
	public String getWeekday() {
		return weekday;
	}

	/**
	 * Method to set the day of the flight
	 * 
	 * @param day of the flight
	 */
	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	/**
	 * Method to get the time of the flight
	 * 
	 * @return time of the flight
	 */
	public String getTimeString() {
		return timeString;
	}

	/**
	 * Method to set the time of the flight
	 * 
	 * @param time of the flight
	 */
	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}

	/**
	 * Method to get the number of seats
	 * 
	 * @return number of seats
	 */
	public int getSeats() {
		return seats;
	}

	/**
	 * Method to set number of seats
	 * 
	 * @param seats
	 */
	public void setSeats(int seats) {
		this.seats = seats;
	}

	/**
	 * Method to get the cost of the flight seat
	 * 
	 * @return cost of the seat
	 */
	public double getCostPerSeat() {
		return costPerSeat;
	}

	/**
	 * Method to set the cost of the flight seat
	 * 
	 * @param costPerSeat
	 */
	public void setCostPerSeat(double costPerSeat) {
		this.costPerSeat = costPerSeat;
	}

	/**
	 * Method to check if the flight is domestic or international
	 * 
	 * @return
	 */
	public boolean isDomestic() {
		return false;
	}

	/**
	 * Method to check if the flight code is valid and return the airline name
	 * 
	 * @param code Flight code
	 * @throws InvalidFlightCodeException
	 */
	public void parseCode(String code) throws InvalidFlightCodeException {

		String airline = "" + code.charAt(0) + code.charAt(1);

		if (airline == "OA") {
			airlineNameString = "Otto Airlines";
		} else if (airline == "CA") {
			airlineNameString = "Conned Air";
		} else if (airline == "TB") {
			airlineNameString = "Try a Bus Airways";
		} else if (airline == "CA") {
			airlineNameString = "Vertical Airways";
		} else
			throw new InvalidFlightCodeException();

		setAirlineNameString(airlineNameString);

	}

	@Override
	public String toString() {
		return code + ", " + "From: " + from + ", To:" + to + ", Day: " + weekday + ", Cost: " + costPerSeat;
	}

}
