package com.stone.equipsys.core.common.util.excel;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.stone.equipsys.core.common.util.DateUtil;



public class TableDataRow
{

	private LinkedList<TableDataCell> cells;

	private TableData table;

	private int rowStyle = TableColumn.COLUMN_TYPE_STRING;

	public void addCell(TableDataCell cell)
	{
		cells.add(cell);
	}

	public void addCell(String value)
	{
		TableDataCell cell = new TableDataCell(this);
		cell.setValue(value);
		cell.setCellStyle(rowStyle);
		addCell(cell);
	}

	public void addCell(Integer value)
	{
		TableDataCell cell = new TableDataCell(this);
		cell.setValue(value);
		cell.setCellStyle(rowStyle);
		addCell(cell);
	}

	public void addCell(Double value)
	{
		TableDataCell cell = new TableDataCell(this);
		cell.setValue(value);
		cell.setCellStyle(rowStyle);
		addCell(cell);
	}

	public void addCell(Object value)
	{
		if (value instanceof String)
		{
			addCell((String) value);
		} else if (value instanceof Integer)
		{
			addCell((Integer) value);
		} else if (value instanceof Double)
		{
			addCell((Double) value);
		} else if (value instanceof BigDecimal)
		{
			addCell(value.toString());
		} else if (value instanceof Long)
		{
			addCell(value.toString());
		} else if (value instanceof Date)
		{
			addCell(DateUtil.dateStr4((Date) value));
			// addCell(((Date) value).toLocaleString());
		} else if (value instanceof Timestamp)
		{
			addCell(DateUtil.dateStr4((Timestamp) value));
			// addCell(((Timestamp) value).toLocaleString());
		} else if (value == null)
		{
			addCell("");
		} else
			addCell(value + "");
	}

	public TableDataCell getCellAt(int index)
	{
		return cells.get(index);
	}

	public List<TableDataCell> getCells()
	{
		return cells;
	}

	public TableData getTable()
	{
		return table;
	}

	public TableDataRow(TableData table)
	{
		cells = new LinkedList<TableDataCell>();
		this.table = table;
	}

	public void setRowStyle(int rowStyle)
	{
		this.rowStyle = rowStyle;
	}

	public int getRowStyle()
	{
		return rowStyle;
	}
}
