package com.ujwal.billsoft.models;

public class MUom {

	private int uomId;
	private String uomName;
	private int delStatus;
	private int exInt1;
	private String exVar1;
	private boolean exBool1;
	
	public int getUomId() {
		return uomId;
	}
	public void setUomId(int uomId) {
		this.uomId = uomId;
	}
	public String getUomName() {
		return uomName;
	}
	public void setUomName(String uomName) {
		this.uomName = uomName;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public int getExInt1() {
		return exInt1;
	}
	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}
	public String getExVar1() {
		return exVar1;
	}
	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}
	public boolean isExBool1() {
		return exBool1;
	}
	public void setExBool1(boolean exBool1) {
		this.exBool1 = exBool1;
	}
	@Override
	public String toString() {
		return "MUom [uomId=" + uomId + ", uomName=" + uomName + ", delStatus=" + delStatus + ", exInt1=" + exInt1
				+ ", exVar1=" + exVar1 + ", exBool1=" + exBool1 + "]";
	}
	
	
}
