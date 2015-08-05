<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/selectTag" prefix="selectTag" %>
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
<form id="ff_subCategoryEdit" action="" method="post">
	<input type="hidden" name="id">
	<table>
		<tr>
			<td>名称</td>
			<td><input type="text" class="input" name="subCategoryName"/></td>
		</tr>
		<tr>
			<td>名称</td>
			<td><selectTag:selectTag type="category" name="categoryId"/></td>
		</tr>
		<tr>
			<td>排序号</td>
			<td><input type="text" class="input" name="sort"/></td>
		</tr>
		<tr>
			<td>是否为全部</td>
			<td>
				<select name="status">
					<option value="0">否</option>
					<option value="1">是</option>
				</select>
			</td>
		</tr>
	</table>
</form>
</body>
</html>