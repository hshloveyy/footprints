//加载黄页子分类列表
	function loadSubKindDg(){
		$('#subkind_dg').datagrid({    
		    url:'subkind/list',   
		    pagination:true,
		    fitColumns:true,
		    singleSelect:true,
		    striped:true,
		    columns:[[    
		        {field:'id',title:'ID',width:100,align:'center'},    
		        {field:'subKindName',title:'名称',width:100,align:'center'},    
		        {field:'parentId',title:'黄页分类',width:100,align:'center'},    
		        {field:'sort',title:'排序号',width:100,align:'center'}
		    ]],
		    toolbar: [{
		    	text:'添加',
				iconCls: 'icon-add',
				handler: function(){
					$('#dd_addSubKind').dialog({    
					    title: '添加黄页子分类',    
					    width: 400,    
					    height: 200,    
					    closed: false,    
					    cache: false,    
					    href: 'subkind/toAdd',    
					    modal: true,
					    buttons:[{
							text:'保存',
							handler:function(){
								$('#ff_subkindAdd').form('submit',{    
								    url:'subkind/save',    
								    onSubmit: function(){    
								        // do some check    
								        // return false to prevent submit;    
								    },    
								    success:function(data){
								    	$('#dd_addSubKind').dialog('close');
								    	data = eval('(' + data + ')');
								    	$.messager.show({
								    		title:'我的消息',
								    		msg:data.result?'添加成功':'添加失败',
								    		timeout:5000,
								    		showType:'slide'
								    	});
								    	$('#subkind_dg').datagrid('reload');
								    }    
								});    

							}
						},{
							text:'关闭',
							handler:function(){
								$('#dd_addSubKind').dialog('close');
							}
						}]   
					});    
				}
			},'-',{
		    	text:'编辑',
				iconCls: 'icon-edit',
				handler: function(){
					var row = $('#subkind_dg').datagrid('getSelected');
					if(row == null){
						$.messager.alert('提示','请选择一条记录进行操作','info');
						return;
					}
					$('#dd_addSubKind').dialog({    
					    title: '编辑黄页子分类',    
					    width: 400,    
					    height: 200,    
					    closed: false,    
					    cache: false,    
					    href: 'subkind/toForm',    
					    modal: true,
					    onLoad:function(){
					    	$('#ff_subkindEdit').form('load','subkind/toEdit?id=' + row.id);
					    },
					    buttons:[{
							text:'保存',
							handler:function(){
								$('#ff_subkindEdit').form('submit',{    
								    url:'subkind/update',    
								    onSubmit: function(){    
								        // do some check    
								        // return false to prevent submit;    
								    },    
								    success:function(data){
								    	data = eval('(' + data + ')');
								    	$('#dd_addSubKind').dialog('close');
								    	$.messager.show({
								    		title:'我的消息',
								    		msg:data.result?'修改成功':'修改失败',
								    		timeout:5000,
								    		showType:'slide'
								    	});
								    	$('#subkind_dg').datagrid('reload');
								    }    
								});    

							}
						},{
							text:'关闭',
							handler:function(){
								$('#dd_addSubKind').dialog('close');
							}
						}]   
					}); 
				}
			},'-',{
		    	text:'删除',
				iconCls: 'icon-remove',
				handler: function(){
					var row = $('#subkind_dg').datagrid('getSelected');
					if(row == null){
						$.messager.alert('提示','请选择一条记录进行操作','info');
						return;
					}
					$.messager.confirm('确认对话框', '确认删除？', function(r){
						if (r){
						    $.get('subkind/delete','id=' + row.id,function(data){
						    	data = eval('(' + data + ')');
						    	$.messager.show({
						    		title:'我的消息',
						    		msg:data.result?'删除成功':'删除失败',
						    		timeout:5000,
						    		showType:'slide'
						    	});
						    	$('#subkind_dg').datagrid('reload');
						    },'json');
						}
					});
				}
			}],
			loadMsg:'正在加载数据...'
		}); 
	}