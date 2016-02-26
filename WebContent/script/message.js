//加载黄页分类列表
	function loadMessageDg(){
		$('#message_dg').datagrid({    
		    url:'message/list',   
		    pagination:true,
		    fitColumns:true,
		    singleSelect:true,
		    striped:true,
		    columns:[[    
		        {field:'id',title:'ID',width:10,align:'center'},    
		        {field:'userName',title:'用户名',width:30,align:'center'},    
		        {field:'email',title:'邮箱',width:50,align:'center'},    
		        {field:'theme',title:'主题',width:50,align:'center'},    
		        {field:'content',title:'内容',width:100,align:'center',formatter:function(value){
		        	return '<a href="javascript:loadDetail(\'' + value + '\');">' + value + '</a>';
		        }}
		    ]],
		    toolbar: [{
		    	text:'删除',
				iconCls: 'icon-remove',
				handler: function(){
					var row = $('#message_dg').datagrid('getSelected');
					if(row == null){
						$.messager.alert('提示','请选择一条记录进行操作','info');
						return;
					}
					$.messager.confirm('确认对话框', '确认删除？', function(r){
						if (r){
						    $.get('message/delete','id=' + row.id,function(data){
						    	$.messager.show({
						    		title:'我的消息',
						    		msg:data.result?'删除成功':'删除失败',
						    		timeout:5000,
						    		showType:'slide'
						    	});
						    	$('#message_dg').datagrid('reload');
						    },'json');
						}
					});
				}
			}],
			loadMsg:'正在加载数据...'
		}); 
	}
	
	function loadDetail(value){
		$('#dd_messageDetail').html(value);
		console.log($('#dd_messageDetail').html());
		$('#dd_messageDetail').dialog({    
		    title: '消息详情',    
		    width: 400,    
		    height: 200,    
		    closed: false,    
		    cache: false,    
		    content: value,    
		    modal: true,
		    buttons:[{
				text:'关闭',
				handler:function(){
					$('#dd_messageDetail').dialog('close');
				}
			}]   
		});    
	}