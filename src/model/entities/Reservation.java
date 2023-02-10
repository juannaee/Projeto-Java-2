package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

	private Integer numberRoom;
	private Date checkIn;
	private Date checkOut;

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Reservation(Integer numberroom, Date checkIn, Date checkOut) {
		this.numberRoom = numberroom;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getNumberroom() {
		return numberRoom;
	}

	public void setNumberroom(Integer numberroom) {
		this.numberRoom = numberroom;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	public String updateDates(Date checkIn, Date checkOut) {

		Date now = new Date();

		if (checkIn.before(now) || checkOut.before(now)) {

			return "Error in reservation: Resrvation dates for update must be future dates";
		}

		if (!checkOut.after(checkIn)) {

			return "Error in reservation: Check-out date must be after check-in date";
		}

		this.checkIn = checkIn;
		this.checkOut = checkOut;
		return null;
	}

	@Override
	public String toString() {
		return "Room " + numberRoom + ", check-in: " + sdf.format(checkIn) + ", check-out: " + sdf.format(checkOut)
				+ ", " + duration() + " nights";
	}

}
