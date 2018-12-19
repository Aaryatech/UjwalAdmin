package com.ujwal.billsoft.models;

public class CradentialValidator {

	private MUser musr;
	private boolean error;
	private String msg;
	
	public MUser getMusr() {
		return musr;
	}
	public void setMusr(MUser musr) {
		this.musr = musr;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "CradentialValidator [musr=" + musr + ", error=" + error + ", msg=" + msg + "]";
	}
	
	
}
