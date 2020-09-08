package com.dianda.auth.service;

import com.dianda.auth.dto.EditCompanyDto;
import com.dianda.auth.entity.ResCompany;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.auth.vo.ResCompanyVo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author huachao
 * @since 2020-08-17
 */
public interface IResCompanyService extends IService<ResCompany> {
	
	ResCompanyVo find( );
	
	ResCompanyVo findByParentId( long parentId );
	
	ResCompany edit( EditCompanyDto company );
	
	ResCompany delete( long id );
	
}
