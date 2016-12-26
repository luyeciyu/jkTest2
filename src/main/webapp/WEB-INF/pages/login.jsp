<%@ page language="java"  pageEncoding="UTF-8"%>

<%-- <jsp:include page="include.jsp "></jsp:include> --%>
<%@include file="include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" rev="stylesheet" type="text/css" href="${ctx}/skin/default/css/login.css" media="all" />
<title>login</title>


</head>
<body>
<form action="" method="post">
	<div id="warpbox">
	<div class="main">
		 <div class="zck">
		  <div class="zc">
				<div class="zc_line">用户名：
					<input type="text" value="${requestScope.user.name}" name="name" id="name"
				 		onFocus="this.select();"
				 		autocomplete="off" title="请您输入用户名"/>
					 	<div id="ts" style="z-index:1;"></div>
				</div>
			    <div class="zc_line">密　码：
					<input type="password" value="${requestScope.user.password}" name="password" id="password"
					 onfocus="$('#ts').css('display','none');this.select();"
					 onKeyDown="javascript:if(event.keyCode==13){ formSubmit('${ctx}/user/login.action','_self'); }"
					 title="请您输入密码"/>
				</div>
		  </div>
			<div class="dl">
				<input  class="dl_img" value="" type="button" onclick="formSubmit('${ctx}/user/login.action','_self');"
				  onmouseover="this.style.background='url(${ctx}/skin/default/images/login/dl_h.jpg) no-repeat'" 
				  onmouseout="this.style.background='url(${ctx}/skin/default/images/login/dl_a.jpg) no-repeat'"
				/>
				<input class="reset_img" value="" type="button"   
				  onmouseover="this.style.background='url(${ctx}/skin/default/images/login/reset_h.jpg) no-repeat'" 
				  onmouseout="this.style.background='url(${ctx}/skin/default/images/login/reset_a.jpg) no-repeat'"
				/>
			</div>
		</div>
		<div class="bqxx" style="text-align:right;margin-top:0px;padding-right: 20px;">
		<a href="#">系统帮助</a> | <a href="#" onclick="bookmarkit();">加入收藏</a>
	    </div>

	  	<div class="mirro"></div>
			<logic:notEmpty name="loginFailed">
				<c:if test="${loginFailed==1}">
					<c:set var="errorInfo" value="用户名或密码错误, 请重新输入!"/>
				</c:if>
				<c:if test="${loginFailed==2}">
					<c:set var="errorInfo" value="用户名不存在, 请重新输入!"/>
				</c:if>
				<div class="erro" id="erro">
					<div class="erro_intro">
					${errorInfo}
					</div>
				</div>
			</logic:notEmpty>
		</div>
	</div>
</form>


		<!-- <form class="form-horizontal" role="form">
			<div class="form-group" >
				<label class="control-label col-md-2">用户名：</label>
				<div class="col-md-3">
					<input class="form-control" type="text" name="" class="form-control" title="请输入用户名" >
				</div>
			</div>
			<div class="form-group">
				<label class="control-label">密码：</label>
				<div class="col-md-3">
					<input class="form-control" type="password" name="" class="form-control" title="请输入密码">
				</div>
			</div>
		</form>  -->


		<!--  <form class="form-horizontal" role="form" ng-controller="UserInfoCtrl">
                        <div class="form-group">
                            <label class="col-md-2 control-label">邮箱：</label>
                            <div class="col-md-6">
                                <input type="email" class="form-control" placeholder="邮箱" ng-model="userInfo.email">
                            </div>
                        </div>     
                        <div class="form-group">
                            <label class="col-md-2 control-label">密码：</label>
                            <div class="col-md-6">
                                <input type="password" class="form-control" placeholder="密码" ng-model="userInfo.password">
                            </div>
                        </div>  
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-6">
                                <input type="checkbox" ng-model="userInfo.autoLogin">  自动登录sd
                            </div>
                        </div> 
                        <div class="form-group">
                            <div class="col-md-offset-2">
                                <button class="btn btn-default" ng-click="getFormData()">获取</button>
                                <button class="btn btn-default" ng-click="setData()">设置</button>
                                <button class="btn btn-default" ng-click="resetData()">重置</button>
                            </div>
                        </div>                
                    </form> -->

</body>
</html>