package com.stone.equipsys.core.common.util.generator;

public class Table
{

	private String columnName;

	private String dataType;

	private String jdbcType;

	private String columnComment;

	private String columnNameUpper;

	private String typeName;

	public String getColumnName()
	{
		return columnName;
	}

	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}

	public String getDataType()
	{
		return dataType;
	}

	public void setDataType(String dataType)
	{
		this.dataType = dataType;
	}

	public String getColumnComment()
	{
		return columnComment;
	}

	public void setColumnComment(String columnComment)
	{
		this.columnComment = columnComment;
	}

	public String getColumnNameUpper()
	{
		return columnNameUpper;
	}

	public void setColumnNameUpper(String columnNameUpper)
	{
		this.columnNameUpper = columnNameUpper;
	}

	public String getTypeName()
	{
		return typeName;
	}

	public void setTypeName(String typeName)
	{
		this.typeName = typeName;
	}

	public String getJdbcType()
	{
		return jdbcType;
	}

	public void setJdbcType(String jdbcType)
	{
		this.jdbcType = jdbcType;
	}

}
