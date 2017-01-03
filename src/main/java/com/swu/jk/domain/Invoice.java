package com.swu.jk.domain;

import java.util.Date;

public class Invoice {
	
	private String id;
	private String scNo;
	private String blNo;
	private String tradeTerms;
	private String createBy;
	private String createDept;
	private Date createTime;
	
	// private PackingList packingList;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getScNo() {
		return scNo;
	}
	public void setScNo(String scNo) {
		this.scNo = scNo;
	}
	public String getBlNo() {
		return blNo;
	}
	public void setBlNo(String blNo) {
		this.blNo = blNo;
	}
	public String getTradeTerms() {
		return tradeTerms;
	}
	public void setTradeTerms(String tradeTerms) {
		this.tradeTerms = tradeTerms;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateDept() {
		return createDept;
	}
	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/*public PackingList getPackingList() {
		return packingList;
	}
	public void setPackingList(PackingList packingList) {
		this.packingList = packingList;
	}*/
}
