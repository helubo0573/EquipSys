package com.stone.equipsys.core.common.util;

import com.github.pagehelper.Page;

public class RdPage
{

	public static final int PAGE_NUM_DEFAULT = 1;

	public static final int PAGE_SIZE_DEFAULT = 10;

	private long total;

	private int pages;

	private int current;

	private int pageSize;

	// private int startRow;

	// private int endRow;

	public RdPage()
	{
		super();
	}

	public RdPage(Page<?> page)
	{
		this.total = page.getTotal();
		this.pages = page.getPages();
		this.current = page.getPageNum();
		this.pageSize = page.getPageSize();
		// this.startRow = page.getStartRow();
		// this.endRow = page.getEndRow();
	}

	public boolean hasPre()
	{
		if (pages > 1 && current > 1)
		{
			return true;
		}
		return false;
	}

	public boolean hasNext()
	{
		if (pages > 1 && current < pages)
		{
			return true;
		}
		return false;
	}

	public long getTotal()
	{
		return total;
	}

	public void setTotal(long total)
	{
		this.total = total;
	}

	public int getPages()
	{
		return pages;
	}

	public void setPages(int pages)
	{
		this.pages = pages;
	}

	public int getCurrent()
	{
		return current == 0 ? PAGE_NUM_DEFAULT : current;
	}

	public void setCurrent(int current)
	{
		this.current = current;
	}

	public int getPageSize()
	{
		return pageSize == 0 ? PAGE_SIZE_DEFAULT : pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}
	
	public String getPageStr(String getpagemethodname) {
		StringBuffer sb = new StringBuffer();
		if(this.total>0){
			sb.append("	<ul class=\"pagination\" style=\"margin-top:0px;margin-bottom:0px;\">\n");
			if(this.current==1){
				sb.append("	<li><a>共<font color=red>"+this.total+"</font>条</a></li>\n");
				sb.append("	<li><a>首页</a></li>\n");
				sb.append("	<li><a>上页</a></li>\n");
			}else{
				sb.append("	<li><a>共<font color=red>"+this.total+"</font>条</a></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\""+getpagemethodname+"(1)\">首页</a></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\""+getpagemethodname+"("+(this.current-1)+")\">上页</a></li>\n");
			}
			int showTag = 5;//分页标签显示数量
			int startTag = 1;
			if(this.current>showTag){
				startTag = this.current-1;
			}
			int endTag = startTag+showTag-1;
			for(int i=startTag; i<=this.pages && i<=endTag; i++){
				if(this.current==i)
					sb.append("<li><a><font color='#808080'>"+i+"</font></a></li>\n");
				else
					sb.append("	<li style=\"cursor:pointer;\"><a onclick=\""+getpagemethodname+"("+i+")\">"+i+"</a></li>\n");
			}
			if(this.current==this.pages){
				sb.append("	<li><a>下页</a></li>\n");
				sb.append("	<li><a>尾页</a></li>\n");
			}else{
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\""+getpagemethodname+"("+(this.current+1)+")\">下页</a></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\""+getpagemethodname+"("+this.pages+")\">尾页</a></li>\n");
			}
			sb.append("	<li><a>第"+this.current+"页</a></li>\n");
			sb.append("	<li><a>共"+this.pages+"页</a></li>\n");
			sb.append("</ul>\n");
		}
		return sb.toString();
	}
}
