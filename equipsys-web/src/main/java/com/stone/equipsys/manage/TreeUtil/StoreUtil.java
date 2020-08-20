package com.stone.equipsys.manage.TreeUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.codehaus.jackson.map.util.Comparators;

import com.stone.equipsys.core.domain.StoreInfo;


public class StoreUtil {

	public static class StoreObject{
		public Long id;
		public Long pId;
		public String name;
		public int order;
		public String remarks="";
		public List<StoreObject> children = null;// new ArrayList();
		
		public int getOrder() {
			return this.order;
		}
	}
	/**
	 * 
	 * @param storelist
	 * @param type	用于区别显示主界面树还是上级仓库选择树 true-主界面树  false-上级仓库树
	 * @return
	 */
	public static List<StoreObject> StoreTreeList(List<StoreInfo> storelist,boolean type){
		Map<String,StoreObject> storemap=new TreeMap<String,StoreObject>();
		List<StoreObject> relist=new ArrayList<StoreObject>();
		for(StoreInfo store:storelist) {
			StoreObject storeob=new StoreObject();
			storeob.id=store.getId();
			storeob.pId=store.getParentId();
			storeob.name=store.getStroeName();
			storeob.order=store.getStoreOrder();
			storeob.remarks=store.getRemarks();
			storemap.put(store.getId().toString(), storeob);
		}
		List<Map.Entry<String, StoreObject>> Sortmap=new ArrayList<Map.Entry<String, StoreObject>>(storemap.entrySet());
		Collections.sort(Sortmap, new Comparator<Map.Entry<String, StoreObject>>(){

			@Override
			public int compare(Entry<String, StoreObject> o1, Entry<String, StoreObject> o2) {
				if(o1.getValue().order!=o2.getValue().order) {
					return o1.getValue().order-o2.getValue().order;
				}else {
					return o1.getKey().compareTo(o2.getKey());
				}
			}
		});
		if(type) {
			for(StoreObject store:storemap.values()) {
				if(store.pId!=0) {
					StoreObject pstore=storemap.get(store.pId+"");
					if(pstore.children==null) {
						pstore.children=new ArrayList<StoreObject>();
					}
					pstore.children.add(store);
				}
			}
			for(StoreObject store:storemap.values()) {
				if(store.children!=null) {
					relist.add(store);
				}
			}
		}else {
			for(StoreObject store:storemap.values()) {
				if(store.pId==0) {
					relist.add(store);
				}
			}
		}
		for(StoreObject store:relist) {
			List<StoreObject> templist=store.children;
			if(templist!=null) {
				templist=templist.stream().sorted(Comparator.comparing(StoreObject::getOrder)).collect(Collectors.toList());
			}
			store.children=templist;
		}
		relist=relist.stream().sorted(Comparator.comparing(StoreObject::getOrder)).collect(Collectors.toList());
		return relist;
	}
}
