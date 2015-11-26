package com.t2t.top.base.utils;


import java.util.List;
import java.util.ArrayList;

/**
 * 分页mapping
 */
public class PageInfos {

	private String propertyName;

	private String propertyValue;

	private static final long serialVersionUID = 7873852623469564487L;

	public Long totalRows = 0l;// 总的记录数 如:共100条记录

	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Long.MAX_VALUE);
	}

	public List pageList = new ArrayList(0);

	public Integer startIndex = 0; // 当前页面第一条记录数

	public Integer currentPage = 1; // 当前页面 如:当前第1页

	public Integer totalPages = 1; // 总的页面数 如:共10页

	public Integer perPageRows = 15;// 每一页显示的记录数 (可以通过set方法来进行修改)

	public boolean isFromDwr = false;

	public String html; // 分页查询按钮
	// 是不是实现分页

	private boolean showPages = true;
	// 数字控制
	private String js =

	" var positive= false;var negative = false;var maxValue =999999999;	var minValue = -999999999; var dft="+perPageRows+" ;	function isEmpty(input){  return (input==null||input.length==0)	}"
			+ " function isInt(input){ if(isEmpty(input)||(isNaN(input))) { return false; }  return(!isNaN(parseInt(input,10)));} "
			+ "  function toInt(input){return (isInt(input)) ? parseInt(input,10) : dft;}  function doBlur(element){  var tmp = toInt(element.value); "
			+ " if(element.positive=='true'){  tmp=Math.abs(tmp);   if(element.maxValue!=null){  var tmpMax = toInt(element.maxValue);   if(tmp>tmpMax){tmp=tmpMax; } } element.value = Math.abs(tmp);return;} "
			+ " if(element.negative=='true'){  tmp=Math.abs(tmp)*-1; if(element.minValue!=null){ var tmpMin = toInt(element.minValue);   if(tmp<tmpMin){tmp=tmpMin; }} "
			+ "  element.value = Math.abs(tmp)*-1; return;} if(element.maxValue!=null){var tmpMax = toInt(element.maxValue);   if(tmp>tmpMax){tmp=tmpMax;}} "
			+ "  if(element.minValue!=null){ var tmpMin = toInt(element.minValue);  if(tmp<tmpMin){ tmp=tmpMin; }}element.value = toInt(tmp);	return;} ";

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPerPageRows() {
		return perPageRows;
	}

	public void setPerPageRows(Integer perPageRows) {
		if (perPageRows > 0)
			this.perPageRows = perPageRows;
	}

	public boolean isShowPages() {
		return showPages;
	}

	public void setShowPages(boolean showPages) {
		this.showPages = showPages;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Long totalRows) {
		this.totalRows = totalRows;
		// 计算出现在的总的页数
		this.totalPages = (int) Math.ceil((this.totalRows - 1) / this.perPageRows) + 1;
		if (this.currentPage > this.totalPages) {
			this.currentPage = 1;
		}
	}

	public List getPageList() {
		return pageList;
	}

	public void setPageList(List pageList) {
		this.pageList = pageList;
	}

	public Integer getStartIndex() {
		this.setStartIndex((currentPage - 1) * perPageRows);
		return startIndex;
	}

	private void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	/**
	 * 
	 * @return 分页查询按钮
	 */
	public String getHtml() {
		if (!showPages)
			return "&nbsp;";
		StringBuffer string = new StringBuffer();
		string.append("<script>\n");
		string.append(js);
		string.append("function go(num,obj){\n");
		string.append("if(num==null||num==''){alert('请输入页码！');return;}");
		string.append(" var pageform = window.document.getElementById('pageform');\n");
		string.append(" pageform.action=pageform.action+'?currentPage='+num;\n");
		string.append(" pageform.submit();$('#loading').show();for(var i = 0; i < pageform.length; i++) { pageform[i].disabled = true; }\n");
		string.append("}\n");
		string.append("function goto(thisPage,totalPage,obj){\n");
		//string.append(" var allPage = '<%=request.getAttribute('totalPages')%>'; \n");
		string.append(" var gotoPageObject = window.document.getElementById('gotoPage');\n");
		string.append(" var gotoPage = gotoPageObject.value ;\n");
		string.append(" var reg =/^[0-9]*$/;\n");
		string.append(" if(!reg.test(gotoPage)){\n");
		string.append("     gotoPageObject.focus();\n");
		string.append("     alert('你输入的页码有误请重新输入!');\n");
		string.append("		gotoPageObject.value=1;\n");
		string.append("		return ; \n");
		string.append(" }\n");
		string.append(" else if(gotoPage > "+totalPages+"){\n");
		string.append("		alert('你输入的页码大于最大页，请重新输入！');\n");
		string.append("		return ; \n");
		string.append(" }\n");
		string.append(" go(gotoPage,obj);\n");
		string.append("}\n");
		string.append("</script>\n");
		// 当前为第一页

		string.append("每页记录");
		string.append("<input type='text' onblur='doBlur(this);' form='pageform' name='perPageRows' id='perPageRows' value='");
		string.append(perPageRows);
		string.append("' style='width:30px;'/>");
		string.append("条");
		if (currentPage == 1) {
			string.append("<input title='首页' type='button' class='homepage'  value='' disabled/>");
			string.append("<input title='上一页' type='button' class='prepage'  value='' disabled/>");
			string.append("&nbsp;");
		} else {
			string.append("<input title='首页' type='button' class='homepage'  value='' onClick='go(1,this);' />");
			string.append("<input title='上一页' type='button' class='prepage'  value='' onClick='go(");
			string.append(currentPage - 1);
			string.append(",this)'/>&nbsp;");
		}
		// 当前为最后一页


		if (currentPage.equals(totalPages)) {
			
			string.append("<input title='下一页' type='button' class='nextpage'  value='' disabled/>");
			string.append("<input title='尾页' type='button' class='lastpage'  value='' disabled/>");
		} else {

			string.append("<input title='下一页' type='button' class='nextpage'  value='' onClick='go(");
			string.append(currentPage + 1);
			string.append(",this)'/>");
			string.append("<input type='button' class='lastpage'  value='' onClick='go(");
			string.append(totalPages);
			string.append(",this)'/>&nbsp;");
		}
		string.append("&nbsp;");
		string.append("<input type='text' id='gotoPage' class='int' value='");
		string.append(currentPage);
		string.append("' style='width:30px;'/>&nbsp;&nbsp;<input title='跳转到' type='button' class='gopage'   style='width:30px;' value='' onClick='goto(");
		string.append(currentPage);
		string.append(",");
		string.append(totalPages);
		string.append(",this)'/>");
		string.append("&nbsp;当前第");
		string.append(currentPage);
		string.append("页/共");
		string.append(totalPages);
		string.append("页&nbsp;总共记录数");
		string.append(totalRows);
		string.append("条&nbsp;");
		setHtml(string.toString());
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public boolean isFromDwr() {
		return isFromDwr;
	}

	public void setFromDwr(boolean isFromDwr) {
		this.isFromDwr = isFromDwr;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
}
