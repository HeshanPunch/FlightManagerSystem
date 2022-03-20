package sait.frms.problemdomain;

import java.nio.charset.CoderMalfunctionError;

public class Flight {
	private String code;
	private String airlineName;
	private String from;
	private String to;
	private String weekday;
	private String time;
	private int seats;
	private double costPerSeat;

	public Flight() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Flight(String code, String from, String to, String weekday, String timeString, int seats,
			double costPerSeat) {
		super();
		this.code = code;
		// this.airlineNameString = airlineNameString;
		this.from = from;
		this.to = to;
		this.weekday = weekday;
		this.time = timeString;
		this.seats = seats;
		this.costPerSeat = costPerSeat;
	}

	public Flight(String code, String airlineNameString, String from, String to, String weekday, String timeString,
			int seats, double costPerSeat) {
		super();
		this.code = code;
		this.airlineName = airlineNameString;
		this.from = from;
		this.to = to;
		this.weekday = weekday;
		this.time = timeString;
		this.seats = seats;
		this.costPerSeat = costPerSeat;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineNameString(String airlineName) {
		this.airlineName = airlineName;
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

	public String getTime() {
		return time;
	}

	public void setTimeString(String time) {
		this.time = time;
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

	public boolean isDomestic(String code) {
		char firstChar = code.charAt(0);

		if (firstChar == 'D') {
			return true;
		} else {
			return false;
		}
	}

	private void parseCode(String code) {
		char codeChar;
		for(int i = 0; i < code.length(); i++) {
			codeChar = code.charAt(i);
		}

	}


	@Override
	public String toString() {
		return "Flight [code=" + code + ", airlineNameString=" + airlineName + ", from=" + from + ", to=" + to
				+ ", weekday=" + weekday + ", timeString=" + time + ", seats=" + seats + ", costPerSeat="
				+ costPerSeat + ", getCode()=" + getCode() + ", getAirlineNameString()=" + getAirlineName()
				+ ", getFrom()=" + getFrom() + ", getTo()=" + getTo() + ", getWeekday()=" + getWeekday()
				+ ", getTimeString()=" + getTime() + ", getSeats()=" + getSeats() + ", getCostPerSeat()="
				+ getCostPerSeat() + "]";
	}

}
