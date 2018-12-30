package com.ujwal.billsoft.models;

public class MPartList {

	private int partId;
	private String partName;
	private String partNo;
	private String partRegisterNo;
	private int partUomId;
	private String partSpecification;
	private int partTaxId;
	private String partRoNo;
	private String partMrp;
	private int compId;
	private String compName;
	private String taxDesc;
	private String uomName;
	private int uomId;
	private int partDelStatus;
	private String modelName;
	private int modelId;
	
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public int getModelId() {
		return modelId;
	}
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public String getPartRegisterNo() {
		return partRegisterNo;
	}
	public void setPartRegisterNo(String partRegisterNo) {
		this.partRegisterNo = partRegisterNo;
	}
	public int getPartUomId() {
		return partUomId;
	}
	public void setPartUomId(int partUomId) {
		this.partUomId = partUomId;
	}
	public String getPartSpecification() {
		return partSpecification;
	}
	public void setPartSpecification(String partSpecification) {
		this.partSpecification = partSpecification;
	}
	public int getPartTaxId() {
		return partTaxId;
	}
	public void setPartTaxId(int partTaxId) {
		this.partTaxId = partTaxId;
	}
	public String getPartRoNo() {
		return partRoNo;
	}
	public void setPartRoNo(String partRoNo) {
		this.partRoNo = partRoNo;
	}
	public String getPartMrp() {
		return partMrp;
	}
	public void setPartMrp(String partMrp) {
		this.partMrp = partMrp;
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
	public int getPartId() {
		return partId;
	}
	public void setPartId(int partId) {
		this.partId = partId;
	}
	public String getTaxDesc() {
		return taxDesc;
	}
	public void setTaxDesc(String taxDesc) {
		this.taxDesc = taxDesc;
	}
	public String getUomName() {
		return uomName;
	}
	public void setUomName(String uomName) {
		this.uomName = uomName;
	}
	public int getUomId() {
		return uomId;
	}
	public void setUomId(int uomId) {
		this.uomId = uomId;
	}
	public int getPartDelStatus() {
		return partDelStatus;
	}
	public void setPartDelStatus(int partDelStatus) {
		this.partDelStatus = partDelStatus;
	}
	@Override
	public String toString() {
		return "MPartList [partName=" + partName + ", partNo=" + partNo + ", partRegisterNo=" + partRegisterNo
				+ ", partUomId=" + partUomId + ", partSpecification=" + partSpecification + ", partTaxId=" + partTaxId
				+ ", partRoNo=" + partRoNo + ", partMrp=" + partMrp + ", compId=" + compId + ", compName=" + compName
				+ "]";
	}
	
	
}
