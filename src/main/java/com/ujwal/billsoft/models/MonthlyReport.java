package com.ujwal.billsoft.models;

public class MonthlyReport {

	private int compId;
	private String compName;
	private int billHeaderId; 
	private String billDate;
	private int custId;
	private int companyId;
	private float taxPer;
	private float taxableAmt;
	private float sgstAmt;
	private float cgstAmt;
	private float igstAmt;
	private float totaTax;
	private float grandTotal;
	private String custName;
	private int locId;
	private int userId;
	private float roundOff;
	private float discAmt;
	private String userName;
	private String month;
	
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
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
	public int getBillHeaderId() {
		return billHeaderId;
	}
	public void setBillHeaderId(int billHeaderId) {
		this.billHeaderId = billHeaderId;
	}
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
	public float getTaxPer() {
		return taxPer;
	}
	public void setTaxPer(float taxPer) {
		this.taxPer = taxPer;
	}
	public float getTaxableAmt() {
		return taxableAmt;
	}
	public void setTaxableAmt(float taxableAmt) {
		this.taxableAmt = taxableAmt;
	}
	public float getSgstAmt() {
		return sgstAmt;
	}
	public void setSgstAmt(float sgstAmt) {
		this.sgstAmt = sgstAmt;
	}
	public float getCgstAmt() {
		return cgstAmt;
	}
	public void setCgstAmt(float cgstAmt) {
		this.cgstAmt = cgstAmt;
	}
	public float getIgstAmt() {
		return igstAmt;
	}
	public void setIgstAmt(float igstAmt) {
		this.igstAmt = igstAmt;
	}
	public float getTotaTax() {
		return totaTax;
	}
	public void setTotaTax(float totaTax) {
		this.totaTax = totaTax;
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
	public int getLocId() {
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
	}
	@Override
	public String toString() {
		return "MonthlyReport [compId=" + compId + ", compName=" + compName + ", billHeaderId=" + billHeaderId
				+ ", billDate=" + billDate + ", custId=" + custId + ", companyId=" + companyId + ", taxPer=" + taxPer
				+ ", taxableAmt=" + taxableAmt + ", sgstAmt=" + sgstAmt + ", cgstAmt=" + cgstAmt + ", igstAmt="
				+ igstAmt + ", totaTax=" + totaTax + ", grandTotal=" + grandTotal + ", custName=" + custName
				+ ", locId=" + locId + ", userId=" + userId + ", roundOff=" + roundOff + ", discAmt=" + discAmt
				+ ", userName=" + userName + ", month=" + month + "]";
	}
	
	
}
