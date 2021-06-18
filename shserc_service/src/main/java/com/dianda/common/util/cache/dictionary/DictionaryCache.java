package com.dianda.common.util.cache.dictionary;

import com.dianda.common.common.Constant;
import com.dianda.common.exceptions.GlobalException;
import com.dianda.common.service.IDictionaryService;
import com.dianda.common.util.basic.ObjectUtil;
import com.dianda.common.util.cache.CacheObject;
import com.dianda.common.util.cache.manager.ICacheManager;
import com.dianda.common.vo.DictionaryVo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * data dictionary cache  数据字典缓存
 *
 * @author huachao
 * @since 2020-08-05
 */
@Component
public class DictionaryCache {
	
	private static final String KEY = "dict";
	private static final long EXPIRE_SECONDS = 100000;
	
	@Resource( name = "memoryCache" )
	ICacheManager manager;
	
	@Resource
	IDictionaryService service;
	
	public String translate( String code , String value, String defaultValue ) {
		//TODO: parent code ->  children -> find name by value
		CacheObject o = manager.getCache ( KEY );
		if ( ObjectUtil.isNull ( o ) || ObjectUtil.isNull ( o.getMap ( ) ) ) {
			throw new GlobalException (Constant.ErrorCode.PARAM_NULL_POINT_REFERENCE , Constant.Error.OBJECT_IS_REQUIRED );
		}
		
		Map<String, Object> map = o.getMap ( );
		DictionaryVo parent;
		DictionaryVo child;
		
		for ( String key : map.keySet ( ) ) {
			if(ObjectUtil.isNull ( key )) continue;
			if ( key.equals ( code ) ) {
				parent = ( DictionaryVo ) ( map.get ( key ) );
				for ( String childKey : map.keySet ( ) ) {
					child = ( DictionaryVo ) ( map.get ( childKey ) );
					if ( child.getParentId ( ) == parent.getId ( ) && child.getValue ( ).equals ( value ) ) {
						return child.getName ( );
					}
				}
			}
		}
		
		return defaultValue;
	}
	
	public CacheObject getCache( ) {
		CacheObject o = manager.getCache ( KEY );
		return o;
	}
	
	public void setCacheFromService( long expireSeconds ) {
		CacheObject o = getCache ( );
		if ( ! ObjectUtil.isNull ( o ) )
			return;
		
		DictionaryVo vo = service.findAll ( );
		List<DictionaryVo> voList = vo.getListData ( );
		Map<String, Object> map = new HashMap<> ( );
		o = new CacheObject ( );
		o.setMap ( map );
		for ( DictionaryVo item : voList ) {
			map.put ( item.getCode ( ) , item );
		}
		
		if ( expireSeconds == 0 )
			expireSeconds = EXPIRE_SECONDS;
		
		manager.setCache ( KEY , o , expireSeconds );
	}
	
}

