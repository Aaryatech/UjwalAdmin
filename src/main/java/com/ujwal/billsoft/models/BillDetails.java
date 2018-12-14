package com.ujwal.billsoft.models;

import java.sql.Date;


public class BillDetails {

	private int billDetailId; 
	private int billHeaderId; 
	private int partId; 

	
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

	private String hsnCode;
	private String taxDesc;
	private String uomName;
	private String remark;
	private int delStatus;
	private int ex_int1;
	private int ex_int2;
	private String ex_var1;
	private String ex_var2;
	private float ex_float1;
	private float ex_float2;
	
	
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
	public String getUomName() {
		return uomName;
	}
	public void setUomName(String uomName) {
		this.uomName = uomName;
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
	public int getEx_int1() {
		return ex_int1;
	}
	public void setEx_int1(int ex_int1) {
		this.ex_int1 = ex_int1;
	}
	public int getEx_int2() {
		return ex_int2;
	}
	public void setEx_int2(int ex_int2) {
		this.ex_int2 = ex_int2;
	}
	public String getEx_var1() {
		return ex_var1;
	}
	public void setEx_var1(String ex_var1) {
		this.ex_var1 = ex_var1;
	}
	public String getEx_var2() {
		return ex_var2;
	}
	public void setEx_var2(String ex_var2) {
		this.ex_var2 = ex_var2;
	}
	public float getEx_float1() {
		return ex_float1;
	}
	public void setEx_float1(float ex_float1) {
		this.ex_float1 = ex_float1;
	}
	public float getEx_float2() {
		return ex_float2;
	}
	public void setEx_float2(float ex_float2) {
		this.ex_float2 = ex_float2;
	}
	@Override
	public String toString() {
		return "BillDetails [billDetailId=" + billDetailId + ", billHeaderId=" + billHeaderId + ", partId=" + partId
				+ ", qty=" + qty + ", mrp=" + mrp + ", baseRate=" + baseRate + ", taxableAmount=" + taxableAmount
				+ ", sgstPer=" + sgstPer + ", sgstRs=" + sgstRs + ", cgstPer=" + cgstPer + ", cgstRs=" + cgstRs
				+ ", igstPer=" + igstPer + ", igstRs=" + igstRs + ", discPer=" + discPer + ", discRs=" + discRs
				+ ", cessPer=" + cessPer + ", cessRs=" + cessRs + ", totalTax=" + totalTax + ", grandTotal="
				+ grandTotal + ", hsnCode=" + hsnCode + ", taxDesc=" + taxDesc + ", uomName=" + uomName + ", remark="
				+ remark + ", delStatus=" + delStatus + ", ex_int1=" + ex_int1 + ", ex_int2=" + ex_int2 + ", ex_var1="
				+ ex_var1 + ", ex_var2=" + ex_var2 + ", ex_float1=" + ex_float1 + ", ex_float2=" + ex_float2 + "]";
	}
	
	
	
}
