package com.ujwal.billsoft.models;


public class MLocComp {
	//Location
	private int locationId;
	private String location_name;
	private String locationAddress;
	private String phoneNo;
	private String faxNo;
	private String email;
	private int delStatus;
	
	//Company
	private int compId;
	private String compName;
	
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public String getLocation_name() {
		return location_name;
	}
	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
	public String getLocationAddress() {
		return locationAddress;
	}
	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getFaxNo() {
		return faxNo;
	}
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	@Override
	public String toString() {
		return "MLocComp [locationId=" + locationId + ", location_name=" + location_name + ", locationAddress="
				+ locationAddress + ", phoneNo=" + phoneNo + ", faxNo=" + faxNo + ", email=" + email + ", delStatus="
				+ delStatus + ", compId=" + compId + ", compName=" + compName + "]";
	}
	
	
}
