package com.ujwal.billsoft.models;


public class TaxBillBean {
	

	private int billDetailId;
	private int billHeaderId;
	private int companyId;
	private int custId;
	private String custGstn; 
	private String billDate;
	private String custName;
	private float cgstAmt;
	private float sgstAmt;
	private float igstAmt;
	private float taxableAmt;
	private float taxPer;
	private String invoiceNo;
	private float grandTotal;
	
	
	
	public float getCgstAmt() {
		return cgstAmt;
	}
	public void setCgstAmt(float cgstAmt) {
		this.cgstAmt = cgstAmt;
	}
	public float getSgstAmt() {
		return sgstAmt;
	}
	public void setSgstAmt(float sgstAmt) {
		this.sgstAmt = sgstAmt;
	}
	public float getIgstAmt() {
		return igstAmt;
	}
	public void setIgstAmt(float igstAmt) {
		this.igstAmt = igstAmt;
	}
	public float getTaxableAmt() {
		return taxableAmt;
	}
	public void setTaxableAmt(float taxableAmt) {
		this.taxableAmt = taxableAmt;
	}
	public float getTaxPer() {
		return taxPer;
	}
	public void setTaxPer(float taxPer) {
		this.taxPer = taxPer;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public int getBillDetailId() {
		return billDetailId;
	}
	public void setBillDetailId(int billDetailId) {
		this.billDetailId = billDetailId;
	}
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
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getCustGstn() {
		return custGstn;
	}
	public void setCustGstn(String custGstn) {
		this.custGstn = custGstn;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}	
	public float getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}
	@Override
	public String toString() {
		return "TaxBillBean [billDetailId=" + billDetailId + ", billHeaderId=" + billHeaderId + ", companyId="
				+ companyId + ", custId=" + custId + ", custGstn=" + custGstn + ", billDate=" + billDate + ", custName="
				+ custName + ", cgstAmt=" + cgstAmt + ", sgstAmt=" + sgstAmt + ", igstAmt=" + igstAmt + ", taxableAmt="
				+ taxableAmt + ", taxPer=" + taxPer + ", invoiceNo=" + invoiceNo + ", grandTotal=" + grandTotal + "]";
	}
	
	
	
	
	
	
	
}
