<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <%@include file="/jsp/common/js.jsp" %>
</head>
<body>
<div class="container">
    <h1>菜单</h1>

    <div style="width: 50%">
        <form method="post" action="${pageContext.request.contextPath}/menu/update">
            <div class="form-group" style="display: none">
                <label for="id">id</label>
                <input name="id" id="id" type="text" class="form-control shortInput" placeholder="" value="">
            </div>
            <div class="form-group">
                <label>父ID</label>
                <input name="pid" id="pid" type="text" class="form-control shortInput" placeholder="">
            </div>
            <div class="form-group">
                <label>名称</label>
                <input name="name" id="name" type="text" class="form-control shortInput" placeholder="">
            </div>
            <div class="form-group">
                <label>URL</label>
                <input name="url" id="url" type="text" class="form-control shortInput" placeholder="">
            </div>
            <div class="form-group">
                <label>描述</label>
                <input name="description" id="description" type="text" class="form-control shortInput" placeholder="">
            </div>
            <div class="form-group">
                <label>类型</label>
                <input name="type" id="type" type="text" class="form-control shortInput" placeholder="">
            </div>
            <div class="form-group">
                <label>排序号</label>
                <input name="orderNun" id="orderNun" type="text" class="form-control shortInput" placeholder="">
            </div>
            <button type="submit" class="btn btn-default">保存</button>
            <button type="reset" class="btn btn-default">重置</button>
        </form>
    </div>
</div>
</body>
</html>
