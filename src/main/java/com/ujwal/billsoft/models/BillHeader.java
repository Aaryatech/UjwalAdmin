package com.ujwal.billsoft.models;

import java.sql.Date;
import java.util.List;



public class BillHeader {
	private int billHeaderId; 
	private String invoiceNo;
	private String billDate;
	private String billDateTime;
	private int custId;
	private int companyId;
	private int locId;
	private int userId;
	private float taxPer;
	private float taxableAmt;
	private float sgstAmt;
	private float cgstAmt;
	private float igstAmt;
	private float discAmt;
	private float roundOff;
	private float totaTax;
	private float grandTotal;
	private int status ;
	private String remark;
	private int delStatus;
	private int ex_int1;
	private int ex_int2;
	private String ex_var1;
	private String ex_var2;
	private float ex_float1;
	private float ex_float2;
	
	List<BillDetails> billDetailList;
	
	
	public List<BillDetails> getBillDetailList() {
		return billDetailList;
	}
	public void setBillDetailList(List<BillDetails> billDetailList) {
		this.billDetailList = billDetailList;
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
	public String getBillDateTime() {
		return billDateTime;
	}
	public void setBillDateTime(String billDateTime) {
		this.billDateTime = billDateTime;
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
	
	public float getDiscAmt() {
		return discAmt;
	}
	public void setDiscAmt(float discAmt) {
		this.discAmt = discAmt;
	}
	public float getRoundOff() {
		return roundOff;
	}
	public void setRoundOff(float roundOff) {
		this.roundOff = roundOff;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public int getEx_int2() {
		return ex_int2;
	}
	public void setEx_int2(int ex_int2) {
		this.ex_int2 = ex_int2;
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
	public float getEx_float1() {
		return ex_float1;
	}
	public void setEx_float1(float ex_float1) {
		this.ex_float1 = ex_float1;
	}
	public float getEx_float2() {
		return ex_float2;
	}
	public void setEx_float2(float ex_float2) {
		this.ex_float2 = ex_float2;
	}
	@Override
	public String toString() {
		return "BillHeader [billHeaderId=" + billHeaderId + ", invoiceNo=" + invoiceNo + ", billDate=" + billDate
				+ ", billDateTime=" + billDateTime + ", custId=" + custId + ", companyId=" + companyId + ", locId="
				+ locId + ", userId=" + userId + ", taxPer=" + taxPer + ", taxableAmt=" + taxableAmt + ", sgstAmt="
				+ sgstAmt + ", cgstAmt=" + cgstAmt + ", igstAmt=" + igstAmt + ", discAmt=" + discAmt + ", roundOff="
				+ roundOff + ", totaTax=" + totaTax + ", grandTotal=" + grandTotal + ", status=" + status + ", remark="
				+ remark + ", delStatus=" + delStatus + ", ex_int1=" + ex_int1 + ", ex_int2=" + ex_int2 + ", ex_var1="
				+ ex_var1 + ", ex_var2=" + ex_var2 + ", ex_float1=" + ex_float1 + ", ex_float2=" + ex_float2
				+ ", billDetailList=" + billDetailList + "]";
	}	
	
	
}
