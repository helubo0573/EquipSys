package com.stone.equipsys.core.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

public class Tree
{

	private static final Logger logger = Logger.getLogger(Tree.class);

	public static class TreeObject
	{
		public Object id;
		public Object pId;
		public String name;
		public String title;
//		public Boolean leaf = true;
//		public Boolean expanded = true;
		public List<TreeObject> children = null;// new ArrayList();
	}

	public static List<TreeObject> TreeList(List<?> list, String primaryKey, String textKey, String parentKey,String remarks){
		Map<String, TreeObject> mapping = new TreeMap<String, TreeObject>();
		List<TreeObject> treelist=new ArrayList<>();
		
		for (Object object : list){
			TreeObject treeObject = new TreeObject();
			treeObject.id = getObjectKeyValue(object, primaryKey).toString();
			if(parentKey!="")	treeObject.pId = getObjectKeyValue(object, parentKey).toString();
			else treeObject.pId=0;
			treeObject.name = "" + getObjectKeyValue(object, textKey);
			treeObject.title="" +	getObjectKeyValue(object, remarks);
			mapping.put(treeObject.id + "", treeObject);
			treelist.add(treeObject);
		}
		for (TreeObject treeObject : mapping.values()){
			TreeObject parentObject = mapping.get(treeObject.pId+"");
			if (parentObject != null)
			{
//				parentObject.leaf = false;
				if (parentObject.children == null)
				{
					parentObject.children = new ArrayList<Tree.TreeObject>();
				}
				parentObject.children.add(treeObject);
			}
		}
		
		List<TreeObject> treeList = new ArrayList<TreeObject>();
		for (TreeObject treeObject : mapping.values()){
			TreeObject parentObject = mapping.get(treeObject.pId + "");
			if (parentObject == null){
				treeList.add(treeObject);
			}
		}

		return treeList;
	}

	@SuppressWarnings("rawtypes")
	public static String TreeJson(List list, String primaryKey, String textKey, String parentKey,String remarks)
	{
		return JSONObject.toJSONString(Tree.TreeList(list, primaryKey, textKey, parentKey, remarks)).toString();
	}

	/**
	 * 遍历object，找到设置的key的值
	 * @param object	对象
	 * @param key		对象属性
	 * @return			值
	 */
	@SuppressWarnings("rawtypes")
	public static Object getObjectKeyValue(Object object, String key){
		Object value = null;
		if (object instanceof Map) {
			return ((Map) object).get(key);
		}else{
			value=ObjectValueByKey.ValueByMethod(object, key);
			if(value!=null) return value;
			value=ObjectValueByKey.ValueByField(object, key);
		}
		return value;
	}
}
