package com.stone.equipsys.manage.TreeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSONObject;
import com.stone.equipsys.core.domain.EmployeeInfo;
import com.stone.equipsys.core.domain.OrgDept;
import com.stone.equipsys.core.domain.OrgPost;


public class EmployeeUtil {

	static class TreeObject{
		public Object id;
		public Object pId;
		public String name;
		public boolean check=false;
		public boolean nocheck=false;
		public boolean isemp=false;
		public List<TreeObject> children = null;
	}
	
	/**
	 * 输入部门岗位人员表生成人员树
	 * @param emplist
	 * @param deptlist
	 * @param postlist
	 * @return	人员树list对象
	 */
	public static List<TreeObject> EmployeeTreeList(List<EmployeeInfo> emplist,List<OrgDept> deptlist){
		Map<String, TreeObject> empmapping = new TreeMap<String, TreeObject>();
		Map<String, TreeObject> deptmapping = new TreeMap<String, TreeObject>();
		for(EmployeeInfo emp:emplist ) {
			TreeObject treeObject = new TreeObject();
			treeObject.id=emp.getId();
			treeObject.pId=emp.getEmpPost();
			treeObject.name=emp.getEmpName();
			treeObject.check=false;
			treeObject.isemp=true;
			empmapping.put(treeObject.id.toString(), treeObject);
		}
		for(OrgDept dept:deptlist) {
			TreeObject treeObject = new TreeObject();
			treeObject.id=dept.getId();
			treeObject.name=dept.getDeptName();
			treeObject.pId=dept.getParentId();
			treeObject.nocheck=true;
			treeObject.children=new ArrayList<TreeObject>();
			deptmapping.put(treeObject.id.toString(), treeObject);
		}
		
		for(TreeObject treeObject:empmapping.values()) {
			TreeObject postObject = deptmapping.get(treeObject.pId+"");
			postObject.children.add(treeObject);
		}
		for(TreeObject treeObject:deptmapping.values()) {
			TreeObject deptObject = deptmapping.get(treeObject.pId+"");
			if(deptObject!=null)	deptObject.children.add(treeObject);
		}
		List<TreeObject> treeList = new ArrayList<TreeObject>();
		for (TreeObject treeObject : deptmapping.values()){
			TreeObject parentObject = deptmapping.get(treeObject.pId + "");
			if (parentObject == null)	treeList.add(treeObject);
			
		}
		for(TreeObject tree:treeList) {
			String jsonStr = JSONObject.toJSONString(tree);
		}
		return treeList;
	}
	
}
