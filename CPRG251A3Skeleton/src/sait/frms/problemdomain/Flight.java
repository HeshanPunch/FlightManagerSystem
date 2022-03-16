package sait.frms.problemdomain;

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

	public Flight(String code, String airlineNameString, String from, String to, String weekday, String timeString,
			int seats, double costPerSeat) {
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAirlineNameString() {
		return airlineNameString;
	}

	public void setAirlineNameString(String airlineNameString) {
		this.airlineNameString = airlineNameString;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	public String getTimeString() {
		return timeString;
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public double getCostPerSeat() {
		return costPerSeat;
	}

	public void setCostPerSeat(double costPerSeat) {
		this.costPerSeat = costPerSeat;
	}

	public boolean isDomestic() {
		return false;
	}
	
	public void parseCode(String code) {
		
	}

	@Override
	public String toString() {
		return "Flight [code=" + code + ", airlineNameString=" + airlineNameString + ", from=" + from + ", to=" + to
				+ ", weekday=" + weekday + ", timeString=" + timeString + ", seats=" + seats + ", costPerSeat="
				+ costPerSeat + ", getCode()=" + getCode() + ", getAirlineNameString()=" + getAirlineNameString()
				+ ", getFrom()=" + getFrom() + ", getTo()=" + getTo() + ", getWeekday()=" + getWeekday()
				+ ", getTimeString()=" + getTimeString() + ", getSeats()=" + getSeats() + ", getCostPerSeat()="
				+ getCostPerSeat() + "]";
	}

}
