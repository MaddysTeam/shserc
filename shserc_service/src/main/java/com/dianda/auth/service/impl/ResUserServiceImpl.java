package com.dianda.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dianda.auth.bean.UserSelectParams;
import com.dianda.auth.common.Constant;
import com.dianda.auth.dto.EditUserDto;
import com.dianda.auth.dto.mappers.IEditUserMapper;
import com.dianda.auth.entity.ResUser;
import com.dianda.auth.exceptions.GlobalException;
import com.dianda.auth.mapper.ResUserMapper;
import com.dianda.auth.service.IResUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.auth.util.basic.ObjectUtil;
import com.dianda.auth.util.basic.StringUtil;
import com.dianda.auth.vo.ResUserVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huachao
 * @title ResUserServiceImpl
 * @Description 描述
 * @Date 2020/8/15 11:18
 * @Copyright 2019-2020
 */

@Service
public class ResUserServiceImpl extends ServiceImpl<ResUserMapper, ResUser> implements IResUserService {

	@Resource
	ResUserMapper resUserMapper;

	@Override
	public ResUserVo find(UserSelectParams params) {
		if (params == null) return new ResUserVo();

		long companyId = params.getCompanyId();
		String phrase = params.getPhrase();
		int current = params.getCurrent();
		int size = params.getSize();

		IPage<ResUser> page = new Page<>(current, size);
		QueryWrapper<ResUser> wrapper = new QueryWrapper<>();
		ResUserVo resUserVo = new ResUserVo();

		if (!StringUtil.IsNullOrEmpty(phrase))
			wrapper = wrapper.like("user_name", phrase);

		if (companyId > 0) {
			wrapper.eq( "company_id", companyId);
		}

		IPage<ResUser> pageData = resUserMapper.selectUsers(page, wrapper);
		List<ResUser> users= pageData.getRecords();
		resUserVo.setListData(users);

		if (!ObjectUtil.isNull(users) && users.size() > 0) {
			resUserVo.setTotal(page.getTotal());
			resUserVo.setCurrent(current);
			resUserVo.setSize(size);
		}

		return resUserVo;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public ResUser edit(EditUserDto userDto) {
		if (ObjectUtil.isNull(userDto))
			return null;

		// execute user mapping from dto
		ResUser user = IEditUserMapper.INSTANCE.mapFrom(userDto);
		if (user.isNewOne()) {
			resUserMapper.insert(user);
		} else {
			resUserMapper.updateById(user);
		}

		return user;
	}

	@Override
	public ResUser delete(long id) {
		if (id <= 0)
			return null;

		ResUser user = resUserMapper.selectById(id);
		user.setIsDeleted(Constant.Status.DELETED);

		int result = resUserMapper.updateById(user);
		return user;
	}

}
