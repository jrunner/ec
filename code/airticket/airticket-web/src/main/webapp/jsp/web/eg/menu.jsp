<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%@include file="../common/init.jsp"%>

<html>
	<head>
	</head>
	<body>
		<div title="关联事件" data-options="iconCls:'icon-menu1'">
			<ul>
				<li>
					<div class="selected">
						<a href="javascript:OpenPage('菜单管理','jczx/list.html');"><span
							class="icon-nav"></span>菜单管理</a>
					</div>
				</li>
				<li>
					<div class="">
						<a href="demo.html" target="mainFrame"><span class="icon-add"></span>添加用户</a>
					</div>
				</li>
				<li>
					<div class="">
						<a href="demo2.html" target="mainFrame"><span
							class="icon-users"></span>用户管理</a>
					</div>
				</li>
				<li>
					<div class="">
						<a href="demo2.html" target="mainFrame"><span
							class="icon-role"></span>角色管理</a>
					</div>
				</li>
				<li>
					<div class="">
						<a href="demo.html" target="mainFrame"><span class="icon-set"></span>权限设置</a>
					</div>
				</li>
				<li>
					<div class="">
						<a href="demo.html" target="mainFrame"><span class="icon-log"></span>系统日志</a>
					</div>
				</li>
			</ul>
		</div>

	</body>
</html>