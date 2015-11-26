<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="common/init.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="<%=request.getContextPath() %>/jsp/web/css/header.css" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript" src="<%=request.getContextPath() %>/jsp/web/js/head_menu.js"></script>
	</head>

	<body>
		<!--head-begin-->
		<div id="head">
			<div class="head_logo"></div>
			<div class="head_menu">
				<ul class="nav">
					<li>
						<a href="<%=request.getContextPath() %>/jsp/web/welcome.html" target="mainFrame" onfocus="this.blur()" class="nav_current" onclick="change_bg(this)"><img src="<%=request.getContextPath() %>/jsp/web/images/001.png" width="35" height="35" /><span style="display: inline-block;">首&nbsp;&nbsp;&nbsp;页</span></a>
					</li>
					<%
					List list = (List)request.getAttribute("list");
					for (int i = 0; i < list.size(); i++) {
						Map map = (Map) list.get(i);
						%>
						<li>
							<a href="<%=map.get("uri") %>&code=<%=map.get("_code") %>" target="mainFrame" onfocus="this.blur()" onclick="change_bg(this)"><img src="<%=request.getContextPath() %>/jsp/web/<%=map.get("src") %>" width="35" height="35" /><span style="display: inline-block;"><%=map.get("alias") %></span></a>
						</li>
						
					<%} %>
				</ul>
			</div>
		</div>
		<!--head-over-->
		<!--admin-begin-->
		<div id="admin">
			<div id="userId">
				<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11%">
							<img src="<%=request.getContextPath() %>/jsp/web/images/userId.png" width="16" height="16" />
						</td>
						<td width="72%">
							[admin]&nbsp;欢迎登录
						</td>
						<td width="17%" align="left">
							<img src="<%=request.getContextPath() %>/jsp/web/images/lighter.gif" width="24" height="24" />
						</td>
					</tr>
				</table>
			</div>
			<div id="out">
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11%">
							[
						</td>
						<td width="30%">
							<img src="<%=request.getContextPath() %>/jsp/web/images/map1_07.png" width="16" height="16" />
						</td>
						<td width="45%">
							<a href="#">退出</a>
						</td>
						<td width="14%">
							]
						</td>
					</tr>
				</table>
			</div>
			<div id="notice">
             <span style="padding-top:8px;height:26px;"><img src="<%=request.getContextPath() %>/jsp/web/images/map1_05.png" width="16" height="16" /></span><span>通知公告：</span>
             <samp><%--<marquee direction="right" scrolldelay="100">各个小组请注意，本系统将在2012年6月6日集中测试!</marquee></samp>--%>
        </div>
		</div>

		<!--admin-over-->
	</body>
</html>
