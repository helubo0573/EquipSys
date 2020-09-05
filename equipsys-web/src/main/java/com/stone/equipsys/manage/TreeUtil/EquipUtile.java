package com.stone.equipsys.manage.TreeUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.stone.equipsys.core.common.util.Tree.TreeObject;
import com.stone.equipsys.core.domain.EquipInfo;
import com.stone.equipsys.manage.TreeUtil.StoreStockGoodsUtil.StockGoodsObject;

public class EquipUtile {

	static final String flagship="../static/img/flagship.png";
	static final String model="../static/img/model.png";
	static final String equip="../static/img/equip.png";
	
	public static class EquipObject{
		public Long id;
		public Object pId;
		public String name;
		public String title;
		public String icon;
		public Integer level;
//		public Boolean leaf = true;
//		public Boolean expanded = true;
		public List<EquipObject> children = null;// new ArrayList();
		
		public Long getId() {
			return this.id;
		}
	}
	public static List<EquipObject> setTreeIcon(List<EquipObject> equiplist){
		if(equiplist!=null) {
			for(EquipObject equip:equiplist) {
				equip.icon=LeveltoModel(equip.level);
				List<EquipObject> chiequiplist=equip.children;
				if(chiequiplist!=null) {
					for(EquipObject chiequip:chiequiplist) {
						chiequip.icon=LeveltoModel(chiequip.level);;
						List<EquipObject> thrquiplist=chiequip.children;
						if(thrquiplist!=null) {
							for(EquipObject thrquip:thrquiplist) {
								thrquip.icon=LeveltoModel(thrquip.level);
							}
						}
					}
				}
			}
		}
		return equiplist;
		
	}
	
	public static List<EquipObject> createEquipTree(List<EquipInfo> equiplist){
		Map<String, EquipObject> mapping = new TreeMap<String, EquipObject>();
		for(EquipInfo equip:equiplist) {
			EquipObject object=new EquipObject();
			object.id=equip.getId();
			object.pId=equip.getParentId();
			object.name=equip.getEquipName();
			object.level=equip.getEquipLevel();
			object.title=equip.getEquipCode();
			mapping.put(object.id+"", object);
		}
		for(EquipObject equipobject:mapping.values()) {
			EquipObject parentobject=mapping.get(equipobject.pId+"");
			if(parentobject!=null) {
				if(parentobject.children==null) {
					parentobject.children=new ArrayList<EquipUtile.EquipObject>();
				}
				parentobject.children.add(equipobject);
			}
		}
		List<EquipObject> equiptree=new ArrayList<EquipObject>();
		for(EquipObject equipobject:mapping.values()) {
			EquipObject parentobject=mapping.get(equipobject.pId+"");
			if(parentobject==null) {
				equiptree.add(equipobject);
			}
		}
		for(EquipObject equip:equiptree) {
			if(equip.children!=null) {
				for(EquipObject cequip:equip.children) {
					if(cequip.children!=null) {
						cequip.children=cequip.children.stream().sorted(Comparator.comparing(EquipObject::getId)).collect(Collectors.toList());
					}
				}
				equip.children=equip.children.stream().sorted(Comparator.comparing(EquipObject::getId)).collect(Collectors.toList());
			}
		}
		equiptree=equiptree.stream().sorted(Comparator.comparing(EquipObject::getId)).collect(Collectors.toList());
		return equiptree;
	}
	
	public static String LeveltoModel(Integer level) {
		String m="";
		switch (level) {
		case 0:
			m=flagship;
			break;
		case 1:
			m= model;
			break;
		case 2:
			m= EquipUtile.equip;
			break;
		}
		return m;
	}
}
