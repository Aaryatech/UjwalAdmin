package com.ujwal.billsoft.models;

public class MUser {
	private int userId;
	private String userName;
	private String userMobile;
	private String userPwd;
	private String userEmail;
	private int locationId;
	private int companyId;
	private int delStatus;
	private int ex_int1;
	private int ex_int;
	private String ex_var1;
	private String ex_var2;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
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
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public int getEx_int1() {
		return ex_int1;
	}
	public void setEx_int1(int ex_int1) {
		this.ex_int1 = ex_int1;
	}
	public int getEx_int() {
		return ex_int;
	}
	public void setEx_int(int ex_int) {
		this.ex_int = ex_int;
	}
	public String getEx_var1() {
		return ex_var1;
	}
	public void setEx_var1(String ex_var1) {
		this.ex_var1 = ex_var1;
	}
	public String getEx_var2() {
		return ex_var2;
	}
	public void setEx_var2(String ex_var2) {
		this.ex_var2 = ex_var2;
	}
	@Override
	public String toString() {
		return "MUser [userId=" + userId + ", userName=" + userName + ", userMobile=" + userMobile + ", userPwd="
				+ userPwd + ", userEmail=" + userEmail + ", locationId=" + locationId + ", companyid=" + companyId
				+ ", delStatus=" + delStatus + ", ex_int1=" + ex_int1 + ", ex_int=" + ex_int + ", ex_var1=" + ex_var1
				+ ", ex_var2=" + ex_var2 + "]";
	}
	
	
}
