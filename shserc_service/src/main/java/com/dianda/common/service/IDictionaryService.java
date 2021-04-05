package com.dianda.common.service;

import com.dianda.common.entity.Dictionary;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.common.vo.DictionaryVo;

/**
 * 字典服务
 *
 * @author huachao
 *@since 2020-10-05
 */
public interface IDictionaryService extends IService<Dictionary> {

	 DictionaryVo findAll();

}
