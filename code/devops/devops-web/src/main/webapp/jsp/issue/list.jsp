<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@include file="/jsp/common/js.jsp" %>

    <script>
        function searchResource(){
            $("#datagridDiv")[0].innerHTML = "<table id=\"datagrid\" class=\"table table-striped\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"></table>";
            $('#datagrid').DataTable({
                "ajax": '${pageContext.request.contextPath}/issue/query',
                "dom" : '<<t><r><ilp>>',
                "columns": [
                    { "data": "id","title":JCheckBoxTitle,"render": JFormartCheckBox},
                    { "data": "id","title":"序号"},
                    { "data": "title","title":"标题"},
                    { "data": "bugLevel","title":"Bug级别"},
                    { "data": "dealTime","title":"问题处理时间"},
                    { "data": "dealOwner","title":"处理人" },
                    { "data": "createTime","title":"创建时间"},
                    { "data": "updateTime","title":"更新时间" },
                ],
                "language" : language
            });
            JFormatGrid('datagrid');
        }

        function toUpdate(){
            window.location = '${pageContext.request.contextPath}/jsp/issue/update.jsp';
        }

        function deleteResource(){
            var items = JGetSelected();
            var url = '${pageContext.request.contextPath}/issue/del';
            JSendAjax(url,items)
        }

        function JCollBack(_data){
            if(_data.success){
                alert(_data.data);
                searchResource();
                return;
            }
            return alert(_data.data);
        }
    </script>

</head>
<body>
<div class="container">
    <section>
        <h1>ISSUE <span>问题情况列表</span></h1>

        <div class="info" style="max-height: 130px">
            <p >关于项目问题处理列表</p><br>
        </div>
        <div>
            <button type="button" class="btn btn-success dropdown-toggle" id="btnToUpdate">创建</button>
            <button type="button" class="btn btn-success dropdown-toggle" id="btnDelete">删除</button>
            <button type="button" class="btn btn-success dropdown-toggle" id="btnSearch">查询</button>
            <div style="float: right">
                <input type="text" name="global_filter" id="global_filter" class="form-control" >
            </div>
        </div>
        <div id="datagridDiv">
            <table id="datagrid" class="display" cellspacing="0" width="100%"></table>
        </div>
    </section>
</div>

</body>
</html>