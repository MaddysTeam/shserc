package com.dianda.shserc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.shserc.bean.BulletinSelectParams;
import com.dianda.shserc.dto.EditBulletinDto;
import com.dianda.shserc.dto.EditStateDto;
import com.dianda.shserc.entity.Bulletin;
import com.dianda.common.validators.NotNull;
import com.dianda.shserc.vo.BulletinVo;
import com.dianda.shserc.vo.BulletinVoList;

import javax.validation.Valid;

public interface IBulletinService extends IService<Bulletin> {

	boolean edit( @Valid @NotNull EditBulletinDto editBulletinDto );
	
	boolean top( @Valid @NotNull EditStateDto editStateDto );

	BulletinVoList find(BulletinSelectParams params);

	BulletinVo findById(long id);
}
