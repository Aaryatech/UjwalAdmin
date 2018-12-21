package com.ujwal.billsoft.models;

public class CompReport {
	private int billHeaderId; 
	private String invoiceNo;
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
	private float disc_amt;
	private String user_name;
	private int del_status;
	
	
	public float getDisc_amt() {
		return disc_amt;
	}
	public void setDisc_amt(float disc_amt) {
		this.disc_amt = disc_amt;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getDel_status() {
		return del_status;
	}
	public void setDel_status(int del_status) {
		this.del_status = del_status;
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
	public int getBillHeaderId() {
		return billHeaderId;
	}
	public void setBillHeaderId(int billHeaderId) {
		this.billHeaderId = billHeaderId;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
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
	@Override
	public String toString() {
		return "CompReport [billHeaderId=" + billHeaderId + ", invoiceNo=" + invoiceNo + ", billDate=" + billDate
				+ ", custId=" + custId + ", companyId=" + companyId + ", taxPer=" + taxPer + ", taxableAmt="
				+ taxableAmt + ", sgstAmt=" + sgstAmt + ", cgstAmt=" + cgstAmt + ", igstAmt=" + igstAmt + ", totaTax="
				+ totaTax + ", grandTotal=" + grandTotal + ", custName=" + custName + ", locId=" + locId + ", userId="
				+ userId + ", roundOff=" + roundOff + ", disc_amt=" + disc_amt + ", user_name=" + user_name
				+ ", del_status=" + del_status + "]";
	}
	
	
}
