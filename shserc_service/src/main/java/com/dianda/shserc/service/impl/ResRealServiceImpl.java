package com.dianda.shserc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.shserc.bean.RealSelectParams;
import com.dianda.shserc.dto.EditRealDto;
import com.dianda.shserc.entity.ResReal;
import com.dianda.shserc.mapper.ResRealMapper;
import com.dianda.shserc.service.IResRealService;
import com.dianda.shserc.vo.ResRealVoList;

public class ResRealServiceImpl extends ServiceImpl<ResRealMapper, ResReal> implements IResRealService {

	@Override
	public boolean edit(EditRealDto model) {
		return false;
	}

	@Override
	public ResRealVoList find(RealSelectParams params) {
		return null;
	}

}
