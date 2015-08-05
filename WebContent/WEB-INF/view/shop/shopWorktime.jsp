<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/selectTag" prefix="selectTag" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<c:forEach items="${worktimes }" var="w">
					<c:if test="${w.PWeek == 0 }">
						<c:set var="monday_startTime" value="${w.startTime }"></c:set>
						<c:set var="monday_endTime" value="${w.endTime }"></c:set>
					</c:if>
					<c:if test="${w.PWeek == 1 }">
						<c:set var="tuesday_startTime" value="${w.startTime }"></c:set>
						<c:set var="tuesday_endTime" value="${w.endTime }"></c:set>
					</c:if>
					<c:if test="${w.PWeek == 2 }">
						<c:set var="wednesday_startTime" value="${w.startTime }"></c:set>
						<c:set var="wednesday_endTime" value="${w.endTime }"></c:set>
					</c:if>
					<c:if test="${w.PWeek == 3 }">
						<c:set var="thursday_startTime" value="${w.startTime }"></c:set>
						<c:set var="thursday_endTime" value="${w.endTime }"></c:set>
					</c:if>
					<c:if test="${w.PWeek == 4 }">
						<c:set var="friday_startTime" value="${w.startTime }"></c:set>
						<c:set var="friday_endTime" value="${w.endTime }"></c:set>
					</c:if>
					<c:if test="${w.PWeek == 5 }">
						<c:set var="saturday_startTime" value="${w.startTime }"></c:set>
						<c:set var="saturday_endTime" value="${w.endTime }"></c:set>
					</c:if>
					<c:if test="${w.PWeek == 6 }">
						<c:set var="sunday_startTime" value="${w.startTime }"></c:set>
						<c:set var="sunday_endTime" value="${w.endTime }"></c:set>
					</c:if>
				</c:forEach>
<form id="ff_shopWorktime" action="" method="post">
	<input type="hidden" name="id">
	<table>
		<tr>
			<td>星期一</td>
			<td>
				AM<input name="monday_start" class="easyui-timespinner"  style="width:80px;" 
			 		data-options="showSeconds:false" value="${monday_startTime}"/> 到
			 	PM<input name="monday_end" class="easyui-timespinner"  style="width:80px;"   
       				data-options="showSeconds:false" value="${monday_endTime}"/>
       		</td>
		</tr>
		<tr>
			<td>星期二</td>
			<td>
				AM<input name="tuesday_start" class="easyui-timespinner"  style="width:80px;" 
			 		data-options="showSeconds:false" value="${tuesday_startTime }"/> 到
			 	PM<input name="tuesday_end" class="easyui-timespinner"  style="width:80px;"   
       				data-options="showSeconds:false" value="${tuesday_endTime }"/>
       		</td>
		</tr>
		<tr>
			<td>星期三</td>
			<td>
				AM<input name="wednesday_start" class="easyui-timespinner"  style="width:80px;" 
			 		data-options="showSeconds:false" value="${wednesday_startTime }"/> 到
			 	PM<input name="wedbesday_end" class="easyui-timespinner"  style="width:80px;"   
       				data-options="showSeconds:false" value="${wednesday_endTime }"/>
       		</td>
		</tr>
		<tr>
			<td>星期四</td>
			<td>
				AM<input name="thursday_start" class="easyui-timespinner"  style="width:80px;" 
			 		data-options="showSeconds:false" value="${thursday_startTime }"/> 到
			 	PM<input name="thursday_end" class="easyui-timespinner"  style="width:80px;"   
       				data-options="showSeconds:false" value="${thursday_endTime }"/>
       		</td>
		</tr>
		<tr>
			<td>星期五</td>
			<td>
				AM<input name="friday_start" class="easyui-timespinner"  style="width:80px;" 
			 		data-options="showSeconds:false" value="${friday_startTime }"/> 到
			 	PM<input name="friday_end" class="easyui-timespinner"  style="width:80px;"   
       				data-options="showSeconds:false" value="${friday_endTime }"/>
       		</td>
		</tr>
		<tr>
			<td>星期六</td>
			<td>
				AM<input name="saturday_start" class="easyui-timespinner"  style="width:80px;" 
			 		data-options="showSeconds:false" value="${saturday_startTime }"/> 到
			 	PM<input name="saturday_end" class="easyui-timespinner"  style="width:80px;"   
       				data-options="showSeconds:false" value="${saturday_endTime }"/>
       		</td>
		</tr>
		<tr>
			<td>星期日</td>
			<td>
				AM<input name="sunday_start" class="easyui-timespinner"  style="width:80px;" 
			 		data-options="showSeconds:false" value="${sunday_startTime }"/> 到
			 	PM<input name="sunday_end" class="easyui-timespinner"  style="width:80px;"   
       				data-options="showSeconds:false" value="${sunday_endTime }"/>
       		</td>
		</tr>
	</table>
</form>
</body>
</html>