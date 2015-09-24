 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%@include file="../../common/init.jsp"%>

	<link rel="stylesheet" type="text/css" href="<%=contextPath%>/themes/css/right.css" />
	<link rel="stylesheet" type="text/css" href="<%=contextPath%>/themes/css/blueStyle.css" />

	<script type="text/javascript">
	
	 $(document).ready(function(){
		
	 });
	 
	 function save(){
		 var data={resName:$('#resName').val(),
				 id:$('#id').val(),
				 createTime:$('#createTime').val()};
		 JSendAjax('<%=contextPath%>/baseinfo/vipunit.do?cmd=updateBase',data);
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
                 var code = $(_data).find('code').text();
                 var resname = $(_data).find('resname').text(); 
                 if(state != 'true'){ 
		             onAddNode(id,code,'<%=request.getParameter("pcode") %>','/demo/baseinfo/vipunit.do?cmd=toBaseList',resname,'<%=request.getParameter("treeid") %>','baseinstry') ;
                 }
                 if(state == 'true'){ 
                	 onEditNode(code,resname,'<%=request.getParameter("treeid") %>') ;
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
	
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	</head>  
	<body > 
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="15" height="30" class="td_left_top_table"></td>
							<td class="td_middle_table" valign="middle">
								<div class="ico_win">添加</div>
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
										<td height="26" colspan="2" class="table_content_title_td"></td>
									</tr>
									<tr>
										<td class="table_content_td">行业名称：</td>
										<td class="table_content_td_left">
											<input class="txt_input" name="resName" id="resName" value="${bean.resName}"/>
											<input class="txt_input" name="id" type="hidden" id="id" value="${bean.id}"/>
										</td>
									</tr> 
									<tr>
										<td class="table_content_td" width="15%">创建时间：</td>
										<td class="table_content_td_left"><input class="txt_input_date" onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" name="createTime" id="createTime" value="${bean.createTime}"/></td>
									</tr>
									<tr>
										<td colspan="2" class="table_content_td" height="30">
											<input type="button" class="btn_input" value="保存" onclick="save()" /> 
											<input type="button" class="btn_input" value="返回" id="JBtnGoBack" />
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
	</body>
</html>

 