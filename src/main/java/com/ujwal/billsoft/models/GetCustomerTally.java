package com.ujwal.billsoft.models;


public class GetCustomerTally {

	private String id;
	private int custId;
	private String custName;
	private int compId; 
	private String compName;
	private String custAddress;
	private String custPhone;
	private String custGstn;
	private String custPan;
	private String custEmail;
	private String custVehNo;
	private String custVinNo;
	private String custRegisNo;
	private String custModelNo;
	private String custState;
	private int custDelStatus;
	private int modelId;
	private String modelName;
	
	
	
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public int getModelId() {
		return modelId;
	}
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getCustState() {
		return custState;
	}
	public void setCustState(String custState) {
		this.custState = custState;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
	public String getCustRegisNo() {
		return custRegisNo;
	}
	public void setCustRegisNo(String custRegisNo) {
		this.custRegisNo = custRegisNo;
	}
   
	public String getCustVinNo() {
		return custVinNo;
	}
	public void setCustVinNo(String custVinNo) {
		this.custVinNo = custVinNo;
	}
	public String getCustModelNo() {
		return custModelNo;
	}
	public void setCustModelNo(String custModelNo) {
		this.custModelNo = custModelNo;
	}
	public int getCustDelStatus() {
		return custDelStatus;
	}
	public void setCustDelStatus(int custDelStatus) {
		this.custDelStatus = custDelStatus;
	}
	@Override
	public String toString() {
		return "GetCustomerTally [id=" + id + ", custName=" + custName + ", compId=" + compId + ", compName="
				+ compName + ", custAddress=" + custAddress + ", custPhone=" + custPhone + ", custGstn=" + custGstn
				+ ", custPan=" + custPan + ", custEmail=" + custEmail + ", custVehNo=" + custVehNo + ", custVinNo="
				+ custVinNo + ", custRegisNo=" + custRegisNo + ", custModelNo=" + custModelNo + ", custState="
				+ custState + ", custDelStatus=" + custDelStatus + ", modelId=" + modelId + ", modelName=" + modelName
				+ "]";
	}
	
	
	
	
	
	

}
