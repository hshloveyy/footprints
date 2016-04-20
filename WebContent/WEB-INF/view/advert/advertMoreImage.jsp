<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/selectTag" prefix="selectTag"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="script/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="script/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="jeasyui/easyui.css">
<link rel="stylesheet" type="text/css" href="jeasyui/icon.css">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		function uploadImg(num) {
			$('#ff_advertMoreImage' + num + ' input:file').next().remove();
			$('#ff_advertMoreImage' + num)
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
										$('#img' + num).attr(
												'src',
												'fileService/Download?fileId='
														+ data.data + '');
										$('#ff_advertMoreImage' + num).next('input').val(data.data);
									} else {
										$(
												'#ff_advertMoreImage' + num
														+ ' input:file').after(
												'<input type="button" value="重新上传" onclick="uploadImg('
														+ num + ')">');
										$.messager.alert('提示', '上传失败', 'error');
									}
								}
							});
		}
		
		function deleteImage(id){
			$('#' + id).removeAttr("src");
			$('#' + id).parent().parent().next().val('');
		}
	</script>
	<table>
		<tr>
			<td>
				<div>
					<form id="ff_advertMoreImage1" action="" method="post"
						enctype="multipart/form-data">
						<div>
							<img width="120" height="160" border="1" src="" id="img1" />
							<input type="button" value="删除" onclick="deleteImage('img1')"/> 
							<input
								type="file" name="file" accept=".jpg,.png,.bmp"
								onchange="uploadImg(1)">
						</div>
					</form>
					<input type="hidden" name="imageId1" />
				</div>
			</td>
			<td>
				<div>
					<form id="ff_advertMoreImage2" action="" method="post"
						enctype="multipart/form-data">
						<div>
							<img width="120" height="160" border="1" src="" id="img2" />
							<input type="button" value="删除" onclick="deleteImage('img2')"/> 
							<input
								type="file" name="file" accept=".jpg,.png,.bmp"
								onchange="uploadImg(2)">
						</div>
					</form>
					<input type="hidden" name="imageId2" />
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div>
					<form id="ff_advertMoreImage3" action="" method="post"
						enctype="multipart/form-data">
						<div>
							<img width="120" height="160" border="1" src="" id="img3" />
							<input type="button" value="删除" onclick="deleteImage('img3')"/> 
							<input
								type="file" name="file" accept=".jpg,.png,.bmp"
								onchange="uploadImg(3)">
						</div>
					</form>
					<input type="hidden" name="imageId3" />
				</div>
			</td>
			<td>
				&nbsp;
			</td>
		</tr>
	</table>

</body>
</html>