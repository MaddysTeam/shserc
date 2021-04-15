package com.dianda.common.util.basic;

import java.util.Map;

public class MapperUtil {
	
	public static  <K,V> V  FindValueByKey( Map<K,V> map,K key){
		if(!ObjectUtil.isNull ( map )){
			for( Map.Entry<K,V> entry: map.entrySet ()){
				if(entry.getKey ().equals (key ) || entry.getKey ()== key){
					return entry.getValue ();
				}
			}
		}
		
		return null;
	}
	
}
