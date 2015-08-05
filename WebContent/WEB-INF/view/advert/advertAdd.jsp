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
<script type="text/javascript">
function uploadImg(num) {
	$('#imgForm')
			.form(
					'submit',
					{
						url : 'fileService/Upload',
						onSubmit : function() {
							// do some check    
							// return false to prevent submit;    
						},
						success : function(data) {
							data = eval('(' + data + ')');
							if (data.code == 1) {
								$('#imageId').val(data.data);
								$('#img1').attr(
										'src',
										'fileService/Download?fileId='
												+ data.data + '');
							}
						}
					});
}
</script>
<form id="ff_advertAdd" action="" method="post">
<!-- 	<input type="hidden" id="imageId" name="imageId">  -->
	<table>
		<tr>
			<td>城市</td>
			<td>
				<selectTag:selectTag name="cityId" type="pagecity"/>
			</td>
		</tr>
		<tr>
			<td>尺寸</td>
			<td>
				<select name="type">
					<option value="1">大尺寸</option>
					<option value="2">小尺寸</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>商铺</td>
			<td><selectTag:selectTag name="pageId" type="page"/></td>
		</tr>
		<tr>
			<td>排序号</td>
			<td><input type="text" class="input" name="sort"/></td>
		</tr>
	</table>
</form>
<!-- <table> -->
<!-- 		<tr> -->
<!-- 			<td>图片</td> -->
<!-- 			<td> -->
<!-- 				<img width="120" height="160" border="1" src="" id="img1" /> -->
<!-- 				<form id="imgForm" action="" method="post" enctype="multipart/form-data"> -->
<!-- 					<input type="file" name="file" onchange="uploadImg(this)"/> -->
<!-- 				</form> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- </table> -->
</body>
</html>