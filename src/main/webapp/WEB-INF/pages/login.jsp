<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<%@include file="include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" rev="stylesheet" type="text/css" href="skin/default/css/login.css" media="all" />
<title>login</title>

</head>
<body>
<div class="contrainer login">
	<div class="row" style="">
		<form class="form-horizontal col-sm-offset-2" role="form" method="post" action="user/login.action">
			<div class="form-group">
				<label class="col-sm-2 control-label">用户名</label>
				<div class="col-sm-5">
					<input type="text" name="name" value="${requestScope.user.name}" class="form-control" placeholder="输入用户名">
				</div>
				<div class="col-sm-5 text-left errorInfo">
					<sf:errors path="user.name"></sf:errors>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">密码</label>
				<div class="col-sm-5">
					<input type="password" name="password" value="${requestScope.user.password}" class="form-control" placeholder="密码">
				</div>
				<div class="col-sm-5 text-left errorInfo" >
					<sf:errors path="user.password"></sf:errors>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-8 col-sm-offset-2">
					<div class="col-sm-3">
						<button class="btn btn-primary" type="submit">提交</button>
					</div>

				</div>
			</div>
		</form>

	</div>
</div>

</body>
</html>