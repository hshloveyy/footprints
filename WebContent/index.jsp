<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="user/login.action" method="post">
	<input type="text" name="username" value="芒小果">
	<input type="text" name="password" value="9059230240">
	<input type="submit">
</form>
<form action="user/verifyPhone.action" method="get">
	<input type="text" name="phoneNumber" value="15084929682">
	<input type="submit">
</form>
</body>
</html>