<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%@include file="../common/init.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=contextPath%>/themes/css/right.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/themes/css/blueStyle.css" />

<html>
	<head>
	<style type="text/css">  
	.table_content_td_header{
		padding-left:5px;
		text-align:left;
		color:#666
	}
	</style>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	 
	 <script type="text/javascript">
	 	//保存
	 	function tsmSave(){
	 		if(validate()){
	 			document.forms[0].submit();
	 		}
	 	}
	 	
	 	function otherModify(e){
	 		window.location='<%=contextPath %>/jsp/web/emp/other.jsp?id=';
	 	}
	 	function back(e){
	 		window.history.go(-1);
	 	}
	 	
	 	
	 	//是否入历史表
	    function openHisPage(e) {
	        /*mini.open({
	            url: "<%=contextPath%>/jsp/web/emp/history.jsp",
	            showMaxButton: false,
	            title: "选择树",
	            width: 320,
	            height: 350,
	            ondestroy: function (action) { 
	            	alert(1111);
	                if (action == "ok") {
	                    var iframe = this.getIFrameEl();
	                    var data = iframe.contentWindow.GetData();
	                    data = mini.clone(data);
	                }
	            }
	        });*/  
	        JOpenAutoPage('<%=contextPath%>/jsp/web/emp/history.jsp', '修改个人信息','400','550');
	    }
	 	
	 	//验证
	 	function validate(){
	 		return true;
	 	}
	 	
	 	//选择部门
	    function selDpt(e) {
	        mini.open({
	            url: "<%=contextPath%>/jsp/web/emp/dptTree.jsp?id=",
	            showMaxButton: false,
	            title: "选择树",
	            width: 320,
	            height: 450,
	            ondestroy: function (action) {   
	                if (action == "ok") {
	                    var iframe = this.getIFrameEl();
	                    var data = iframe.contentWindow.GetData();
	                    data = mini.clone(data);
	                    if (data) {
	                    	JSendAjax('<%=contextPath%>/dpt.do?cmd=getInfo',data);
	                        $('#dptId').val(data.id);
	                        $('#dptName').val(data.resname);
	                        mini.get('miniDptNames').setText(data.resname);
	                        //mini.get('salaryArea').setText(data.hesuandi);
	                        $('#salaryArea').combobox("setValue",data.hesuandi);
	                    }
	                }
	            }
	        });            
	    }    
	
	 	//遍历json对象
	 	function traverseJson(json){
	       var st='';
	       $.each(json,function(name,value) {
	           st += name+":"+value+","; 
	       });
	       return st;
	   }
	 	//回调函数
	    function JCollBack(_data,_textStuats){
	        var info = $(_data).find('info').text();
	        var msg = $(_data).find('msg').text();
	        if(info == '0'){
	        	if(msg == 'success'){
	        		//var dptname = $(_data).find('dptname').text();//部门
	        		//var dptid = $(_data).find('dptid').text();//部门id
	        		
	        		var productLine2 = $(_data).find('row_n')[0].text;//二级产品中心
	        		var productLine1 = $(_data).find('row_n')[1].text;//一级产品中心
	        		var unit = $(_data).find('row_n')[2].text;//单位
	        		var tsm = $(_data).find('row_n')[3].text;//集团
	
	
	                $('#productLine1').val(productLine1);
	                $('#productLine2').val(productLine2);
	                $('#relationship').val(unit);
	        	}
	        }
	        if(info == '-1'){
	            alert('错误信息: '+msg);return;
	        }
	    }
	 	//给子页面提供数据
	    function getFormData(e){
	         var dptName = mini.get('miniDptNames').getText();
	         var station = $('#station').combobox("getValue");
	         var name = $("#resName").val();
	         return name+","+dptName+","+station+","+$("#relationship").val();
	    }
	 </script>
</head>
<body>
	<form action="<%=contextPath %>/eg.do?cmd=update" name="form1" method="post">
	<input class="txt_input_underline" maxlength="20"  type="hidden" name="id" id="id" value=""/>
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td >
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="15"  class="td_left_top_table"></td>
							<td class="td_middle_table" valign="middle"><div class="ico_win">员工信息</div></td>
							<td class="td_middle_table">
								<div class="title_oper">
									
								</div>
							</td>
							<td width="14" class="td_right_top_table"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
			  <table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="9" class="td_left_table_content">&nbsp;</td>
						  	<td class="td_global">
                              	<div style="height:8px;"></div>
								<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_content">
									<tr>
										<td height="26" colspan="7" class="table_content_title_td"><div class="table_content_td_header">基本信息</div></td>
									</tr>
									<tr>
										<td width="120" class="table_content_td">姓名</td>
									    <td class="table_content_td_left" ><input class="txt_input"  maxlength="20"  name="emp.resName" id="resName" value=""/> </td>
                                        <td class="table_content_td">编号</td>
										<td class="table_content_td_left"><input class="txt_input"  maxlength="20"  name="emp.no" id="no" value=""/> </td>
                                        <td class="table_content_td">出生年月</td>
										<td class="table_content_td_left"><input class="txt_input_date txt_input" onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" name="emp.datebirth" id="datebirth" value=""/> </td>
                                        <td rowspan="5" width="100" class="table_content_td" style="padding:8px">
								    	<img width="100" height="120" src="<%=contextPath %>/dataImport.do?cmd=downloadByEmpId&id="/>
                                        </td>
								  </tr>
									<tr>
										<td class="table_content_td" >性别</td>
									  	<td class="table_content_td_left" >
                                        	<input class="easyui-combobox txt_input" name="emp.sex" id="sex" value=""
												data-options="
														valueField: 'label',
														textField: 'value',
														editable: false, 
														data: [{label: '男',value: '男'},{label: '女',value: '女'}], 
														panelHeight:'auto'" />
                                      </td>
									  <td class="table_content_td" >婚否</td>
									  <td class="table_content_td_left" >
                                        <input class="easyui-combobox txt_input"  name="emp.marital" id="marital" value=""
												data-options="
														valueField: 'label',
														textField: 'value',
														editable: false, 
														data: [{label: '已婚',value: '已婚'},{label: '未婚',value: '未婚'}], 
														panelHeight:'auto'" />  
                                      </td>
									  <td class="table_content_td" >年龄</td>
									  <td class="table_content_td_left" >
									  	  <input class="easyui-numberbox txt_input" required data-options="" name="emp.age" id="age" value=""/>
									  </td>
								  	</tr>
									<tr>
									  <td class="table_content_td" >身份证号</td>
									  <td class="table_content_td_left" ><input class="txt_input" maxlength="20"  name="emp.idno" id="idno" value=""/></td>
									  <td class="table_content_td" >民族</td>
									  <td class="table_content_td_left" ><input class="txt_input" maxlength="20"  name="emp.nation" id="nation" value=""/></td>
									  <td class="table_content_td" >政治面貌</td>
									  <td class="table_content_td_left" ><input class="txt_input" maxlength="20"  name="emp.political" id="political" value=""/></td>
								  	</tr>
									<tr>
									  <td class="table_content_td" >户口所在地</td>
									  <td class="table_content_td_left" ><input class="txt_input" maxlength="20"  name="emp.idAddress" id="idAddress" value=""/></td>
									  <td class="table_content_td" >档案所在地</td>
									  <td class="table_content_td_left" ><input class="txt_input" maxlength="40"  name="emp.archiveAddress" id="archiveAddress" value=""/></td>
									  <td class="table_content_td" >子女状况</td>
									  <td class="table_content_td_left" ><input class="txt_input" maxlength="20"  name="emp.childrenInfo" id="childrenInfo" value=""/></td>
								 	 </tr>
									<tr>
									  <td class="table_content_td" >家庭电话</td>
									  <td class="table_content_td_left" ><input class="txt_input" maxlength="20"  name="emp.homePhone" id="homePhone" value=""/></td>
									  <td class="table_content_td" >家庭住址</td>
									  <td class="table_content_td_left" ><input class="txt_input" maxlength="40"  name="emp.archiveAddress" id="archiveAddress" value=""/></td>
									  <td class="table_content_td" >&nbsp;</td>
									  <td class="table_content_td" >&nbsp;</td>
								  	</tr>
									<tr>
									  <td class="table_content_td" >现联系方式</td>
									  <td class="table_content_td_left" ><input class="txt_input" maxlength="40"  name="emp.curPhone" id="curPhone" value=""/></td>
									  <td class="table_content_td" >现住址</td>
									  <td class="table_content_td_left" ><input class="txt_input" maxlength="40"  name="emp.curtAddress" id="curtAddress" value=""/></td>
									  <td class="table_content_td" >&nbsp;</td>
									  <td class="table_content_td" >&nbsp;</td>
									  <td class="table_content_td" >&nbsp;</td>
								  </tr>
								</table>
       			      			<div style="height:8px;"></div>
                               <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_content">
									<tr>
										<td height="26" colspan="6" class="table_content_title_td"><div class="table_content_td_header">工作信息</td>
									</tr>
								   <tr>
									  <td class="table_content_td" >隶属关系</td>
									  <td class="table_content_td_left" >
                                      	<input class="txt_input" maxlength="20" readonly="readonly"  name="emp.relationship" id="relationship" value="" />
									  </td>
									  <td class="table_content_td" >一级产品线/中心</td>
									  <td class="table_content_td_left" ><input class="txt_input" maxlength="20" readonly="readonly"  name="emp.productLine1" id="productLine1" value="" /></td>
									  <td class="table_content_td" >二级产品线/中心</td>
									  <td class="table_content_td_left" ><input class="txt_input" maxlength="20" readonly="readonly" name="emp.productLine2" id="productLine2" value=""/></td>
								 	</tr>
									<tr>
									  <td width="120" class="table_content_td">部门</td>
									  <td width="220" class="table_content_td_left" >
									  		<input id="miniDptNames" text="" class="mini-buttonedit" style="width: 162px;" onbuttonclick="selDpt"/>
											<input class="txt_input" name="emp.dptId" type="hidden"  id="dptId" value=""/>
											<input class="txt_input" name="emp.dptName" type="hidden"  id="dptName" value=""/> 
									  </td>
									  <td class="table_content_td" >组别</td>
									  <td class="table_content_td_left" >
                                        	<input class="easyui-combobox txt_input" 
												name="emp.groupName"
												id="groupName"
												value=""  
												data-options="
														url:'<%=contextPath %>/ajax.do?cmd=list&type=&key=&limit=true',
														method:'get',
														valueField:'resname',
														textField:'resname', 
														mode:'remote',
														panelHeight:'auto'
												"/>
                                      </td>
									  <td class="table_content_td" >岗位</td>
									  <td class="table_content_td_left" >
                                      	<input class="easyui-combobox txt_input" name="emp.station" id="station" value=""  
												data-options="
														url:'<%=contextPath %>/ajax.do?cmd=list&type=&key=&limit=true',
														method:'get',
														valueField:'resname',
														textField:'resname', 
														mode:'remote',
														panelHeight:'auto'
												"/>
                                      </td>
                                	</tr>
									<tr>
									  <td class="table_content_td" >人员分类</td>
									  <td class="table_content_td_left" >
                                        <input class="easyui-combobox txt_input" name="emp.personType" id="personType" value=""  
												data-options="
														url:'<%=contextPath %>/ajax.do?cmd=list&type=&key=&limit=true',
														method:'get',
														valueField:'resname',
														textField:'resname', 
														mode:'remote',
														panelHeight:'auto'"
												/>
                                      </td>
									  <td width="120" class="table_content_td">分支机构类型</td>
										<td width="220" class="table_content_td_left"><input class="txt_input" maxlength="20"  name="emp.branchOrganType" id="branchOrganType" value=""/></td>
                                        <td width="120" class="table_content_td"></td>
										<td class="table_content_td_left">
										<input class="txt_input" style="display: none" maxlength="20" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" name="emp.salary" id="salary" value=""/> 
									   </td>
								   </tr>
									<tr>
									  <td class="table_content_td" >工作地点</td>
									  <td class="table_content_td_left" >
                                      <input class="txt_input" name="emp.workPlace1" id="workPlace1" text="" valueField="resname" textField="resname" 
										url="<%=contextPath %>/ajax.do?cmd=list&type=" />
                                      </td>
									  <td class="table_content_td" >工作地点（二级）</td>
									  <td class="table_content_td_left" >
									  <input class="txt_input" name="emp.workPlace2" id="workPlace2" text="" valueField="resname" textField="resname" 
										url="<%=contextPath %>/ajax.do?cmd=list&type="/>
									  </td>
									  <td class="table_content_td" >工资核算地</td>
									  <td class="table_content_td_left" >
										 <input class="easyui-combobox txt_input" 
												name="emp.salaryArea" id="salaryArea" value=""
												data-options="
														valueField: 'label',
														textField: 'value',
														editable: false, 
														data: [{label: '总部',value: '总部'},{label: '分公司',value: '分公司'}], 
														panelHeight:'auto'" />  
									  </td>
								  </tr>
								  <tr>
									  <td width="120" class="table_content_td">入职时间</td>
									  <td width="220" class="table_content_td_left" >
									  		<input class="txt_input_date txt_input"  name="emp.entryTime" id="entryTime" value=""  onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" /> 
									  </td>
                                      <td width="120" class="table_content_td">转正时间</td>
										<td width="220" class="table_content_td_left"><input class="txt_input_date txt_input"  name="emp.realTime" id="realTime" value=""  onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" /></td>
                                        <td width="120" class="table_content_td">员工类型</td>
										<td class="table_content_td_left">
	                                        <input class="easyui-combobox txt_input"  name="emp.empArea" id="empArea" value=""
												data-options="
														valueField: 'label',
														textField: 'value',
														editable: false, 
														data: [{label: '非外籍',value: '非外籍'},{label: '外籍',value: '外籍'}], 
														panelHeight:'auto'" />
										</td>
                                	</tr>
								</table>
                            	<div style="height:8px;"></div>
								<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_content">
									<tr>
										<td height="26" colspan="6" class="table_content_title_td"><div class="table_content_td_header">学历信息</div></td>
									</tr>
									<tr>
										<td width="120" class="table_content_td">学历</td>
										<td width="220" class="table_content_td_left" > 
                                          <input class="easyui-combobox txt_input"  name="emp.edu" id="edu" value="" 
												data-options="
														url:'<%=contextPath %>/ajax.do?cmd=list&type=&key=&limit=false',
														method:'get',
														valueField:'resname',
														textField:'resname', 
														mode:'remote',
														panelHeight:'auto'"
												/>
                                      </td>
                                        <td width="120" class="table_content_td">毕业院校</td>
									  <td width="220" class="table_content_td_left"><input class="txt_input" maxlength="20"  name="emp.graduateSchool" id="graduateSchool" value=""/> </td>
                                        <td width="120" class="table_content_td">专业</td>
									  <td class="table_content_td_left"><input class="txt_input" maxlength="20"  name="emp.professional" id="professional" value=""/> </td>
                                </tr>
									<tr>
									  <td class="table_content_td" >参加工作时间</td>
									  <td class="table_content_td_left" ><input class="txt_input_date txt_input"  name="emp.participateTime" id="participateTime" value="" onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" /></td>
									  <td class="table_content_td" >技术职称</td>
									  <td class="table_content_td_left" >
									  <input class="txt_input" name="emp.technicalName" id="technicalName" text="" valueField="resname" textField="resname" 
										url="<%=contextPath %>/ajax.do?cmd=list&type=" style="width: 160px;" />
									  </td>
									  <td class="table_content_td" >毕业时间</td>
									  <td class="table_content_td_left" ><input class="txt_input" maxlength="20"   name="emp.graduateTime" id="graduateTime" value=""/></td>
								    </tr>
							</table>
                                 <div style="height:8px;"></div>
                              <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_content">
									<tr>
										<td height="26" colspan="6" class="table_content_title_td"><div class="table_content_td_header">合同信息</td>
									</tr>
									<tr>
										<td width="120" class="table_content_td">合同起始日期</td>
									  	<td width="220" class="table_content_td_left" ><input class="txt_input_date txt_input"  name="emp.contractStartTime" id="contractStartTime" value=""  onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" /></td>
                                        <td width="120" class="table_content_td">合同截至日期</td>
									  	<td width="220" class="table_content_td_left"><input class="txt_input_date txt_input"  name="emp.contractEndTime" id="contractEndTime" value=""  onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" /></td>
                                        <td width="120" class="table_content_td">服务年限</td>
									 	<td class="table_content_td_left"><input class="txt_input" maxlength="20"  name="emp.serviceYear" id="serviceYear" value="" /> </td>
                                </tr>
									<tr>
									  <td class="table_content_td" >服务年限分类</td>
									  <td class="table_content_td_left" ><input class="txt_input" maxlength="20"  name="emp.serviceYearType" id="serviceYearType" value=""/></td>
									  <td class="table_content_td" >续签次数</td>
									  <td class="table_content_td_left" >
									  	<input class="easyui-numberbox txt_input" required data-options="" name="emp.renewalCount" id="renewalCount" value=""/>
									  </td>
									  <td class="table_content_td" >合同是否到期</td>
									  <td class="table_content_td_left" >
                                      	<input class="easyui-combobox txt_input"  name="emp.isContractEnd" id="isContractEnd" value=""
												data-options="
														valueField: 'label',
														textField: 'value',
														editable: false, 
														data: [{label: '是',value: '是'},{label: '否',value: '否'}], 
														panelHeight:'auto'" />
                                      </td>
								  </tr>
							</table>
                            <div style="height:8px;"></div>
                            <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_content">
								  <tr>
									  <td height="26" colspan="6" class="table_content_title_td"><div class="table_content_td_header">其他</td>
								  </tr>
								  <tr>
									<td width="120" class="table_content_td">离职时间</td>
								    <td width="220" class="table_content_td_left" ><input class="txt_input_date txt_input"  name="emp.leaveTime" id="leaveTime" value=""  onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" /> </td>
                                    <td width="120" class="table_content_td">离职原因简述</td>
								    <td width="220" class="table_content_td_left"><input class="txt_input" maxlength="40"  name="emp.leaveReason" id="leaveReason" value=""/> </td>
                                    <td width="120" class="table_content_td">离职后去向</td>
								    <td class="table_content_td_left"><input class="txt_input" maxlength="20"  name="emp.leaveWhere" id="leaveWhere" value=""/> </td>
                              	</tr>
								<tr>
								    <td class="table_content_td" >可否再次雇佣</td>
								    <td class="table_content_td_left" >
                                        <input class="easyui-combobox txt_input" name="emp.reEmploy" id="reEmploy" value=""
												data-options="
														valueField: 'label',
														textField: 'value',
														editable: false, 
														data: [{label: '是',value: '是'},{label: '否',value: '否'}], 
														panelHeight:'auto'" />
                                    </td>
								    <td class="table_content_td" >备注</td>
								    <td colspan="1" class="table_content_td_left" >
								    	<input class="txt_input" maxlength="40"  name="emp.remark" id="remark" value=""/>
								    </td>
								    <td class="table_content_td" >状态</td>
								    <td colspan="1" class="table_content_td_left" >
                                        <input class="easyui-combobox txt_input"  name="emp.state" id="state" value=""
												data-options="
														valueField: 'label',
														textField: 'value',
														editable: false, 
														data: [{label: '入职',value: '入职'},{label: '正式',value: '正式'},{label: '离职',value: '离职'}], 
														panelHeight:'auto'" />
								    </td>
							    </tr>
								
								<tr>
								    <td class="table_content_td" >告知书</td>
								    <td colspan="5" class="table_content_td_left" ></td>
						      	</tr>
								<tr>
								    <td class="table_content_td" >&nbsp;</td>
								    <td height="29" colspan="5" align="center" class="table_content_td" >
									    <a class="mini-button" onclick="openHisPage">保存</a>
									    <a class="mini-button" onclick="otherModify">其他修改</a>
									    <a class="mini-button" onclick="back">返回</a>
								    </td>
						      </tr>
						    </table>
				      		</td>
							<td width="9" class="td_right_table_content">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" height="29">
						<tr>
							<td width="15" class="td_left_buttom_table"></td>
							<td class="td_middle_buttom_table">&nbsp;</td>
							<td width="14" class="td_right_buttom_table"></td>
						</tr>
					</table></td>
			</tr>
		</table><br />
</body>
</html>
  
 
 
 

	