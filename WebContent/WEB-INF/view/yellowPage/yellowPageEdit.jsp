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
<form id="ff_pageEdit" action="" method="post">
	<input type="hidden" name="id">
	<table>
		<tr>
			<td>店铺名称</td>
			<td><input type="text" class="input" name="name"/></td>
			<td>地址</td>
			<td><input type="text" class="input" name="address"/></td>
		</tr>
		<tr>
			<td>电话	</td>
			<td><input type="text" class="input" name="mobile"/></td>
			<td>语言</td>
			<td><input type="text" class="input" name="lang"/></td>
		</tr>
		<tr>
			<td>经度</td>
			<td><input type="text" class="input" name="longitude"/></td>
			<td>纬度</td>
			<td><input type="text" class="input" name="latitude"/></td>
		</tr>
		<tr>
			<td>省份</td>
			<td>
				<selectTag:selectTag id="pageprovince" name="province" type="pageprovince" onchange="loadPageCity()"/>
			</td>
			<td>城市</td>
			<td>
				<select name="city" id="page_add_city"><option value="">请选择</option></select>
			</td>
		</tr>
		<tr>
			<td>分类</td>
			<td><selectTag:selectTag name="kindId" type="kind" id="kind" onchange="loadSubKind()"/></td>
			<td>子分类</td>
			<td><select name="subKindId" id="page_add_subkind"><option value="">请选择</option></select></td>
		</tr>
		<tr valign="top">
			<td>标签</td>
			<td><textarea rows="7" cols="20" name="mark"></textarea></td>
			<td>详细介绍</td>
			<td><textarea rows="7" cols="20" name="moreMsg"></textarea></td>
		</tr>
		<tr>
			<td>排序号</td>
			<td><input type="text" class="input" name="sort"/></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
	</table>
</form>
<input type="hidden" id="pageEdit_city">
<input type="hidden" id="pageEdit_subkind">
</body>
</html>