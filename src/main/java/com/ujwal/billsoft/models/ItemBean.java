package com.ujwal.billsoft.models;

public class ItemBean {
	
	
	private int partId;
	private String partName;
	private String hsnCode;
	private float cgstRs;
	private float sgstRs;
	private float igstRs;
	private int partTaxId;
	private float taxableAmount;
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
	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}
	public float getCgstRs() {
		return cgstRs;
	}
	public void setCgstRs(float cgstRs) {
		this.cgstRs = cgstRs;
	}
	public float getSgstRs() {
		return sgstRs;
	}
	public void setSgstRs(float sgstRs) {
		this.sgstRs = sgstRs;
	}
	public float getIgstRs() {
		return igstRs;
	}
	public void setIgstRs(float igstRs) {
		this.igstRs = igstRs;
	}
	
	
	public int getPartTaxId() {
		return partTaxId;
	}
	public void setPartTaxId(int partTaxId) {
		this.partTaxId = partTaxId;
	}
	
	public float getTaxableAmount() {
		return taxableAmount;
	}
	public void setTaxableAmount(float taxableAmount) {
		this.taxableAmount = taxableAmount;
	}
	@Override
	public String toString() {
		return "ItemBean [partId=" + partId + ", partName=" + partName + ", hsnCode=" + hsnCode + ", taxable=" + taxableAmount
				+ ", cgstPer=" + cgstRs + ", sgstPer=" + sgstRs + ", igstPer=" + igstRs + ", partTax_id="
				+ partTaxId + "]";
	}

	
}
