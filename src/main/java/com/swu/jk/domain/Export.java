package com.swu.jk.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Export {
	
	private String id;
	private String contractIds;
	private String customerContract;
	private Date inputDate;
	private String consignee; 
	private String lcno;
	
	//虚拟字段
	private String epnum;
	private String extnum;
	
	private String marks;
	private String shipmentPort;
	private String destinationPort;
	private String transportMode;
	private String priceCondition;
	private String remark;
	private Integer boxNum;
	private Integer cnumber;
	private String packingUnit;
	private BigDecimal grossWeight;
	private BigDecimal netWeight;
	private BigDecimal sizeLength;
	private BigDecimal sizeWidth;
	private BigDecimal sizeHeight;
	private BigDecimal csize;
	private BigDecimal amount;
	private BigDecimal noTax;
	private BigDecimal tax;
	private BigDecimal costPrice;
	private BigDecimal costTax;
	private Integer state;
	private String createBy;
	private String createDept;
	private Date createTime;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContractIds() {
		return contractIds;
	}
	public void setContractIds(String contractIds) {
		this.contractIds = contractIds;
	}
	public String getCustomerContract() {
		return customerContract;
	}
	public void setCustomerContract(String customerContract) {
		this.customerContract = customerContract;
	}
	public Date getInputDate() {
		return inputDate;
	}
	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getLcno() {
		return lcno;
	}
	public void setLcno(String lcno) {
		this.lcno = lcno;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	public String getShipmentPort() {
		return shipmentPort;
	}
	public void setShipmentPort(String shipmentPort) {
		this.shipmentPort = shipmentPort;
	}
	public String getDestinationPort() {
		return destinationPort;
	}
	public void setDestinationPort(String destinationPort) {
		this.destinationPort = destinationPort;
	}
	public String getTransportMode() {
		return transportMode;
	}
	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}
	public String getPriceCondition() {
		return priceCondition;
	}
	public void setPriceCondition(String priceCondition) {
		this.priceCondition = priceCondition;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getBoxNum() {
		return boxNum;
	}
	public void setBoxNum(Integer boxNum) {
		this.boxNum = boxNum;
	}
	public Integer getCnumber() {
		return cnumber;
	}
	public void setCnumber(Integer cnumber) {
		this.cnumber = cnumber;
	}
	public String getPackingUnit() {
		return packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}
	public BigDecimal getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(BigDecimal grossWeight) {
		this.grossWeight = grossWeight;
	}
	public BigDecimal getNetWeight() {
		return netWeight;
	}
	public void setNetWeight(BigDecimal netWeight) {
		this.netWeight = netWeight;
	}
	public BigDecimal getSizeLength() {
		return sizeLength;
	}
	public void setSizeLength(BigDecimal sizeLength) {
		this.sizeLength = sizeLength;
	}
	public BigDecimal getSizeWidth() {
		return sizeWidth;
	}
	public void setSizeWidth(BigDecimal sizeWidth) {
		this.sizeWidth = sizeWidth;
	}
	public BigDecimal getSizeHeight() {
		return sizeHeight;
	}
	public void setSizeHeight(BigDecimal sizeHeight) {
		this.sizeHeight = sizeHeight;
	}
	public BigDecimal getCsize() {
		return csize;
	}
	public void setCsize(BigDecimal csize) {
		this.csize = csize;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getNoTax() {
		return noTax;
	}
	public void setNoTax(BigDecimal noTax) {
		this.noTax = noTax;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public BigDecimal getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}
	public BigDecimal getCostTax() {
		return costTax;
	}
	public void setCostTax(BigDecimal costTax) {
		this.costTax = costTax;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
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
	public String getEpnum() {
		return epnum;
	}
	public void setEpnum(String epnum) {
		this.epnum = epnum;
	}
	public String getExtnum() {
		return extnum;
	}
	public void setExtnum(String extnum) {
		this.extnum = extnum;
	}
	
	
	
	
	
}
