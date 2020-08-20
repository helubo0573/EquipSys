package com.stone.equipsys.core.common.util;

public class Page {
	
	private int showCount; //每页显示记录数
	private int totalPage;		//总页数
	private int totalResult;	//总记录数
	private int currentPage;	//查询页码
	private int currentResult;	//查询起始索引
	private int endResult;		//list的分页结束索引
	private boolean entityOrField;	//true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性
	private String pageStr;		//最终页面显示的底部翻页导航，详细见：getPageStr();


	
	public Page(){
		try {
			this.showCount = 12;
		} catch (Exception e) {
			this.showCount = 12;
		}
	}
	
	/**
	 * 带参的page构造方法
	 * @param totalresult  	总记录数
	 * @param showcount  	每页显示记录数
	 * @param currentpage	当前需要查询的页码
	 */
	public Page(int totalresult,int showcount,int currentpage){
		this.setTotalResult(totalresult);
		this.setShowCount(showcount);
		this.setTotalPage(totalresult%showcount==0?totalresult/showcount:totalresult/showcount+1);
		this.setCurrentPage(currentpage);
		this.setCurrentResult((currentpage-1)*showcount);		
		if(totalresult>showcount*currentpage){
			this.setEndResult(showcount*currentpage);
		}else{
			this.setEndResult(totalresult);

		}
	}
	
	public int getTotalPage() {
		if(totalResult%showCount==0)
			totalPage = totalResult/showCount;
		else
			totalPage = totalResult/showCount+1;
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getTotalResult() {
		return totalResult;
	}
	
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}
	
	public int getCurrentPage() {
		if(currentPage<=0)
			currentPage = 1;
		if(currentPage>getTotalPage())
			currentPage = getTotalPage();
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	/**
	 * 由于复杂度问题，在后台生成换页代码
	 * @param getpagemethodname 动态控制页面翻页的函数名称
	 * */
	public String getPageStr(String getpagemethodname) {
		StringBuffer sb = new StringBuffer();
		if(totalResult>0){
			sb.append("	<ul class=\"pagination\" style=\"margin-top:0px;margin-bottom:0px;\">\n");
			if(currentPage==1){
				sb.append("	<li><a>共<font color=red>"+totalResult+"</font>条</a></li>\n");
				sb.append("	<li><a>首页</a></li>\n");
				sb.append("	<li><a>上页</a></li>\n");
			}else{
				sb.append("	<li><a>共<font color=red>"+totalResult+"</font>条</a></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\""+getpagemethodname+"(1)\">首页</a></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\""+getpagemethodname+"("+(currentPage-1)+")\">上页</a></li>\n");
			}
			int showTag = 5;//分页标签显示数量
			int startTag = 1;
			if(currentPage>showTag){
				startTag = currentPage-1;
			}
			int endTag = startTag+showTag-1;
			for(int i=startTag; i<=totalPage && i<=endTag; i++){
				if(currentPage==i)
					sb.append("<li><a><font color='#808080'>"+i+"</font></a></li>\n");
				else
					sb.append("	<li style=\"cursor:pointer;\"><a onclick=\""+getpagemethodname+"("+i+")\">"+i+"</a></li>\n");
			}
			if(currentPage==totalPage){
				sb.append("	<li><a>下页</a></li>\n");
				sb.append("	<li><a>尾页</a></li>\n");
			}else{
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\""+getpagemethodname+"("+(currentPage+1)+")\">下页</a></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\""+getpagemethodname+"("+totalPage+")\">尾页</a></li>\n");
			}
			sb.append("	<li><a>第"+currentPage+"页</a></li>\n");
			sb.append("	<li><a>共"+totalPage+"页</a></li>\n");
			sb.append("</ul>\n");
		}
		pageStr = sb.toString();
		return pageStr;
	}
	/**
	 * 生成简单的翻页内容
	 * @param getpagemethodname
	 * @return
	 */
	public String getsimplePageStr(String getpagemethodname) {
		StringBuffer sb = new StringBuffer();
		if(totalResult>0){
			sb.append("	<ul class=\"pagination\" style=\"margin-top:0px;margin-bottom:0px;\">\n");
			if(currentPage==1){
				sb.append("	<li><a>上页</a></li>\n");
			}else{
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\""+getpagemethodname+"("+(currentPage-1)+")\">上页</a></li>\n");
			}
			int showTag = 3;//分页标签显示数量
			int startTag = 1;
			if(currentPage>showTag){
				startTag = currentPage-1;
			}
			int endTag = startTag+showTag-1;
			for(int i=startTag; i<=totalPage && i<=endTag; i++){
				if(currentPage==i)
					sb.append("<li><a><font color='#808080'>"+i+"</font></a></li>\n");
				else
					sb.append("	<li style=\"cursor:pointer;\"><a onclick=\""+getpagemethodname+"("+i+")\">"+i+"</a></li>\n");
			}
			if(currentPage==totalPage){
				sb.append("	<li><a>下页</a></li>\n");
			}else{
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\""+getpagemethodname+"("+(currentPage+1)+")\">下页</a></li>\n");
			}
			sb.append("	<li><a>共"+totalPage+"页</a></li>\n");		
			sb.append("</ul>\n");
		}
		pageStr = sb.toString();
		return pageStr;
	}
	public void setPageStr(String pageStr) {
		this.pageStr = pageStr;
	}
	
	public int getShowCount() {
		return showCount;
	}
	
	public void setShowCount(int showCount) {
		
		this.showCount = showCount;
	}
	
	public int getCurrentResult() {
		currentResult = (getCurrentPage()-1)*getShowCount();
		if(currentResult<0)
			currentResult = 0;
		return currentResult;
	}
	
	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}
	
	public boolean isEntityOrField() {
		return entityOrField;
	}
	
	public void setEntityOrField(boolean entityOrField) {
		this.entityOrField = entityOrField;
	}
	
	public int getEndResult() {
		return endResult;
	}

	public void setEndResult(int endResult) {
		this.endResult = endResult;
	}
	
}
