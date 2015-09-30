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
	 	$('#grid2').datagrid('resize');
	 	$('#grid3').datagrid('resize');
	 	$('#panel').datagrid('resize');
	 });

	 $(document).ready(function(){
		 
	 });

	 function save(){
		 var data={
				   resName:$('#resName').val(),
				   id:$('#id').val(),
				   remark:$('#remark').val()};
		 JSendAjax('<%=contextPath%>/baseinfo/vipunit.do?cmd=updateUnit',data);
	 }
	 
	 //回调函数
     function JCollBack(_data,_textStuats){
         var info = $(_data).find('info').text();
         var msg = $(_data).find('msg').text(); 
         if(info == '0'){
             if(msg=='ok'){
            	 alert('保存成功');
            	 var state = $(_data).find('state').text();
                 var id = $(_data).find('id').text();
                 var resname = $(_data).find('resname').text(); 
                 if(state=='true'){
                	 var code = $(_data).find('code').text();
                	 onEditNode(code,resname,'<%=request.getParameter("treeid") %>') ;
                 }
                 if(state!='true'){
		             onAddNode(id,id,'<%=request.getParameter("code") %>','/demo/baseinfo/vipunit.do?cmd=toUnitList&id='+id,resname,'<%=request.getParameter("treeid") %>','unit') ;
                 }
	             var url = '<%=request.getContextPath() %>/baseinfo/vipunit.do?cmd=toSubList&code=<%=request.getParameter("code") %>&pcode=<%=request.getParameter("pcode") %>&treeid=<%=request.getParameter("treeid") %>';
	             window.location = url;
	             return; 
             }
         }
         if(info == '-1'){
             alert('错误信息: '+msg);return;
         }
     }
	</script>

<html>
	<head> 
	</head>   
	<body > 
		<div id="divPanel" style="width: 99%; margin-left: 4px;margin-top:2px;"> 
			<div id="panel" class="easyui-panel" style="padding:10px;" 
					data-options="title:'单位信息',iconCls:'icon-save',collapsible:true">
				<table width="80%" border="0" align="left" cellpadding="3" cellspacing="1">
					<tr>
						<td width="100px" align="center"> 
							单位名称： 
						</td> 
						<td >
							<input class="txt_input" name="id" id="id" maxlength="10" type="hidden" value="${bean.id }"/>
							<input class="txt_input" name="resName" id="resName" maxlength="10" value="${bean.alias}"/>
						</td>
						<td align="center">
							城市： 
						</td>
						<td >
							<input class="txt_input" name="city" id="city" maxlength="20" value="" />
						</td>
					</tr>
					<tr>
						<td align="center">
							邮编： 
						</td>
						<td >
							<input class="txt_input" name="codeno" id="codeno" maxlength="20" value="" />
						</td>
						<td align="center">
							传真： 
						</td>
						<td >
							<input class="txt_input" name="phone" id="phone" maxlength="20" value="" />
						</td>
					</tr>
					<tr>
						<td align="center">
							通讯地址： 
						</td>
						<td >
							<input class="txt_input" name="address" id="address" maxlength="20" value="" />
						</td>
						<td align="center">
							单位类型： 
						</td>
						<td >
							<select id="state" class="easyui-combobox" name="state" data-options="panelHeight:100"  >
                                <option value="处置">处置</option>
                                <option value="协议">协议</option> 
                                <option value="服务">服务</option>
                            </select>
						</td>
					</tr>
                    <tr>
						<td align="center">
							主行业： 
						</td>
						<td >
							<input class="txt_input" name="address" id="address" maxlength="20" value="" readonly="readonly"/>
						</td>
						<td align="center">
							子行业： 
						</td>
						<td >
							<input class="txt_input" name="address" id="address" maxlength="20" value="" readonly="readonly"/>&nbsp;<a href="#">选择</a>
						</td>
					</tr>
					<tr>
						<td>
						</td>
						<td height="30">
							<input type="button" class="btn_input" value="保存" onClick="save()" />
							<input type="button" class="btn_input" value="返回" onClick="window.history.go(-1)" />
						</td>
					</tr>
				</table>
			</div>
			
			<div style="height: 5px;"></div>
			
			<table id="grid" class="easyui-datagrid" title="联系人列表" align="center" style="height: 200px;"
				data-options="rownumbers:true,
					singleSelect:true,
					autoRowHeight:false,
					loadMsg:'数据正在努力加载中...',
                    toolbar: '#tb1',
					url:'<%=contextPath %>/baseinfo/vipunit.do?cmd=list&pid<%=request.getParameter("id") %>'
					">
				<thead>
					<tr>
						<th data-options="field:'leakName',width:80,align:'right'">联系人</th>
						<th data-options="field:'leakPhone',width:80,align:'right'">联系人电话</th>
						<th data-options="field:'createTime',width:80,align:'right'">创建时间</th>
					</tr>
				</thead>
			</table>
             <div id="tb1" style="height: auto">
			 <a href="javascript:edit()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" >新增</a>
			 <a href="../edit.html" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" >编辑</a>
			 <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onClick="delUnit()">移除</a>
            <table border="0" cellspacing="0" cellpadding="0" align="right">
            <tr><td><input class="txt_input"/></td><td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onClick="">查询</a></span></td></tr>
            </table>
            </div>
            <div id="tb2" style="height: auto">
			 <a href="javascript:edit()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" >新增</a>
			 <a href="../edit.html" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" >编辑</a>
			 <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onClick="delUnit()">移除</a>
             <table border="0" cellspacing="0" cellpadding="0" align="right">
            <tr><td><input class="txt_input"/></td><td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onClick="">查询</a></span></td></tr>
            </table>
            </div>
            <div id="tb3" style="height: auto">
			 <a href="javascript:edit()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" >新增</a>
			 <a href="../edit.html" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" >编辑</a>
			 <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onClick="delUnit()">移除</a>
             <table border="0" cellspacing="0" cellpadding="0" align="right">
            <tr><td><input class="txt_input"/></td><td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onClick="">查询</a></span></td></tr>
            </table>
            </div>
			<div style="height: 5px;"></div> 
			<table id="grid2" class="easyui-datagrid" title="IP列表" align="center" style="height: 200px;"
				data-options="rownumbers:true,
					singleSelect:true,
					autoRowHeight:false,toolbar: '#tb2',
					loadMsg:'数据正在努力加载中...',
					url:'<%=contextPath %>/baseinfo/vipunit.do?cmd=list&pid<%=request.getParameter("id") %>'
					">

				<thead>
					<tr>
						<th data-options="field:'leakName',width:80,align:'right'">IP地址</th>
						<th data-options="field:'createTime',width:80,align:'right'">创建时间</th>
					</tr>
				</thead>
			</table>
			<div style="height: 5px;"></div> 
			<table id="grid3" class="easyui-datagrid" title="域名列表" align="center" style="height: 200px;"
				data-options="rownumbers:true,
					singleSelect:true,
					autoRowHeight:false,toolbar: '#tb3',
					loadMsg:'数据正在努力加载中...',
					url:'<%=contextPath %>/baseinfo/vipunit.do?cmd=list&pid<%=request.getParameter("id") %>'
					">

				<thead>
					<tr>
						<th data-options="field:'leakName',width:80,align:'right'">域名</th>
						<th data-options="field:'createTime',width:80,align:'right'">创建时间</th>
					</tr>
				</thead>
			</table>
		</div>
		 
		<div style="height: 5px;"></div> 
	</body>
</html>

 