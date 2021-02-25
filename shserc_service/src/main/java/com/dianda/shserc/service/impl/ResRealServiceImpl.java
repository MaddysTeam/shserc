package com.dianda.shserc.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.shserc.bean.RealSelectParams;
import com.dianda.shserc.common.Constant;
import com.dianda.shserc.dto.EditRealDto;
import com.dianda.shserc.dto.mappers.IEditCompanyMapper;
import com.dianda.shserc.dto.mappers.IEditRealMapper;
import com.dianda.shserc.entity.ResReal;
import com.dianda.shserc.exceptions.GlobalException;
import com.dianda.shserc.mapper.ResRealMapper;
import com.dianda.shserc.service.IResRealService;
import com.dianda.shserc.util.basic.ObjectUtil;
import com.dianda.shserc.vo.ResRealVoList;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

public class ResRealServiceImpl extends ServiceImpl<ResRealMapper, ResReal> implements IResRealService {

	@Autowired
	ResRealMapper mapper;


	@Override
	public boolean edit(EditRealDto model) {
		if (ObjectUtil.isNull(model)) {
			throw new GlobalException(Constant.ErrorCode.PARAM_NULL_POINT_REFERENCE, Constant.Error.OBJECT_IS_REQUIRED);
		}

		ResReal o = IEditRealMapper.INSTANCE.mapFrom(model);
		if (o.isNewOne()) {
			mapper.insert(o);
		} else {
			mapper.updateById(o);
		}

		return false;
	}

	@Override
	public ResRealVoList find(RealSelectParams params) {
		QueryWrapper<ResReal> queryWrapper = new QueryWrapper<>();
		int current = params.getCurrent();
		int size = params.getSize();
		IPage<ResReal> page = new Page<>(current, size);
		page = mapper.selectReal(page, queryWrapper);
		List<ResReal> data = page.getRecords();

		ResRealVoList voList = new ResRealVoList ( );
		voList.setListData(data);
		voList.setCurrent ( current );
		voList.setSize ( size );
		voList.setTotal ( page.getTotal ( ) );

		return voList;
	}

}
