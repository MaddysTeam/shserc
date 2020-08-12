package com.dianda.auth.service.impl;

import com.dianda.auth.entity.ResUser;
import com.dianda.auth.mapper.ResUserMapper;
import com.dianda.auth.service.IResUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author huachao
 * @since 2020-08-08
 */
@Service
public class ResUserServiceImpl extends ServiceImpl<ResUserMapper, ResUser> implements IResUserService {

	@Resource
	ResUserMapper resUserMapper;

	@Override
	public List<ResUser> findByPhrase(String phrase, Integer pageIndex, Integer pageSize) {
		return null;
	}

	@Override
	public ResUser add(ResUser user) {
		return null;
	}

	@Override
	public ResUser edit(ResUser user) {
		return null;
	}

	@Override
	public ResUser delete(String id) {
		return null;
	}

}
