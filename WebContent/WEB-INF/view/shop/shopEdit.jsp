<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/selectTag" prefix="selectTag" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
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
<form id="ff_shopEdit" action="" method="post">
	<input type="hidden" name="id">
	<input type="hidden" name="uploadPics">
	<table>
		<tr>
			<td>店铺名称</td>
			<td><input type="text" class="input" name="name"/></td>
			<td>纬度</td>
			<td><input type="text" class="input" name="latitude"/></td>
			<td>经度</td>
			<td><input type="text" class="input" name="longitude"/></td>
		</tr>
		<tr>
			<td>广告商铺</td>
			<td>
				<select name="isAds">
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
			</td>
			<td>图标</td>
			<td><input type="text" class="input" name=""/></td>
			<td>优惠	</td>
			<td><input type="text" class="input" name="isbbsdiscounts"/></td>
		</tr>
		<tr>
			<td>地址</td>
			<td><input type="text" class="input" name="address"/></td>
			<td>电话	</td>
			<td><input type="text" class="input" name="phoneNumber"/></td>
			<td>网址	</td>
			<td><input type="text" class="input" name="website"/></td>
		</tr>
		<tr>
			<td>营业时间</td>
			<td>
				<select name="hours">
					<option>周一</option>
					<option>周二</option>
					<option>周三</option>
					<option>周四</option>
					<option>周五</option>
					<option>周六</option>
					<option>周日</option>
				</select>
			</td>
			<td>省份</td>
			<td>
				<selectTag:selectTag id="province" name="province" type="province" onchange="loadCity()"/>
			</td>
			<td>城市</td>
			<td>
				<select name="city" id="shop_add_city"><option value="">请选择</option></select>
			</td>
		</tr>
		<tr>
			<td>备注</td>
			<td><input type="text" class="input" name="comments"/></td>
			<td>分类</td>
			<td><selectTag:selectTag name="class_" type="category" id="category" onchange="loadSubCategory()"/></td>
			<td>子分类</td>
			<td><select name="subclass" id="shop_add_subcategory"><option value="">请选择</option></select></td>
		</tr>
		<tr valign="top">
			<td>标签</td>
			<td><input type="text" class="input" name="mark" placeholder="标签之间以逗号隔开"/></td>
			<td>排序号</td>
			<td><input type="text" class="input" name="sort"/></td>
			<td>详情地址</td>
			<td>
				<input type="text" class="input" name="infoUrl"/>
			</td>
		</tr>
		<tr valign="top">
			<td>详细介绍</td>
			<td  colspan="5">
				<textarea rows="10" cols="20" name="description"></textarea>
			</td>
		</tr>
	</table>
</form>
<input type="hidden" id="shopEdit_city">
<input type="hidden" id="shopEdit_subclass">
</body>
</html>