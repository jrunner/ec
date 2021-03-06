<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%@include file="../common/init.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=contextPath%>/themes/css/right.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/themes/css/blueStyle.css" />


<script type="text/javascript">
	 var grid;		 
	 $(window).resize(function(){
	 	$('#grid').datagrid('resize');
	 });

	 $(document).ready(function(){
		 grid = $("#grid"); 
		 $("#grid").datagrid({
			 onRowContextMenu: onRowContextMenu //右键。[表头(tab)右键onHeaderContextMenu,树形(tree)右键onContextMenu]
		 });
		 
		 
		//设置分页控件  
	    var p = $('#grid').datagrid('getPager'); 
	    $(p).pagination({  
	        pageSize: 10,//每页显示的记录条数，默认为10  
	        pageList: [10,50,100,1000],//可以设置每页记录条数的列表  
	        beforePageText: '第',//页数文本框前显示的汉字  
	        afterPageText: '页    共 {pages} 页',  
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	    });  
	 });
	
	 //右键功能
	function onRowContextMenu(e, rowIndex, rowData){
		e.preventDefault();
		var selected=$("#grid").datagrid('getRows'); //获取所有行集合对象
		var idValue = selected[rowIndex].id;
		$(this).datagrid('selectRecord', idValue);  //通过获取到的id的值做参数选中一行
		$('#mm').menu('show', {
			left:e.pageX,
			top:e.pageY
	    });        
	} 

	//新增
	function add(){
		window.location = '<%=contextPath%>/emp.do?cmd=to_update';
	}
	//查看
	function view(){}
	//编辑
	function edit(){
		var row = grid.datagrid('getSelected');//返回第一个被选中的行或如果没有选中的行则返回null。
		if(row) window.location = '<%=contextPath%>/emp.do?cmd=to_update&id='+row.id;
	}
	//删除
	function del(){};
	//打印
	function print(){}
	//刷新
	function reload(){}	
	function cols_(key,value){
		grid.datagrid(key,value);
	}
	
	/** 
	 *  
	 * @requires jQuery,EasyUI 
	 *  
	 * 为datagrid、treegrid增加表头菜单，用于显示或隐藏列，注意：冻结列不在此菜单中 
	 */  
	var createGridHeaderContextMenu = function(e, field) {  
	    e.preventDefault();  
	    var grid = $(this);/* grid本身 */  
	    var headerContextMenu = this.headerContextMenu;/* grid上的列头菜单对象 */  
	    if (!headerContextMenu) {  
	        var tmenu = $('<div style="width:150px;"></div>').appendTo('body');  
	        var fields = grid.datagrid('getColumnFields');  
	        for (var i = 0; i < fields.length; i++) {  
	            var fildOption = grid.datagrid('getColumnOption', fields[i]);  
	            if (!fildOption.hidden) {  
	                $('<div iconCls="icon-ok" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);  
	            } else {  
	                $('<div iconCls="icon-empty" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);  
	            }  
	        }  
	        headerContextMenu = this.headerContextMenu = tmenu.menu({  
	            onClick : function(item) {  
	                var field = $(item.target).attr('field');  
	                if (item.iconCls == 'icon-ok') {  
	                    grid.datagrid('hideColumn', field);  
	                    $(this).menu('setIcon', {  
	                        target : item.target,  
	                        iconCls : 'icon-empty'  
	                    });  
	                } else {  
	                    grid.datagrid('showColumn', field);  
	                    $(this).menu('setIcon', {  
	                        target : item.target,  
	                        iconCls : 'icon-ok'  
	                    });  
	                }  
	            }  
	        });  
	    }  
	    headerContextMenu.menu('show', {  
	        left : e.pageX,  
	        top : e.pageY  
	    });  
	};  
	$.fn.datagrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;  

	function search(){
		 var name = $("#name").val();
		 var no = $("#no").val();
		 grid.datagrid('load',{name:name,no:no});
	}
	</script>
	
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	</head>  
	<body >
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="15" height="30" class="td_left_top_table"></td>
							<td class="td_middle_table" valign="middle">
								<div class="ico_win">
									CRUD示例
								</div>
							</td>
							<td class="td_middle_table">
								<div class="title_oper">
									<a href="<%=request.getContextPath() %>/eg.do?cmd=toUpdate">新建</a> | <a href="javascript:tsmDel()">删除</a>
								</div>
							</td>
							<td width="14" class="td_right_top_table"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="9" class="td_left_table_content">
								&nbsp;
							</td>
							<td class="td_global">
								<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_content">
									<tr>
										<td height="26" colspan="6" class="table_content_title_td"></td>
									</tr>
									<tr>
										<td class="table_content_td" width="150px">
											工号：
										</td>
										<td class="table_content_td">
											<input class="txt_input" name="no" id="no" maxlength="10" />
										</td>
										<td class="table_content_td" width="150px">
											姓名：
										</td>
										<td class="table_content_td">
											<input class="txt_input" name="resName" id="resName" valueField="resname" textField="resname" url="<%=contextPath%>/ajax.do?cmd=list&kind=emp" maxlength="20" />
										</td>
										<td class="table_content_td" width="150px">
											学历：
										</td>
										<td class="table_content_td">
											<input class="txt_input" name="edu" id="edu" maxlength="40" />
										</td>
									</tr>
									<tr>
										<td class="table_content_td">
											专业：
										</td>
										<td class="table_content_td">
											<input class="txt_input" name="professional" id="professional" maxlength="40" />
										</td>
										<td class="table_content_td">
											毕业院校：
										</td>
										<td class="table_content_td">
											<input class="txt_input" name="graduateSchool" id="graduateSchool" maxlength="40" />
										</td>
										<td class="table_content_td">
											户籍所在地：
										</td>
										<td class="table_content_td">
											<input class="txt_input" name="idAddress" id="idAddress" maxlength="40" />
										</td>
									</tr>
									<tr>
										<td colspan="6" class="table_content_td" height="30">
											<input type="button" class="btn_input" value="查询" onclick="search()" />
											<input type="button" class="btn_input" value="重置" onclick="" />
										</td>
									</tr>
								</table>
							</td>
							<td width="9" class="td_right_table_content">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" height="29">
						<tr>
							<td width="15" class="td_left_buttom_table"></td>
							<td class="td_middle_buttom_table">
								&nbsp;
							</td>
							<td width="14" class="td_right_buttom_table"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div style="height: 5px;"></div>

		<div id="divTest" style="width: 99.5%; margin-left: 4px;">
			<table id="grid" class="easyui-datagrid" title="员工列表" align="center" style="height: 400px;"
				data-options="rownumbers:true,
					singleSelect:true,
					autoRowHeight:false,
					pagination:true,
					pageSize:50,
					loadMsg:'数据正在努力加载中...',
					toolbar: '#tb',
					url:'<%=contextPath %>/eg.do?cmd=list'
					">

				<thead data-options="frozen:true">
					<tr>
						<th data-options="field:'id',width:60">
							ID
						</th>
						<th data-options="field:'no',width:100">
							工号
						</th>
						<th data-options="field:'resName',width:80,align:'right'">
							姓名
						</th>
					</tr>
				</thead>
				<thead>
					<tr>
						<!-- 基本信息 -->
						<th data-options="field:'sex',width:80,align:'right'">
							性别
						</th>
						<th data-options="field:'idno',width:80,align:'right'">
							身份证号码
						</th>
						<th data-options="field:'datebirth',width:80,align:'right'">
							出生日期
						</th>
						<th data-options="field:'age',width:80,align:'right'">
							年龄
						</th>
						<th data-options="field:'nation',width:80,align:'right'">
							民族
						</th>
						<th data-options="field:'political',width:80,align:'right'">
							政治面貌
						</th>
						<th data-options="field:'curtAddress',width:80,align:'right'">
							现住址
						</th>
						<th data-options="field:'curPhone',width:80,align:'right'">
							现联系方式
						</th>
						<th data-options="field:'workPlace1',width:80,align:'right'">
							工作地点
						</th>
						<th data-options="field:'state',width:80,align:'right'">
							状态
						</th>
					</tr>
				</thead>
			</table>
		</div>

		<div id="mm" class="easyui-menu" style="width: 120px;">
			<div onClick="view()" data-options="iconCls:'icon-search'">查看</div>
			<div onClick="add()" data-options="iconCls:'icon-add'">新增</div>
			<div onClick="edit()" data-options="iconCls:'icon-edit'">编辑</div>
			<div onClick="del()" data-options="iconCls:'icon-remove'">删除</div>
			<div class="menu-sep"></div>
			<div onClick="print()" data-options="iconCls:'icon-print'">打印</div>
			<div onClick="reload()" data-options="iconCls:'icon-reload'">刷新</div>
		</div>
		<div id="tb" style="height: auto">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="cols_('hideColumn','idno');">隐藏列</a>
		</div>
		<div style="height: 5px;"></div> 
	</body>
</html>

