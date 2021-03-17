package com.dianda.shserc.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.dianda.shserc.entity.Menu;
import com.dianda.shserc.entity.MenuRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

	List<Menu> selectMenus(@Param(Constants.WRAPPER)Wrapper<Menu> wrapper);

	int addMenuRole(MenuRole menuRole);

	int deleteMenuRoles(MenuRole menuRole);
}
