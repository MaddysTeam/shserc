package com.dianda.shserc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.shserc.bean.RealSelectParams;
import com.dianda.shserc.common.Constant;
import com.dianda.shserc.dto.EditRealDto;
import com.dianda.shserc.dto.RegistRealDto;
import com.dianda.shserc.entity.ResReal;
import com.dianda.shserc.validators.NotNull;
import com.dianda.shserc.vo.ResRealVo;
import com.dianda.shserc.vo.ResRealVoList;

import javax.validation.Valid;

public interface IResRealService extends IService<ResReal> {

	ResRealVoList find(RealSelectParams params);

	boolean edit( @Valid @NotNull EditRealDto model);

	boolean register(RegistRealDto model);

	ResRealVo findById(long id);

	ResRealVo findByIdCard(String idCard);

}
