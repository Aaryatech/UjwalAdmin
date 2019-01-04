package com.ujwal.billsoft.models;


public class TaxBillBean {
	

	private int billDetailId;
	private int billHeaderId;
	private int companyId;
	private int custId;
	private String custGstn; 
	private String billDate;
	private String custName;
	private float cgstPer;
	private float sgstPer;
	private float cgst;
	private float sgst;
	private float igst;
	private float igstPer;
	private float taxableAmount;
	private float taxPer;
	private String invoiceNo;
	private float billAmount;
	private float grandTotal;
	
	
	public float getCgstPer() {
		return cgstPer;
	}
	public void setCgstPer(float cgstPer) {
		this.cgstPer = cgstPer;
	}
	public float getSgstPer() {
		return sgstPer;
	}
	public void setSgstPer(float sgstPer) {
		this.sgstPer = sgstPer;
	}
	public float getCgst() {
		return cgst;
	}
	public void setCgst(float cgst) {
		this.cgst = cgst;
	}
	public float getSgst() {
		return sgst;
	}
	public void setSgst(float sgst) {
		this.sgst = sgst;
	}
	public float getIgst() {
		return igst;
	}
	public void setIgst(float igst) {
		this.igst = igst;
	}
	public float getTaxPer() {
		return taxPer;
	}
	public void setTaxPer(float taxPer) {
		this.taxPer = taxPer;
	}
	public float getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(float billAmount) {
		this.billAmount = billAmount;
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
	public float getIgstPer() {
		return igstPer;
	}
	public void setIgstPer(float igstPer) {
		this.igstPer = igstPer;
	}
	public float getTaxableAmount() {
		return taxableAmount;
	}
	public void setTaxableAmount(float taxableAmount) {
		this.taxableAmount = taxableAmount;
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
				+ custName + ", cgstPer=" + cgstPer + ", sgstPer=" + sgstPer + ", cgst=" + cgst + ", sgst=" + sgst
				+ ", igst=" + igst + ", igstPer=" + igstPer + ", taxableAmount=" + taxableAmount + ", taxPer=" + taxPer
				+ ", invoiceNo=" + invoiceNo + ", billAmount=" + billAmount + ", grandTotal=" + grandTotal + "]";
	}
	
	
	
	
	
}
