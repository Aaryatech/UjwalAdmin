package com.ujwal.billsoft.models;

import java.io.Serializable;

public class GetBillDetail implements Serializable{
	
	private int billDetailId; 
	private int billHeaderId; 
	private int partId; 
	private String partName;
	private String uomName;
	private String hsnCode;
	private float qty;
	private float mrp;
	private float baseRate;
	private float taxableAmount;
	private float sgstPer;
	private float sgstRs;
	private float cgstPer;
	private float cgstRs;
	private float igstPer;
	private float igstRs;
	private float discPer;
	private float discRs;
	private float cessPer;
	private float cessRs;
	private float totalTax;
	private float grandTotal;
	
	private String remark;
	private int delStatus;
	private int exInt1;
	private int exInt2;
	private String exVar1;
	private String exVar2;
	private float exFloat1;
	private float exFloat2;
	
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public int getExInt2() {
		return exInt2;
	}
	public void setExInt2(int exInt2) {
		this.exInt2 = exInt2;
	}
	public String getExVar1() {
		return exVar1;
	}
	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}
	public String getExVar2() {
		return exVar2;
	}
	public void setExVar2(String exVar2) {
		this.exVar2 = exVar2;
	}
	public float getExFloat1() {
		return exFloat1;
	}
	public void setExFloat1(float exFloat1) {
		this.exFloat1 = exFloat1;
	}
	public float getExFloat2() {
		return exFloat2;
	}
	public void setExFloat2(float exFloat2) {
		this.exFloat2 = exFloat2;
	}
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
	public String getUomName() {
		return uomName;
	}
	public void setUomName(String uomName) {
		this.uomName = uomName;
	}
	public float getQty() {
		return qty;
	}
	public void setQty(float qty) {
		this.qty = qty;
	}
	public float getMrp() {
		return mrp;
	}
	public void setMrp(float mrp) {
		this.mrp = mrp;
	}
	public float getBaseRate() {
		return baseRate;
	}
	public void setBaseRate(float baseRate) {
		this.baseRate = baseRate;
	}
	public float getTaxableAmount() {
		return taxableAmount;
	}
	public void setTaxableAmount(float taxableAmount) {
		this.taxableAmount = taxableAmount;
	}
	public float getSgstPer() {
		return sgstPer;
	}
	public void setSgstPer(float sgstPer) {
		this.sgstPer = sgstPer;
	}
	public float getSgstRs() {
		return sgstRs;
	}
	public void setSgstRs(float sgstRs) {
		this.sgstRs = sgstRs;
	}
	public float getCgstPer() {
		return cgstPer;
	}
	public void setCgstPer(float cgstPer) {
		this.cgstPer = cgstPer;
	}
	public float getCgstRs() {
		return cgstRs;
	}
	public void setCgstRs(float cgstRs) {
		this.cgstRs = cgstRs;
	}
	public float getIgstPer() {
		return igstPer;
	}
	public void setIgstPer(float igstPer) {
		this.igstPer = igstPer;
	}
	public float getIgstRs() {
		return igstRs;
	}
	public void setIgstRs(float igstRs) {
		this.igstRs = igstRs;
	}
	public float getDiscPer() {
		return discPer;
	}
	public void setDiscPer(float discPer) {
		this.discPer = discPer;
	}
	public float getDiscRs() {
		return discRs;
	}
	public void setDiscRs(float discRs) {
		this.discRs = discRs;
	}
	public float getCessPer() {
		return cessPer;
	}
	public void setCessPer(float cessPer) {
		this.cessPer = cessPer;
	}
	public float getCessRs() {
		return cessRs;
	}
	public void setCessRs(float cessRs) {
		this.cessRs = cessRs;
	}
	public float getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(float totalTax) {
		this.totalTax = totalTax;
	}
	public float getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}
	
	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}
	@Override
	public String toString() {
		return "GetBillDetail [billDetailId=" + billDetailId + ", billHeaderId=" + billHeaderId + ", partId=" + partId
				+ ", partName=" + partName + ", uomName=" + uomName + ", hsnCode=" + hsnCode + ", qty=" + qty + ", mrp="
				+ mrp + ", baseRate=" + baseRate + ", taxableAmount=" + taxableAmount + ", sgstPer=" + sgstPer
				+ ", sgstRs=" + sgstRs + ", cgstPer=" + cgstPer + ", cgstRs=" + cgstRs + ", igstPer=" + igstPer
				+ ", igstRs=" + igstRs + ", discPer=" + discPer + ", discRs=" + discRs + ", cessPer=" + cessPer
				+ ", cessRs=" + cessRs + ", totalTax=" + totalTax + ", grandTotal=" + grandTotal + "]";
	}

	
}
