package com.ujwal.billsoft.models;

public class MModelBean {
	
	private int modelId;
	private String modelName;
	private String modelNo;
	private String productionDate;
	private int delStatus;
	private int companyId;
	
	
	
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
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
	public String getModelNo() {
		return modelNo;
	}
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}
	public String getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}
	
	@Override
	public String toString() {
		return "MModelBean [modelId=" + modelId + ", modelName=" + modelName + ", modelNo=" + modelNo
				+ ", productionDate=" + productionDate + "]";
	}
		
}
