<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="now" class="java.util.Date" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>

    <!-- 调用样式表 -->
    <link rel="stylesheet" rev="stylesheet" type="text/css" href="${ctx}/skin/default/css/title.css" media="all"/>
	
<script language="javascript">

	$(function(){
		$("#rightKey").hide();
		$("#leftKey").hide();
		$("#prompt_div").hide();
	});

	
	

	
	function doLoginDiv(){
		ShowLoginDiv();
	}
	function HideLoginDiv(){
		$("#userInfo").hide();
	}	
	function ShowLoginDiv(){
		$("#userInfo").show();
	}	

			

	function logout(){
		return formSubmit("${ctx}/user/login.action", "_top");
	}
</script>
	
</head>

<body onSelectStart="return true"><!-- 文档主题部分开始 -->
<div class="PositionFrame_black" id="PositionFrame"></div>
	<!-- <div id="userInfo" style="z-index:999;"></div> -->
	<div id="userInfo" style="z-index:999;">
		<img src="${ctx}/skin/default/images/title/avataronline.gif" border="0" style="margin-top:-1px;"/>
		您好：<strong>${loginUser.name}</strong>&nbsp;&nbsp;|
		您所属单位：<strong>供应链金融</strong>&nbsp;&nbsp;
		<img src="${ctx}/skin/default/images/title/close.gif" border="0" onclick="HideLoginDiv()" title="点击关闭" style="cursor: pointer;" />
	</div>

	<a id="memos"  style="cursor:pointer;" href="${ctx}/home/fmain.action" target="_top" title="点击切换到系统首页"><div id="memo" class="memo" title="点击切换到系统首页"></div></a>
	<a id="logins" style="cursor:pointer;" onclick="doLoginDiv();" title="点击显示您的登录信息"><div id="small_login" class="small_login" title="点击显示您的登录信息"></div></a>
	<a id="logout" style="cursor:pointer;" onclick="logout();" target="_top" title="点击退出系统"><div id="small_login_out" class="small_login_out" title="点击退出系统"></div></a>

<div class="headerBg">
	<div class="top_logo">
	    <div class="navMenu"  style="float:left;text-align:left;">
	    		<div class="titleDate" style="float:left;"><fmt:formatDate value="${now}" pattern="yyyy年M月d日 E" /> </div>
	    		<div style="height:29px;">
		    		<span id="leftKey"  onmouseover="periodOffset(this, 'left')"><img src="${ctx}/skin/default/images/title/left_arrow.png"/></span>
			    	<div class="mavMeau_top"></div>
			    	<div id="mask">
			    		<div id="menuContent">
			    			<span id="topmenu" onclick="top.location.href='${ctx}/home/fmain.action'">系统首页</span>
			    			<span id="tm_separator"></span>
			    			<%-- <span id="topmenu" onclick="top.leftframe.location.href='${ctx}/home/cpLeft.action';top.main.location.href='${ctx}/home/cargoMain.action';">货运管理</span> --%>
			    			<span id="topmenu" onclick="top.leftFrame.location.href='${ctx}/home/cargoLeft.action';top.main.location.href='${ctx}/home/cargoMain.action';">货运管理</span>
			    			<span id="tm_separator"></span>
			    			<span id="topmenu"onclick="">基础信息</span>
			    			<span id="tm_separator"></span>
			    			<span id="topmenu" onclick="">系统管理</span>
			    		</div>
					</div>
					<span id="rightKey" onmouseover="periodOffset(this, 'right')"><img src="${ctx}/skin/default/images/title/right_arrow.png"/></span>
				</div>
		</div>
	</div>
</div>




<div id="prompt_div"><img src="${ctx}/skin/default/images/title/prompt.png"/><span style="position:absolute;top:2px;left:35px;z-index: 99999;width:100%;color:#FFFFFF;text-align: left; ">鼠标指向箭头位置<br/>可显示更多菜单项</span></div>

<form name="form1" style="display: none;"></form>	<%//备忘录等使用%>
</body>
</html>