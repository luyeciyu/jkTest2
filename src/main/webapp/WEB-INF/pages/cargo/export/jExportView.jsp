<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 告诉浏览器本网页符合XHTML1.0过渡型DOCTYPE -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 
 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>	
<style> 
	.product_image{ margin:5px;border:1px solid black;height:100px;weight:80px; }
</style>	
</head>
 
<body>
<form method="post">
	<input type="hidden" name="id" id="id" value="${id}"/>
 
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
	<li id="back">
		<a href="${ctx}/cargo/export/list.action">返回</a>
	</li>
</ul>
    </div>
</div>
</div>
</div>
     
<div class="textbox" id="centerTextbox">
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
        浏览报运信息
       &nbsp;&nbsp;&nbsp;
 
	</div> 
    </div>
    </div>
<div>
 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle" nowrap>合同或确认书号：</td>
	            <td class="tableContent">${obj.customerContract}</td>
	            <td class="columnTitle">制单日期：</td>
	            <td class="tableContent"> <fmt:formatDate value="${obj.inputDate}" pattern="yyyy-MM-dd"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle">L/C No.：</td>
	            <td class="tableContent">${obj.lcno}</td>
	            <td class="columnTitle">装运港：</td>
	            <td class="tableContent">${obj.shipmentPort}</td>
	        </tr>
	        <tr>
	            <td class="columnTitle">收货人及地址：</td>
	            <td class="tableContent" colspan="3">${obj.consignee}</td>
	        </tr>
	        <tr>
	            <td class="columnTitle">运输方式：</td>
	            <td class="tableContent">${obj.transportMode}</td>
	            <td class="columnTitle">价格条件：</td>
	            <td class="tableContent">${obj.priceCondition}</td>
	        </tr>
	        <tr>
				<td class="columnTitle">唛头：</td>
	            <td class="tableContent" colspan="3">${obj.marks}</td>
			</tr>
	        <tr>
				<td class="columnTitle">备注：</td>
	            <td class="tableContent" colspan="3">${obj.remark}</td>
			</tr>
		</table>
	</div>
</div>
 
<!-- 货物列表 -->
<div class="textbox" id="centerTextbox">
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
        货物信息
    </div> 
    </div>
    </div>
</div>
 
<div class="listTablew">
<table class="commonTable_main" cellSpacing="1" id="resultTable">
	<tr class="rowTitle" align="middle">
		<td width="33">序号</td>
		<td>货号</td>
		<td>货物描述</td>
		<td>厂家</td>
		<td>数量</td>
		<td>单位</td>
		<td>箱数</td>
		<td>单价</td>
		<td>总金额</td>
		<td>净重</td>
		<td>毛重</td>
	</tr>
	
	<c:forEach var="ep" items="${obj.exportProducts}" varStatus="status">
		<tr height="30">
			<td align="center">${status.index+1}</td>
			<td>${ep.productNo}</td>
			<td width="400">${ep.productDesc}</td>
			<td>${ep.factory.factoryName}</td>
			<td>${ep.cnumber}</td>
			<td>${ep.packingUnit}</td>
			<td>${ep.boxNum}</td>
			<td>${ep.price}</td>
			<td>${ep.amount}</td>
			<td>${ep.grossWeight}</td>
			<td>${ep.netWeight}</td>
		</tr>
		
		<c:forEach var="ext" items="${ep.extEproducts}" varStatus="status">
		<tr height="30">
			<td align="center"></td>
			<td><font color="blue">附件${status.index+1}: </font>${ext.productNo}</td>
			<td>${ext.productDesc}</td>
			<td>${ext.factory.factoryName}</td>
			<td>${ext.cnumber}</td>
			<td>${ext.packingUnit}</td>
			<td></td>
			<td>${ext.price}</td>
			<td>${ext.amount}</td>
		</tr>
		
		
		</c:forEach>
		
	</c:forEach>
	
	<%-- <s:iterator value="%{#root.exportProduct}" var="cp" status="line">
		<tr height="30">
			<td align="center"><s:property value="#line.index+1"/></td>
			<td>${cp.productNo}</td>
			<td width="400">${cp.productDesc}</td>
			<td>${cp.factory.factoryName}</td>
			<td>${cp.cnumber}</td>
			<td>${cp.packingUnit}</td>
			<td>${cp.boxNum}</td>
			<td>${cp.price}</td>
			<td>${cp.amount}</td>
			<td>${cp.grossWeight}</td>
			<td>${cp.netWeight}</td>
		</tr>
	<s:iterator value="%{#cp.extEproduct}" var="ep" status="line">
		<tr height="30">
			<td align="center"></td>
			<td><font color="blue">附件<s:property value="#line.index+1"/>: </font>${cp.productNo}</td>
			<td>${selffn:htmlNewline(ep.productDesc)}</td>
			<td>${ep.factory.factoryName}</td>
			<td>${ep.cnumber}</td>
			<td>${ep.packingUnit}</td>
			<td></td>
			<td>${ep.price}</td>
			<td>${ep.amount}</td>
		</tr>
	</s:iterator>
	</s:iterator> --%>
</table>
</div>
 
 
 
 
</form>
</body>
</html>

