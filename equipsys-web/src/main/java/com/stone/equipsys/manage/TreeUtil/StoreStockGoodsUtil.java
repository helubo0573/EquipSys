package com.stone.equipsys.manage.TreeUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.stone.equipsys.core.domain.EquipPartsInfo;
import com.stone.equipsys.core.domain.StoreGoodsInfo;
import com.stone.equipsys.core.domain.StoreGoodsModelNumberInfo;
import com.stone.equipsys.core.domain.StoreGoodsType;
import com.stone.equipsys.manage.TreeUtil.StoreUtil.StoreObject;


public class StoreStockGoodsUtil {

	public static class StockGoodsObject{
		public Long id;
		public Long pId;
		public String name;
		public int order;
		public String icon="";
		public String remarks="";
		public List<StockGoodsObject> children = null;
		public int getOrder() {
			return this.order;
		}
	}
	
	public static class StockGoodsModelObject{
		public Long id;
		public Long pId;
		public String name;
		public int order;
		public String icon="";
		public List<StockGoodsModelObject> children = null;
		public int getOrder() {
			return this.order;
		}
		public String getModelName() {
			return this.name;
		}
	}
	
	
	public static List<StockGoodsObject> CreateStockGoodsList(List<StoreGoodsType> goodstypelist,List<StoreGoodsInfo> goodsinfolist){
		Map<String,StockGoodsObject> goodstypemap=new TreeMap<String,StockGoodsObject>();
		Map<String,StockGoodsObject> goodsinfomap=new TreeMap<String,StockGoodsObject>();
		for(StoreGoodsType goodstype:goodstypelist) {
			StockGoodsObject sgo=new StockGoodsObject();
			sgo.id=goodstype.getId();
			sgo.name=goodstype.getTypeName();
			sgo.order=goodstype.getTypeOrder();
			sgo.children=new ArrayList<StockGoodsObject>();
			goodstypemap.put(goodstype.getId()+"", sgo);
		}
		for(StoreGoodsInfo goodsinfo:goodsinfolist) {
			StockGoodsObject sgo=new StockGoodsObject();
			sgo.id=goodsinfo.getId();
			sgo.name=goodsinfo.getGoodsName();
			sgo.pId=goodsinfo.getGoodsType();
			sgo.icon="";
			sgo.remarks=goodsinfo.getRemarks();
			goodsinfomap.put(goodsinfo.getId()+"", sgo);
		}
		for(StockGoodsObject goodsinfo:goodsinfomap.values()) {
			goodstypemap.get(goodsinfo.pId+"").children.add(goodsinfo);
		}
		List<StockGoodsObject> relist=new ArrayList<StockGoodsObject>();
		for(StockGoodsObject goodstype:goodstypemap.values()) {
			relist.add(goodstype);
		}
		relist=relist.stream().sorted(Comparator.comparing(StockGoodsObject::getOrder)).collect(Collectors.toList());
		return relist;
		
	}
	
	public static List<StockGoodsModelObject> CreateStockGoodsModelObjectList(List<StoreGoodsType> goodstypelist,
			List<StoreGoodsInfo> goodsinfolist,
			List<StoreGoodsModelNumberInfo> modelnumberlist,
			List<EquipPartsInfo> partslist){
		List<Long> modelidlist=new ArrayList<>();
		Map<String,StockGoodsModelObject> goodstypemap=new TreeMap<String,StockGoodsModelObject>();
		Map<String,StockGoodsModelObject> goodsinfomap=new TreeMap<String,StockGoodsModelObject>();
		Map<String,StockGoodsModelObject> goodsmodelmap=new TreeMap<String,StockGoodsModelObject>();
		List<StockGoodsModelObject> relist=new ArrayList<StockGoodsModelObject>();
		for(EquipPartsInfo parts:partslist) {
			modelidlist.add(parts.getGoodsModelId());
		}
		for(StoreGoodsModelNumberInfo model:modelnumberlist) {
			StockGoodsModelObject mob=new StockGoodsModelObject();
			mob.id=model.getId();
			mob.pId=model.getGoodsId();
			mob.name=model.getModelNumberName();
			goodsmodelmap.put(model.getId()+"", mob);
		}
		for(StoreGoodsType goodstype:goodstypelist) {
			StockGoodsModelObject mob=new StockGoodsModelObject();
			mob.id=goodstype.getId();
			mob.name=goodstype.getTypeName();
			mob.order=goodstype.getTypeOrder();
			mob.children=new ArrayList<StockGoodsModelObject>();
			goodstypemap.put(goodstype.getId()+"", mob);
		}
		for(StoreGoodsInfo goodsinfo:goodsinfolist) {
			StockGoodsModelObject mob=new StockGoodsModelObject();
			mob.id=goodsinfo.getId();
			mob.name=goodsinfo.getGoodsName();
			mob.pId=goodsinfo.getGoodsType();
			mob.children=new ArrayList<StockGoodsModelObject>();
			goodsinfomap.put(goodsinfo.getId()+"", mob);
		}
		for(StockGoodsModelObject model:goodsmodelmap.values()) {
			if(modelidlist.indexOf(model.id)==-1)
				goodsinfomap.get(model.pId+"").children.add(model);
		}
		for(StockGoodsModelObject goods:goodsinfomap.values()) {
			List<StockGoodsModelObject> tlist=goods.children;
			if(tlist!=null) goods.children=tlist.stream().sorted(Comparator.comparing(StockGoodsModelObject::getModelName)).collect(Collectors.toList());
			goodstypemap.get(goods.pId+"").children.add(goods);
		}
		for(StockGoodsModelObject goodstype:goodstypemap.values()) {
			List<StockGoodsModelObject> tlist=goodstype.children;
			if(tlist!=null) goodstype.children=tlist.stream().sorted(Comparator.comparing(StockGoodsModelObject::getOrder)).collect(Collectors.toList());
			relist.add(goodstype);
		}
		return relist;
	}
}
