package com.stone.equipsys.core.common.util.excel;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

public class TableData
{

	private static DecimalFormat format = new DecimalFormat("0.##");

	public static final int STYLE_TYPE_STRING = 0;

	public static final int STYLE_TYPE_FLOAT_2 = 1;

	public static final int STYLE_TYPE_FLOAT_3 = 2;

	public static final int STYLE_TYPE_INTEGER = 3;

	public static final int STYLE_TYPE_RED_BG = 10;

	public static final int STYLE_TYPE_YELLOW_BG = 11;

	public static final int STYLE_TYPE_GREEN_BG = 12;

	private String sheetTitle;

	private TableHeaderMetaData header;

	private LinkedList<TableDataRow> rows;

	private int totalRows;

	public TableData()
	{
	}

	public TableData(TableHeaderMetaData header)
	{
		this.header = header;
		rows = new LinkedList<TableDataRow>();
	}

	public void compute()
	{
		for (int i = 0; i < header.getColumnCount(); i++)
		{
			TableColumn tc = header.getColumnAt(i);
			if (tc.isVisible() && tc.isGrouped() && tc.isDisplaySummary())
			{
				buildSummary(i);
			}
		}
	}

	class SummaryData
	{
		String key;
		int index;
		int count;
		TableDataRow row;

		@Override
		public String toString()
		{
			StringBuilder sb = new StringBuilder();
			sb.append(key).append("\t").append(index).append("\t").append(count).append("\t");
			for (TableDataCell cell : row.getCells())
			{
				sb.append(cell.getValue()).append("\t");
			}
			return sb.toString();
		}
	}
	
	private TableDataRow buildNewRow(String key, int index)
	{
		TableDataRow nrow = new TableDataRow(this);
		for (int i = 0; i < index; i++)
		{
			nrow.addCell(""); // 添加空单元格
		}
		nrow.addCell("(" + key + ")小计");
		for (int i = index + 1; i < header.getColumnCount(); i++)
		{
			TableColumn thc = header.getColumnAt(i);
			if (thc.getAggregateRule() != null)
			{
				nrow.addCell(0);
			} else
			{
				nrow.addCell("");
			}
		}
		return nrow;
	}
	
	/**
	 * 	创建合计行
	 * @param index
	 */
	private void buildSummary(int index)
	{
		LinkedList<SummaryData> lst = new LinkedList<SummaryData>();
		String okey = null;
		SummaryData sd = null;
		for (int j = 0; j < rows.size(); j++)
		{
			TableDataRow row = rows.get(j);
			String key = row.getCellAt(index).getValue();

			if (okey == null)
			{
				okey = key;
				sd = new SummaryData();
				sd.key = key;
				sd.count = 0;
				sd.row = buildNewRow(key, index);
			}

			if (okey != null && !okey.equals(key))
			{
				for (int i = index; i < header.getColumnCount(); i++)
				{
					TableColumn thc = header.getColumnAt(i);
					if (thc.getAggregateRule() != null)
					{
						String value = sd.row.getCellAt(i).getValue();
						if ("avg".equalsIgnoreCase(thc.getAggregateRule()))
						{
							if (sd.count > 0)
							{
								sd.row.getCellAt(i).setValue("平均：" + format.format(Double.parseDouble(value) / sd.count));
							} else
							{
								sd.row.getCellAt(i).setValue("平均：" + 0);
							}
						} else if ("max".equalsIgnoreCase(thc.getAggregateRule()))
						{
							sd.row.getCellAt(i).setValue("最大值：" + value);
						} else if (thc.getAggregateRule().equalsIgnoreCase("min"))
						{
							sd.row.getCellAt(i).setValue("最小值：" + value);
						} else if (thc.getAggregateRule().equalsIgnoreCase("sum"))
						{
							sd.row.getCellAt(i).setValue("求和：" + value);
						} else if (thc.getAggregateRule().equalsIgnoreCase("count"))
						{
							sd.row.getCellAt(i).setValue("共" + value + "行");
						}
					}
				}
				sd.index = j;

				FormulaProcessor.getInstance().fillValue(sd.row);
				lst.add(sd);

				okey = key;
				sd = new SummaryData();
				sd.key = key;
				sd.count = 0;
				sd.row = buildNewRow(key, index);
			}
			sd.count++;
			for (int i = index + 1; i < header.getColumnCount(); i++)
			{
				TableColumn thc = header.getColumnAt(i);
				if (thc.getColumnType() != TableColumn.COLUMN_TYPE_FORMULA && thc.getAggregateRule() != null)
				{
					Double value = Double.parseDouble(sd.row.getCellAt(i).getValue());
					Double cellValue = null;
					try
					{
						cellValue = Double.valueOf(row.getCellAt(i).getValue());
					} catch (NumberFormatException e)
					{
						cellValue = null;
					}
					if (cellValue == null)
						continue;
					if ("max".equalsIgnoreCase(thc.getAggregateRule()))
					{
						if (value < cellValue)
						{
							value = cellValue;
						}
					} else if ("min".equalsIgnoreCase(thc.getAggregateRule()))
					{
						if (value > cellValue)
						{
							value = cellValue;
						}
					} else if ("count".equalsIgnoreCase(thc.getAggregateRule()))
					{
						value++;
					} else if ("sum".equalsIgnoreCase(thc.getAggregateRule()))
					{
						value += cellValue;
					} else if ("avg".equalsIgnoreCase(thc.getAggregateRule()))
					{
						value += cellValue;
					}
					sd.row.getCellAt(i).setValue(format.format(value));
				}
			}
		}

		if (sd != null)
		{
			for (int i = index; i < header.getColumnCount(); i++)
			{
				TableColumn thc = header.getColumnAt(i);
				if (thc.getAggregateRule() != null)
				{
					String value = sd.row.getCellAt(i).getValue();
					if ("avg".equalsIgnoreCase(thc.getAggregateRule()))
					{
						if (sd.count > 0)
						{
							sd.row.getCellAt(i).setValue("平均：" + format.format(Double.parseDouble(value) / sd.count));
						} else
						{
							sd.row.getCellAt(i).setValue("平均：" + 0);
						}
					} else if ("max".equalsIgnoreCase(thc.getAggregateRule()))
					{
						sd.row.getCellAt(i).setValue("最大值：" + value);
					} else if ("min".equalsIgnoreCase(thc.getAggregateRule()))
					{
						sd.row.getCellAt(i).setValue("最小值：" + value);
					} else if ("sum".equalsIgnoreCase(thc.getAggregateRule()))
					{
						sd.row.getCellAt(i).setValue("求和：" + value);
					} else if ("count".equalsIgnoreCase(thc.getAggregateRule()))
					{
						sd.row.getCellAt(i).setValue("共" + value + "行");
					}
				}
			}
			FormulaProcessor.getInstance().fillValue(sd.row);
			lst.add(sd);
			sd.index = rows.size();
		}
		for (int i = 0; i < lst.size(); i++)
		{
			SummaryData sda = lst.get(i);
			rows.add(sda.index + i, sda.row);
		}
	}

	// GETTER和SETTER方法

	public TableHeaderMetaData getTableHeader()
	{
		return header;
	}

	public void addRow(TableDataRow row)
	{
		rows.add(row);
	}

	public List<TableDataRow> getRows()
	{
		return rows;
	}

	public TableDataRow getRowAt(int index)
	{
		return rows.get(index);
	}

	public int getRowCount()
	{
		return rows.size();
	}

	public int getTotalRows()
	{
		return totalRows;
	}

	public void setTotalRows(int totalRows)
	{
		this.totalRows = totalRows;
	}

	public void setHeader(TableHeaderMetaData header)
	{
		this.header = header;
	}

	public void setRows(LinkedList<TableDataRow> rows)
	{
		this.rows = rows;
	}

	public String getSheetTitle()
	{
		return sheetTitle;
	}

	public void setSheetTitle(String sheetTitle)
	{
		this.sheetTitle = sheetTitle;
	}

}
