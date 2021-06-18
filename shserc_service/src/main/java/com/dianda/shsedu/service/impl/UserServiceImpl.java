package com.dianda.shsedu.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.common.common.Constant;
import com.dianda.common.exceptions.GlobalException;
import com.dianda.common.util.basic.ObjectUtil;
import com.dianda.common.util.basic.StringUtil;
import com.dianda.shsedu.bean.UserSelectParams;
import com.dianda.shsedu.dto.EditUserDto;
import com.dianda.shsedu.dto.mappers.IEditUserMapper;
import com.dianda.shsedu.entity.ShseduUser;
import com.dianda.shsedu.mapper.ShseduUserMapper;
import com.dianda.shsedu.service.IRoleService;
import com.dianda.shsedu.service.IUserService;
import com.dianda.shsedu.vo.RoleVo;
import com.dianda.shsedu.vo.UserVo;
import com.dianda.shsedu.vo.UserVoList;
import com.dianda.shsedu.vo.mappers.IUserVoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@DS("shsedu")
@Service("ShseduUserService")
public class UserServiceImpl extends ServiceImpl<ShseduUserMapper, ShseduUser> implements IUserService {

	@Resource
	ShseduUserMapper shseduUserMapper;

	@Resource
	IRoleService roleService;

	@Override
	public UserVoList findByPhrase(UserSelectParams params) {
		if (params == null)
			throw new GlobalException(Constant.ErrorCode.PARAM_NULL_POINT_REFERENCE, Constant.Error.OBJECT_IS_REQUIRED);

		String phrase = params.getSearchPhrase();
		Map<String, String> orderPhrases = params.getOrderPhrases();
		int current = params.getCurrent();
		int size = params.getSize();

		// build search condition

		QueryWrapper<ShseduUser> wrapper = new QueryWrapper<>();
		wrapper.eq("u.is_deleted", 0);

		if (!StringUtil.isNullOrEmpty(phrase))
			wrapper = wrapper.like("user_name", phrase);

		// order phrase

		if (!ObjectUtil.isNull(orderPhrases) && orderPhrases.size() > 0) {
			for (Map.Entry<String, String> entry : orderPhrases.entrySet()) {
				String direction = entry.getValue();
				String orderPhrase = params.translateOrderPhrase(entry.getKey());
				if (direction.equals(Constant.OrderDirection.ASC)) {
					wrapper = wrapper.orderByAsc(orderPhrase);
				} else if (direction.equals(Constant.OrderDirection.DESC)) {
					wrapper = wrapper.orderByDesc(orderPhrase);
				}
			}
		} else {
			wrapper = wrapper.orderByDesc("id");
		}

		// get paged list data

		IPage<ShseduUser> page = new Page<>(current, size);
		page = shseduUserMapper.selectUser(page, wrapper);
		List<ShseduUser> listData = page.getRecords();
		List<UserVo> userVoListData = new ArrayList<>();

		for (ShseduUser user : listData) {
			userVoListData.add(IUserVoMapper.INSTANCE.mapFrom(user));
		}

		// return vo list data

		UserVoList userVoList = new UserVoList();
		userVoList.setListData(userVoListData);
		userVoList.setTotal(page.getTotal());
		userVoList.setCurrent(current);
		userVoList.setSize(size);

		return userVoList;
	}

	@Override
	public UserVo findById(long id) {
		ShseduUser user = shseduUserMapper.selectById(id);
		UserVo vo = new UserVo();
		if (!ObjectUtil.isNull(user))
			vo = IUserVoMapper.INSTANCE.mapFrom(user);
		return vo;
	}

	@Override
	public boolean edit(EditUserDto editUserDto) {
		// execute user mapping from dto
		ShseduUser user = IEditUserMapper.INSTANCE.mapFrom(editUserDto);
		if (user.isNewOne()) {
			boolean userIsExists = shseduUserMapper.selectCount(new QueryWrapper<ShseduUser>()
					.eq("user_name", user.getUserName())
					.or()
					.eq("email", user.getEmail())
					.or()
					.eq("mobile", user.getMobile())
			) > 0;
			if (userIsExists) {
				throw new GlobalException(Constant.ErrorCode.LOGIC_RESULT_INVALID, Constant.Error.USER_IS_EXISTS);
			}

			String password = StringUtil.isNullOrEmpty(user.getPassword()) ? Constant.ThisApp.DEFAULT_PASSWORD : user.getPassword();
			user.setPassword(com.dianda.common.util.basic.EncoderUtil.SHA(password));
			user.setAddUser(editUserDto.getOperatorId());
			user.setAddTime(editUserDto.getOperateDate());
			return shseduUserMapper.insert(user) > 0;
		} else {
			user.setUpdateUser(editUserDto.getOperatorId());
			user.setUpdateTime(editUserDto.getOperateDate());
			return shseduUserMapper.updateById(user) >= 0;
		}
	}


	@Override
	public boolean hasPermissions(long id, long permissionId) {
		ShseduUser user = shseduUserMapper.selectById(id);
		if (ObjectUtil.isNull(user)) return false;

		RoleVo roleVo=roleService.findById(user.getRoleId());
		if (ObjectUtil.isNull(roleVo)) return false;

		String pid = StringUtil.toString(permissionId);

		return user.hasPermission(pid) || roleService.hasPermissions(roleVo.getId(),permissionId);
	}

	@Override
	public boolean hasInRole(long id, long roleId) {
		ShseduUser user = shseduUserMapper.selectById(id);
		if (ObjectUtil.isNull(user)) return false;

		return user.hasInRole(roleId);
	}
}

