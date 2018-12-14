package com.ujwal.billsoft.models;

import java.util.Date;

public class MLocation {

	private int locationId;
	private String location_name;
	private String locationAddress;
	private String phoneNo;
	private String faxNo;
	private String email;
	private int compId;
	private int delStatus;
	private int exInt1;
	private int exInt2;
	private int exInt3;
	private String extVar1;
	private String extVar2;
	private String extVar3;
	private boolean exBool1;
	private boolean exBool2;
	private Date exDate1;
	private Date exDate2;
	
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
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public int getExInt1() {
		return exInt1;
	}
	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}
	public int getExInt2() {
		return exInt2;
	}
	public void setExInt2(int exInt2) {
		this.exInt2 = exInt2;
	}
	public int getExInt3() {
		return exInt3;
	}
	public void setExInt3(int exInt3) {
		this.exInt3 = exInt3;
	}
	public String getExtVar1() {
		return extVar1;
	}
	public void setExtVar1(String extVar1) {
		this.extVar1 = extVar1;
	}
	public String getExtVar2() {
		return extVar2;
	}
	public void setExtVar2(String extVar2) {
		this.extVar2 = extVar2;
	}
	public String getExtVar3() {
		return extVar3;
	}
	public void setExtVar3(String extVar3) {
		this.extVar3 = extVar3;
	}
	public boolean isExBool1() {
		return exBool1;
	}
	public void setExBool1(boolean exBool1) {
		this.exBool1 = exBool1;
	}
	public boolean isExBool2() {
		return exBool2;
	}
	public void setExBool2(boolean exBool2) {
		this.exBool2 = exBool2;
	}
	public Date getExDate1() {
		return exDate1;
	}
	public void setExDate1(Date exDate1) {
		this.exDate1 = exDate1;
	}
	public Date getExDate2() {
		return exDate2;
	}
	public void setExDate2(Date exDate2) {
		this.exDate2 = exDate2;
	}
	@Override
	public String toString() {
		return "MLocation [locationId=" + locationId + ", location_name=" + location_name + ", locationAddress="
				+ locationAddress + ", phoneNo=" + phoneNo + ", faxNo=" + faxNo + ", email=" + email + ", compId="
				+ compId + ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3="
				+ exInt3 + ", extVar1=" + extVar1 + ", extVar2=" + extVar2 + ", extVar3=" + extVar3 + ", exBool1="
				+ exBool1 + ", exBool2=" + exBool2 + ", exDate1=" + exDate1 + ", exDate2=" + exDate2 + "]";
	}
	
	
}
