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
	 });

	function search(){
		 var name = $("#name").val();
		 grid.datagrid('load',{name:name});
	}

	//添加或编辑子行业
    function toBaseUpdate(id){
        var data = '&code=<%=request.getParameter("code") %>&pcode=<%=request.getParameter("pcode") %>&treeid=<%=request.getParameter("treeid") %>'; 
    	window.location = '<%=request.getContextPath() %>/baseinfo/vipunit.do?cmd=toBaseUpdate&id='+id+data;
    }
	//删除子行业
	function delBase(){
		var data = {id:'<%=request.getParameter("code") %>'};
		JSendAjax('<%=contextPath%>/baseinfo/vipunit.do?cmd=delBase',data);
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
								<div class="ico_win">主行业</div>
							</td>
							<td class="td_middle_table">
								<div class="title_oper">
                                	<!--<a href="javascript:toBaseUpdate('')" title="新建主行业">新建</a> | <a href="javascript:toBaseUpdate('<%=request.getParameter("id") %>')" title="编辑主行业">编辑</a> | <a href="javascript:delBase()" title="删除主行业">删除</a>-->
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
							<td width="9" class="td_left_table_content">&nbsp;</td>
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
											&nbsp;<input class="txt_input" name="no" id="no" maxlength="10" />
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
							<td class="td_middle_buttom_table">&nbsp;
								
							</td>
							<td width="14" class="td_right_buttom_table"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div style="height: 5px;"></div>

		<div id="divTest" style="width: 99.5%; margin-left: 4px;">
			<table id="grid" class="easyui-datagrid" title="子行业列表" align="center" style="height: 400px;"
				data-options="rownumbers:true,
					singleSelect:true,
					autoRowHeight:false,
					loadMsg:'数据正在努力加载中...',
					url:'<%=contextPath %>/baseinfo/vipunit.do?cmd=baselist&code<%=request.getParameter("code") %>'
					">
				<thead>
					<tr>
						<th data-options="field:'alias',width:100">名称</th>
                        <th data-options="field:'isvip',width:100">重点行业</th>
						<th data-options="field:'remark',width:80">备注</th>
						<th data-options="field:'createTime',width:140">创建时间</th>
						<th data-options="field:'createTime',width:140">修改时间</th>
					</tr>
				</thead>  
			</table>
		</div>

		<div style="height: 5px;"></div> 
	</body>
</html>

 