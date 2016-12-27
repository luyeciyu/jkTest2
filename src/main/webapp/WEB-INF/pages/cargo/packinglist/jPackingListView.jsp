<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 告诉浏览器本网页符合XHTML1.0过渡型DOCTYPE -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
</head>
 
<body>
<form method="post">
	<input type="hidden" name="id" id="id" value="${o.id}"/>
 
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
<li id="back"><a href="${ctx}/cargo/packinglist/list.action">返回</a></li>
</ul>
    </div>
</div>
</div>
</div>
     
<div class="textbox" id="centerTextbox">
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
        浏览装箱单信息
       &nbsp;&nbsp;&nbsp;
 
	</div> 
    </div>
    </div>
<div>
 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle" nowrap>发票号：</td>
	            <td class="tableContent">${o.invoiceNo}</td>
	            <td class="columnTitle">发票时间：</td>
				<td class="tableContent"><fmt:formatDate value="${o.invoiceDate}" pattern="yyyy-MM-dd"/></td>
	        </tr>
	        <tr>
				<td class="columnTitle">卖家：</td>
	            <td class="tableContent" colspan="3">${o.seller}</td>
			</tr>
	        <tr>
				<td class="columnTitle">买家：</td>
	            <td class="tableContent" colspan="3">${o.buyer}</td>
			</tr>
	        <tr>
				<td class="columnTitle">唛头：</td>
	            <td class="tableContent" colspan="3">${o.marks}</td>
			</tr>
	        <tr>
				<td class="columnTitle">描述：</td>
	            <td class="tableContent" colspan="3">${o.descriptions}</td>
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
	<div id="contractList" style="float:left;margin:8px;">
		<div id="contractList" style="float:left;margin:8px;">
			<c:forEach var="map" items="${dataList}">
				${map.exportNo} &nbsp;&nbsp; <a href="${ctx}/cargo/export/toviewinfo.action?id=${map.exportId}&&packingListId=${o.id}">货物明细</a>  &nbsp;&nbsp;
			</c:forEach>
		</div>
	</div>
</div>
 
 
 
</form>
</body>
</html>


