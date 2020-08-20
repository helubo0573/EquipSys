package com.stone.equipsys.manage.TreeUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.stone.equipsys.core.domain.OrgDept;
import com.stone.equipsys.core.domain.SysRole;


public class RoleUtile {

	public static class RoleObject{
		public Object id;
		public Object pId;
		public String name;
		public String icon;
		public String remarks="";
		public boolean isrole=false;
		public boolean check=false;
		public List<RoleObject> children = null;// new ArrayList();
	}
	
	public static List<RoleObject> RoleeTreeList(List<OrgDept> deptlist,List<SysRole> rolelist){
		Map<String,RoleObject> rolemap=new TreeMap<String,RoleObject>();
		Map<String,RoleObject> deptmap=new TreeMap<String,RoleObject>();
		for(SysRole role:rolelist) {
			RoleObject roleob=new RoleObject();
			roleob.id=role.getId();
			roleob.pId=role.getDeptId();
			roleob.name=role.getRoleName();
			roleob.isrole=true;
			roleob.icon="";
			roleob.check=true;
			roleob.remarks=role.getRemarks();
			rolemap.put(role.getId()+"", roleob);
		}
		RoleObject commosob=new RoleObject();
		commosob.id=0;
		commosob.name="全局角色";
		commosob.icon="";
		commosob.check=false;
		commosob.children=new ArrayList<RoleObject>();
		deptmap.put("0", commosob);
		for(OrgDept dept:deptlist) {
			RoleObject roleob=new RoleObject();
			roleob.id=dept.getId();
			roleob.name=dept.getDeptName();
			roleob.icon="";
			roleob.check=false;
//			roleob.children=new ArrayList<RoleObject>();
			deptmap.put(dept.getId()+"", roleob);
		}
		for(RoleObject role:rolemap.values()) {
				RoleObject deptob=deptmap.get(role.pId+"");
				if(deptob.children==null) {
					deptob.children=new ArrayList<RoleObject>();
				}
				deptob.children.add(role);
		}
		List<RoleObject> relist=new ArrayList<RoleObject>();
		for(RoleObject deptob:deptmap.values()) {
			if(deptob.children!=null)	relist.add(deptob);
		}
		return relist;
	}
	
}
