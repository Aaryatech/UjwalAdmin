package com.ujwal.billsoft.models;


public class BillReport {
	
	private int billHeaderId; 
	private int billDetailId;
	private String billDate;
	private int custId;
	private String custName;
	public int getBillHeaderId() {
		return billHeaderId;
	}
	public void setBillHeaderId(int billHeaderId) {
		this.billHeaderId = billHeaderId;
	}
	
	public int getBillDetailId() {
		return billDetailId;
	}
	public void setBillDetailId(int billDetailId) {
		this.billDetailId = billDetailId;
	}
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	@Override
	public String toString() {
		return "BillReport [billHeaderId=" + billHeaderId + ", billDetailId=" + billDetailId + ", billDate=" + billDate
				+ ", custId=" + custId + ", custName=" + custName + "]";
	}
	
	
		

}
