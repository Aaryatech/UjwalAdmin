package com.ujwal.billsoft.models;

import java.util.Date;



public class MPart {
	
	private int partId;
	private int compId;
	private String partName;
	private String partNo;
	private String partRegisterNo;
	private int partUomId;
	private String partSpecification;
	private int partTaxId;
	private String partRoNo;
	private String partMrp;
	
	private int partDelStatus;
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
	
	
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
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
		return "MPart [partId=" + partId + ", partName=" + partName + ", partNo=" + partNo + ", partRegisterNo="
				+ partRegisterNo + ", partUomId=" + partUomId + ", partSpecification=" + partSpecification
				+ ", partTaxId=" + partTaxId + ", partRoNo=" + partRoNo + ", partMrp=" + partMrp + ", partDelStatus="
				+ partDelStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", extVar1="
				+ extVar1 + ", extVar2=" + extVar2 + ", extVar3=" + extVar3 + ", exBool1=" + exBool1 + ", exBool2="
				+ exBool2 + ", exDate1=" + exDate1 + ", exDate2=" + exDate2 + ", getPartId()=" + getPartId()
				+ ", getPartName()=" + getPartName() + ", getPartNo()=" + getPartNo() + ", getPartRegisterNo()="
				+ getPartRegisterNo() + ", getPartUomId()=" + getPartUomId() + ", getPartSpecification()="
				+ getPartSpecification() + ", getPartTaxId()=" + getPartTaxId() + ", getPartRoNo()=" + getPartRoNo()
				+ ", getPartMrp()=" + getPartMrp() + ", getPartDelStatus()=" + getPartDelStatus() + ", getExInt1()="
				+ getExInt1() + ", getExInt2()=" + getExInt2() + ", getExInt3()=" + getExInt3() + ", getExtVar1()="
				+ getExtVar1() + ", getExtVar2()=" + getExtVar2() + ", getExtVar3()=" + getExtVar3() + ", isExBool1()="
				+ isExBool1() + ", isExBool2()=" + isExBool2() + ", getExDate1()=" + getExDate1() + ", getExDate2()="
				+ getExDate2() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
