package com.dianda.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.common.bean.InterfaceSelectParams;
import com.dianda.common.dto.EditInterfaceDto;
import com.dianda.common.entity.Interfaces;
import com.dianda.common.vo.InterfaceVoList;
import com.dianda.common.vo.InterfacesVo;

public interface IInterfacesService extends IService<Interfaces> {

	InterfaceVoList find(InterfaceSelectParams interfaceSelectParams);

	InterfacesVo findById(long id);

	InterfacesVo findByVersion(double version);

	boolean edit(EditInterfaceDto editInterfaceDto);

	boolean bindRole(long interfaceId, long roleId);
}
