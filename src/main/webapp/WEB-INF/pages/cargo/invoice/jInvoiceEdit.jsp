<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- html标识扩展，定义名字空间 -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>

	<script type="text/javascript" src="${ctx}/js/datepicker/WdatePicker.js"></script>
</head>

<body>
<form method="post">
	<input type="hidden" name="id" value="${id}"/>
	<input type="hidden" name="subid" value="${obj.id}"/>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('${ctx}/cargo/invoice/insert.action','_self');this.blur();">确定</a></li>
<!-- <li id="print"><a href="#" onclick="formSubmit('/invoice/invoiceAction_print','_self');this.blur();">打印</a></li>
 --><li id="back"><a href="${ctx}/cargo/packinglist/list.action">返回</a></li>
</ul>
    </div>
</div>
</div>
</div>
     
<div class="textbox" id="centerTextbox">
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
       	<c:if test="${obj == null}">新增</c:if>
    	<c:if test="${obj.id != null}">修改</c:if>
	        发票
       &nbsp;&nbsp;&nbsp;

	</div> 
    </div>
    </div>
<div>

    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">B/L No.：</td>
	            <td class="tableContent"><input type="text" name="blNo" value="${obj.blNo}" dataType="字符串" dispName="B/L No." maxLength="30"></td>
	        </tr>
	        <tr>
	            <td class="columnTitle" nowrap>贸易条款：</td>
	            <td class="tableContent"><input type="text" name="tradeTerms" value="${obj.tradeTerms}" dataType="字符串" dispName="贸易条款" maxLength="30"></td>
	        </tr>
		</table>
	</div>
</div>


</form>
</body>
</html>
