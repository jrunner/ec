﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script language="javascript" src="<%=request.getContextPath() %>/js/jquery.js"></script>
<script language="javascript" src="<%=request.getContextPath() %>/js/common.js"></script>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>管理平台</title>
		<style type="text/css">
		body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
			overflow: hidden;
		}
		
		.STYLE3 {
			color: #528311;
			font-size: 12px;
		}
		
		.STYLE4 {
			color: #42870a;
			font-size: 12px;
		}
		
		.txt_input {
			height: 18px;
			width: 130px;
			border: solid 1px #cadcb2;
			font-size: 12px;
			color: #81b432;
		}
		</style>
		
		<script type="text/javascript">
			//登录
			function login(){
				var name = $('#name');
				var pwd = $('#pwd');
				var url = '<%=request.getContextPath()%>/login.do?cmd=login';
				var data = {name:name.val(),pwd:pwd.val()};
				JSendAjax(url,data);
			}
			
			//回调函数
	        function JCollBack(_data,_textStuats){
	            var info = $(_data).find('info').text();
	            var msg = $(_data).find('msg').text();
	            if(info == '1'){
	                if(msg == 'success'){
	                	window.location = '<%=request.getContextPath()%>/jsp/web/main.jsp';
	                }
	                return;
	            }
	            if(info == '-1'){
	                if(msg == 'name_haved'){
	                	alert('名称已存在！'); $('#def_name').focus();
	                	return;
	                }
	                alert('错误信息: '+msg);return;
	            }
	        }
	        
			//验证
			function validate(){
				if($('#name').val()==''){
					alert('用户名不能为空!');
					return false;
				}
				return true;				
			}
		</script>
	</head>

	<body>
		<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td bgcolor="#e5f6cf">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td height="608" background="images/login_03.gif">
					<table width="862" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td height="266" background="images/login_04.gif">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td height="95">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="424" height="95" background="images/login_06.gif">
											&nbsp;
										</td>
										<td width="183" background="images/login_07.gif">
											<table width="100%" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="21%" height="30">
														<div align="center">
															<span class="STYLE3">用户</span>
														</div>
													</td>
													<td width="79%" height="30">
														<input type="text" id="name" name="name" value="admin" class="txt_input">
													</td>
												</tr>
												<tr>
													<td height="30">
														<div align="center">
															<span class="STYLE3">密码</span>
														</div>
													</td>
													<td height="30">
														<input  type="password" id="pwd" name="pwd" class="txt_input">
													</td>
												</tr>
												<tr>
													<td height="30">
														&nbsp;
													</td>
													<td height="30">
														<img src="images/dl.gif" width="81" height="22" border="0" usemap="#Map">
													</td>
												</tr>
											</table>
										</td>
										<td width="255" background="images/login_08.gif">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="247" valign="top" background="images/login_09.gif">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="22%" height="30">
											&nbsp;
										</td>
										<td width="56%">
											&nbsp;
										</td>
										<td width="22%">
											&nbsp;
										</td>
									</tr>
									<tr>
										<td>
											&nbsp;
										</td>
										<td height="30">
											<table width="100%" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="44%" height="20">
														&nbsp;
													</td>
													<td width="56%" class="STYLE4">
														版本 2013V1.0
													</td>
												</tr>
											</table>
										</td>
										<td>
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td bgcolor="#a2d962">
					&nbsp;
				</td>
			</tr>
		</table>

		<map name="Map">
			<area shape="rect" coords="3,3,36,19" href="javascript:login()">
			<area shape="rect" coords="40,3,78,18" href="#">
		</map> 
	</body>
</html>
