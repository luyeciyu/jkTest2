package com.swu.jk.vo;

import java.math.BigDecimal;
import java.util.List;

import com.swu.jk.domain.Export;
import com.swu.jk.domain.ExtEproduct;
import com.swu.jk.domain.Factory;

public class ExportProductVO {
	
	private String id;
	
	//private String exprotId;
	private Export export;
	//private String factoryId;
	private Factory factory;
	
	//private List<ExtEproduct> extEproducts;
	private List<ExtEproductVO> extEproducts;
	
	private String contractProductId;
	private String contractId;
	private String contractNo;
	private String productName;
	private String productNo;
	private String productImage;
	private String productDesc;
	private String loadingRate;
	private String packingUnit;
	private Integer cnumber;
	private Integer outNumber;
	private boolean finished;
	private BigDecimal grossWeight;
	private BigDecimal netWeight;
	private BigDecimal sizeLength;
	private BigDecimal sizeWidth;
	private BigDecimal sizeHeight;
	private String productRequest;
	private BigDecimal price;
	private BigDecimal amount;
	private String cunit;
	private Integer boxNum;
	private BigDecimal exPrice;
	private String exUnit;
	private BigDecimal noTax;
	private BigDecimal tax;
	private BigDecimal costPrice;
	private BigDecimal costTax;
	private Integer orderNo;
	private boolean accessories;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getContractProductId() {
		return contractProductId;
	}
	public void setContractProductId(String contractProductId) {
		this.contractProductId = contractProductId;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getLoadingRate() {
		return loadingRate;
	}
	public void setLoadingRate(String loadingRate) {
		this.loadingRate = loadingRate;
	}
	public String getPackingUnit() {
		return packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}
	public Integer getCnumber() {
		return cnumber;
	}
	public void setCnumber(Integer cnumber) {
		this.cnumber = cnumber;
	}
	public Integer getOutNumber() {
		return outNumber;
	}
	public void setOutNumber(Integer outNumber) {
		this.outNumber = outNumber;
	}
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
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
	public String getProductRequest() {
		return productRequest;
	}
	public void setProductRequest(String productRequest) {
		this.productRequest = productRequest;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getCunit() {
		return cunit;
	}
	public void setCunit(String cunit) {
		this.cunit = cunit;
	}
	public Integer getBoxNum() {
		return boxNum;
	}
	public void setBoxNum(Integer boxNum) {
		this.boxNum = boxNum;
	}
	public BigDecimal getExPrice() {
		return exPrice;
	}
	public void setExPrice(BigDecimal exPrice) {
		this.exPrice = exPrice;
	}
	public String getExUnit() {
		return exUnit;
	}
	public void setExUnit(String exUnit) {
		this.exUnit = exUnit;
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
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public boolean isAccessories() {
		return accessories;
	}
	public void setAccessories(boolean accessories) {
		this.accessories = accessories;
	}
	public Export getExport() {
		return export;
	}
	public void setExport(Export export) {
		this.export = export;
	}
	public Factory getFactory() {
		return factory;
	}
	public void setFactory(Factory factory) {
		this.factory = factory;
	}
	/*public List<ExtEproduct> getExtEproducts() {
		return extEproducts;
	}
	public void setExtEproducts(List<ExtEproduct> extEproducts) {
		this.extEproducts = extEproducts;
	}*/
	public List<ExtEproductVO> getExtEproducts() {
		return extEproducts;
	}
	public void setExtEproducts(List<ExtEproductVO> extEproducts) {
		this.extEproducts = extEproducts;
	}
	
	
	
	
}
