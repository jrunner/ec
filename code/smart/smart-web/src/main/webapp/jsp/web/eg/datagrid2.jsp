<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%@include file="../common/init.jsp"%>

<head>
</head>
<body  class="easyui-layout" data-options="fit:true,border:false">
	<div id="divTest" style="width: 98%;margin-left: 8px;margin-top: 10px;"> 
		<table id="dg" title="Client Side Pagination" style="height:500px" align="center" data-options="
					rownumbers:true,
					singleSelect:true,
					autoRowHeight:false,
					toolbar: '#tb',
					pagination:false,
					pageSize:100">
			<thead>
				<tr>
					<th field="inv" width="80">Inv No</th>
					<th field="date" width="80">Date</th>
					<th field="name" width="80">Name</th>
					<th field="amount" width="80" align="right">Amount</th>
					<th field="price" width="80" align="right">Price</th>
					<th field="cost" width="80" align="right">Cost</th>
					<th field="note" width="80">Note</th>
				</tr>
			</thead>
		</table>
		<div id="tb" style="height:auto">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">Append</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="insert()">Insert</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="modify()">Modify</a>
		</div>
	</div>

	<script> 
		$(window).resize(function(){
		 	$('#dg').datagrid('resize');
		});
		 
		function getData(){
			var rows = [];
			for(var i=1; i<=2; i++){
				var amount = Math.floor(Math.random()*1000);
				var price = Math.floor(Math.random()*1000);
				rows.push({
					inv: 'Inv No '+i,
					date: $.fn.datebox.defaults.formatter(new Date()),
					name: 'Name '+i,
					amount: amount,
					price: price,
					cost: amount*price,
					note: 'Note '+i
				});
			}
			return rows;
		}
		
		function pagerFilter(data){
			if (typeof data.length == 'number' && typeof data.splice == 'function'){	// is array
				data = {
					total: data.length,
					rows: data
				}
			}
			var dg = $(this);
			var opts = dg.datagrid('options');
			var pager = dg.datagrid('getPager');
			pager.pagination({
				onSelectPage:function(pageNum, pageSize){
					opts.pageNumber = pageNum;
					opts.pageSize = pageSize;
					pager.pagination('refresh',{
						pageNumber:pageNum,
						pageSize:pageSize
					});
					dg.datagrid('loadData',data);
				}
			});
			if (!data.originalRows){
				data.originalRows = (data.rows);
			}
			var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
			var end = start + parseInt(opts.pageSize);
			data.rows = (data.originalRows.slice(start, end));
			return data;
		}
		
	
		$(function(){
			$('#dg').datagrid({loadFilter:pagerFilter}).datagrid('loadData', getData());
		});
		
		//追加
		function append(){
			var i = Math.floor(Math.random()*1000);
			var amount = Math.floor(Math.random()*1000);
			var price = Math.floor(Math.random()*1000);
			var row = {
				inv: 'Inv No '+i,
				date: $.fn.datebox.defaults.formatter(new Date()),
				name: 'Name '+i,
				amount: amount,
				price: price,
				cost: amount*price,
				note: 'Note '+i
			}
			
			$('#dg').datagrid('appendRow',row);
		}
		//添加到首条
		function insert(){
			var i = Math.floor(Math.random()*1000);
			var amount = Math.floor(Math.random()*1000);
			var price = Math.floor(Math.random()*1000);
			var row = {
				inv: 'Inv No '+i,
				date: $.fn.datebox.defaults.formatter(new Date()),
				name: 'Name '+i,
				amount: amount,
				price: price,
				cost: amount*price,
				note: 'Note '+i
			}
			
			$('#dg').datagrid('insertRow',{
				index: 0,	// 索引从0开始
				row: row
			});
		}
		//更新
		function modify(){
			var amount = Math.floor(Math.random()*1000);
			var price = Math.floor(Math.random()*1000);
			var row = {
				amount: amount,
				price: price,
				date: $.fn.datebox.defaults.formatter(new Date()),
				cost: amount*price
			}
			
			$('#dg').datagrid('updateRow',{
				index: 0,
				row: row
			});
		}
		
	
	$.fn.datebox.defaults.formatter = function(date){
        var y = date.getFullYear();
        var m = date.getMonth()+1;
        var d = date.getDate();
        return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
    };
    
	</script>
</body>
</html>