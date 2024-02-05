package com.sfb;

public class Facility {

	private int facility_id;
	private String facility_name;
	private String sport_type;
	private int capacity;
	private String description;
	private Boolean status;

	public Facility(int f_id, String f_name, String s_t, int cpty, String desc, int stat) {
		this.facility_id = f_id;
		this.facility_name = f_name;
		this.sport_type = s_t;
		this.capacity = cpty;
		this.description = desc;
		if (stat == 1) {this.status = true;
		}else {this.status = false;}
	}
    public Facility() {
    }
	public int getFacility_id() {
		return facility_id;
	}

	public void setFacility_id(int facility_id) {
		this.facility_id = facility_id;
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

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
}
