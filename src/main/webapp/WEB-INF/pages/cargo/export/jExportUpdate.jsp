<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 告诉浏览器本网页符合XHTML1.0过渡型DOCTYPE -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 
 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
 	<script type="text/javascript" src="../../js/datepicker/WdatePicker.js"></script>
 	<script type="text/javascript" src="../../js/tabledo.js"></script>
    <!-- 调用样式表 -->
	<!-- <link rel="stylesheet" rev="stylesheet" type="text/css" href="../../skin/default/css/default.css" media="all" />
    <script type="text/javascript" src="../../js/common.js"></script>
    <script type="text/javascript" src="../../js/datepicker/WdatePicker.js"></script>
    <script type="text/javascript" src="/components/jquery-ui/jquery-1.2.6.js"></script>
    <script type="text/javascript" src="../../js/tabledo.js"></script> -->
	
<script language="JavaScript">
    $().ready(function(){
		${mrecordData}
    });
    

	/* 实现表格序号列自动调整 created by tony 20081219 */
	function sortnoTR(){
		sortno('resultTable', 2, 1);
	}
		
	function addTRRecord(objId, id, productNo, cnumber, grossWeight, netWeight) {
		
		var _tmpSelect = "";
		var tableObj = document.getElementById(objId);
		var rowLength = tableObj.rows.length;
		
		oTR = tableObj.insertRow();
		
		oTD = oTR.insertCell(0);
		oTD.style.whiteSpace="nowrap";
		oTD.ondragover = function(){this.className="drag_over" };	//动态加事件, 改变样式类
		oTD.ondragleave = function(){this.className="drag_leave" };
		oTD.onmousedown = function(){ clearTRstyle("result"); this.parentNode.style.background = '#0099cc';};	
		//this.style.background="#0099cc url(../images/arroww.gif) 4px 9px no-repeat";
		oTD.innerHTML = "&nbsp;&nbsp;";	
		oTD = oTR.insertCell(1);
		oTD.innerHTML = "<input class=\"input\" type=\"checkbox\" name=\"del\" value=\""+id+"\"><input type=\"hidden\" name=\"mr_id\" value=\""+id+"\"><input class=\"input\" type=\"text\" id=\"mr_changed\" name=\"mr_changed\">";
		oTD = oTR.insertCell(2);
		oTD.innerHTML = "<input class=\"input\" type=\"text\" name=\"orderNo\" readonly size=\"3\" value=\"\">";
		oTD = oTR.insertCell(3);
		oTD.innerHTML = "<input type=\"text\"  name=\"ep_productNo\" maxLength=\"50\" value=\""+productNo+"\" onBlur=\"setTRUpdateFlag(this);\">";
		oTD = oTR.insertCell(4);
		oTD.innerHTML = "<input type=\"text\"  name=\"ep_cnumber\" maxLength=\"10\" value=\""+cnumber+"\" onBlur=\"setTRUpdateFlag(this);\">";
		oTD = oTR.insertCell(5);
		oTD.innerHTML = "<input type=\"text\"  name=\"ep_grossWeight\" maxLength=\"10\" value=\""+grossWeight+"\" onBlur=\"setTRUpdateFlag(this);\">";
		oTD = oTR.insertCell(6);
		oTD.innerHTML = "<input type=\"text\"  name=\"ep_netWeight\" maxLength=\"10\" value=\""+netWeight+"\" onBlur=\"setTRUpdateFlag(this);\">";

		dragtableinit();	//拖动表格行
		sortnoTR();			//排序号
		
	}    
    
</script> 
</head>
 
<body>
<form method="post">
	<input type="hidden" name="id" value="${obj.id}"/>
 
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('${ctx}/cargo/export/update.action','_self');">确定</a></li>
<li id="back"><a href="${ctx}/cargo/export/list.action">返回</a></li>
</ul>
    </div>
</div>
</div>
</div>
     
<div class="textbox" id="centerTextbox">
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
        修改报运信息
    </div> 
        	
    </div>
    </div>
<div>
 
 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">客户号：</td>
	            <td class="tableContent">
					${obj.customerContract}
				</td>
	            <td class="columnTitle">制单日期：</td>
	            <td class="tableContent">
					<input type="text" style="width: 90px;" name="inputDate"
						value="<fmt:formatDate value="${obj.inputDate}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});" />
				</td>
	        </tr>
	        <tr>
	            <td class="columnTitle">L/C No.：</td>
	            <td class="tableContentAuto">  
	            	<input type="radio" name="lcno" value="L/C" class="input" <c:if test="${obj.lcno=='L/C'}">checked</c:if> >L/C
					<input type="radio" name="lcno" value="T/T" class="input" <c:if test="${obj.lcno=='T/T'}">checked</c:if> >T/T
	            </td>	            
	            <td class="columnTitle">装运港：</td>
	            <td class="tableContent"><input type="text" name="shipmentPort" value="${obj.shipmentPort}" dataType="字符串" dispName="装运港" maxLength="100"></td>
	        </tr>
	        <tr>
	            <td class="columnTitle">收货人及地址：</td>
	            <td class="tableContentAuto" colspan="3"><input type="text" name="consignee" value="${obj.consignee}" dataType="非空字符串" dispName="收货人及地址" maxLength="100" style="width:100%"></td>
	        </tr>
	        <tr>
	            <td class="columnTitle">运输方式：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="transportMode" value="SEA" <c:if test="${obj.transportMode=='SEA'}">checked</c:if> class="input">海运
					<input type="radio" name="transportMode" value="AIR" <c:if test="${obj.transportMode=='AIR'}">checked</c:if> class="input">空运
	            </td>
	            <td class="columnTitle">价格条件：</td>
	            <td class="tableContent"><input type="text" name="priceCondition" value="${obj.priceCondition}" dataType="字符串" dispName="价格条件" maxLength="10"></td>
	        </tr>
	        <tr>
				<td class="columnTitle">唛头：</td>
	            <td colspan="3">
	            	<textarea id="textarea_marks" onkeyup="getMaxlength('textarea_marks');textareasize(this);" onmousemove ="getMaxlength('textarea_marks');" onmouseout ="getMaxlength('textarea_marks');" class="textarea" name="marks" rows="5" dataType="字符串" dispName="唛头" maxLength="1000">${obj.marks}</textarea>
	            	<div class="textarea_count">
	            	<span>字符：<a id="num_textarea_marks"><font color=#009900><script>getNownum('textarea_marks')</script> / <script>getMaxnum('textarea_marks')</script></font></a></span> | 
	            	<a style="cursor:pointer;" onclick="clearcontent('textarea_marks')">清空内容</a>
	            	</div>
	            </td>
			</tr>
	        <tr>
				<td class="columnTitle">备注：</td>
	            <td colspan="3">
	            	<textarea id="textarea" onkeyup="getMaxlength('textarea');textareasize(this);" onmousemove ="getMaxlength('textarea');" onmouseout ="getMaxlength('textarea');" class="textarea" name="remark" rows="5" dataType="字符串" dispName="备注" maxLength="100">${obj.remark}</textarea>
	            	<div class="textarea_count">
	            	<span>字符：<a id="num_textarea"><font color=#009900><script>getNownum('textarea')</script> / <script>getMaxnum('textarea')</script></font></a></span> | 
	            	<a style="cursor:pointer;" onclick="clearcontent('textarea')">清空内容</a>
	            	</div>
	            </td>
			</tr>
		</table>
	</div>
</div>
 
 
						<div class="eXtremeTable">
							<table class="commonTable_main" cellSpacing="1" id="resultTable" width="98%">
							<thead>
								<tr class="rowTitle" align="middle">
									<!-- <td width="25" title="可以拖动下面行首,实现记录的位置移动.">
										<img src="../images/drag.gif">
									</td> -->
									<td class="tableHeader">
										<input class="input" type="checkbox" name="ck_del" onclick="checkGroupBox(this);" />
									</td>
									<td class="tableHeader">序号</td>
									<td class="tableHeader">货号</td>
									<td class="tableHeader">数量</td>
									<td class="tableHeader">毛重</td>
									<td class="tableHeader">净重</td>
								</tr>
								<thead>
								<tbody class="tableBody" >
								</tbody>
							</table>
						</div>


						</div>
						<div class="textbox-bottom">
							<div class="textbox-inner-bottom">
								<div class="textbox-go-top">
								</div>
							</div>
						</div>
					</div>

 
</form>
</body>
</html>

