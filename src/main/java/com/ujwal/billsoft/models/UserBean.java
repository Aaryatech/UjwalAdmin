package com.ujwal.billsoft.models;


public class UserBean {

	
	private int delStatus;
	private int userId;
	private int locationId;
	private int companyId;
	private int compId;
	private String userName;
	private String userMobile;
	private String userEmail;
	private String locationName;
	private String compName;
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	@Override
	public String toString() {
		return "UserBean [delStatus=" + delStatus + ", userId=" + userId + ", locationId=" + locationId + ", companyId="
				+ companyId + ", compId=" + compId + ", userName=" + userName + ", userMobile=" + userMobile
				+ ", userEmail=" + userEmail + ", locationName=" + locationName + ", compName=" + compName + "]";
	}
	
	
	
	
}
