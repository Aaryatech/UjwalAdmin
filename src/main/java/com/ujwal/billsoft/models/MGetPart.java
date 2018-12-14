package com.ujwal.billsoft.models;

public class MGetPart {
	private int partId;
	private String partName;
	private String partNo;
	private String partRegisterNo;
	private int partUomId;
	private String partSpecification;
	private int partTaxId;
	private String partRoNo;
	private String partMrp;
	private int partDelStatus;
	private int taxId;
	private String hsnCode;
	private String taxDesc;
	private float taxPer;
	private float cgstPer;
	private float sgstPer;
	private float igstPer;
	private float cessPer;
	private String uomName;
	
	public String getUomName() {
		return uomName;
	}
	public void setUomName(String uomName) {
		this.uomName = uomName;
	}
	public int getPartId() {
		return partId;
	}
	public void setPartId(int partId) {
		this.partId = partId;
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
	public int getPartDelStatus() {
		return partDelStatus;
	}
	public void setPartDelStatus(int partDelStatus) {
		this.partDelStatus = partDelStatus;
	}
	public int getTaxId() {
		return taxId;
	}
	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}
	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}
	public String getTaxDesc() {
		return taxDesc;
	}
	public void setTaxDesc(String taxDesc) {
		this.taxDesc = taxDesc;
	}
	public float getTaxPer() {
		return taxPer;
	}
	public void setTaxPer(float taxPer) {
		this.taxPer = taxPer;
	}
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
	public float getIgstPer() {
		return igstPer;
	}
	public void setIgstPer(float igstPer) {
		this.igstPer = igstPer;
	}
	public float getCessPer() {
		return cessPer;
	}
	public void setCessPer(float cessPer) {
		this.cessPer = cessPer;
	}
	@Override
	public String toString() {
		return "MGetPart [partId=" + partId + ", partName=" + partName + ", partNo=" + partNo + ", partRegisterNo="
				+ partRegisterNo + ", partUomId=" + partUomId + ", partSpecification=" + partSpecification
				+ ", partTaxId=" + partTaxId + ", partRoNo=" + partRoNo + ", partMrp=" + partMrp + ", partDelStatus="
				+ partDelStatus + ", taxId=" + taxId + ", hsnCode=" + hsnCode + ", taxDesc=" + taxDesc + ", taxPer="
				+ taxPer + ", cgstPer=" + cgstPer + ", sgstPer=" + sgstPer + ", igstPer=" + igstPer + ", cessPer="
				+ cessPer + ", uomName=" + uomName + "]";
	}

	
	
	
}
