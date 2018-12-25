package com.ujwal.billsoft.models;

import java.util.Date;


public class MCustomer {
	
	private int custId;
	private String custName;
	private int compId;
	private String custAddress;
	private String custPhone;
	private String custGstn;
	private String custPan;
	private String custEmail;
	private String custVehNo;
	private String custChasiNo;
	private String custRegisNo;
	private String custRoNo;
	private String custState;
	
	
	private int custDelStatus;
	private int exInt1;
	private int exInt2;
	private int exInt3;
	private String extVar1;
	private String extVar2;
	private String extVar3;
	private boolean exBool1;
	private boolean exBool2;
	private Date exDate1;
	private Date exDate2;
	
	
	public String getCustState() {
		return custState;
	}
	public void setCustState(String custState) {
		this.custState = custState;
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
	
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	public String getCustAddress() {
		return custAddress;
	}
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	public String getCustGstn() {
		return custGstn;
	}
	public void setCustGstn(String custGstn) {
		this.custGstn = custGstn;
	}
	public String getCustPan() {
		return custPan;
	}
	public void setCustPan(String custPan) {
		this.custPan = custPan;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	public String getCustVehNo() {
		return custVehNo;
	}
	public void setCustVehNo(String custVehNo) {
		this.custVehNo = custVehNo;
	}
	public String getCustChasiNo() {
		return custChasiNo;
	}
	public void setCustChasiNo(String custChasiNo) {
		this.custChasiNo = custChasiNo;
	}
	public String getCustRegisNo() {
		return custRegisNo;
	}
	public void setCustRegisNo(String custRegisNo) {
		this.custRegisNo = custRegisNo;
	}
	public String getCustRoNo() {
		return custRoNo;
	}
	public void setCustRoNo(String custRoNo) {
		this.custRoNo = custRoNo;
	}
	public int getCustDelStatus() {
		return custDelStatus;
	}
	public void setCustDelStatus(int custDelStatus) {
		this.custDelStatus = custDelStatus;
	}
	public int getExInt1() {
		return exInt1;
	}
	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}
	public int getExInt2() {
		return exInt2;
	}
	public void setExInt2(int exInt2) {
		this.exInt2 = exInt2;
	}
	public int getExInt3() {
		return exInt3;
	}
	public void setExInt3(int exInt3) {
		this.exInt3 = exInt3;
	}
	public String getExtVar1() {
		return extVar1;
	}
	public void setExtVar1(String extVar1) {
		this.extVar1 = extVar1;
	}
	public String getExtVar2() {
		return extVar2;
	}
	public void setExtVar2(String extVar2) {
		this.extVar2 = extVar2;
	}
	public String getExtVar3() {
		return extVar3;
	}
	public void setExtVar3(String extVar3) {
		this.extVar3 = extVar3;
	}
	public boolean isExBool1() {
		return exBool1;
	}
	public void setExBool1(boolean exBool1) {
		this.exBool1 = exBool1;
	}
	public boolean isExBool2() {
		return exBool2;
	}
	public void setExBool2(boolean exBool2) {
		this.exBool2 = exBool2;
	}
	public Date getExDate1() {
		return exDate1;
	}
	public void setExDate1(Date exDate1) {
		this.exDate1 = exDate1;
	}
	public Date getExDate2() {
		return exDate2;
	}
	public void setExDate2(Date exDate2) {
		this.exDate2 = exDate2;
	}
	@Override
	public String toString() {
		return "MCustomer [custId=" + custId + ", custName=" + custName + ", compId=" + compId + ", custAddress="
				+ custAddress + ", custPhone=" + custPhone + ", custGstn=" + custGstn + ", custPan=" + custPan
				+ ", custEmail=" + custEmail + ", custVehNo=" + custVehNo + ", custChasiNo=" + custChasiNo
				+ ", custRegisNo=" + custRegisNo + ", custRoNo=" + custRoNo + ", custState=" + custState
				+ ", custDelStatus=" + custDelStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3="
				+ exInt3 + ", extVar1=" + extVar1 + ", extVar2=" + extVar2 + ", extVar3=" + extVar3 + ", exBool1="
				+ exBool1 + ", exBool2=" + exBool2 + ", exDate1=" + exDate1 + ", exDate2=" + exDate2 + "]";
	}
	
	
}
