package com.ujwal.billsoft.models;

public class GetBillHeadCompany {
	
	private int billHeaderId; 
	private int companyId;
	private String compName;
	private int delStatus;
	private String logo;
	private String email;
	private String phoneNo;
	private String gstid;
	private String address;
	public int getBillHeaderId() {
		return billHeaderId;
	}
	public void setBillHeaderId(int billHeaderId) {
		this.billHeaderId = billHeaderId;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getGstid() {
		return gstid;
	}
	public void setGstid(String gstid) {
		this.gstid = gstid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "GetBillHeadCompany [billHeaderId=" + billHeaderId + ", companyId=" + companyId + ", compName="
				+ compName + ", delStatus=" + delStatus + ", logo=" + logo + ", email=" + email + ", phoneNo=" + phoneNo
				+ ", gstid=" + gstid + ", address=" + address + "]";
	}
	
	
}
