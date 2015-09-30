 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%@include file="../../common/init.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=contextPath%>/themes/css/right.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/themes/css/blueStyle.css" />


<script type="text/javascript">

	 var grid;		 
	 $(window).resize(function(){
		 $('#grid').datagrid('resize');
	 });

	 $(document).ready(function(){
		 grid = $("#grid"); 
		 grid.datagrid({
			 onRowContextMenu: onRowContextMenu //右键。[表头(tab)右键onHeaderContextMenu,树形(tree)右键onContextMenu]
		 });
	 });
	
	 //右键功能
	function onRowContextMenu(e, rowIndex, rowData){
		e.preventDefault();
		var selected=grid.datagrid('getRows'); //获取所有行集合对象
		var idValue = selected[rowIndex].id;
		$(this).datagrid('selectRecord', idValue);  //通过获取到的id的值做参数选中一行
		$('#mm').menu('show', {
			left:e.pageX,
			top:e.pageY
	    });        
	} 

	//查看
	function view(){}
	//新增\编辑
	function toUnitList(op){
		var data = '&code=<%=request.getParameter("code") %>&pcode=<%=request.getParameter("pcode") %>&treeid=<%=request.getParameter("treeid") %>&id=<%=request.getParameter("id")%>';
		if(op==null){
			window.location = '<%=contextPath%>/baseinfo/vipunit.do?cmd=toUnitList'+data;
		    return;
		}
		
		var row = grid.datagrid('getSelected');//返回第一个被选中的行或如果没有选中的行则返回null。
		if(row) window.location = '<%=contextPath%>/baseinfo/vipunit.do?cmd=toUnitList&id='+row.id+data;
	}
	//删除
	function delUnit(){
		var row = grid.datagrid('getSelected');//返回第一个被选中的行或如果没有选中的行则返回null。
		if(row){
			console.info(row)
			var data = {id:row.id};
			JSendAjax('<%=contextPath%>/baseinfo/vipunit.do?cmd=delUnit',data);
		}
	};
	//打印
	function print(){}
	//刷新
	function reload(){}	

	function search1(){
		 var name = $("#name").val();
		 grid.datagrid('load',{name:name});
	}

	//回调函数
    function JCollBack(_data,_textStuats){ 
        var info = $(_data).find('info').text();
        var msg = $(_data).find('msg').text(); 
        if(info == '0'){
            if(msg=='delSub_ok'){
	            alert('删除行业成功');
                var resname = $(_data).find('resname').text(); 
                onRemoveNode('<%=request.getParameter("code") %>','<%=request.getParameter("treeid") %>') ; 
                closeTab();
	            return;  
            }
            if(msg=='delUnit_ok'){
	            alert('删除单位成功');
                var code = $(_data).find('code').text();
                var resname = $(_data).find('resname').text(); 
                onRemoveNode(code,'<%=request.getParameter("treeid") %>') ; 
	            search();
	            return; 
            }
        }
        if(info == '-1'){
            alert('错误信息: '+msg);return;
        }
    }

	//添加或编辑子行业
    function toSubUpdate(id){
		window.location = '<%=request.getContextPath() %>/baseinfo/vipunit.do?cmd=toSubUpdate&code=<%=request.getParameter("code") %>&pcode=<%=request.getParameter("pcode") %>&treeid=<%=request.getParameter("treeid") %>&id=<%=request.getParameter("id")%>';
    }
	//删除子行业
	function delSub(){
		var data = {id:'<%=request.getParameter("id") %>'};
		JSendAjax('<%=contextPath%>/baseinfo/vipunit.do?cmd=delSub',data);
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
								<div class="ico_win">子行业</div>
							</td>
							<td class="td_middle_table">
								<div class="title_oper">
                                	<!--
									<a href="javascript:toSubUpdate()" title="新建子行业">新建</a> |
									<a href="javascript:toSubUpdate('<%=request.getParameter("id") %>')" title="编辑子行业">编辑</a> |
									<a href="javascript:delSub()"  title="删除子行业">删除</a>
                                    -->
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
							<td width="9" class="td_left_table_content">&nbsp;
								
							</td>
							<td class="td_global">
								<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_content">
									<tr>
										<td height="26" colspan="3" class="table_content_title_td"></td>
									</tr>
									<tr>
										<td class="table_content_td" width="150px">
											名称：
										</td>
										<td class="table_content_td" align="left">
											&nbsp;<input class="txt_input" name="resName" id="resName" maxlength="10" />
										</td>
										<td class="table_content_td" height="30">
											<input type="button" class="btn_input" value="查询" onClick="search()" />
											<input type="button" class="btn_input" value="重置" onClick="" />
										</td>
									</tr>
								</table>
							</td>
							<td width="9" class="td_right_table_content">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" height="29">
						<tr>
							<td width="15" class="td_left_buttom_table"></td>
							<td class="td_middle_buttom_table">&nbsp;</td>
							<td width="14" class="td_right_buttom_table"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div style="height: 5px;"></div>
		<div id="divContainer" style="width: 99.5%; margin-left: 4px;">
			<table id="grid" class="easyui-datagrid" title="单位列表" align="center" style="height: 400px;"
				data-options="rownumbers:true,
					singleSelect:true,
					autoRowHeight:false,
					loadMsg:'数据正在努力加载中...',
					toolbar: '#tb',
					url:'<%=contextPath %>/baseinfo/vipunit.do?cmd=sublist&pcode<%=request.getParameter("code") %>'
					">
				<thead>
					<tr>
						<th data-options="field:'alias',width:100">单位名称</th>
						<th data-options="field:'x',width:100">城市</th>
						<th data-options="field:'x',width:100">邮编</th>
						<th data-options="field:'x',width:100">传真</th>
						<th data-options="field:'x',width:100">通讯地址</th>
						<th data-options="field:'x',width:100">单位类型</th> 
						<th data-options="field:'x',width:80">联系人</th>
						<th data-options="field:'ip',width:80">IP地址</th>
						<th data-options="field:'domain',width:80">域名</th>
						<th data-options="field:'createTime',width:140">创建时间</th>
					</tr>
				</thead>
			</table>
		</div>

		<div id="mm" class="easyui-menu" style="width: 120px;">
			<div onClick="view()" data-options="iconCls:'icon-search'">查看</div>
			<div onClick="add()" data-options="iconCls:'icon-add'">新增</div>
			<div onClick="toUnitList('edit')" data-options="iconCls:'icon-edit'">编辑</div>
			<div onClick="delUnit()" data-options="iconCls:'icon-remove'">删除</div>
			<div class="menu-sep"></div>
			<div onClick="print()" data-options="iconCls:'icon-print'">打印</div>
			<div onClick="reload()" data-options="iconCls:'icon-reload'">刷新</div>
		</div>
		<div id="tb" style="height: auto">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onClick="toUnitList()">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onClick="delUnit()">删除</a>
		</div>
		<div style="height: 5px;"></div> 
	</body>
</html>

 