<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <%@include file="/jsp/common/js.jsp" %>
</head>
<body>
<div class="container">
    <h1>发版历史</h1>

    <div style="width: 50%">
        <form method="post" action="${pageContext.request.contextPath}/pub/update">
            <div class="form-group" style="display: none">
                <label for="id">id</label>
                <input name="id" id="id" type="text" class="form-control" placeholder="" value="">
            </div>
            <div class="form-group">
                <label>发布时间</label>
                <input name="pubTime" id="pubTime" type="text" class="form-control my97Date shortInput" placeholder="" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})">
            </div>
            <div class="form-group">
                <label>发布状态</label>
                <select name="canUse" id="canUse" class="form-control shortInput"  >
                    <option value="2" selected>已发布</option>
                    <option value="1">未发布</option>
                </select>
            </div>
            <div class="form-group">
                <label>功能描述</label>
                <textarea name="description" id="description" type="text" class="form-control" placeholder=""></textarea>
            </div>
            <div class="form-group">
                <label>相关应用模块</label>
                <textarea name="app" id="app" type="text" class="form-control" placeholder=""></textarea>
            </div>
            <button type="submit" class="btn btn-default">保存</button>
            <button type="reset" class="btn btn-default">重置</button>
        </form>
    </div>
</div>
</body>
</html>
