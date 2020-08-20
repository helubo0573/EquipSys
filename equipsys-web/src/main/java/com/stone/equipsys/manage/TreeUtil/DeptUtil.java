package com.stone.equipsys.manage.TreeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.stone.equipsys.core.domain.OrgDept;


public class DeptUtil {

	static final String OneLevelDept="../static/img/one_level_dept.png";
	static final String TwoLevelDept="../static/img/two_level_dept.png";
	static final String PostDept="../static/img/post_dept.png";
	
	public static class DeptObject{
		public Object id;
		public Object pId;
		public String name;
		public String title;
		public String icon;
		public Integer depttype;
		public Integer level;
		public List<DeptObject> children = null;// new ArrayList();
	}
	
	public static List<DeptObject> createDeptTree(List<OrgDept> deptlist){
		Map<String, DeptObject> mapping = new TreeMap<String, DeptObject>();
		for(OrgDept dept:deptlist) {
			DeptObject object=new DeptObject();
			object.id=dept.getId();
			object.pId=dept.getParentId();
			object.name=dept.getDeptName();
			object.depttype=dept.getDeptType();
			object.level=dept.getDeptType();
			object.title=dept.getDeptRemarks();
			mapping.put(object.id+"", object);
		}
		for(DeptObject deptobject:mapping.values()) {
			DeptObject parentobject=mapping.get(deptobject.pId+"");
			if(parentobject!=null) {
				if(parentobject.children==null) {
					parentobject.children=new ArrayList<DeptUtil.DeptObject>();
				}
				parentobject.children.add(deptobject);
			}
		}
		List<DeptObject> depttree=new ArrayList<DeptObject>();
		for(DeptObject deptobject:mapping.values()) {
			DeptObject parentobject=mapping.get(deptobject.pId+"");
			if(parentobject==null) {
				depttree.add(deptobject);
			}
		}
		return depttree;
	}
	
	public static List<DeptObject> setTreeIcon(List<DeptObject> deptlist){
		if(deptlist!=null) {
			for(DeptObject dept:deptlist) {
				dept.icon=LeveltoModel(dept.level,dept);
				List<DeptObject> chiequiplist=dept.children;
				if(chiequiplist!=null) {
					for(DeptObject chiedept:chiequiplist) {
						chiedept.icon=LeveltoModel(chiedept.level,chiedept);;
						List<DeptObject> thrquiplist=chiedept.children;
						if(thrquiplist!=null) {
							for(DeptObject thrquip:thrquiplist) {
								thrquip.icon=PostDept;
							}
						}
					}
				}
			}
		}
		return deptlist;
	}
	
	public static String LeveltoModel(Integer level,DeptObject dept) {
		String m="";
		switch (level) {
		case 0:
			if((int) dept.pId==0)
				m=OneLevelDept;
			else
				m=TwoLevelDept;
			break;
		case 1:
			m= PostDept;
			break;
		}
		return m;
	}
}
