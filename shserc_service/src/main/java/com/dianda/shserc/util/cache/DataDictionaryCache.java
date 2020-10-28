package com.dianda.shserc.util.cache;

import com.dianda.shserc.exceptions.GlobalException;
import com.dianda.shserc.service.IDictionaryService;
import com.dianda.shserc.util.basic.ObjectUtil;
import com.dianda.shserc.util.basic.StringUtil;
import com.dianda.shserc.vo.DictionaryVo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * data dictionary cache  数据字典缓存
 * due to dictionary only has tow layers
 *
 * @author huachao
 * @since 2020-08-05
 */
public class DataDictionaryCache {

	private static final String KEY = "dict";

	@Autowired
	ICacheManager manager;

	@Resource
	IDictionaryService service;

	public String translate(String code, String value) {
		//TODO: parent code ->  children -> find name by value
		CacheObject o = manager.getCache(KEY);
		if (ObjectUtil.isNull(o) || ObjectUtil.isNull(o.getMap())) {
			throw new GlobalException("", "");
		}

		Map<String, Object> map = o.getMap();
		DictionaryVo parent;
		DictionaryVo child;
		for (String key : map.keySet()) {
			if (key == code) {
				parent = (DictionaryVo) map.get(key);
				for (String childKey : map.keySet()) {
					child = (DictionaryVo) map.get(childKey);
					if (child.getParentId() == parent.getId() && child.getValue() == value) {
						return child.getName();
					}
				}
			}
		}

		return null;
	}

	public List<String> getValuesByParentCode() {
		return null;
	}

	public void setCacheFromService() {
		DictionaryVo vo = service.findAll();
		List<DictionaryVo> voList = vo.getListData();
		Map<String, Object> map = new HashMap<>();
		CacheObject o = new CacheObject();
		o.setMap(map);
		for (DictionaryVo item : voList) {
			map.put(item.getCode(), item);
		}

		manager.setCache(KEY, o, 0);
	}

}

