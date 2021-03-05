package com.dianda.shserc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.shserc.bean.BulletinSelectParams;
import com.dianda.shserc.dto.EditBulletinDto;
import com.dianda.shserc.entity.Bulletin;
import com.dianda.shserc.mapper.BulletinMapper;
import com.dianda.shserc.service.IBulletinService;
import com.dianda.shserc.validators.NotNull;
import com.dianda.shserc.vo.BulletinVo;
import com.dianda.shserc.vo.BulletinVoList;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;

@Service
public class BulletinServiceImpl extends ServiceImpl<BulletinMapper, Bulletin> implements IBulletinService {

	@Resource
	BulletinMapper mapper;

	@Override
	public boolean edit( @Valid @NotNull EditBulletinDto model ) {
		return false;
	}

	@Override
	public BulletinVoList find(BulletinSelectParams params) {
		return null;
	}

	@Override
	public BulletinVo findById(long id) {
		return null;
	}

}