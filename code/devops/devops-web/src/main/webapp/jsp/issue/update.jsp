<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <%@include file="/jsp/common/js.jsp" %>
</head>
<body>
<div class="container">
    <h1>问题处理</h1>

    <div style="width: 50%">
        <form method="post" action="${pageContext.request.contextPath}/issue/update">
            <div class="form-group" style="display: none">
                <label for="id">id</label>
                <input name="id" id="id" type="text" class="form-control" placeholder="" value="">
            </div>
            <div class="form-group">
                <label>标题</label>
                <input name="title" id="title" type="text" class="form-control  shortInput" placeholder="">
            </div>
            <div class="form-group">
                <label>Bug级别</label>
                <input name="bugLevel" id="bugLevel" type="text" class="form-control  shortInput" placeholder="">
            </div>
            <div class="form-group">
                <label>问题处理时间</label>
                <input name="dealTime" id="dealTime" type="text" class="form-control my97Date  shortInput" placeholder=""   onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})">
            </div>
            <div class="form-group">
                <label>处理人</label>
                <input name="dealOwner" id="dealOwner" type="text" class="form-control  shortInput" placeholder="">
            </div>
            <div class="form-group">
                <label>功能描述</label>
                <textarea name="description" id="description" type="text" class="form-control" placeholder=""></textarea>
            </div>
            <div class="form-group">
                <label>解决方案</label>
                <textarea name="solution" id="solution" type="text" class="form-control" placeholder=""></textarea>
            </div>
            <button type="submit" class="btn btn-default">保存</button>
            <button type="reset" class="btn btn-default">重置</button>
        </form>
    </div>
</div>
</body>
</html>
