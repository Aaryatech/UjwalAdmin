package com.ujwal.billsoft.models;


import com.fasterxml.jackson.annotation.JsonFormat;

public class CustReport {
	
	private int billHeaderId; 

	private String billDate;
	private int custId;
	private int companyId;
	//private float taxPer;
	private float taxableAmount;
	private float sgstRs;
	private float cgstRs;
	private float igstRs;
	private float totalTax;
	private float grandTotal;
	private String custName;
	/*private int locId;
	private int userId;
	private float roundOff;
	private float discAmt;
	private String userName;
*/	private String custGstn;

	
	public int getBillHeaderId() {
		return billHeaderId;
	}
	public void setBillHeaderId(int billHeaderId) {
		this.billHeaderId = billHeaderId;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
/*	public float getTaxPer() {
		return taxPer;
	}
	public void setTaxPer(float taxPer) {
		this.taxPer = taxPer;
	}*/
	

	public float getSgstRs() {
		return sgstRs;
	}
	public float getTaxableAmount() {
		return taxableAmount;
	}
	public void setTaxableAmount(float taxableAmount) {
		this.taxableAmount = taxableAmount;
	}
	public void setSgstRs(float sgstRs) {
		this.sgstRs = sgstRs;
	}
	public float getCgstRs() {
		return cgstRs;
	}
	public void setCgstRs(float cgstRs) {
		this.cgstRs = cgstRs;
	}
	public float getIgstRs() {
		return igstRs;
	}
	public void setIgstRs(float igstRs) {
		this.igstRs = igstRs;
	}
	public float getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(float totalTax) {
		this.totalTax = totalTax;
	}
	public float getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
/*	public int getLocId() {
		return locId;
	}
	public void setLocId(int locId) {
		this.locId = locId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public float getRoundOff() {
		return roundOff;
	}
	public void setRoundOff(float roundOff) {
		this.roundOff = roundOff;
	}
	public float getDiscAmt() {
		return discAmt;
	}
	public void setDiscAmt(float discAmt) {
		this.discAmt = discAmt;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}*/
	public String getCustGstn() {
		return custGstn;
	}
	public void setCustGstn(String custGstn) {
		this.custGstn = custGstn;
	}
	@Override
	public String toString() {
		return "CustReport [billHeaderId=" + billHeaderId + ", billDate=" + billDate + ", custId=" + custId
				+ ", companyId=" + companyId + ", taxableAmount=" + taxableAmount + ", sgstRs=" + sgstRs + ", cgstRs="
				+ cgstRs + ", igstRs=" + igstRs + ", totalTax=" + totalTax + ", grandTotal=" + grandTotal + ", custName="
				+ custName + ", custGstn=" + custGstn + "]";
	}
	
	
	
	
}
