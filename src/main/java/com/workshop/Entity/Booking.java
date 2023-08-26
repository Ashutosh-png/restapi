package com.workshop.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Booking {
	
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private int id;
	private String fromLocation;
	private String toLocation;
	private String tripType;
	private LocalDate startDate;
	private LocalDate returnDate;
	private LocalTime  time;
	private String distance;
	private String bookingId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFromLocation() {
		return fromLocation;
	}
	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}
	public String getToLocation() {
		return toLocation;
	}
	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}
	public String getTripType() {
		return tripType;
	}
	public void setTripType(String tripType) {
		this.tripType = tripType;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public Booking(int id, String fromLocation, String toLocation, String tripType, LocalDate startDate,
			LocalDate returnDate, LocalTime time, String distance, String bookingId) {
		super();
		this.id = id;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.tripType = tripType;
		this.startDate = startDate;
		this.returnDate = returnDate;
		this.time = time;
		this.distance = distance;
		this.bookingId = bookingId;
	}
	
	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Booking [id=" + id + ", fromLocation=" + fromLocation + ", toLocation=" + toLocation + ", tripType="
				+ tripType + ", startDate=" + startDate + ", returnDate=" + returnDate + ", time=" + time
				+ ", distance=" + distance + ", bookingId=" + bookingId + "]";
	}
	
	
	
	
	
	

}
