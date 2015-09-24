<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String contextPath = request.getContextPath();
%>

<script language="javascript" src="<%=contextPath%>/js/jquery.js"></script>
<script language="javascript" src="<%=contextPath%>/js/common.js"></script>
<script language="javascript" src="<%=contextPath%>/js/My97DatePicker/WdatePicker.js"></script>

<link href="<%=contextPath%>/js/scripts/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css">
<script src="<%=contextPath%>/js/scripts/boot.js" type="text/javascript"></script> 

<link rel="stylesheet" type="text/css" href="<%=contextPath %>/js/jquery-easyui-1.3.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath %>/js/jquery-easyui-1.3.5/themes/icon.css">
<script type="text/javascript" src="<%=contextPath %>/js/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=contextPath %>/js/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>

<link rel="stylesheet" type="text/css" href="<%=contextPath%>/themes/css/tsm-icon.css" />

<script>
function onAddNode(id,code,pcode,url,text,treeid,icon){
	window.parent.onAddNode(null,id,code,pcode,url,text,treeid,icon);
}
function onRemoveNode(code,treeid){
	window.parent.onRemoveNode(null,code,treeid);
}
function onEditNode(code,text,treeid){
	window.parent.onEditNode(null,code,text,treeid);
}
function closeTab(){
	window.parent.closeTab();
}
</script>