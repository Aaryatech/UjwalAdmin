package com.ujwal.billsoft.models;

public class FocItemBean {

	private String id;
	private String invoiceNo;
	private String billDate;
	private String partName;
	private String uomName;
	private float mrp;
	private float qty;
	private int partUomId;
	private float grandTotal;
	private int partId;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPartId() {
		return partId;
	}
	public void setPartId(int partId) {
		this.partId = partId;
	}
	public float getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
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
	public float getMrp() {
		return mrp;
	}
	public void setMrp(float mrp) {
		this.mrp = mrp;
	}
	public float getQty() {
		return qty;
	}
	public void setQty(float qty) {
		this.qty = qty;
	}
	public int getPartUomId() {
		return partUomId;
	}
	public void setPartUomId(int partUomId) {
		this.partUomId = partUomId;
	}
	@Override
	public String toString() {
		return "FocItemBean [id=" + id + ", invoiceNo=" + invoiceNo + ", billDate=" + billDate + ", partName="
				+ partName + ", uomName=" + uomName + ", mrp=" + mrp + ", qty=" + qty + ", partUomId=" + partUomId
				+ ", grandTotal=" + grandTotal + ", partId=" + partId + "]";
	}
		
	
}
