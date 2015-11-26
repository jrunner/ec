//弹出页面-窗口可大可
function JOpenAutoPage(openUrl, winTitle, winHeight, winWidth) {
	wSize = "left=" + (screen.width - winWidth) / 2 + ",top=" + (screen.height - winHeight) / 2 + ",width=" + winWidth + ",height=" + winHeight;
	window.open(openUrl, winTitle, "scrollbars=yes,resizable=yes," + wSize);
}
//弹出页面-窗口不可变
function JOpenPage(openUrl, winTitle, winHeight, winWidth) {
	wSize = "left=" + (screen.width - winWidth) / 2 + ",top=" + (screen.height - winHeight) / 2 + ",width=" + winWidth + ",height=" + winHeight;
	window.open(openUrl, winTitle, "scrollbars=yes,resizable=no," + wSize);
}

//发送ajax请求,默认xml方式提交
function JSendAjax(url,data,dataType){
	if(url==null || url==''){
		alert('请求不能为空');
		return;
	}
	
	if(data==null || data=='')
		data={};
	if(dataType==null || dataType=='')
		dataType = 'json';
		
	$.ajax({type: "POST",url: url, cache: false, data: data, dataType: dataType ,success: JCollBack });
}


var PROCESSING_CONTENT = "<div class='dataTables_processing'><div class='progress progress-striped active'><div class='progress-bar' role='progressbar' aria-valuenow='45'  aria-valuemin='0' aria-valuemax='100' style='width: 100%'>正在加载中</div></div></div>";
var language = {
    "processing": PROCESSING_CONTENT,
    "lengthMenu": "每页显示 _MENU_ 条记录 ",
    "zeroRecords": "无数据.",
    "info": "当前 _PAGE_ 页/共 _PAGES_ 页   总计  _TOTAL_ 条记录",
    "infoEmpty": "总计  0  条记录",
    "infoFiltered": "共  _MAX_ 条记录",
    "infoPostFix": "",
    "search": "表内查找",
    "url": "",
    "emptyTable": "无数据.",
    "paginate": {
        "first": "首页",
        "previous": "上一页",
        "next": "下一页",
        "last": "尾页"
    }
};

function JFormatGrid(id){
    var t = $('#'+id).DataTable();
    $('a.toggle-vis').on('click', function(e) {
        e.preventDefault();
        var column = t.column($(this).attr('data-column'));
        column.visible(!column.visible());
    });
    //追加行号
    t.on('order.dt search.dt draw.dt', function() {
        t.column(1, {
            search : 'applied',
            order : 'applied',
            draw : 'applied'
        }).nodes().each(function(cell, i) {
            cell.innerHTML = i + 1;
        });
    }).draw();
}

$(document).ready(function() {
    if(JIsFunction('searchResource')){
        searchResource();
    }

    JAddButtonEvent('btnToUpdate',toUpdate);
    JAddButtonEvent('btnSearch',searchResource);
    JAddButtonEvent('btnDelete',deleteResource);

    //判断是否存在，如果存在则，添加如下方法
    if($('#global_filter').length > 0){
        $('#global_filter').attr('placeholder','实时检索 (CTRL+L 清空)');

        $('#global_filter').on('keyup click', function () {
            $('#datagrid').DataTable().search($('#global_filter').val(),true,true).draw();
        });
    }

    document.onkeydown = function(event) {
        var e = event || window.event || arguments.callee.caller.arguments[0];
        //alert(e.keyCode);
        //执行：ctrl+回车
        if (e && e.keyCode == 13 && e.ctrlKey) {
            //execute();
        }
        //清空: ctrl+退格
        if (e && e.keyCode == 8 && e.ctrlKey) {
            //clearForm();
        }
        //清空: ctrl+l
        if (e && e.keyCode == 76 && e.ctrlKey) {
            if($('#global_filter').length > 0){
                //清空文本框的内容
                $('#global_filter').val('');
                //恢复原始内容
                $('#global_filter').click();
            }
        }
    };
});;

//是否存在指定函数
function JIsFunction(funcName) {
    try {
        if (typeof(eval(funcName)) == "function") {
            return true;
        }
    } catch(e) {}
    return false;
}

function JAddButtonEvent(id,func){
    //判断是否存在
    if($('#'+id).length > 0){
        $('#'+id).click(function(){
            func()
        });
    }
}

var JCheckBoxTitle = "<div style='text-align:center'><input type='checkbox' name='dataTables.row.checkAll' id='dataTables.row.checkAll' onclick='JCheckAll()'/></div>";
function JFormartCheckBox(data) {
    return "<div style='text-align:center'><input name='dataTables.row.ckbox.id' type='checkbox' value="+data+"></div>";
}
function JCheckAll() {
    var chk = document.getElementById("dataTables.row.checkAll");
    chk.checked = !chk.checked;
    var inputObj = $("input[name='dataTables.row.ckbox.id']");
    for (var i = 0; i < inputObj.length; i++) {
        var temp = inputObj[i];
        if (temp.type == "checkbox") {
            temp.checked = chk.checked;
        }
    }
}
function JGetSelected(){
    var inputObj = $("input[name='dataTables.row.ckbox.id']");
    if($("input[name='dataTables.row.ckbox.id']").is(':checked') == false){
        $.messager.alert('提示信息', '请选择需要删除的记录!', 'warning');
        return;
    }
    var selectedRowData = "";
    var selectData = '';
    for (var i = 0; i < inputObj.length; i++) {
        var temp = inputObj[i];
        if (temp.type == "checkbox" && temp.checked == true) {
            selectData += "将要删除的资源 :"+temp.value+ "<br>";
            selectedRowData+=temp.value+",";
        }
    }
    return {"ids":selectedRowData,"tips":selectData};
}