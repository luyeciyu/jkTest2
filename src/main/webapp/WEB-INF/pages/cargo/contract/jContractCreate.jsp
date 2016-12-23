<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript" src="../../js/datepicker/WdatePicker.js"></script>
</head>
<body>
<form method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('insert.action','_self');">确定</a></li>
<li id="back"><a href="list.action">返回</a></li>
</ul>
    </div>
</div>
</div>
</div>
     
<div class="textbox" id="centerTextbox">
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
		新增购销合同信息
    </div> 
    </div>
    </div>
<div>
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle_mustbe">客户名称：</td>
	            <td class="tableContent"><input type="text" name="customName"/></td>
	            <td class="columnTitle_mustbe">收购方：</td>
	            <td class="tableContent"><input type="text" name="offeror" value="杰信商贸有限公司"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">合同号：</td>
	            <td class="tableContent"><input type="text" name="contractNo" dataType="非空字符串" dispName="合同号" maxLength="30" /></td>
	            <td class="columnTitle_mustbe">打印版式：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="printStyle" value="2" class="input" checked>两款
	            	<input type="radio" name="printStyle" value="1" class="input">一款
	            </td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">签单日期：</td>
	            <td class="tableContent">
					<input type="text" style="width:90px;" name="signingDate"
	             			onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
				</td>
	            <td class="columnTitle_mustbe">重要程度：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="importNum" value="3" class="input" checked>★★★
	            	<input type="radio" name="importNum" value="2" class="input">★★
	            	<input type="radio" name="importNum" value="1" class="input">★
	            </td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">交货期限：</td>
	            <td class="tableContent">
					<input type="text" style="width:90px;" name="deliveryPeriod"
	             			onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
				</td>
	            <td class="columnTitle_mustbe">船期：</td>
	            <td class="tableContent">
					<input type="text" style="width:90px;" name="shipTime"
	             			onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
				</td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">贸易条款：</td>
	            <td class="tableContent"><input type="text" name="tradeTerms"/></td>
	            <td class="columnTitle_mustbe">验货员：</td>
	            <td class="tableContent"><input type="text" name="inspector"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">制单人：</td>
	            <td class="tableContent"><input type="text" name="inputBy"/></td>
	            <td class="columnTitle_mustbe">审单人：</td>
	            <td class="tableContent"><input type="text" name="checkBy"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">要求：</td>
	            <td class="tableContent"><textarea name="crequest" style="height:120px;">
	              ★   产品与封样无明显差异，唛头、标签及包装质量务必符合公司要求。 
		 ★★  产品生产前期、中期、后期抽验率不得少于10%，质量和封样一致， 
		       并将验货照片传回公司。 
		★★★ 重点客人的质量标准检验，产品抽验率不得少于50%，务必做到入箱前检查。 
		 交期：deliveryPeriod/工厂。 
		       没有经过我司同意无故延期交货造成严重后果的，按照客人的相关要求处理。 
		 开票：出货后请将增值税发票、验货报告、合同复印件及出库单一并寄至我司，以便我司安排付款。
	            </textarea></td>
	            <td class="columnTitle_mustbe">说明：</td>
	            <td class="tableContent"><textarea name="remark" style="height:120px;"></textarea></td>
	        </tr>
		</table>
	</div>
</div>
 
 
</form>
</body>
</html>

