<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%@include file="common/init.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Demo</title>



<style type="text/css">
	body {
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
	}
</style>

<script>
	function onDrawNode(e) {
		var tree = e.sender;
		var node = e.node;
		//所有子节点加上超链接
		if (node.uri!='' && node.uri!=null) {
			var tmp = node.uri.indexOf('?')>0 ? (node.uri): (node.uri+"?");
			var url = tmp+"&code="+node._code+"&pcode="+node._pcode+"&treeid="+tree.id+"&id="+node.id;
			e.nodeHtml = "<a style='text-decoration: none' href='javascript:OpenPage(\""+node.alias+"\",\""+url+"\");' >" + node.alias + "</a>";
		}
		if(e.node.icon){
			e.iconCls  = e.node.icon; 
		}
	}
	
	function OpenPage(title,url){
		addTab(title,url);
	}
	 
	$(document).ready(function(){
		var names = document.getElementsByName("menu");
		for(var i=0;i<names.length;i++){	
			var tree1 = mini.get(names[i].value);
			tree1.expandAll();
		}
	});

	//添加节点
	function onAddNode(e,id,code,pcode,url,text,treeid,icon) {
        var tree = mini.get(treeid); 
        var node = tree.getNode(pcode);  
        var newNode = {id:id,uri:url,alias:text,_code:code,_pcode:pcode,icon:icon};
        tree.addNode(newNode, "add", node); 
        //tree.setNodeIconCls(newNode,icon);
        tree.expandNode(node);//展开节点 
    }
    //删除节点 
	function onRemoveNode(e,code,treeid) { 
		var tree = mini.get(treeid);
        var node = tree.getNode(code);
        if (node){
             tree.removeNode(node);
        }
    }
    //编辑节点 
	function onEditNode(e,code,text,treeid) {
		var tree = mini.get(treeid);
        var node = tree.getNode(code);
 
        var title = node.alias;
        updateTab(title,text);//修改选项卡名称
        
        //tree.updateNode(node, {text: text});
        tree.setNodeText(node,text);
    }

</script>

<style type="text/css">
	.all{background:url(<%=contextPath%>/themes/images/ico/tree/all.gif) no-repeat 50% 50%;}
    .mini-tree-expand .all{background:url(<%=contextPath%>/themes/images/ico/tree/all.gif) no-repeat 50% 50%;}
    .baseinstry{background:url(<%=contextPath%>/themes/images/ico/tree/baseinstry.gif) no-repeat 50% 50%;}
    .subinstry{background:url(<%=contextPath%>/themes/images/ico/tree/subinstry.gif) no-repeat 50% 50%;}
    .unit{background:url(<%=contextPath%>/themes/images/ico/tree/unit.gif) no-repeat 50% 50%;}
</style>
</head>
<body class="easyui-layout">
	<div region="west" border="true" split="true" title="导航" class="cs-west"  data-options="iconCls:'icon-ok'">
		<div class="easyui-accordion" fit="true" border="false">
		<%
		List list = (List)request.getAttribute("list"); 
		for (int i = 0; i < list.size(); i++) {
			Map<String,String> map = (Map) list.get(i);  
			
			String url = request.getContextPath() + "/menu.do?cmd=menuAjax&name="+map.get("_code");
			if("ajaxTree".equals(map.get("subType"))){
				url = map.get("uri");
			}
			%> 
			<div title="<%=map.get("alias") %>" data-options="iconCls:'<%=map.get("icon") %>'" >
               	 <div id="<%=map.get("name") %>" class="mini-tree" style="padding:0px;height:99%;width:100%;" url="<%=url %>"
                       ondrawnode="onDrawNode" showTreeIcon="true" resultAsTree="false" textField="alias" idField="_code" parentField="_pcode" expandOnNodeClick="true">        
                 </div>
                 <input name="menu" value="<%=map.get("name") %>" type="hidden" /> 
			</div>
		<%} %>
		</div>
	</div>
	<div id="mainPanle" region="center" border="true" border="false">
		 <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
                <div title="Home">
				<div class="cs-home-remark">
					<h1>统管局协作系统 Demo</h1> <br>
					制作：ypf <br>
					说明：基于jQuery EasyUI 1.3.5 Demo
					
					<%
					Properties props = System.getProperties(); // 获得系统属性集
					%>
					
					<br>操作系统名称：<%=props.getProperty("os.name") %>
					<br>操作系统构架：<%=props.getProperty("os.arch") %>
					<br>操作系统版本：<%=props.getProperty("os.version") %>
				</div>
				</div>
        </div>
	</div>
	<div id="mm" class="easyui-menu cs-tab-menu">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseother">关闭其他</div>
		<div id="mm-tabcloseall">关闭全部</div>
	</div>
</body>
</html>


<style type="text/css">
body {
	font: 12px/20px "微软雅黑", "宋体", Arial, sans-serif, Verdana, Tahoma;
	padding: 0;
	margin: 0;
}
a:link {
 text-decoration: none;
}
a:visited {
 text-decoration: none;
}
a:hover {
 text-decoration: underline;
}
a:active {
 text-decoration: none;
}
.cs-north {
	height:60px;
}
.cs-north-bg {
	width: 100%;
	height: 100%;
	background: url(../../js/jquery-easyui-1.3.5/themes/gray/images/header_bg.png) repeat-x;
}
.cs-north-logo {
	height: 40px;
	margin: 15px 0px 0px 5px;
	display: inline-block;
	color:#000000;font-size:22px;font-weight:bold;text-decoration:none
}
.cs-west {
	width:200px;padding:0px;
}
.cs-south {
	height:25px;background:#F7FEEF repeat-x;padding:0px;text-align:center;
}
.cs-navi-tab {
	padding: 5px;
}
.cs-tab-menu {
	width:220px;
}
.cs-home-remark {
	padding: 10px;
}
.wrapper {
    float: right;
    height: 30px;
    margin-left: 10px;
}
.ui-skin-nav {
    float: right;
	padding: 0;
	margin-right: 10px;
	list-style: none outside none;
	height: 30px;
}

.ui-skin-nav .li-skinitem {
    float: left;
    font-size: 12px;
    line-height: 30px;
	margin-left: 10px;
    text-align: center;
}
.ui-skin-nav .li-skinitem span {
	cursor: pointer;
	width:10px;
	height:10px;
	display:inline-block;
}
.ui-skin-nav .li-skinitem span.cs-skin-on{
	border: 1px solid #FFFFFF;
}

.ui-skin-nav .li-skinitem span.gray{background-color:gray;}
.ui-skin-nav .li-skinitem span.pepper-grinder{background-color:#BC3604;}
.ui-skin-nav .li-skinitem span.blue{background-color:blue;}
.ui-skin-nav .li-skinitem span.cupertino{background-color:#D7EBF9;}
.ui-skin-nav .li-skinitem span.dark-hive{background-color:black;}
.ui-skin-nav .li-skinitem span.sunny{background-color:#FFE57E;}

.ui-skin-nav .li-skinitem span.black{background-color:#FD107E;}
.ui-skin-nav .li-skinitem span.bootstrap{background-color:#01A57E;}
.ui-skin-nav .li-skinitem span.metro{background-color:#FFEAAA;}
</style>
<script type="text/javascript">
/**关闭tab标签页*/
function closeTab() {
	var title = $('#tabs').tabs('getSelected').panel('options').title;
	$('#tabs').tabs('close',title);
}

//add function by ypf at 2014-03-27 添加面板标题更新功能
function updateTab(title, titleNew){
	if ($('#tabs').tabs('exists', title)){ 
		var tab = $('#tabs').tabs('getTab',title); 
		$('#tabs').tabs('update', {tab: tab,options: {title: titleNew}});
	} 
}
function addTab(title, url){
	if ($('#tabs').tabs('exists', title)){
		$('#tabs').tabs('select', title);//选中并刷新
		var currTab = $('#tabs').tabs('getSelected');
		//var url = $(currTab.panel('options').content).attr('src');
		if(url != undefined && currTab.panel('options').title != 'Home') {
			$('#tabs').tabs('update',{
				tab:currTab,
				options:{
					content:createFrame(url) 
				}
			})
		}
	} else {
		var content = createFrame(url);
		$('#tabs').tabs('add',{
			title:title,
			content:content,
			closable:true
		});
	}
	tabClose();
}
function createFrame(url) {
	var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return s;
}
		
function tabClose() {
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	})
	/*为选项卡绑定右键*/
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab",subtitle);
		$('#tabs').tabs('select',subtitle);
		return false;
	});
}		
//绑定右键菜单事件
function tabCloseEven() {
	//刷新
	$('#mm-tabupdate').click(function(){
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		if(url != undefined && currTab.panel('options').title != 'Home') {
			$('#tabs').tabs('update',{
				tab:currTab,
				options:{
					content:createFrame(url)
				}
			})
		}
	})
	//关闭当前
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	})
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			if(t != 'Home') {
				$('#tabs').tabs('close',t);
			}
		});
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		var nextall = $('.tabs-selected').nextAll();		
		if(prevall.length>0){
			prevall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				if(t != 'Home') {
					$('#tabs').tabs('close',t);
				}
			});
		}
		if(nextall.length>0) {
			nextall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				if(t != 'Home') {
					$('#tabs').tabs('close',t);
				}
			});
		}
		return false;
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});

	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	})
}

$(function() {
	tabCloseEven();

	$('.cs-navi-tab').click(function() {
		var $this = $(this);
		var href = $this.attr('src');
		var title = $this.text();
		addTab(title, href);
	});

	
});

</script>
