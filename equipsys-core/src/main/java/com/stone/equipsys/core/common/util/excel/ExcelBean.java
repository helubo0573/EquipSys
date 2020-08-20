package com.stone.equipsys.core.common.util.excel;

import java.util.List;

public class ExcelBean
{

	private String title;

	private String creator;

	@SuppressWarnings("rawtypes")
	private List list;

	private String[] hearders;

	private String[][] children;

	private String[] fields;

	public ExcelBean()
	{
	}

	@SuppressWarnings("rawtypes")
	public ExcelBean(String title, String creator, List list, String[] hearders)
	{
		this.title = title;
		this.creator = creator;
		this.list = list;
		this.hearders = hearders;
	}

	@SuppressWarnings("rawtypes")
	public ExcelBean(String title, String creator, List list, String[] hearders, String[] fields)
	{
		this.title = title;
		this.creator = creator;
		this.list = list;
		this.hearders = hearders;
		this.fields = fields;
	}

	@SuppressWarnings("rawtypes")
	public ExcelBean(String title, String creator, List list, String[] hearders, String[][] children, String[] fields)
	{
		this.title = title;
		this.creator = creator;
		this.list = list;
		this.hearders = hearders;
		this.children = children;
		this.fields = fields;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getCreator()
	{
		return creator;
	}

	public void setCreator(String creator)
	{
		this.creator = creator;
	}

	@SuppressWarnings("rawtypes")
	public List getList()
	{
		return list;
	}

	@SuppressWarnings("rawtypes")
	public void setList(List list)
	{
		this.list = list;
	}

	public String[] getHearders()
	{
		return hearders;
	}

	public void setHearders(String[] hearders)
	{
		this.hearders = hearders;
	}

	public String[][] getChildren()
	{
		return children;
	}

	public void setChildren(String[][] children)
	{
		this.children = children;
	}

	public String[] getFields()
	{
		return fields;
	}

	public void setFields(String[] fields)
	{
		this.fields = fields;
	}
}
