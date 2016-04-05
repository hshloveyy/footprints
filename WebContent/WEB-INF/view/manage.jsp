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
<script type="text/javascript" src="script/shop.js"></script>
<script type="text/javascript" src="script/yellowpage.js"></script>
<script type="text/javascript" src="script/province.js"></script>
<script type="text/javascript" src="script/city.js"></script>
<script type="text/javascript" src="script/pageprovince.js"></script>
<script type="text/javascript" src="script/pagecity.js"></script>
<script type="text/javascript" src="script/category.js"></script>
<script type="text/javascript" src="script/comment.js"></script>
<script type="text/javascript" src="script/subcategory.js"></script>
<script type="text/javascript" src="script/kind.js"></script>
<script type="text/javascript" src="script/subkind.js"></script>
<script type="text/javascript" src="script/advert.js"></script>
<script type="text/javascript" src="script/message.js"></script>
<script type="text/javascript" src="script/notice.js"></script>
<link rel="stylesheet" type="text/css" href="jeasyui/easyui.css">
<link rel="stylesheet" type="text/css" href="jeasyui/icon.css">
<title>足记后台管理</title>
<style type="text/css">
</style>
<script type="text/javascript">
	$(function(){
		loadTabs();
		
		$('#selectShop').click(function(){
			$('#shop_dg').datagrid('load',{
				name: $('#name').val(),
				province: $('#province').val(),
				city: $('#city').val(),
				page:1
			});
		});
		
		$('#selectCity').click(function(){
			$('#yellowpage_dg').datagrid('load',{
				name: $('#yname').val(),
				province: $('#yProvince').val(),
				city: $('#yCity').val(),
				page:1
			});
		});
	});
	
	//加载选项卡
	function loadTabs(){
		$('#tt').tabs({    
		    border:false,    
		    onSelect:function(title,index){ 
		        if(index == 0){
		        	loadProvinceDg();
		        }else if(index == 1){
		        	loadCityDg();
		        }else if(index == 2){
		        	loadCategoryDg();
		        }else if(index == 3){
		        	loadSubCategoryDg();
		        }else if(index == 4){
		        	loadShopDg();
		        }else if(index == 5){
		        	loadCommentDg();
		        }else if(index == 6){
		        	loadYellowPageDg();
		        }else if(index == 7){
		        	loadKindDg();
		        }else if(index == 8){
		        	loadSubKindDg();
		        }else if(index == 9){
		        	loadPageProvinceDg();
		        }else if(index == 10){
		        	loadPageCityDg();
		        }else if(index == 11){
		        	loadAdvertDg();
		        }else if(index == 12){
		        	loadMessageDg();
		        }else if(index == 13){
		        	loadAboutForm();
		        }else if(index == 14){
		        	loadProtocolForm();
		        }else if(index == 15){
		        	loadNoticeDg();
		        }else if(index == 16){
		        	loadUrlForm();
		        }
		    },
		    onBeforeClose: function(title,index){
// 		    	var target = this;
// 		    	$.messager.confirm('确认','你确认想要关闭'+title,function(r){
// 		    		if (r){
// 		    			var opts = $(target).tabs('options');
// 		    			var bc = opts.onBeforeClose;
// 		    			opts.onBeforeClose = function(){};  // 允许现在关闭
// 		    			$(target).tabs('close',index);
// 		    			opts.onBeforeClose = bc;  // 还原事件函数
// 		    		}
// 		    	});
		    	return false;	// 阻止关闭
		      }

		});
		

// 		$('#cc').height(document.body.clientHeight);
// 		$('#cc').layout('resize', {
// 			width:'100%',
// 			height:document.body.clientHeight
// 		})
// 		$('#tt').tabs('resize');
	}
	
	function loadUrlForm(){
		$('#url_form').form('load','common/url');
	}
	
	function loadAboutForm(){
		$('#about_form').form('load','common/getabout');
	}
	
	function loadProtocolForm(){
		$('#protocol_form').form('load','common/getprotocol/');
	}
	
	function addAbout(){
		$('#about_form').form('submit',{    
		    url:'common/addabout',    
		    onSubmit: function(){    
		        // do some check    
		        // return false to prevent submit;    
		    },    
		    success:function(data){
		    	data = eval('(' + data + ')');
		    	$.messager.show({
		    		title:'我的消息',
		    		msg:data.result?'修改成功':'修改失败',
		    		timeout:5000,
		    		showType:'slide'
		    	});
		    	loadAboutForm();
		    }    
		}); 
	}
	
	function saveUrl(){
		$('#url_form').form('submit',{    
		    url:'common/saveurl',    
		    onSubmit: function(){    
		        // do some check    
		        // return false to prevent submit;    
		    },    
		    success:function(data){
		    	data = eval('(' + data + ')');
		    	$.messager.show({
		    		title:'我的消息',
		    		msg:data.result?'修改成功':'修改失败',
		    		timeout:5000,
		    		showType:'slide'
		    	});
		    }    
		}); 
	}
	
	function addProtocol(){
		$('#protocol_form').form('submit',{    
		    url:'common/addprotocol',    
		    onSubmit: function(){    
		        // do some check    
		        // return false to prevent submit;    
		    },    
		    success:function(data){
		    	data = eval('(' + data + ')');
		    	$.messager.show({
		    		title:'我的消息',
		    		msg:data.result?'修改成功':'修改失败',
		    		timeout:5000,
		    		showType:'slide'
		    	});
		    	loadProtocolForm();
		    }    
		}); 
	}
	
	function searchComment(value){
		var value = $('#commentText').val();
		if(!value){
			value = '';
		}
		$('#comment_dg').datagrid('reload',{commentContent:value});
	}
	
	function mLoadCitys(id, otherid){
		var provinceId = $('#' + id).val();
		$('#' + otherid + ' option').remove();
		if(provinceId == 0){
			$('#' + otherid).append('<option value="0">请选择</option>');
		}else{
			$.get((id == 'shopProvince' ? 'province' : 'pageprovince') + '/provinceChild', {id:provinceId}, function(data){
				$.each(data.obj.citys, function(index, item){
					$('#' + otherid).append('<option value="' + item.id + '">' + item.cityName + '</option>');
				});
			
			}, 'json');
			
		}
	}
</script>
</head>
<body>
<div id="cc" class="easyui-layout" style="width:auto;height:1000px;">   
    <div data-options="region:'north',title:'Footprints',split:true" style="height:100px;">
    
    </div>   
    <div data-options="region:'center',title:'足记后台管理'" style="padding:5px;background:#eee;width:auto;">
	    <div id="tt" style="width:auto;height:auto;">   
		    <div title="省份管理" style="padding:20px;display: block;">   
		        <table id="province_dg"></table>    
		    </div>   
		    <div title="城市管理" data-options="closable:true" style="overflow:auto;padding:20px;">  
		    	<div>
		    		省份：<selectTag:selectTag id="tableProvince" type="province" onchange="loadCityDg()"/>
		    	</div> 
		       <table id="city_dg"></table> 
		    </div>   
		    <div title="类别管理" data-options="closable:true" style="padding:20px;">   
		        <table id="category_dg"></table>    
		    </div> 
		    <div title="子类别管理" data-options="closable:true" style="padding:20px;">   
		        <table id="subcategory_dg"></table>    
		    </div>   
		    <div title="商铺管理" data-options="closable:true" style="padding:20px;"> 
		    	<div>
		    		商铺名称：<input type="text" autocomplete="on" autofocus="autofocus" placeholder="商铺名称..." id="name"/>
		    		省份：<selectTag:selectTag id="shopProvince" type="province" onchange="mLoadCitys('shopProvince','city')"/>
		    		城市：<select id="city"><option value="">请选择</option></select>
		    		<a class="easyui-linkbutton" href="javascript:void(0);" id="selectShop">查询</a>
		    	</div>  
		        <table id="shop_dg"></table> 
		    </div>   
		    <div title="足迹评论管理" data-options="closable:true" style="padding:20px;">   
		    	<div>
		    		<input id="commentText" type="text" autocomplete="on" autofocus="autofocus" placeholder="足迹内容"/>
		    		<a class="easyui-linkbutton" onclick="searchComment()">查询</a>
		    	</div>  
		        <table id="comment_dg"></table> 
		    </div>   
		    <div title="黄页管理" data-options="closable:true" style="padding:20px;">  
		    	<div>
		    		黄页名称：<input type="text" autocomplete="on" autofocus="autofocus" placeholder="黄页名称..." id="yname"/>
		    		省份：<selectTag:selectTag id="yProvince" type="pageprovince" onchange="mLoadCitys('yProvince','yCity')"/>
		    		城市：<select id="yCity"><option value="">请选择</option></select>
		    		<a class="easyui-linkbutton" href="javascript:void(0);" id="selectCity">查询</a>
		    	</div>   
		        <table id="yellowpage_dg"></table> 
		    </div>
		     <div title="黄页分类管理" data-options="closable:true" style="padding:20px;">   
		        <table id="kind_dg"></table>    
		    </div> 
		    <div title="黄页子分类管理" data-options="closable:true" style="padding:20px;">   
		        <table id="subkind_dg"></table>    
		    </div>   
		    <div title="黄页省份管理" data-options="closable:true" style="padding:20px;">   
		        <table id="pageprovince_dg"></table>    
		    </div>   
		    <div title="黄页城市管理" data-options="closable:true" style="padding:20px;">   
		       <table id="pagecity_dg"></table> 
		    </div>   
		    <div title="广告管理" data-options="closable:true" style="padding:20px;">   
		       <table id="advert_dg"></table> 
		    </div>   
		    <div title="用户留言" data-options="closable:true" style="padding:20px;">   
		       <table id="message_dg"></table> 
		    </div>   
		    <div title="关于" data-options="closable:true" style="padding:20px;">   
		       <form id="about_form" action="" method="post">
			       <input type="hidden" name="id"> 
			       	<textarea style="width:100%;height:300px;" name="aboutContent"></textarea>
		       </form>
		       <a class="easyui-linkbutton" onclick="addAbout()">保存</a>
		    </div>   
		    <div title="协议" data-options="closable:true" style="padding:20px;">   
		       <form id="protocol_form" action="" method="post">
		       	<input type="hidden" name="id"> 
		       	<textarea rows="20" cols="160" name="protocolContent"></textarea>
		       </form>
		       <a class="easyui-linkbutton" onclick="addProtocol()">保存</a>
		    </div>
		    <div title="通知管理" data-options="closable:true" style="padding:20px;">   
		       <table id="notice_dg"></table> 
		    </div> 
		     <div title="url" data-options="closable:true" style="padding:20px;">   
		       <form id="url_form" action="" method="post">
			       <input type="hidden" name="id"> 
			       	<textarea style="width:100%;height:300px;" name="dictvalue"></textarea>
		       </form>
		       <a class="easyui-linkbutton" onclick="saveUrl()">保存</a>
		    </div>  
		</div>  
    </div>
</div> 
<div id="dd_addProvince"></div>
<div id="dd_addCity"></div>
<div id="dd_addPageProvince"></div>
<div id="dd_addPageCity"></div>
<div id="dd_addCategory"></div>
<div id="dd_addSubCategory"></div>
<div id="dd_addShop"></div>
<div id="dd_addPage"></div>
<div id="dd_addSubKind"></div>
<div id="dd_addKind"></div>
<div id="dd_uploadMore"></div>
<div id="dd_worktime"></div>
<div id="dd_addAdvert"></div>
<div id="dd_addNotice"></div>
<div id="dd_uploadMoreAdvert"></div>
<div id="dd_messageDetail"></div>
</body>
</html>