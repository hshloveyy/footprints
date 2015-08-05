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
<script type="text/javascript" src="script/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="script/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="jeasyui/easyui.css">
<link rel="stylesheet" type="text/css" href="jeasyui/icon.css">
<title>Insert title here</title>
<style type="text/css">
a{
	line-height:20px;
	font-family: Microsoft YaHei;
}
</style>
</head>
<body>
<form id="ff_cityEdit" action="" method="post">
	<input type="hidden" name="id">
	<input type="hidden" name="provinceId">
	<table>
		<tr>
			<td>名称</td>
			<td><input type="text" class="input" name="cityName"/></td>
		</tr>
		<tr>
			<td>英文名称</td>
			<td><input type="text" class="input" name="enName"/></td>
		</tr>
		<tr>
			<td>排序号</td>
			<td><input type="text" class="input" name="sort"/></td>
		</tr>
	</table>
</form>
</body>
</html>