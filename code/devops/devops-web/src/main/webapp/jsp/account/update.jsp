<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <%@include file="/jsp/common/js.jsp" %>
</head>
<body>
<div class="container">
    <h1>账户</h1>

    <div style="width: 50%">
        <form method="post" action="${pageContext.request.contextPath}/account/update">
            <div class="form-group" style="display: none">
                <label for="id">id</label>
                <input name="id" id="id" type="text" class="form-control" placeholder="" value="">
            </div>
            <div class="form-group" style="display: none">
                <label for="salt">id</label>
                <input name="salt" id="salt" type="text" class="form-control" placeholder="" value="">
            </div>
            <div class="form-group">
                <label>用户名</label>
                <input name="name" id="name" type="text" class="form-control shortInput" placeholder="yangpengfei">
            </div>
            <div class="form-group">
                <label>密码</label>
                <input name="password" id="password" type="password" class="form-control shortInput" placeholder="123456789">
            </div>
            <div class="form-group">
                <label>邮箱</label>
                <input name="email" id="email" type="text" class="form-control shortInput" placeholder="yangpengfei@yolo24.com">
            </div>
            <div class="form-group">
                <label>QQ</label>
                <input name="qq" id="qq" type="text" class="form-control shortInput" placeholder="262549369">
            </div>
            <div class="form-group">
                <label>手机号</label>
                <input name="mobile" id="mobile" type="text" class="form-control shortInput" placeholder="13439083551">
            </div>
            <div class="form-group">
                <label>姓名</label>
                <input name="realName" id="realName" type="text" class="form-control shortInput" placeholder="杨鹏飞">
            </div>
            <div class="form-group">
                <label>是否启用</label>
                <select name="canUse" id="canUse" class="form-control" style="width: 100px"  >
                    <option value="2" selected>是</option>
                    <option value="1">否</option>
                </select>
            </div>
            <button type="submit" class="btn btn-default">保存</button>
            <button type="reset" class="btn btn-default">重置</button>
        </form>
    </div>
</div>
</body>
</html>
