package com.dianda.shserc.service;

import com.dianda.shserc.dto.EditRoleDto;
import com.dianda.shserc.entity.ResRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author huachao
 * @since 2020-08-12
 */
public interface IResRoleService extends IService<ResRole> {
	ResRole edit(EditRoleDto role);
	boolean delete(String id);
}
