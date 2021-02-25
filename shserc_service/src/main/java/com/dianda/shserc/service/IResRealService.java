package com.dianda.shserc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.shserc.bean.RealSelectParams;
import com.dianda.shserc.dto.EditRealDto;
import com.dianda.shserc.entity.ResReal;
import com.dianda.shserc.vo.ResRealVoList;

public interface IResRealService extends IService<ResReal> {
	ResRealVoList find(RealSelectParams params);
	boolean edit(EditRealDto model);
}
