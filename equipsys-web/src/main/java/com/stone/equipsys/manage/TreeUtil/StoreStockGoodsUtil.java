package com.stone.equipsys.manage.TreeUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.stone.equipsys.core.domain.StoreGoodsInfo;
import com.stone.equipsys.core.domain.StoreGoodsType;


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
}
