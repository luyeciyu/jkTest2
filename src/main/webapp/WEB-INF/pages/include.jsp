<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<base href="<%=basePath%>">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>

<link rel="stylesheet"  type="text/css" href="skin/default/css/default.css" media="all" />
<link rel="stylesheet"  type="text/css" href="components/bootstrap-3.0.0/css/bootstrap.min.css" media="all" />


