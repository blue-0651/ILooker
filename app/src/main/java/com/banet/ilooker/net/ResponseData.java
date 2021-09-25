package com.banet.ilooker.net;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseData<T> {
	@SerializedName("ProcRsltCd")
	@Expose
	private String procRsltCd = "";

	@SerializedName("RsltMsg")
	@Expose
	private String rsltMsg = "";


	@SerializedName("RsltMsgPopupYN")
	@Expose
	private String rsltMsgPopupYN = "";

	@SerializedName("TotCnt")
	@Expose
	private String TotCnt = "";

	@SerializedName("CurrPnt")
	@Expose
	private String CurrPnt = "";


	@SerializedName("data")
	@Expose
	private T data;

	@SerializedName("list")
	@Expose
	private List<T> list;

	public String getProcRsltCd() {
		return procRsltCd;
	}

	public void setProcRsltCd(String procRsltCd) {
		this.procRsltCd = procRsltCd;
	}

	public String getError() {
		return rsltMsg;
	}

	public void setError(String error) {
		this.rsltMsg = error;
	}

	public String getMsgPopupYN() {
		return rsltMsgPopupYN;
	}

	public String getResultMsg() {
		return rsltMsgPopupYN;
	}

	public String getCurrPnt() {
		return CurrPnt;
	}

	public void setMsgPopupYN(String message) {
		this.rsltMsgPopupYN = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<T> getList(){
		return list;
	}

	public void setList(List<T> list){
		this.list = list;
	}
}
