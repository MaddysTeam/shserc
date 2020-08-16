package com.dianda.auth.service;

import com.dianda.auth.entity.ResRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.auth.entity.ResUser;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author huachao
 * @since 2020-08-12
 */
public interface IResRoleService extends IService<ResRole> {
	ResRole add( ResRole role);
	ResRole edit(ResRole role);
	boolean delete(String id);
}
