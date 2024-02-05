package com.sfb;

import java.util.Date;

public class Booking {
	private int booking_id;
	private User user;
	private String facility_name;
	private String sport_type;
	private Date date;
	private int user_id;
	private int facility_id;
	public Booking(
			int b_id,
			String f_name,
			String s_t,
			Date date,
			int u_id,
			int f_id) {
		this.booking_id = b_id;
		this.facility_name = f_name;
		this.sport_type = s_t;
		this.date = date;
		this.user_id = u_id;
		this.facility_id = f_id;
	}

	
	public int getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}
	public String getFacility_name() {
		return facility_name;
	}
	public void setFacility_name(String facility_name) {
		this.facility_name = facility_name;
	}
	public String getSport_type() {
		return sport_type;
	}
	public void setSport_type(String sport_type) {
		this.sport_type = sport_type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getFacility_id() {
		return facility_id;
	}
	public void setFacility_id(int facility_id) {
		this.facility_id = facility_id;
	}
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
