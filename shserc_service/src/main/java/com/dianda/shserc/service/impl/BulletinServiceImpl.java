package com.dianda.shserc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.shserc.bean.BulletinSelectParams;
import com.dianda.shserc.dto.EditBulletinDto;
import com.dianda.shserc.dto.mappers.IEditBulletinMapper;
import com.dianda.shserc.entity.Bulletin;
import com.dianda.shserc.mapper.BulletinMapper;
import com.dianda.shserc.service.IBulletinService;
import com.dianda.shserc.validators.NotNull;
import com.dianda.shserc.vo.BulletinVo;
import com.dianda.shserc.vo.BulletinVoList;
import com.dianda.shserc.vo.mappers.IBulletinVoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class BulletinServiceImpl extends ServiceImpl<BulletinMapper, Bulletin> implements IBulletinService {

	@Resource
	BulletinMapper mapper;

	@Override
	public boolean edit(@Valid @NotNull EditBulletinDto editBulletinDto) {
		Bulletin bulletin = IEditBulletinMapper.INSTANCE.mapFrom(editBulletinDto);
		if (bulletin.isNewOne()) {
			return mapper.insert(bulletin) > 0;
		} else {
			return mapper.updateById(bulletin) >= 0;
		}
	}

	@Override
	public BulletinVoList find(BulletinSelectParams bulletinSelectParams) {
		QueryWrapper<Bulletin> wrapper = new QueryWrapper<>();
		int current = bulletinSelectParams.getCurrent();
		int size = bulletinSelectParams.getSize();
		long typeId = bulletinSelectParams.getTypeId();

		// where phrase

		if (typeId > 0) {
			wrapper.eq("type_id", typeId);
		}

		// get paged list data

		IPage<Bulletin> page = new Page<>( current , size );
		page = mapper.selectBulletins(page, wrapper);
		List<BulletinVo> voListData = new ArrayList<>();
		List<Bulletin> listData = page.getRecords();
		for (Bulletin bulletin : listData) {
			BulletinVo bulletinVo = IBulletinVoMapper.INSTANCE.mapFrom(bulletin);
			voListData.add(bulletinVo);
		}

		// get vo list

		BulletinVoList voList = new BulletinVoList();
		voList.setListData(voListData);
		voList.setTotal(page.getTotal());
		voList.setCurrent(current);
		voList.setSize(size);

		return voList;
	}

	@Override
	public BulletinVo findById(long id) {
		try {
			Bulletin bulletin = mapper.selectById(id);
			return IBulletinVoMapper.INSTANCE.mapFrom(bulletin);
		} catch (Exception e) {
			return null;
		}
	}

}
