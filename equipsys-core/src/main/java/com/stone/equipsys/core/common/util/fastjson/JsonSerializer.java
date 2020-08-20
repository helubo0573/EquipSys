package com.stone.equipsys.core.common.util.fastjson;

import java.util.List;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.stone.equipsys.core.common.util.json.AbstractJsonSerializer;
//序列化JSON
public class JsonSerializer extends AbstractJsonSerializer
{

	private JSONSerializer json;

	private boolean hasObject = false;

	@SuppressWarnings("unused")
	private static final String DEFAULT_NAME = "data";

	public JsonSerializer(String name)
	{
		json = new JSONSerializer();
		if (name != null)
		{
			writeHead();
		}
	}

	public JsonSerializer(boolean needErrorInfo, String name)
	{
		json = new JSONSerializer();
		this.setNeedErrorInfo(needErrorInfo);
		if (name != null)
		{
			writeHead();
		}
	}

	private void writeHead()
	{
		json.getWriter().append('{');
	}

	@Override
	public void addObject(Object object, String name, String[] properties, Boolean isFilter)
	{
		SerializeWriter writer = json.getWriter();
		// 当已经添加过object,需要加逗号
		if (hasObject)
		{
			writer.append(',');
		} else
		{
			hasObject = true;
		}

		json.getPropertyFilters().clear();

		// 当存在特殊处理的字段的时候
		if (properties != null && properties.length > 0)
		{
			this.arrayToFilters(json, properties, isFilter);
		}

		if (name != null && !"".equals(name) && name.length() > 0)
		{
			json.write(name);
			writer.append(':');
		}
		json.write(object);
	}

	@Override
	public void addObject(Object object, String name)
	{
		this.addObject(object, name, null, false);
	}

	@Override
	public void addObjectWithExclude(Object object, String name, String[] properties)
	{
		this.addObject(object, name, properties, true);
	}

	@Override
	public void addObjectWithInclude(Object object, String name, String[] properties)
	{
		this.addObject(object, name, properties, false);
	}

	@Override
	public String toString()
	{
		return json.toString();
	}

	public String toString(String name)
	{
		writeTail(name);
		return json.toString();
	}

	private void writeTail(String name)
	{
		SerializeWriter writer = json.getWriter();
		// 如果需要添加错误信息。
		if (this.isNeedErrorInfo())
		{
			if (this.hasObject)
			{
				writer.append(',');
			}
			json.write("errorNo");
			writer.append(":");
			json.write(this.getErrorNo());
			writer.append(',');
			json.write("errorInfo");
			writer.append(":");
			json.write(this.getErrorInfo());
		}
		if (name != null)
		{
			writer.append('}');
		}
	}

	private void arrayToFilters(JSONSerializer jsonSerializer, String[] properties, Boolean isFilter)
	{
		// 当特殊处理的字段是需要转化的字段时
		List<PropertyFilter> filterList = jsonSerializer.getPropertyFilters();
		if (isFilter != null && isFilter == false)
		{
			// 遍历所有的特殊属性
			for (final String property : properties)
			{
				PropertyFilter filter = new PropertyFilter()
				{
					public boolean apply(Object source, String name, Object value)
					{
						if (property.equals(name))
							return true;
						else
							return false;
					}
				};
				filterList.add(filter);
			}
		} else
		{
			// 遍历所有的特殊属性
			for (final String property : properties)
			{
				PropertyFilter filter = new PropertyFilter()
				{
					public boolean apply(Object source, String name, Object value)
					{
						if (property.equals(name))
							return false;
						else
							return true;
					}
				};
				filterList.add(filter);
			}
		}
	}
}
