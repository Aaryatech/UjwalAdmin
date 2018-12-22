package com.ujwal.billsoft.models;


public class TaxBillBean {
	

	private int billDetailId;
	private int billHeaderId;
	private int companyId;
	private int custId;
	private String custGstn; 
	private String billDate;
	private String custName;
	private float igstPer;
	private float taxableAmount;
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
	@Override
	public String toString() {
		return "TaxBillBean [billDetailId=" + billDetailId + ", billHeaderId=" + billHeaderId + ", companyId="
				+ companyId + ", custId=" + custId + ", custGstn=" + custGstn + ", billDate=" + billDate + ", custName="
				+ custName + ", igstPer=" + igstPer + ", taxableAmount=" + taxableAmount + "]";
	}
	
	
	
	
}
