<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/ztreejs/jquery.ztree.all-3.5.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/ztreecss/zTreeStyle/zTreeStyle.css" >
</head>
<body bgcolor="grey">
	current_user<h3>${current_user}</h3>
	session_user<h3>${session_user}</h3>
	user.loginuid<h3>${user.loginuid}</h3>
	<form action="/HNTIC/services/loginservice/init" method="post">
		登录初始化<input type="submit">
	</form>
	<br/>
<!-- 	<form action="http://10.159.30.135:8080/service/fileService/CreateTask" method="post"> -->
<!-- 		id:<input type="text" name="id" value="4A24580D5A7044A799A3FB1EC63E35EC" style="width: 300px;"/><br> -->
<!-- 		filename:<input type="text" name="filename" value="4A24580D5A7044A799A3FB1EC63E35EC_filename.jsp" style="width: 300px;"/><br> -->
<!-- 		size:<input type="text" name="size" value="1024" style="width: 300px;"/><br> -->
<!-- 		<input type="submit"> -->
<!-- 	</form> -->
	<form action="http://104.238.124.54:8080/service/fileService/Upload" method="post" enctype="multipart/form-data">
		id:<input type="text" name="id" value="3A24580D5A7044A799A3FB1EC63E35EC" style="width: 300px;"/><br>
		file:<input type="file" name="file" style="width: 300px;"/><br>
<!-- 		savetype:<input type="text" name="savetype" value="04" style="width: 300px;"/> -->
		<input type="submit" value="上传">
	</form>
	
	<form action="http://localhost:8081/footprints/fileService/Upload" method="post" enctype="multipart/form-data">
		file:<input type="file" name="file" style="width: 300px;"/><br>
		<input type="submit" value="上传">
	</form>
<!-- 	<br/> http://10.159.30.135:9080/HNTIC/-->
	<form action="/HNTIC/services/loginservice/login" method="post">
		userId:<input type="text" name="id" value="p_chencc" style="width: 300px;"/><br>
		userId:<input type="text" name="password" value="a888888" style="width: 300px;"/><br>
		userId:<input type="text" name="hostid" value="10.159.30.137" style="width: 300px;"/><br>
		userId:<input type="text" name="trust" value="false" style="width: 300px;"/><br>
		登录:<input type="submit">
	</form>
	
	<form action="http://10.159.30.110:8080/service/fileService/Download" method="post">
		fileId:<input type="text" name="fileId" value="8969288F4245120E7C3870287CCE0FF3" style="width: 300px;"/><br>
		下载<input type="submit">
	</form>
	<form action="http://tam.hngytobacco.com/WeatherServer/services/weather" method="post">
		<input type="text" name="city" value="changsha">
		天气预报<input type="submit">
	</form>
	<form action="/IECM/base/treenode/unittree" method="post">
		<input type="text" name="isFilterStatus" value="true">
		<input type="text" name="isPrivilegeFilter" value="true">
		<input type="text" name="isLoadUser" value="false">
		<input type="text" name="isUnitExpand" value="true">
<!-- 		<input type="text" name="id" value="unitCode"> -->
<!-- 		<input type="text" name="name" value="cn"> -->
		树<input type="submit">
	</form>
	<form action="/HNTIC/services/positionService/p_lup0223" method="post">
		<input type="submit">
	</form>
	<form action="http://www.isbbs.ca/do/login.php" method="post">
		<input type="text" name="username" value="芒小果">
		<input type="text" name="password" value="9059230240">
		<input type="text" name="Submit" value="登录">
		<input type="text" name="step" value="2">
		<input type="text" name="cookietime" value="86400">
		<input type="submit">
	</form>
</body>
</html>